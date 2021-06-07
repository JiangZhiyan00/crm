layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    //机会数据列表展示
    var  tableIns = table.render({
        elem: '#saleChanceList',
        url : ctx+'/cusDevPlan/queryOwnSaleChances',
        cellMinWidth : 20,
        page : true,
        height : "full-120",
        limits : [10,20,30,40],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "saleChanceListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true",align:"center",sort:true},
            {field: 'customerName', title: '客户名称',  align:'center',sort:true},
            {field: 'chanceSource', title: '机会来源',align:"center",sort:true},
            {field: 'successPossibility', title: '成功几率(%)', align:'center',sort:true},
            {field: 'overview', title: '概要', align:'center'},
            {field: 'linkMan', title: '联系人',  align:'center',sort:true},
            {field: 'linkPhone', title: '联系电话', align:'center',sort:true},
            {field: 'description', title: '描述', align:'center'},
            {field: 'createMan', title: '创建人', align:'center',sort:true},
            {field: 'createDate', title: '创建时间', align:'center',sort:true},
            {field: 'updateDate', title: '更新时间', align:'center',sort:true},
            {field: 'devResult', title: '开发状态', align:'center',sort:true,templet:function (d) {
                    return formatterDevResult(d.devResult);
                }},
            {title: '操作',fixed:"right",align:"center", minWidth:80,templet:"#op"}
        ]]
    });

    //开发状态显示按钮
    function formatterDevResult(value){
        /**
         * 0-未开发
         * 1-开发中
         * 2-开发成功
         * 3-开发失败
         */
        if(value===0){
            return "<div style='color: #0937bd'><b>未开发</b></div>";
        }else if(value===1){
            return "<div style='color: #ea8e02;'><b>开发中</b></div>";
        }else if(value===2){
            return "<div style='color: #00B83F'><b>开发成功</b></div>";
        }else if(value===3){
            return "<div style='color: red'><b><del><i>开发失败</i></del></b></div>";
        }else {
            return "<div style='color: #505050'><b>未知</b></div>"
        }
    }


    // 多条件搜索
    $(".search_btn").on("click",function () {
        table.reload("saleChanceListTable",{
            page:{
                curr:1
            },
            where:{
                customerName:$("input[name='customerName']").val(),// 客户名
                createMan:$("input[name='createMan']").val(),// 创建人
                devResult:$("#devResult").val()    //开发状态
            }
        })
    });

    // 头工具栏事件
    table.on('toolbar(saleChances)',function (obj) {
        if (obj.event === "refresh"){
            table.reload("saleChanceListTable",{
                page:{curr:1},
                where:{
                    customerName:'',// 客户名
                    createMan:'',// 创建人
                    devResult:''//开发状态
                }
            })
        }
    });


    //根据两种情况显示不同的标题
    table.on("tool(saleChances)",function (obj) {
        let layEvent = obj.event;
        if(layEvent==="dev"){
            openCusDevPlanDialog("<h2>计划项数据维护</h2>",obj.data.id);
        }else if(layEvent ==="info"){
            openCusDevPlanDialog("<h2>计划项数据详情</h2>",obj.data.id);
        }
    });


    //打开选择的计划数据窗口
    function openCusDevPlanDialog(title,id) {
        layui.layer.open({
            title:title,
            type:2,
            area:["800px","600px"],
            maxmin:true,
            content:ctx+"/cusDevPlan/toCusDevPlanDataPage?id="+id
        })
    }
});
