/**
 * author : chaoluo
 * date : 2015/6/7
 * depiction :
 */
$(function() {
    // custom namespace
    $local = {};

    // utils
    $.extend({
        // empty check
        isEmpty: function (obj) {
            if (obj == undefined || $.isEmptyObject(obj)) {
                return true;
            } else {
                return false;
            }
        },
        isNotEmpty: function (obj) {
            return !this.isEmpty(obj);
        }
    });

    $local.infoAlert = function(msg){
        bootbox.alert(msg)
    };

    $local.ajax = function (option, successFn, errorFn) {
        option = option || {};

        var optionSuccessFn = option.success;
        var optionErrorFn = option.error;
        if ($.isFunction(optionSuccessFn) && $.isEmpty(successFn)) {
            successFn = optionSuccessFn;
        }

        if ($.isFunction(optionErrorFn) && $.isEmpty(errorFn)) {
            errorFn = optionErrorFn;
        }

        var defaultOption = {
            success: function (data, textStatus) {
                if ($.isFunction(successFn)) {
                    successFn(data, textStatus);
                }
            },
            error: function (xhr, status, error) {
                var response = null;
                try {
                    response = eval("(" + xhr.responseText  + ")");
                } catch (e) {
                    // ignore
                    console.log(e)
                }
                var msg;
                if ($.isNotEmpty(response) && $.isNotEmpty(response.msg)) {
                    var msg = response.msg;
                } else {
                    msg = 'unknown exception';
                }
                $local.infoAlert(msg);
                if ($.isFunction(errorFn)) {
                    errorFn(response, xhr);
                }
            }
        }
        $.extend(option, defaultOption);
        return $.ajax(option);
    };

    $local.ajaxRest = function(option, successFn, errorFn) {
        option = option || {};
        option.contentType = "application/json;charset=utf-8";
        if (!option.hasOwnProperty('method')) {
            option.method = "POST";
        }
        return $local.ajax(option, successFn, errorFn);
    }
})