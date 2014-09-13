/**
 * Created by Semyon on 13.09.2014.
 */

function Modal(id) {

    var $this = this;
    var modal = null;

    var isOpen = false;

    var backScreen = $(document.createElement("div"));
    var body = $({type: "tag", value: "body"});
    backScreen.addClass("modal-backscreen");
    backScreen.addClass("hidden");
    body.appendAsFirst(backScreen);

    modal = $({type: "id", value: id});
    var closeButton = modal.find({type: "class", value: "close"});

    $this.open = function () {
        backScreen.removeClass("hidden")
        body.addClass("modal-active");
        modal.addClass("active");
        setTimeout(function () {
            modal.addClass("in");
        }, 0);
        isOpen = true;
    };

    $this.close = function () {
        backScreen.addClass("hidden");
        modal.removeClass("active");
        body.removeClass("modal-active");
        modal.removeClass("in");
        isOpen = false;
    };

    modal.click(function (e) {
        if (e.target !== e.currentTarget) {
            return;
        }
        if (isOpen) {
            $this.close();
        }
    });

    closeButton.click(function (e) {
        if (isOpen) {
            $this.close();
        }
    });

    return $this;
}
