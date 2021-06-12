layui.use(['table','layer',"form"],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    let orderId = $("input[name='orderId']").val();
    let tableIns = table.render({
        elem: '#orderDetailList',
        url : ctx+'/orderDetails/list?orderId='+$("input[name='orderId']").val(),
        cellMinWidth : 20,
        page : true,
        height : "full-160",
        limits : [10,20,30,40],
        limit : 10,
        toolbar: "#toolbarDemo",
        totalRow: true,//开启合计行
        id : "orderDetailsListTable",
        cols : [[
            {type: "checkbox", fixed:"left"},
            {field: "id", title:'编号',fixed:"true",align:"center",totalRowText: '合计'},
            {field: 'goodsName', title: '商品名称',align:"center",sort:true},
            {field: 'goodsNum', title: '商品数量',align:"center",sort:true},
            {field: 'unit', title: '单位',align:"center"},
            {field: 'price', title: '单价(￥)',align:"center",sort:true},
            {field: 'sum', title: '总价(￥)',align:"center",totalRow: true,sort:true},
            {field: 'createDate', title: '创建时间',align:"center"},
            {field: 'updateDate', title: '更新时间',align:"center"},
            {title: '操作', templet:'#orderDetailsListBar',fixed:"right",align:"center", minWidth:112}
        ]]
    });

    table.on('toolbar(orderDetails)',function (obj){
        if (obj.event === "add"){
            openAddOrUpdateOrderDetailsDialog();
        }else if (obj.event === "del"){
            delOrderDetails(table.checkStatus(obj.config.id).data);
        }else if (obj.event === "refresh"){
            table.reload("orderDetailsListTable", {
                page: {curr: 1}
            })
        }
    })

    function openAddOrUpdateOrderDetailsDialog(id) {
        let title="<h2>订单详情-添加商品订单</h2>";
        let url=ctx+"/orderDetails/addOrUpdateOrderDetailsPage?orderId="+orderId;
        if (id){
            title="<h2>订单详情-编辑商品订单</h2>"
            url=url+"&id="+id;
        }
        layui.layer.open({
            title:title,
            type:2,
            area:["700px","300px"],
            maxmin:true,
            content:url
        })
    }

    function delOrderDetails(datas) {
        if (datas.length < 1) {
            layer.msg("<div align='center' style='color: red'><b><h3>请先选择要删除的商品订单!</h3></b></div>",
                {icon: 2});
            return;
        }
        layer.confirm("<div align='center'><b><h3>确定删除选中的商品订单?</h3></b></div>",
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
                url: ctx + "/orderDetails/delete",
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

    table.on('tool(orderDetails)',function (obj) {
        if (obj.event === "edit"){
            openAddOrUpdateOrderDetailsDialog(obj.data.id);
        }else if (obj.event === "del"){
            layer.confirm("<div align='center'><b><h3>确定删除此商品订单?</h3></b></div>",
                {icon: 3, title: "提示"},
            function (index) {
                layer.close(index);
                $.post(ctx+"/orderDetails/delete",{ids:obj.data.id},function (data) {
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
});
