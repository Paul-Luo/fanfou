/**
 * author : chaoluo
 * date : 2015/8/22
 * depiction :
 */

$(function() {

    loadContent = function (url) {
        $.AdminLTE.controlSidebar.close($('.control-sidebar'), true);
        REFRESH_URL_CONSTANT = url;
        $('#content').bload(function (bload) {
            $('#content').load(url, function () {
                bload.hide();
            });
        });
    };

    refresh = function() {
        var url = REFRESH_URL_CONSTANT;
        loadContent(url);
    };

    $("[id^='menu-']").click(function() {
        $(this).closest('li').siblings().removeClass('active');
        var url = $(this).attr('url');
        loadContent(url);
        $(this).closest('li').addClass('active')
    });

    $('#menu-order').click();

});