layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //计划项数据表
    var tableIns = table.render({
        elem: '#cusDevPlanList',
        url : ctx+'/cusDevPlan/list?saleChanceId='+$("input[name='saleChanceId']").val(),
        cellMinWidth : 20,
        page : true,
        height : "full-120",
        limits : [10,20,30,40],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "cusDevPlanTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"left",align:"center",sort:"true"},
            {field: 'planItem', title: '计划项',align:"center"},
            {field: 'planDate', title: '执行时间',align:"center",sort:"true"},
            {field: 'exeAffect', title: '执行效果',align:"center"},
            {field: 'createDate', title: '创建时间',align:"center",sort:"true"},
            {field: 'updateDate', title: '更新时间',align:"center",sort:"true"},
            {title: '操作',fixed:"right",align:"center", minWidth:112,templet:"#cusDevPlanListBar"}
        ]]
    });


    //选项操作（添加计划、开发成功、开发失败）
    table.on("toolbar(cusDevPlans)",function (obj) {
        if (obj.event == "add"){
            openAddOrUpdateCusDevPlanDialog();
        }else if (obj.event == "success") {
            updateSaleChanceDevResult($("input[name='saleChanceId']").val(), 2);
        }else if (obj.event == "failed"){
            updateSaleChanceDevResult($("input[name='saleChanceId']").val(),3);
        }else if (obj.event == "refresh") {
            tableIns.reload();
        }
    });

    //计划管理（编辑、删除）
    table.on("tool(cusDevPlans)",function (obj) {
        let layEvent = obj.event;
        if(layEvent === "edit"){
            openAddOrUpdateCusDevPlanDialog(obj.data.id);
        }else if(layEvent === "del"){
            layer.confirm("<div align='center'><b><h3>确认删除当前记录?</h3></b></div>",
            {icon: 3, title: "提示"},function (index) {
                    layer.close(index);

                $.post(ctx+"/cusDevPlan/delete",{id:obj.data.id},function (data) {
                    if(data.code==200){
                        layer.msg("<div align='center' style='color: #00B83F'><b><h3>删除成功!</h3></b></div>",
                            {
                            icon: 6,
                            time: 2000,
                            shade : [0.6 , '#000' , true]
                        });
                        tableIns.reload();
                    }else{
                        layer.msg("<div align='center' style='color: red '><b><h3>"+data.msg+"</h3></b></div>",
                            {icon:5,time:1500,shade : [0.6 , '#000' , true]});
                    }
                })
            })
        }
    });



    function openAddOrUpdateCusDevPlanDialog(id) {
        let title="计划项管理-添加计划项";
        let url=ctx+"/cusDevPlan/addOrUpdateCusDevPlanPage?saleChanceId="+$("input[name='saleChanceId']").val();
        if(id){
            title="计划项管理-更新计划项";
            url=url+"&id="+id;
        }
        layui.layer.open({
            title:title,
            type:2,
            area:["520px","500px"],
            maxmin:true,
            content:url
        })
    }



    //更新开发状态
    function updateSaleChanceDevResult(id,devResult) {
        layer.confirm("<div align='center'><b><h3>确认要更改开发状态?</h3></b></div>",
            {icon: 3, title: "<div style='color: red'>提示:此操作不可撤销!</div>"},
            function (index) {
                layer.close(index);

                $.post(ctx+"/saleChance/updateSaleChanceDevResult",{
                id:id,
                devResult:devResult},
                    function (data) {
                    if(data.code==200){
                        layer.msg("<div align='center' style='color: #00B83F'><b><h3>开发状态更改成功!</h3></b></div>",
                            {icon:6,time:1500,shade : [0.6 , '#000' , true]});
                        // 刷新父页面
                        parent.location.reload();
                    }else{
                        layer.msg("<div align='center' style='color: red '><b><h3>"+data.msg+"</h3></b></div>",
                            {icon:5,time:1500,shade : [0.6 , '#000' , true]});
                    }
            })
        })
    }

});
