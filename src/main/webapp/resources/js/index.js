/**
 * author : chaoluo
 * date : 2015/8/22
 * depiction :
 */

$(function() {

    loadContent = function(url) {
        $('#content').load(url, function() {

        });
    }

    loadContent("403")
});