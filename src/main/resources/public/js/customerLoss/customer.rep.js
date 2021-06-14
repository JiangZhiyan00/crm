layui.use(['table','layer',"form"],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    //暂缓列表展示
    let lossId = $("input[name='lossId']").val();
    let tableIns = table.render({
        elem: '#customerRepList',
        url : ctx+'/customerRep/list?lossId='+lossId,
        cellMinWidth : 20,
        page : true,
        height : "full-120",
        limits : [10,20,30,40],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "customerRepListTable",
        cols : [[
            {type: "checkbox", fixed:"left"},
            {field: "id", title:'编号',align:"center",minWidth: 65},
            {field: 'measure', title: '暂缓措施',align:"center"},
            {field: 'createDate', title: '创建时间',align:"center",minWidth: 141},
            {field: 'updateDate', title: '更新时间',align:"center",minWidth: 141},
            {title: '操作',fixed:"right",align:"center", minWidth:112,templet:"#customerRepListBar"}
        ]]
    });

    // 头工具栏事件
    table.on('toolbar(customerReps)',function (obj) {
        if (obj.event === "add"){
            openAddOrUpdateCustomerReprDialog();
        }else if (obj.event === "del"){
            delReprieves(table.checkStatus(obj.config.id).data);
        }else if (obj.event === "refresh"){
            table.reload("customerRepListTable",{
                page:{curr:1},
            })
        }
    });

    function delReprieves(datas){
        if(datas.length===0){
            layer.msg("<div align='center' style='color: red'><b><h3>请先选择要删除的数据!</h3></b></div>",
                {icon: 2});
            return;
        }
        layer.confirm("<div align='center'><b><h3>确定删除选中的暂缓措施?</h3></b></div>",
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
                url:ctx+"/customerRep/delete",
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

    table.on('tool(customerReps)',function (obj) {
        if(obj.event === "edit"){
            openAddOrUpdateCustomerReprDialog(obj.data.id);
        }else if(obj.event === "del"){
            layer.confirm("<div align='center'><b><h3>确定删除此暂缓措施吗?</h3></b></div>",
                {icon: 3, title: "提示"},
            function (index) {
                layer.close(index);
                $.post(ctx+"/customerRep/delete",{ids:obj.data.id},function (data) {
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


    function openAddOrUpdateCustomerReprDialog(id) {
        let title="添加暂缓措施";
        let url=ctx+"/customerRep/addOrUpdateReprievePage?lossId="+$("input[name='lossId']").val();
        if(id){
            title="更新暂缓措施";
            url=url+"&id="+id;
        }
        layui.layer.open({
            title:title,
            type:2,
            area:["600px","180px"],
            maxmin:true,
            content:url
        })
    }
});
