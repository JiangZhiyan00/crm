layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    let cusId = $("input[name='id']").val();
    let tableIns = table.render({
        elem: '#customerOrderList',
        url : ctx+"/customerOrder/list?cusId="+cusId,
        cellMinWidth : 20,
        page : true,
        height : "full-170",
        limits : [10,20,30,40],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "customerOrderListTable",
        cols : [[
            {type: "checkbox", fixed:"left",align:"center"},
            {field: "id", title:'编号',align:"center",hide:true},
            {field: 'orderNo', title: '订单编号',align:"center"},
            {field: 'orderDate', title: '下单日期',align:"center",sort:true},
            {field: 'requiredDate', title: '要求交付日期',align:"center",sort:true},
            {field: 'actualDate', title: '实际交付日期',align:"center",sort:true},
            {field: 'address', title: '收货地址',align:"center"},
            {field: 'state', title: '交付状态',sort:true,minWidth:101,align:"center",templet:function (d) {
                    if(d.state === "逾期未交付"){
                        return "<div style='color: red'><b>逾期未交付</b></div>";
                    }else if (d.state === "逾期已交付"){
                        return "<div style='color: #0937bd'><b>逾期已交付</b></div>";
                    }else if (d.state === "进行中"){
                        return "<div style='color: #ea8e02;'><b>进行中</b></div>";
                    }else if (d.state === "按时交付"){
                        return "<div style='color: #00B83F'><b>按时交付</b></div>";
                    }
                }},
            {field: 'createDate', title: '创建时间',align:"center"},
            {field: 'updateDate', title: '更新时间',align:"center"},
            {title: '操作',fixed:"right",align:"center", minWidth:162,templet:"#customerOrderListBar"}
        ]]
    });

    table.on('toolbar(customerOrders)',function (obj){
        if (obj.event === "add"){
            openAddOrUpdateCustomerOrderDialog();
        }else if (obj.event === "del"){
            delCustomerOrders(table.checkStatus(obj.config.id).data);
        }else if (obj.event === "refresh"){
            table.reload("customerOrderListTable", {
                page: {curr: 1}
            })
        }
    })

    function delCustomerOrders(datas) {
        if (datas.length < 1) {
            layer.msg("<div align='center' style='color: red'><b><h3>请先选择要删除的记录!</h3></b></div>",
                {icon: 2});
            return;
        }
        layer.confirm("<div align='center'><b><h3>确定删除选中的记录?</h3></b></div>",
            {icon: 3, title: "提示"},
        function (index) {
            layer.close(index);
            let ids = "ids=";
            for (let i = 0; i < datas.length; i++) {
                if (i < datas.length - 1) {
                    ids = ids + datas[i].id + "&ids=";
                } else {
                    ids = ids + datas[i].id;
                }
            }

            $.ajax({
                type: "post",
                url: ctx + "/customerOrder/delete",
                data: ids,
                dataType: "json",
                success: function (data) {
                    if (data.code === 200) {
                        layer.msg("<div align='center' style='color: #00B83F'><b><h3>删除成功!</h3></b></div>",
                            {icon: 6})
                        tableIns.reload();
                    } else {
                        layer.msg("<div align='center' style='color: red'><b><h3>" + data.msg + "</h3></b></div>",
                            {icon: 5});
                    }
                }
            })
        })
    }

    table.on('tool(customerOrders)',function (obj) {
        if(obj.event === "details"){
            openOrderDetailDialog(obj.data.id);
        }else if (obj.event === "edit"){
            openAddOrUpdateCustomerOrderDialog(obj.data.id);
        }else if (obj.event === "del"){
            layer.confirm("<div align='center'><b><h3>确定删除此条记录?</h3></b></div>",
                {icon: 3, title: "提示"},
            function (index) {
                layer.close(index);
                $.post(ctx+"/customerOrder/delete",{ids:obj.data.id},function (data) {
                    if(data.code===200){
                        layer.msg("<div align='center' style='color: #00B83F'><b><h3>删除成功!</h3></b></div>",
                            {icon: 6})
                        tableIns.reload();
                    }else{
                        layer.msg("<div align='center' style='color: red '><b><h3>"+data.msg+"</h3></b></div>",
                            {icon: 5});
                    }
                })
            })
        }
    });


    function openAddOrUpdateCustomerOrderDialog(id) {
        let title="<h2>客户管理-新增订单</h2>";
        let url=ctx+"/customerOrder/addOrUpdateCustomerOrderPage?cusId="+cusId;
        if (id){
            title="<h2>客户管理-编辑订单</h2>"
            url=url+"&id="+id;
        }
        layui.layer.open({
            title:title,
            type:2,
            area:["700px","550px"],
            maxmin:true,
            content:url
        })
    }


    function openOrderDetailDialog(id) {
        let title="<h2>订单详情</h2>";
        let url=ctx+"/orderDetails/orderDetailPage?orderId="+id;
        layui.layer.open({
            title:title,
            type:2,
            area:["900px","550px"],
            maxmin:true,
            content:url
        })
    }
});
