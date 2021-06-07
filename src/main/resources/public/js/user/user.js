layui.use(['table','layer',"form"],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //用户列表展示
    let tableIns = table.render({
        elem: '#userList',
        url : ctx+'/user/queryUsers',
        cellMinWidth : 20,
        page : true,
        height : "full-120",
        limits : [10,20,30,40],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left",align:"center"},
            {field: "id", title:'编号',fixed:"true", minWidth:58,align:"center"},
            {field: 'userName', title: '用户名', minWidth:86, align:"center"},
            {field: 'trueName', title: '真实姓名',minWidth:100,align:'center'},
            {field: 'email', title: '邮箱', minWidth:168, align:'center'},
            {field: 'phone', title: '手机号', minWidth:116, align:'center'},
            {field: 'createDate', title: '创建时间', align:'center',minWidth:150},
            {field: 'updateDate', title: '更新时间', align:'center',minWidth:150},
            {title: '操作', minWidth:150, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });


    // 多条件搜索
    $(".search_btn").on("click",function () {
        table.reload("userListTable",{
            page:{
                curr:1
            },
            where:{
                trueName:$("input[name='trueName']").val(),
                email:$("input[name='email']").val(),
                phone:$("input[name='phone']").val()
            }
        })
    });


    // 头工具栏事件
    table.on('toolbar(users)',function (obj) {
        if (obj.event == "add"){
            openAddOrUpdateUserDialog();
        }else if (obj.event == "del"){
            delUser(table.checkStatus(obj.config.id).data);
        }else if (obj.event == "refresh"){
            table.reload("userListTable",{
                page:{curr:1},
                where:{
                    trueName:'',
                    email:'',
                    phone:''
                }
            })
        }
    });



    /**
     * 批量删除
     *   datas:选择的待删除记录数组
     */
    function delUser(datas){
        if(datas.length === 0){
            layer.msg("<div align='center' style='color: red'><b><h3>请先选择要删除的用户!</h3></b></div>",
                {icon: 5});
            return;
        }
        layer.confirm("<div align='center'><b><h3>确定删除选中的用户?</h3></b></div>",
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
                url:ctx+"/user/delete",
                data:ids,
                dataType:"json",
                success:function (data) {
                    if(data.code==200){
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

    // 编辑 删除选项
    table.on('tool(users)',function (obj) {
        var layEvent =obj.event;
        if(layEvent === "edit"){
            openAddOrUpdateUserDialog(obj.data.id);
        }else if(layEvent === "del"){
            layer.confirm("<div align='center'><b><h3>确定删除当前用户?</h3></b></div>",
                {icon: 3, title: "提示"},function (index) {
                $.post(ctx+"/user/delete",{ids:obj.data.id},function (data) {
                    layer.close(index);
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


    //弹出框
    function openAddOrUpdateUserDialog(id) {
        let title="<h2>用户管理-添加用户信息</h2>";
        let url=ctx+"/user/addOrUpdateUserPage";
        if(id){
            title="<h2>用户管理-更改用户信息</h2>";
            url=url+"?id="+id;
        }
        layui.layer.open({
            title:title,
            type:2,
            area:["700px","650px"],
            maxmin:true,
            content:url
        })
    }
});
