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
    if (!xmlhttp && typeof XMLHttpRequest != 'undefined') {
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
    var promise = new ProtoPromise(function (resolve, reject) {

        xmlhttp.open(method.toUpperCase(), url, true);

        xmlhttp.onload = function () {
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
        xmlhttp.onerror = function () {
            reject(Error("Network Error"));
        };

        xmlhttp.send(null);

    });
    this.success = function (fn) {
        promise.success(fn);
        return $this;
    };
    this.error = function (fn) {
        promise.error(fn);
        return $this;
    };
    this.done = function (fn) {
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

    setTimeout(function () {
        fn(commitSuccess, commitError);
    }, 0);

    this.success = function (onSuccessListener) {
        if (finished && !error) {
            onSuccessListener(data);
            return $this;
        }
        onSuccess = onSuccessListener;
        return $this;
    };

    this.error = function (onErrorListener) {
        if (finished && error) {
            onErrorListener(data);
            return $this;
        }
        onError = onErrorListener;
        return $this;
    };

    this.then = function (onFinishListener) {
        if (finished) {
            onFinishListener(data);
            return $this;
        }
        onFinish = onFinishListener;
        return $this;
    };

    return $this;
}
/**
 *
 */
var $ = function () {

    function hasClass(ele,cls) {
        return ele.className.match(new RegExp('(\\s|^)'+cls+'(\\s|$)'));
    }

    function addClass(o, c){
        var re = new RegExp("(^|\\s)" + c + "(\\s|$)", "g");
        if (re.test(o.className)) return
        o.className = (o.className + " " + c).replace(/\s+/g, " ").replace(/(^ | $)/g, "")
    }


    function removeClass(ele,cls) {
        if (hasClass(ele,cls)) {
            var reg = new RegExp('(\\s|^)'+cls+'(\\s|$)');
            ele.className=ele.className.replace(reg,' ');
        }
    }

    function r(f){
        document.readyState !== "complete" ? setTimeout(r, 9, f):f()
    }

    function isNode(o) {
        return (
            typeof Node === "object" ? o instanceof Node :
                o && typeof o === "object" && typeof o.nodeType === "number" && typeof o.nodeName === "string"
            );
    }

    function isElement(o) {
        return (
            typeof HTMLElement === "object" ? o instanceof HTMLElement : //DOM2
                o && typeof o === "object" && o !== null && o.nodeType === 1 && typeof o.nodeName === "string"
            );
    }

    function getElementsByAttribute(attr, value, root) {
        root = root || document.body;
        var array = [];
        if (root.hasAttribute(attr) && root.getAttribute(attr) == value) {
            array.push(root);
        }
        var children = root.children;
        for (var i = children.length; i--;) {
            var elements = getElementsByAttribute(attr, value, children[i]);
            for (var j = 0; j < elements.length; j++) {
                array.push(elements[j]);
            }
        }
        return array;
    }

    return function (selector, parent) {

        var $this = {};

        $this.elements = [];

        $this.isProtoQuery = true;

        var elements = $this.elements;

        var value = selector ? selector.value : null;

        if (!parent) {
            parent = document;
        }

        if (selector && (isNode(selector) || isElement(selector))) {
            elements.push(selector);
        } else {
            if (selector) {
                switch (selector.type) {
                    case "id":
                        elements.push(parent.getElementById(value));
                        break;
                    case "name":
                        var array = parent.getElementsByName(value);
                        for (var i = 0; i < array.length; i++) {
                            elements.push(array[i]);
                        }
                        break;
                    case "class":
                        var array = parent.getElementsByClassName(value);
                        for (var i = 0; i < array.length; i++) {
                            elements.push(array[i]);
                        }
                        break;
                    case "attr":
                        var array = getElementsByAttribute(value.attr, value.value, parent);
                        for (var i = 0; i < array.length; i++) {
                            elements.push(array[i]);
                        }
                        break;
                    case "tag":
                        var array = parent.getElementsByTagName(value);
                        for (var i = 0; i < array.length; i++) {
                            elements.push(array[i]);
                        }
                        break;
                }
            }
        }


        $this.find = function (selector) {
            var found = $();
            $this.each(function(el) {
                found.join($(selector, el));
            });
            return found;
        };

        $this.addElement = function(el) {
            elements.push(el);
        };

        $this.join = function(pQ) {
            pQ.each(function(e) {
                elements.push(e);
            });
        };

        $this.each = function (consumer) {
            for (var i = 0; i < elements.length; i++) {
                consumer(elements[i]);
            }
        };

        $this.click = function (onClick) {
            for (var i = 0; i < elements.length; i++) {
                elements[i].onclick = onClick;
            }
        };

        $this.val = function (value) {
            if (!value && elements[0]) {
                return elements[0].value;
            }
            if (value) {
                $this.each(function (e) {
                    e.value = value;
                });
            }
            return null;
        };

        $this.ready = function(fn) {
            r(fn);
        };

        $this.empty = function() {
            $this.each(function(element) {
                while (element.firstChild) {
                    element.removeChild(element.firstChild);
                }
            });
        };

        $this.removeClass = function(className) {
            $this.each(function(element) {
                removeClass(element, className);
            });
        };

        $this.addClass = function(className) {
            $this.each(function(element) {
                element.className += " " + className;
            });
        };

        $this.clone = function(deep) {
            if (deep == undefined) {
                deep =  true;
            }
            if (elements[0]) {
               return $(elements[0].cloneNode(deep));
            }
            return $();
        };

        $this.removeAttr = function(attr) {
            $this.each(function(e) {
                e[attr] = "";
                e.attributes[attr] = "";
                e.setAttribute(attr, "");
            });
        };

        $this.attr = function(attr, value) {
            if (value) {
              $this.each(function(e) {
                  e[attr] = value;
                  e.attributes[attr] = value;
                  e.setAttribute(attr, value);
              });
            } else {
                return elements[0] ? elements[0][attr] : null;
            }
        };

        $this.text = function(text) {
            $this.each(function(el) {
                el.innerHTML = text;
            })
        };

        $this.hasClass = function(className) {
            return elements[0] ? hasClass(elements[0], className) : false;
        };

        $this.append = function(pQ) {
            $this.each(function(el) {
                pQ.each(function(elToAppend) {
                    el.appendChild(elToAppend);
                });
            });
        };

        $this.appendAsFirst = function (pQ) {
            $this.each(function (el) {
                pQ.each(function (elToAppend) {
                    if (el.childNodes.length > 0) {
                        el.insertBefore(elToAppend, el.childNodes[0]);
                    } else {
                        el.append(elToAppend);
                    }
                });
            });
        };

        $this.removeSelf = function () {
            $this.each(function (e) {
                e.parentNode.removeChild(e);
            });
        };

        return $this;

    }
}();