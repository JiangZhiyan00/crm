layui.use(['layer'], function () {
    let layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    $(function () {
        loadModuleInfo();
    });

    let zTreeObj;

    function loadModuleInfo() {
        $.ajax({
            type: "get",
            url: ctx + "/module/selectAllModules?roleId=" + $("input[name='roleId']").val(),
            dataType: "json",
            success: function (data) {

                //console.log(data)
                // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
                let setting = {
                    check: {
                        enable: true
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        onCheck: zTreeOnCheck
                    }
                };
                zTreeObj = $.fn.zTree.init($("#moduleTree"), setting, data);
            }
        })
    }

    let moduleIds = "";
    let nodes = [];
    //复选框选中与取消选中时触发的函数
    function zTreeOnCheck() {
        //getCheckedNodes(true)获取被选中的节点的集合
        nodes = zTreeObj.getCheckedNodes(true);
        moduleIds = "moduleIds=";
          // console.log(nodes)
        for (let i = 0; i < nodes.length; i++) {
            if (i < nodes.length - 1) {
                moduleIds += nodes[i].id + "&moduleIds=";
            } else {
                moduleIds += nodes[i].id;
            }
        }
          // console.log(moduleIds)
    }

    $("#addPermission").on("click", function () {
        if (nodes.length < 1) {
            layer.msg("<div align='center' style='color: red'><b><h3>请选择至少一项权限!</h3></b></div>",
                {icon: 2});
            return;
        }
        let index = top.layer.msg("数据提交中,请稍后...", {icon: 16, time: false, shade: 0.8});
        $.ajax({
            type: "post",
            url: ctx + "/role/addGrant",
            data: moduleIds + "&roleId=" + $("input[name='roleId']").val(),
            dataType: "json",
            success: function (res) {
                if (res.code === 200) {
                    top.layer.msg("<div align='center' style='color: #00B83F'><b><h3>授权成功!</h3></b></div>", {
                        icon: 6,
                        time: 2000,
                        shade: [0.6, '#000', true]
                    });
                    layer.close(index);
                    // 刷新父页面
                    parent.location.reload();
                } else {
                    layer.msg("<div align='center' style='color: red '><b><h3>" + res.msg + "</h3></b></div>",
                        {icon: 5, time: 1500, shade: [0.6, '#000', true]});
                }
            }
        })
    })
    /**
     * 监听取消
     */
    $("#cancel").on("click", function () {
        let index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })
})
