layui.use(['table','layer',"form"],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //服务列表展示
    table.render({
        elem: '#customerServeList',
        url : ctx+'/customerServe/list?state=fw_001',
        cellMinWidth : 20,
        page : true,
        height : "full-120",
        limits : [10,20,30,40],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "customerServeListTable",
        cols : [[
            {type: "checkbox", fixed:"left"},
            {field: "id", title:'编号',align:"center"},
            {field: 'cusName', title: '客户名', minWidth:50, align:"center"},
            {field: 'serveType', title: '服务类型',minWidth:100,sort:true, align:'center',templet:function(d){
                    return formatterServeType(d.serveType);
                }},
            {field: 'serviceRequest', title: '服务内容', align:'center'},
            {field: 'createPeople', title: '创建人', minWidth:100, align:'center'},
            {field: 'assigner', title: '分配人', minWidth:100, align:'center'},
            {field: 'assignTime', title: '分配时间', minWidth:50, align:"center"},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:150},
            {field: 'updateDate', title: '更新时间', align:'center',minWidth:150},
            {title: '操作', minWidth:80, templet:'#customerServeListBar',fixed:"right",align:"center"}
        ]]
    });

    function formatterServeType(serveType){
        if (serveType == 6){
            return "<div style='color: #00B83F'><b>咨询</b></div>";
        }else if (serveType == 7){
            return "<div style='color: #0937bd'><b>建议</b></div>";
        }else if (serveType == 8){
            return "<div style='color: red'><b>投诉</b></div>";
        }
    }

    // 多条件搜索
    $(".search_btn").on("click",function(){
        table.reload("customerServeListTable",{
            page: {
                curr: 1
            },
            where: {
                cusName: $("input[name='cusName']").val(),
                serveType: $("#serveType").val()
            }
        })
    });

    table.on('toolbar(customerServes)',function (obj) {
        if(obj.event === "refresh"){
            table.reload("customerServeListTable",{
                page: {
                    curr: 1
                },
                where: {
                    cusName: '',
                    serveType: ''
                }
            })
        }
    });

    table.on('tool(customerServes)',function (obj) {
        if(obj.event === "assign"){
            openCustomerAssignDialog(obj.data.id);
        }
    });
    
    
    function openCustomerAssignDialog(id) {
        let title="<h2>服务管理-服务分配</h2>";
        let url=ctx+"/customerServe/addCustomerServeAssignPage?id="+id;
        layui.layer.open({
            title:title,
            type:2,
            area:["700px","620px"],
            maxmin:true,
            content:url
        })
    }
});
