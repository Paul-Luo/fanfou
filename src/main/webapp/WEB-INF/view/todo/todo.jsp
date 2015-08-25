<%--
  Created by IntelliJ IDEA.
  User: chaoluo
  Date: 2015/8/23
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<div class="pull-right" id="toolbar">
        <button id="confirmed" type="submit" class="btn btn-success">Confirmed</button>
        <button id="canceled" type="submit" class="btn btn-default">Canceled</button>
</div>

<table id="table" data-toolbar="#toolbar"></table>

<script type="text/javascript">
    $(function() {
        var orderDtos = [];
        $local.ajax({
            url: 'order/today',
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

        $('#confirmed').click(function() {
            bootbox.confirm('Are You Sure You Want To Confirm ?', function(result) {
                if (result) {
                    var data = getSelections('orderId');
                    $local.ajaxRest({
                        url: 'order',
                        method: 'PUT',
                        data: JSON.stringify(data),
                        success: function(data) {
                            refresh();
                        }
                    })
                }
            });
        });

        $('#canceled').click(function() {
            bootbox.confirm('Are You Sure You Want To Cancel ?', function(result) {
                if (result) {
                    var data = getSelections('orderId');
                    $local.ajaxRest({
                        url: 'order',
                        method: 'DELETE',
                        data: JSON.stringify(data),
                        success: function(data) {
                            refresh();
                        }
                    });
                }
            });
        });

        function stateFormatter(value, row) {
            if (row.orderState == 'Unconfirmed') {
                return value;
            }
            return {
                disabled: true
            };
        }

        $('#table').bootstrapTable({
            pagination: true,
            pageSize: 20,
            columns: [{
                field: 'state',
                checkbox: true,
                formatter: stateFormatter
            },{
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
    });
</script>