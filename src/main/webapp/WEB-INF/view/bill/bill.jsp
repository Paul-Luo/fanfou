<%--
  Created by IntelliJ IDEA.
  User: chaoluo
  Date: 2015/8/23
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/view/common/taglib.jsp" %>
<security:authorize access="hasRole('App_Admin')">
    <div  id="toolbar">
        <button id="paid" type="submit" class="btn btn-success">Paid</button>
    </div>
</security:authorize>

<table id="table" data-toolbar="#toolbar"></table>

<script type="text/javascript">
    $(function() {

        var canceledCount = 0;
        var confirmedCount = 0;
        var unconfirmedCount = 0;
        var paidCount = 0;
        var orderDtos = [];
        $local.ajax({
            url: 'order/bill',
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
                var item = getItemFromOrderDto(orderDto);
                data.push(item);
            }
        }

        function calculateStateCount(orderState, count) {
            if (orderState == 'Unconfirmed' ) {
                unconfirmedCount += count;
                return;
            }

            if (orderState == 'Canceled' ) {
                canceledCount += count;
                return;
            }

            if (orderState == 'Confirmed' ) {
                confirmedCount += count;
                return;
            }

            if (orderState == 'Paid' ) {
                paidCount += count;
                return;
            }
        }

        function getItemFromOrderDto(orderDto) {
            var item = {};
            item.orderState = orderDto.orderState;
            item.total = 0;
            item.productName = orderDto.goodsName;
            item.userName = orderDto.userName;
            item.count = 0;
            item.price = 0;
            item.orderId = orderDto.orderId;
            var time = new Date(orderDto.createdDatetime);
            item.date = time.getFullYear() + '/' + (time.getMonth() + 1) + '/' + time.getDate();
            var orderDetailList = orderDto.orderDetailList;
            if (!$.isEmptyObject(orderDetailList)) {
                for (var k in orderDetailList) {
                    orderDetail = orderDetailList[k];
                    item.total += orderDetail.price * orderDetail.count;
                    item.count = orderDetail.count;
                    item.price = orderDetail.price;
                }
            }
            calculateStateCount(item.orderState, item.count);
            return item;
        }

        function operateFormatter(value, row, index) {
            if (row.orderState == 'Canceled') {
                return '';
            } else {
                return [
                    '<a class="cancel" href="javascript:void(0)" title="Cancel">',
                    '<i class="glyphicon glyphicon-remove"></i>',
                    '</a>'
                ].join('');
            }
        };

        window.operateEvents = {
            'click .cancel': function (e, value, row, index) {
                var orderId = row.orderId;
                $local.ajax({
                    url: 'order/' + orderId,
                    method: 'DELETE',
                    success: function() {
                        row.operate = '';
                        row.orderState = 'Canceled'
                        $('#table').bootstrapTable('updateRow', {index: index, row: row});
                    }
                })
            }
        };

        function getSelections(attrName) {
            var selects = $('#table').bootstrapTable('getSelections');
            var data = [];
            if (!$.isEmptyObject(selects)) {
                for (var index in selects) {
                    var row = selects[index];
                    data.push(row[attrName]);
                }
            }
            return data;
        };

        $('#paid').click(function() {
            bootbox.confirm('Are You Sure You Want To Pay ?', function(result) {
                if (result) {
                    var data = getSelections('orderId');
                    $local.ajaxRest({
                        url: 'order/pay',
                        method: 'PUT',
                        data: JSON.stringify(data),
                        success: function(data) {
                            refresh();
                        }
                    })
                }
            });
        });


        function stateFormatter(value, row) {
            if (row.orderState == 'Confirmed') {
                return value;
            }
            return {
                disabled: true
            };
        }

        $('#table').bootstrapTable({
            pagination: true,
            pageSize: 20,
            columns: [
                <security:authorize access="hasRole('App_Admin')">
                {
                    field: 'state',
                    checkbox: true,
                    formatter: stateFormatter
                },
                </security:authorize>
                {
                    field: 'userName',
                    title: 'User Name'
                },{
                    field: 'price',
                    title: 'Price'
                },{
                    field: 'count',
                    title: 'Count'
                }, {
                    field: 'total',
                    title: 'Total(RMB)'
                }, {
                    field: 'orderState',
                    title: 'Order State'
                }, {
                    field: 'date',
                    title: 'Date'
                }],
            data: data
        });
        var statisticalData = "Confirmed: " + confirmedCount + " Unconfirmed: " + unconfirmedCount + " Canceled: " + canceledCount;
        var divContent = "<div class='pull-right search'><span class='label label-info'>" + statisticalData + "</span></div>";
        $('.fixed-table-toolbar').append(divContent)
    });
</script>