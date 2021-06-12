layui.use(['table','layer','form'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        form = layui.form;

    //从请求域中获取客户id
    let cusId = $("input[name='cusId']").val()
    let tableIns = table.render({
        elem: '#customerContactList',
        url : ctx+'/customerContact/list?cusId='+cusId,
        cellMinWidth : 20,
        page : true,
        height : "full-50",
        limits : [10,20,30,40],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "customerContactListTable",
        cols : [[
            {type: "checkbox", fixed:"left",align:"center"},
            {field: "id", title:'编号',align:'center'},
            {field: 'contactTime', title: '交流时间',  align:'center',minWidth:159},
            {field: 'address', title: '交流地址', align:'center'},
            {field: 'overview', title: '交流内容概要', align:'center',minWidth:120},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:159},
            {field: 'updateDate', title: '更新时间', align:'center',minWidth:159},
            {title: '操作', templet:'#customerListBar',fixed:"right",align:"center", minWidth:112}
        ]]
    });

    // 头工具栏事件
    table.on('toolbar(customerContacts)',function (obj) {
        if (obj.event === "add"){
            openAddOrUpdateCustomerContactDialog();
        }else if (obj.event === "del"){
            delCustomerContacts(table.checkStatus(obj.config.id).data);
        }else if (obj.event === "refresh"){
            table.reload("customerContactListTable",{
                page:{curr:1}
            })
        }
    });

    /**
     * 批量删除客户交流记录
     * @param datas 选中的交流记录信息
     */
    function delCustomerContacts(datas){
        if(datas.length < 1){
            layer.msg("<div align='center' style='color: red'><b><h3>请先选择要删除的记录!</h3></b></div>",
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
                    url:ctx+"/customerContact/delete",
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

    table.on('tool(customerContacts)',function (obj) {
        if(obj.event === "edit"){
            openAddOrUpdateCustomerContactDialog(obj.data.id);
        }else if(obj.event === "del"){
            layer.confirm("<div align='center'><b><h3>确定删除此条记录?</h3></b></div>",
                {icon: 3, title: "提示"},
            function (index) {
                layer.close(index);
                $.post(ctx+"/customerContact/delete",{ids:obj.data.id},function (data) {
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



    function openAddOrUpdateCustomerContactDialog(id) {
        let title="<h2>添加客户交流记录</h2>";
        let url=ctx+"/customerContact/addOrUpdateCustomerContactPage?cusId="+cusId;
        if(id){
            title="<h2>更新客户交流记录</h2>";
            url=url+"&id="+id;
        }
        layui.layer.open({
            title:title,
            type:2,
            area:["650px","450px"],
            maxmin:true,
            content:url
        })
    }

});
