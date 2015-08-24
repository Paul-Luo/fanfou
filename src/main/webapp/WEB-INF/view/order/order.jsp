<%--
  Created by IntelliJ IDEA.
  User: chaoluo
  Date: 2015/8/23
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<div id="toolbar">
    <div class="form-inline" role="form">
        <div class="form-group">
            <span>Count: </span>
            <input id='count' name="count" class="form-control w70" type="number" value="0">
        </div>
        <button id="book" type="submit" class="btn btn-default">book</button>
    </div>
</div>

<table id="table" data-toolbar="#toolbar"></table>

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
                var item = getItemFromOrderDto(orderDto);
                data.push(item);
            }
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
            item.date = time.getFullYear() + '/' + time.getMonth() + '/' + time.getDate();
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
            $('#content').bload(function (bload) {
                $local.ajax({
                    url: 'order/count/' + count,
                    method: 'POST',
                    success: function(data) {
                        var item = getItemFromOrderDto(data);
                        $('#table').bootstrapTable('insertRow', {index: 0, row: item})
                        bload.hide();
                    },
                    error: function() {
                        bload.hide();
                    }
                })
            });

        });

        $('#table').bootstrapTable({
            columns: [{
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
            }, {
                field: 'operate',
                title: 'Operate',
                align: 'center',
                events: operateEvents,
                formatter: operateFormatter
            }],
            data: data
        });
    });
</script>