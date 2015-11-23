<%--
  Created by IntelliJ IDEA.
  User: chaoluo
  Date: 2015/8/23
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<div class="fc-view-container">
    <div id='calendar'></div>
</div>

<script type="text/javascript">
    $(function() {
        var orderDtos = [];
        $local.ajax({
            url: 'order/mine',
            method: 'GET',
            async : false,
            success: function(data) {
                orderDtos = data;
            }
        });


        var data = [];
        if (!$.isEmptyObject(orderDtos)) {
            for (var index in orderDtos) {
                var orderDto = orderDtos[index];
                var item = getEventObjectFromOrderDto(orderDto);
                data.push(item);
            }
        }

        function getEventObjectFromOrderDto(orderDto) {
            var item = {};
            item.orderState = orderDto.orderState;
            item.total = 0;
            item.productName = orderDto.goodsName;
            item.count = 0;
            item.price = 0;
            item.orderId = orderDto.orderId;
            item.start = new Date(orderDto.createdDatetime);
            var orderDetailList = orderDto.orderDetailList;
            if (!$.isEmptyObject(orderDetailList)) {
                for (var k in orderDetailList) {
                    orderDetail = orderDetailList[k];
                    item.total += orderDetail.price * orderDetail.count;
                    item.count = orderDetail.count;
                    item.price = orderDetail.price;
                }
            }
            item.title = "count: " + item.count + " total: " + item.total + " RMB";
            item.allDay = true;
            return item;
        };

        addOrder = function(thiz) {
            var count = 1;
            var jqTd = $(thiz).closest("td");
            var createdDatetime = jqTd.attr("data-date");
            $('#content').bload(function (bload) {
                $local.ajax({
                    url: 'order/count/' + 1,
                    method: 'POST',
                    data: 'createdDatetime=' + createdDatetime,
                    success: function(data) {
                        var item = getEventObjectFromOrderDto(data);
                        var events = [];
                        events.push(item);
                        $('#calendar').fullCalendar('addEventSource', events);
                        bload.hide();
                    },
                    error: function() {
                        bload.hide();
                    }
                })
            });
        };

        function attachPlus() {
            $('.fc-day-number').each(function() {
                if ($.isEmptyObject($(this).attr("plus"))) {
                    $(this).append("    <a href='#'><i class='fa fa-plus-circle' style='color:#3498DB;' onclick='addOrder(this)'></i></a>");
                    $(this).attr('plus', true)
                }
            });
        }

        $('#calendar').fullCalendar({
            firstDay: 1,
            aspectRatio: 2.65,
            events: data,
            viewDisplay: function (view) {
                // Clean up any previously added "dropdowns"
                $('.dropdown').remove();

                // Only add "drowdowns" if the current view is the weekly view
                if (view.name === 'agendaWeek') {

                    // Add the "dropdowns" to the day headers
                    $('.fc-widget-header.fc-sun, .fc-widget-header.fc-mon, .fc-widget-header.fc-tue, .fc-widget-header.fc-wed, .fc-widget-header.fc-thu, .fc-widget-header.fc-fri, .fc-widget-header.fc-sat')
                            .prepend("<div class='dropdown' style='display: inline'>[||] </div>");
                }
            },
            eventRender: function (event, eventElement) {
                var orderState = event.orderState;
                if (orderState == "Canceled") {
                    $(eventElement).css('background-color', '#BDC3C7');
                } else if (orderState == "Unconfirmed") {
                    $(eventElement).css('background-color', '#2980B9');
//                    $(eventElement).effect("highlight", {}, 3000);
                } else if (orderState == "Confirmed") {
                    $(eventElement).css('background-color', '#2ECC71');
                }
            },
            eventOrder: "-orderState",
            eventAfterAllRender: function (view ) {
                attachPlus();
            },
            eventClick: function(calEvent, jsEvent, view) {
                var orderState = calEvent.orderState;
                if (orderState == "Canceled" || orderState == "Confirmed") {
                    return;
                }
                var thiz = this;
                bootbox.confirm('Are You Sure You Want To Cancel ?', function(result) {
                    if (result) {
                        var orderId = calEvent.orderId;
                        $local.ajax({
                            url: 'order/' + orderId,
                            method: 'DELETE',
                            success: function() {
                                calEvent.orderState = 'Canceled';
                                $(thiz).css('background-color', '#BDC3C7');
//                                $('#table').bootstrapTable('updateRow', {index: index, row: row});
                            }
                        })
                    }
                });
            }
        });

    });
</script>