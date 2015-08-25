<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div id="allMap"></div>

<script type="text/javascript">

    $('#allMap').css({
        height:  $('.content-wrapper').height()
    });
    var color = ["red", "orange", "black", "green", "blue", "cyan", "violet", "azure"];

    function addClickHandler(content, marker) {
        marker.addEventListener("click", function (e) {
                    openInfo(content, e)
                }
        );
    }

    function openInfo(content, e) {
        var opts = {
            width: 250,     // 信息窗口宽度
            height: 80,     // 信息窗口高度
            title: "信息窗口", // 信息窗口标题
            enableMessage: true//设置允许信息窗发送短息
        };
        var p = e.target;
        var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
        var infoWindow = new BMap.InfoWindow(content, opts);  // 创建信息窗口对象
        map.openInfoWindow(infoWindow, point); //开启信息窗口
    }

    function drawLine(lineNubmer) {
        $.ajax({
            url: "line/" + lineNubmer,
            success: function (data) {
                var lastPoint;
                var lastLine;
                var colorIndex = 0;
                $.each(data, function (n, value) {
                    var point = new BMap.Point(value.LON, value.LAT);
                    var marker = new BMap.Marker(point);
                    map.addOverlay(marker);
                    var content = "线路: " + value.LINE_NUMBER + "<br />" + "站点: " + value.STATION_NAME + "<br />" + "到站时间: " + value.ARRIVAL_TIME
                    addClickHandler(content, marker);
                    if (lastPoint != null) {
                        if (lastLine == value.line_number) {
                            var polyline = new BMap.Polyline([
                                        lastPoint,
                                        point
                                    ],
                                    {strokeColor: color[value.line_number - 1], strokeWeight: 6, strokeOpacity: 0.5}
                            );
                            map.addOverlay(polyline);
                        }
                    }
                    lastPoint = point;
                    lastLine = value.line_number;
                })
            }
        });
    }

    function drawMap() {
        // 百度地图API功能
        map = new BMap.Map("allMap");    // 创建Map实例
        var company = new BMap.Point(117.144, 31.837);
        map.centerAndZoom(company, 15);  // 初始化地图,设置中心点坐标和地图级别
        map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
        map.setCurrentCity("合肥");          // 设置地图显示的城市 此项是必须设置的
        map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
        var marker = new BMap.Marker(company);
        map.addOverlay(marker);
        addClickHandler("公司", marker);

        $.ajax({
            url: "line/all",
            success: function (data) {
                var lastPoint;
                var lastLine;
                var colorIndex = 0;
                $.each(data, function (n, value) {
                    var point = new BMap.Point(value.LON, value.LAT);
                    var marker = new BMap.Marker(point);
                    map.addOverlay(marker);
                    var content = "线路: " + value.LINE_NUMBER + "<br />" + "站点: " + value.STATION_NAME + "<br />" + "到站时间: " + value.ARRIVAL_TIME
                    addClickHandler(content, marker);
                    if (lastPoint != null) {
                        if (lastLine == value.line_number) {
                            var polyline = new BMap.Polyline([
                                        lastPoint,
                                        point
                                    ],
                                    {strokeColor: color[value.line_number - 1], strokeWeight: 6, strokeOpacity: 0.5}
                            );
                            map.addOverlay(polyline);
                        }
                    }
                    lastPoint = point;
                    lastLine = value.line_number;
                });
//                $('#allMap').css({
//                    overflow: 'visible'
//                });
            }
        });
    }

    $(function () {
        drawMap();
    })
</script>
