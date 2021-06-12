layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on('submit(addOrUpdateCustomerContact)',function (data) {
        let index= top.layer.msg("数据提交中,请稍后...",{icon:16,time:false,shade:0.8});
        let url = ctx+"/customerContact/add";
        if ($("input[name='id']").val()){
            url = ctx+"/customerContact/update";
        }
        $.post(url,data.field,function (res) {
            if(res.code===200){
                top.layer.msg("<div align='center' style='color: #00B83F'><b><h3>"+res.msg+"</h3></b></div>",{
                    icon: 6,
                    time: 2000,
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