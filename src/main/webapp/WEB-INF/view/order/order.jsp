<%--
  Created by IntelliJ IDEA.
  User: chaoluo
  Date: 2015/8/23
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<div id="toolbar">
    <div class="form-inline" role="form">
        <span>Date: </span>
        <input type="text" id="date-picker" class="form-control">
        <div class="form-group">
            <span>Count: </span>
            <input id='count' name="count" min="1" class="form-control w70" type="number" value="1">
        </div>
        <button id="book" type="submit" class="btn btn-default">book</button>
    </div>
</div>
<div class="fc-view-container">
    <div id='calendar'></div>
</div>


<%--<table id="table" data-toolbar="#toolbar"></table>--%>

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

       initDatePicker = function() {
           var now = ${now};
           var today = new Date(now);
           var year = today.getFullYear();
           var month = today.getMonth() + 1;
           var day = today.getDate();
           var dateValue = year + '-' + (month < 10 ? '0' + month : month) + '-' + day;
           $('#date-picker').val(dateValue);
           $('#date-picker').datepicker({
               format: "yyyy-mm-dd",
               autoclose: true,
               todayBtn: "linked",
               todayHighlight: true,
               startDate: today,
               weekStart: 1
           });
       }

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
            item.title = "count: " + item.count + " total: " + item.total;
            item.allDay = true;
            return item;
        }

        function getItemFromOrderDto(orderDto) {
            var item = {};
            item.orderState = orderDto.orderState;
            item.total = 0;
            item.productName = orderDto.goodsName;
            item.count = 0;
            item.price = 0;
            item.orderId = orderDto.orderId;
            var time = new Date(orderDto.createdDatetime);
            item.date = time.getFullYear() + '-' + (time.getMonth() + 1)  + '-' + time.getDate();
            var orderDetailList = orderDto.orderDetailList;
            if (!$.isEmptyObject(orderDetailList)) {
                for (var k in orderDetailList) {
                    orderDetail = orderDetailList[k];
                    item.total += orderDetail.price * orderDetail.count;
                    item.count = orderDetail.count;
                    item.price = orderDetail.price;
                }
            }
            return item;
        }

        function operateFormatter(value, row, index) {
            if (row.orderState == 'Unconfirmed') {
                return [
                    '<a class="cancel" href="javascript:void(0)" title="Cancel">',
                    '<i class="glyphicon glyphicon-remove"></i>',
                    '</a>'
                ].join('');
            }
            return '';
        };

        window.operateEvents = {
            'click .cancel': function (e, value, row, index) {
                bootbox.confirm('Are You Sure You Want To Cancel ?', function(result) {
                    if (result) {
                        var orderId = row.orderId;
                        $local.ajax({
                            url: 'order/' + orderId,
                            method: 'DELETE',
                            success: function() {
                                row.operate = '';
                                row.orderState = 'Canceled';
                                $('#table').bootstrapTable('updateRow', {index: index, row: row});
                            }
                        })
                    }
                });
            }
        };

        $('#book').click(function() {
            var count = $('#count').val();
            var createdDatetime = $('#date-picker').val();
            $('#content').bload(function (bload) {
                $local.ajax({
                    url: 'order/count/' + count,
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

        });
//
//        $('#table').bootstrapTable({
//            pagination: true,
//            columns: [{
//                field: 'price',
//                title: 'Price'
//            },{
//                field: 'count',
//                title: 'Count'
//            }, {
//                field: 'total',
//                title: 'Total(RMB)'
//            }, {
//                field: 'orderState',
//                title: 'Order State'
//            }, {
//                field: 'date',
//                title: 'Date'
//            }, {
//                field: 'operate',
//                title: 'Operate',
//                align: 'center',
//                events: operateEvents,
//                formatter: operateFormatter
//            }],
//            data: data
//        });

        $('#calendar').fullCalendar({
            aspectRatio: 2.65,
            events: data,
            eventRender: function (event, eventElement) {
                var orderState = event.orderState;
                if (orderState == "Canceled") {
                    $(eventElement).css('background-color', 'gray');
                } else if (orderState == "Unconfirmed") {
                    $(eventElement).css('background-color', 'green');
                } else if (orderState == "Confirmed") {
                    $(eventElement).css('background-color', 'blue');
                }
//                event.eventBackgroundColor  = "red"

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
                                $(thiz).css('background-color', 'gray');
//                                $('#table').bootstrapTable('updateRow', {index: index, row: row});
                            }
                        })
                    }
                });
                // change the border color just for fun
//                $(this).css('border-color', 'red');

            }
        });


        initDatePicker();
    });
</script>