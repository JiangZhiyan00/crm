layui.use(['table','layer',"form"],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    //客户流失列表展示
    let tableIns = table.render({
        elem: '#customerLossList',
        url : ctx+'/customerLoss/list',
        cellMinWidth : 20,
        page : true,
        height : "full-120",
        limits : [10,20,30,40],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "customerLossListTable",
        cols : [[
            {type: "checkbox", fixed:"left"},
            {field: "id", title:'编号',align:"center",hide:true},
            {field: 'cusNo', title: '客户编号',align:"center"},
            {field: 'cusName', title: '客户名称',align:"center"},
            {field: 'cusManager', title: '客户经理',align:"center",minWidth: 86},
            {field: 'lastOrderTime', title: '最后下单时间',align:"center",minWidth: 114},
            {field: 'confirmLossTime', title: '流失时间',align:"center",minWidth: 141},
            {field: 'lossReason', title: '流失原因',align:"center",minWidth: 164},
            {field: 'createDate', title: '创建时间',align:"center",minWidth: 141},
            {field: 'updateDate', title: '更新时间',align:"center",minWidth: 141},
            {title: '操作',fixed:"right",align:"center", minWidth:140,templet:"#op"}
        ]]
    });


    // 多条件搜索
    $(".search_btn").on("click",function () {
        table.reload("customerLossListTable",{
            page:{
                curr:1
            },
            where:{
                cusNo:$("input[name='cusNo']").val(),//客户编号
                cusName:$("input[name='cusName']").val(),// 客户名称
                state:$("#state").val()//流失状态
            }
        })
    });

    table.on('toolbar(customerLosses)',function (obj) {
        if (obj.event === "refresh"){
            table.reload("customerLossListTable",{
                page:{curr:1},
                where:{
                    cusNo:'',
                    cusName:'',
                    state:''
                }
            })
        }
    });

    function openAddReprievePage(title,id) {
        layui.layer.open({
            title:title,
            type:2,
            area:["600px","180px"],
            maxmin:true,
            content:ctx+"/customerLoss/addReprievePage?lossId="+id
        })
    }

    function openCustomerReprDialog(title,id) {
        layui.layer.open({
            title:title,
            type:2,
            area:["800px","600px"],
            maxmin:true,
            content:ctx+"/customerLoss/customerReprievePage?id="+id
        })
    }

    table.on('tool(customerLosses)',function (obj) {
        if (obj.event === "add"){
            openAddReprievePage("<h2>设置暂缓</h2>",obj.data.id);
        }else if(obj.event === "info"){
            openCustomerReprDialog("<h2>暂缓详情</h2>",obj.data.id);
        } else if(obj.event === "cancel"){
            layer.confirm("<div align='center'><b><h3>确定将该客户取消暂缓吗?</h3></b></div>",
                {icon: 3, title: "提示"},
            function (index) {
                layer.close(index);
                $.post(ctx+"/customerRep/cancel",{lossId:obj.data.id},function (data) {
                    if(data.code===200){
                        layer.msg("<div align='center' style='color: #00B83F'><b><h3>取消暂缓成功!</h3></b></div>",
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
