/**
 * author : chaoluo
 * date : 2015/8/22
 * depiction :
 */

$(function() {

    loadContent = function(url) {
        $('#content').load(url, function() {

        });
    };

    $('#menu-order').click();
    $('#menu-order').closest('li').addClass('active');
});