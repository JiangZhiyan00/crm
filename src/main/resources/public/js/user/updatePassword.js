layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    form.on('submit(saveBtn)', function (data) {
        let index = layer.load(1);
        $.ajax({
            type:"post",
            url:ctx+"/user/updatePassword",
            data:{
                oldPassword:data.field.oldPassword,
                newPassword:data.field.newPassword,
                confirmPassword:data.field.confirmPassword
            },
            dataType:"json",
            success:function (response) {
                layer.close(index);
                if(response.code === 200){
                    layer.msg("<div align='center' style='color: #00B83F'><b><h3>修改成功,系统即将退出...</h3></b></div>",{
                        icon: 1,
                        time: 2000,
                        shade : [0.6 , '#000' , true]
                    },function () {
                        //清空原有cookie
                        $.removeCookie("userId",{domain:"localhost",path:"/"});
                        $.removeCookie("userName",{domain:"localhost",path:"/"});
                        $.removeCookie("trueName",{domain:"localhost",path:"/"});
                        $.removeCookie("JSESSIONID",{domain:"localhost",path:"/"});
                        setTimeout(function () {
                            window.parent.location.href = ctx + "/";
                        },1500);
                    })
                }else {
                    layer.msg("<div align='center' style='color: red '><b><h3>"+response.msg+"</h3></b></div>",{
                        icon: 2,
                        time: 1200,
                        shade : [0.6 , '#000' , true]
                    });
                }
            }
        });
        return false;
    });
});
