layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on("submit(addOrUpdateCustomerRep)", function (data) {
        let index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        //弹出loading
        let url=ctx + "/customerRep/add";
        if($("input[name='id']").val()){
            url=ctx + "/customerRep/update";
        }
        $.post(url, data.field, function (res) {
            if(res.code===200){
                top.layer.msg("<div align='center' style='color: #00B83F'><b><h3>操作成功!</h3></b></div>",{
                    icon: 6,
                    time: 1500,
                    shade : [0.6 , '#000' , true]
                });
                layer.close(index);
                // 刷新父页面
                parent.location.reload();
            }else{
                layer.msg("<div align='center' style='color: red '><b><h3>"+res.msg+"</h3></b></div>",
                    {icon:5,time:1500,shade : [0.6 , '#000' , true]});
            }
        });
        return false;
    });

    $("#cancel").on("click",function(){
        let index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })
});