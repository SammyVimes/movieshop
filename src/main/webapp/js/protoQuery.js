/**
 * Created by semyon on 08.09.14.
 */

function AjaxRequest(opts) {
    var $this = this;
    var xmlhttp;
    try {
        xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
        try {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (E) {
            xmlhttp = false;
        }
    }
    if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
        xmlhttp = new XMLHttpRequest();
    }

    var url = opts.url;
    var contentType = opts.contentType;
    var data = opts.data;
    var dataType = opts.dataType;
    var method = opts.method;
    if (!method) {
        method = "GET";
    }

    if (contentType) {
        xmlhttp.setRequestHeader('Content-Type', contentType)
    }
    if (data) {
        xmlhttp.setRequestHeader("Content-length", data.length);
    }

    //используем НЕ нативное обещание
    //TODO: добавить логи в консоль
    var promise = new ProtoPromise(function(resolve, reject) {

        xmlhttp.open(method.toUpperCase(), url, true);

        xmlhttp.onload = function() {
            // Этот кусок вызовется даже при 404
            if (xmlhttp.status == 200) {
                var response = xmlhttp.response;
                if (dataType && dataType == "json") {
                    try {
                        response = JSON.parse(response);
                    } catch (e) {
                        reject(e);
                    }
                }

                resolve(response);
            }
            else {
                reject(Error(xmlhttp.statusText));
            }
        };

        // отлавливаем ошибки сети
        xmlhttp.onerror = function() {
            reject(Error("Network Error"));
        };

        xmlhttp.send(null);

    });
    this.success = function(fn) {
        promise.success(fn);
        return $this;
    };
    this.error = function(fn) {
        promise.error(fn);
        return $this;
    };
    this.done = function(fn) {
        promise.then(fn);
        return $this;
    };
    return $this;
}


//wow, many promise, such future 0_-
function ProtoPromise(fn) {

    var $this = this;

    var data = null;

    var finished = false;

    var error = false;

    var onFinish = null;

    var onSuccess = null;

    var onError = null;


    function commitFinish(_data) {
        data = _data;
        if (onFinish) {
            onFinish(_data);
        }
    }

    function commitSuccess(data) {
        finished = true;
        if (onSuccess) {
            onSuccess(data);
        }
        commitFinish(data);
    }

    function commitError(_error) {
        finished = true;
        error = true;
        if (onError) {
            onError(_error);
        }
        commitFinish(_error);
    }

    setTimeout(function() {
        fn(commitSuccess, commitError);
    }, 0);

    this.success = function(onSuccessListener) {
        if (finished && !error) {
            onSuccessListener(data);
            return $this;
        }
        onSuccess = onSuccessListener;
        return $this;
    };

    this.error = function(onErrorListener) {
        if (finished && error) {
            onErrorListener(data);
            return $this;
        }
        onError = onErrorListener;
        return $this;
    };

    this.then = function(onFinishListener) {
        if (finished) {
            onFinishListener(data);
            return $this;
        }
        onFinish = onFinishListener;
        return $this;
    };

    return $this;
}