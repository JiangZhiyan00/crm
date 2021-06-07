layui.use(['table','layer'],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //职位列表展示
    let tableIns = table.render({
        elem: '#roleList',
        url : ctx+'/role/list',
        cellMinWidth : 20,
        page : true,
        height : "full-120",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "roleListTable",
        cols : [[
            {type: "checkbox", fixed:"left"},
            {field: "id", title:'编号',fixed:"true",align:"center",minWidth:60},
            {field: 'roleName', title: '职位名称', align:"center",minWidth:140},
            {field: 'roleRemark', title: '职位备注', align:'center',minWidth:140},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:142},
            {field: 'updateDate', title: '更新时间', align:'center',minWidth:142},
            {title: '操作', templet:'#roleListBar',fixed:"right",align:"center",minWidth:112}
        ]]
    });

    // 多条件搜索
    $(".search_btn").on("click",function () {
        table.reload("roleListTable",{
            page:{
                curr:1
            },
            where:{
                // 职位名
                roleName:$("input[name='roleName']").val()
            }
        })
    });

    // 头工具栏事件 添加 授权
    table.on('toolbar(roles)',function (obj) {
        if (obj.event == "add"){
            openAddOrUpdateRoleDialog();
        }else if (obj.event == "del"){
            delRoles(table.checkStatus(obj.config.id).data);
        }else if (obj.event =="grant"){
            openAddGrantDialog(table.checkStatus(obj.config.id).data);
        }else if (obj.event == "refresh"){
            table.reload("roleListTable",{
                page:{curr:1},
                where:{
                    roleName:'',// 职位名
                }
            })
        }
    })

    /**
     * 批量删除职位
     *   datas:选择的待删除记录数组
     */
    function delRoles(datas){
        if(datas.length===0){
            layer.msg("<div align='center' style='color: red'><b><h3>请先选择要删除的数据!</h3></b></div>",
                {icon: 2});
            return;
        }
        layer.confirm("<div align='center'><b><h3>确定删除选中的职位信息吗?</h3></b></div>",
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
                    url:ctx+"/role/deleteRoles",
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


    //编辑 删除 职位数据
    table.on('tool(roles)',function (obj) {
        let layEvent =obj.event;
        if(layEvent === "edit"){
            openAddOrUpdateRoleDialog(obj.data.id);
        }else if(layEvent === "del"){
            layer.confirm("<div align='center'><b><h3>确定删除此职位信息吗?</h3></b></div>",
                {icon: 3, title: "提示"},function (index) {
                layer.close(index);
                $.post(ctx+"/role/deleteRoles",{ids:obj.data.id},function (data) {
                    if(data.code==200){
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




    // 弹出添加/编辑框
    function openAddOrUpdateRoleDialog(id) {
        let title = "<h2>角色管理-职位添加</h2>";
        let url = ctx + "/role/addOrUpdateRolePage";
        if(id){
            title="<h2>角色管理-职位更新</h2>";
            url=url+"?id="+id;
        }
        layui.layer.open({
            title:title,
            type:2,
            area:["600px","300px"],
            maxmin:true,
            content:url
        })
    }



    function openAddGrantDialog(datas) {
        if(datas.length === 0){
            layer.msg("<div align='center' style='color: red'><b><h3>请先选择需授权的职位!</h3></b></div>",
                {icon:5});
            return;
        }
        if(datas.length > 1){
            layer.msg("<div align='center' style='color: red'><b><h3>暂不支持批量授权!</h3></b></div>",
                {icon:5});
            return;
        }
        //打开可分配的资源界面
        layui.layer.open({
            title:"<h2>角色管理-职位授权</h2>",
            type:2,
            area:["400px","600px"],
            maxmin:true,
            content:ctx+"/role/toAddGrantPage?roleId="+datas[0].id
        })
    }
});
