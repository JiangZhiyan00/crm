layui.use(['table', 'treetable','layer'], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        treeTable = layui.treetable;

    // 渲染表格treeTable
    treeTable.render({
        id: "moduleListTable",
        treeColIndex: 1,
        treeSpid: -1,
        treeIdName: 'id',
        treePidName: 'parentId',
        elem: '#munu-table',
        url: ctx + '/module/list',
        toolbar: "#toolbarDemo",
        treeDefaultClose: true,
        page: true,
        minWidth: 20,
        cols: [[
            {type: "numbers", align: "center", title: '序号'},
            {field: 'moduleName', minWidth: 222, title: '资源名称'},
            {field: 'optValue', width: 85, title: '权限码'},
            {field: 'url', title: '资源地址', minWidth: 178},
            {field: 'createDate', title: '创建时间', align: "center", minWidth: 159},
            {field: 'updateDate', title: '更新时间', align: "center", minWidth: 159},
            {
                field: 'grade', minWidth: 87, align: 'center', templet: function (d) {
                    if (d.grade === 0) {
                        return '<span class="layui-badge layui-bg-blue"><i class="layui-icon">&#xe656;</i>主目录</span>';
                    }
                    if (d.grade === 1) {
                        return '<span class="layui-badge-rim layui-bg-green"><i class="layui-icon">&#xe60a;</i>菜单</span>';
                    }
                    if (d.grade === 2) {
                        return '<span class="layui-badge-rim layui-bg-orange">按钮</span>';
                    }
                }, title: '资源类型'
            },
            {minWidth: 238, align: 'center', title: '操作', templet: '#auth-state', fixed: "right"}
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    });

    // 头工具栏事件
    table.on('toolbar(munu-table)',function (obj) {
        if (obj.event === "add"){
            openAddModuleDialog(0,-1);
        }else if(obj.event === "expand"){
            treeTable.expandAll('#munu-table');
        }else if (obj.event === "fold"){
            treeTable.foldAll('#munu-table');
        }else if (obj.event === "refresh"){
            //treeTable.reload()没用
            location.reload();
        }
    });


    //操作栏
    table.on('tool(munu-table)',function (obj) {
        let layEvent =obj.event;
        if(layEvent === "add"){
            /*if(obj.data.grade == 2){
                layer.msg("暂不支持四级菜单添加操作!");
                return;
            }*/
            //子级别grade+1
            openAddModuleDialog(obj.data.grade+1,obj.data.id);
        }else if(layEvent === "edit"){
            openUpdateModuleDialog(obj.data.id);
        }else if(layEvent === "del"){
            layer.confirm("<div align='center'><b><h3>确定删除此条数据吗?</h3></b></div>",
                {icon: 3, title: "提示"},
            function (index) {
                layer.close(index);
                $.post(ctx+"/module/deleteModule",{id:obj.data.id},function (data) {
                    if(data.code===200){
                        layer.msg("<div align='center' style='color: #00B83F'><b><h3>删除成功!</h3></b></div>",
                            {icon: 6})
                        //treeTable.reload()没用
                        location.reload();
                    }else{
                        layer.msg("<div align='center' style='color: red '><b><h3>"+data.msg+"</h3></b></div>",
                            {icon: 5});
                    }
                })
            })
        }
    });


    //打开添加弹出框，传入需要的信息
    function openAddModuleDialog(grade,parentId) {
        layui.layer.open({
            title:"<h2>资源管理-添加资源</h2>",
            type:2,
            area:["700px","500px"],
            maxmin:true,
            content:ctx+"/module/toAddModulePage?grade="+grade+"&parentId="+parentId
        })
    }


    //打开添加弹出框，传入需要的信息
    function openUpdateModuleDialog(id) {
        layui.layer.open({
            title:"<h2>资源管理-更新资源</h2>",
            type:2,
            area:["700px","500px"],
            maxmin:true,
            content:ctx+"/module/toUpdateModulePage?id="+id
        })
    }
});