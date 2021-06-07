layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    //查询所有销售
    $.get(ctx+"/user/selectAllSales",function (res) {
        if (res != null){
            for(let i=0;i<res.length;i++) {
                //绑定下拉框显示
                if ($("#assignManId").val() == res[i].id) {
                    $("#assignManSelect").append("<option value='"+res[i].id+"' selected>"+res[i].trueName + "</option>");
                }else {
                    $("#assignManSelect").append("<option value='"+res[i].id+"'>"+res[i].trueName+"</option>");
                }
            }
        }
        // 重新渲染下拉框内容
        layui.form.render("select");
    });


    /**
     * 监听提交
     */
    form.on('submit(addOrUpdateSaleChance)',function (data) {
        let index= top.layer.msg("数据提交中,请稍后...",{icon:16,time:false,shade:0.8});
        let url = ctx+"/saleChance/addSaleChance";
        if ($("input[name='id']").val()){
            url = ctx+"/saleChance/updateSaleChance";
        }
        $.post(url,data.field,function (res) {
            if(res.code===200){
                top.layer.msg("<div align='center' style='color: #00B83F'><b><h3>操作成功!</h3></b></div>",{
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

    /**
     * 监听取消
     */
    $("#cancel").on("click",function(){
        let index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })
});