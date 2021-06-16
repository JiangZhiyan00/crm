layui.use(['table','layer',"form"],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //服务列表展示
    let tableIns = table.render({
        elem: '#customerServeList',
        url : ctx+'/customerServe/list?',
        cellMinWidth : 20,
        page : true,
        height : "full-120",
        limits : [10,20,30,40],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "customerServeListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "id", title:'编号', width:80, align:"center"},
            {field: 'cusName', title: '客户名', minWidth:50, align:"center"},
            {field: 'serveType', title: '服务类型', minWidth:100,sort:true, align:'center',templet:function(d){
                    return formatterServeType(d.serveType);
                }},
            {field: 'serviceRequest', title: '服务内容', align:'center'},
            {field: 'overview', title: '服务概要', align:'center'},
            {field: 'createPeople', title: '创建人', minWidth:100, align:'center'},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:150},
            {field: 'updateDate', title: '更新时间', align:'center',minWidth:150},
            {title: '操作', templet:'#customerServeListBar',fixed:"right",align:"center", minWidth:112}
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
                curr: 1 //重新从第 1 页开始
            },
            where: {
                cusName: $("input[name='cusName']").val(),  //客户名
                serveType: $("#serveType").val()  //服务类型
            }
        })
    });


    // 头工具栏事件
    table.on('toolbar(customerServes)',function (obj) {
        if (obj.event === "add"){
            openAddOrUpdateCustomerServeDialog();
        }else if (obj.event === "refresh"){
            table.reload("customerServeListTable",{
                page: {
                    curr: 1
                },
                where: {
                    cusName: '',
                    serveType: ''
                }
            })
        }else if (obj.event === "del"){
            delCustomerServes(table.checkStatus(obj.config.id).data);
        }
    });

    function openAddOrUpdateCustomerServeDialog(id) {
        let title="<h2>服务管理-服务创建<h2>";
        let url=ctx+"/customerServe/addOrUpdateCustomerServePage";
        if (id){
            title="<h2>服务管理-服务更新<h2>";
            url=url+"?serveId="+id;
        }
        layui.layer.open({
            title:title,
            type:2,
            area:["670px","370px"],
            maxmin:true,
            content:url
        })
    }

    function delCustomerServes(datas){
        if(datas.length===0){
            layer.msg("<div align='center' style='color: red'><b><h3>请先选择要删除的数据!</h3></b></div>",
                {icon: 2});
            return;
        }
        layer.confirm("<div align='center'><b><h3>确定删除选中的记录?</h3></b></div>",
            {icon: 3, title: "提示"},
        function (index) {
            layer.close(index);
            let ids="ids=";
            for(let i=0;i<datas.length;i++){
                if(i<datas.length-1){
                    ids=ids+datas[i].id+"&ids=";
                }else{
                    ids=ids+datas[i].id;
                }
            }

            $.ajax({
                type:"post",
                url:ctx+"/customerServe/delete",
                data:ids,
                dataType:"json",
                success:function (data) {
                    if(data.code===200){
                        layer.msg("<div align='center' style='color: #00B83F'><b><h3>删除成功!</h3></b></div>",
                            {icon: 6})
                        tableIns.reload();
                    }else{
                        layer.msg("<div align='center' style='color: red'><b><h3>"+data.msg+"</h3></b></div>",
                            {icon: 5});
                    }
                }
            })
        })
    }

    table.on('tool(customerServes)',function (obj) {
        if(obj.event === "edit"){
            openAddOrUpdateCustomerServeDialog(obj.data.id);
        }else if(obj.event === "del"){
            layer.confirm("<div align='center'><b><h3>确定删除此条记录吗?</h3></b></div>",
                {icon: 3, title: "提示"},
            function (index) {
                layer.close(index);
                $.post(ctx+"/customerServe/delete",{ids:obj.data.id},function (data) {
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
