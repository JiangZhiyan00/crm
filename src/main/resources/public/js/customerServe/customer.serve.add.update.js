layui.use(['form', 'layer','xmSelect'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    var  xmSelect = layui.xmSelect;

    let customers = xmSelect.render({
        el: "#allCustomers",
        toolbar: {show: true}, //下拉框工具栏
        data: [],
        radio: true,//开启单选
        clickClose:true,//选中关闭下拉框
        searchTips: "搜索客户", //下拉框显示的提示信息
        filterable: true, //打开输入文本搜索
        direction: "down", //下拉方向
        layVerify: "required", //必填项目
        layReqText: "客户不能为空!", //未选择时提醒信息
        name: "cusName", //默认为select,需要手动设置
        paging: true, //开启分页
        pageSize: 5, //每页显示5条数据
        prop: {name: "name",value:"name"}//设置字段的key值
    })

    //更新操作的ajax请求,返回name,selected两个字段
    $.get(ctx+"/customer/selectAllCustomersAndSelected",{serveId:$("input[name='id']").val()},function (res){
        if (res != null){
            // console.log(res)
            for (let i=0;i<res.length;i++){
                res[i].selected = !!res[i].selected;
            }
            // console.log(res)
            customers.update({
                data: res,
                autoRow: true,
            })
        }
    })


    form.on('submit(addOrUpdateCustomerServe)',function (data) {
        let index = top.layer.msg("数据提交中,请稍后...", {icon: 16, time: false, shade: 0.8});
        let url = ctx + "/customerServe/add";
        if ($("input[name='id']").val()) {
            url = ctx + "/customerServe/update";
        }
        $.post(url, data.field, function (res) {
            if (res.code === 200) {
                top.layer.msg("<div align='center' style='color: #00B83F'><b><h3>"+ res.msg +"</h3></b></div>", {
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
        });
        return false;
    })

    $("#cancel").on("click",function (){
        let index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })
});