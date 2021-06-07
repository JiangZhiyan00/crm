layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    // 进行登录操作
    form.on('submit(login)', function (data) {
        //加载
        let index= top.layer.msg("登录中,请稍后...",{icon:16,time:5000,shade:0.8});
        $.ajax({
            type:"post",
            url:ctx+"/user/login",
            data:{
                userName:data.field.username,
                userPassword:data.field.password
            },
            dataType:"json",
            success:function (response) {
                layer.close(index);
                if(response.code===200){
                    layer.msg("<div align='center' style='color: #00B83F'><b><h3>登录成功!</h3></b></div>",{
                        icon: 6,
                        time: 2000, //2秒关闭(不配置默认3秒)
                        shade : [0.6 , '#000' , true]
                    }, function () {
                        // 如果点击记住密码,设置cookie,过期时间7天
                        if($("#rememberMe").checked){
                            // 写入cookie 7天
                            $.cookie("userId",response.result.userIdStr, { expires: 7 });
                            $.cookie("userName",response.result.userName, { expires: 7 });
                            $.cookie("trueName",response.result.trueName, { expires: 7 });
                        }else{
                            $.cookie("userId",response.result.userIdStr);
                            $.cookie("userName",response.result.userName);
                            $.cookie("trueName",response.result.trueName);
                        }
                        window.location.href=ctx+"/main";
                    });
                }else{
                    layer.msg("<div align='center' style='color: red '><b><h3>"+response.msg+"</h3></b></div>",
                        {icon:5});
                }
            }
        });
        return false;
    });
});