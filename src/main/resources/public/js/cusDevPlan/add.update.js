layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on('submit(addOrUpdateCusDevPlan)',function (data) {
        let index = top.layer.msg("<div align='center'><b><h3>数据提交中,请稍后...</h3></b></div>",
            {icon:16,time:false,shade:0.8});
        let url = ctx+"/cusDevPlan/addPlan";
        let time = data.field.planDate
        console.log(time.match(/^(\d{4})(-)(\d{2})(-)(\d{2})$/))

        if($("input[name='id']").val()){
            url=ctx+"/cusDevPlan/updatePlan";
        }
        $.post(url,data.field,function (res) {
            if(res.code===200){
                top.layer.msg("<div align='center' style='color: #00B83F'><b><h3>操作成功!</h3></b></div>",{
                    icon: 6,
                    time: 2000,
                    shade : [0.6 , '#000' , true]
                });
                top.layer.close(index);
                // 刷新父页面
                parent.location.reload();
            }else{
                layer.msg("<div align='center' style='color: red '><b><h3>"+res.msg+"</h3></b></div>",
                    {icon:5,time:1500,shade : [0.6 , '#000' , true]});
            }
        });
        return false;
    });

    /**
     * 监听取消
     */
    $("#cancel").on("click",function(){
        let index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })

});