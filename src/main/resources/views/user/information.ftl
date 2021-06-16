<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户资料</title>
    <#include "*/common.ftl">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <div class="layui-form layuimini-form">
        <div class="layui-card-header">设置我的资料</div>
            <input type="hidden" name="id"  value="${(user.id)!}">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input type="text" value="${(user.userName)!}" disabled class="layui-input">
                    <div class="layui-form-mid layui-word-aux">更改用户名请联系管理员</div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">真实姓名</label>
                <div class="layui-input-inline">
                    <input type="text" value="${(user.trueName)!}" disabled class="layui-input">
                    <div class="layui-form-mid layui-word-aux">更改真实姓名请联系管理员</div>
                </div>
            </div>
            <#--<div class="layui-form-item">
                <label class="layui-form-label">头像</label>
                <div class="layui-input-inline">
                    <input name="avatar" lay-verify="required" id="LAY_avatarSrc" placeholder="图片地址" value="https://sentsin.gitee.io/res/images/demo/layer.png" class="layui-input">
                </div>
                <div class="layui-input-inline layui-btn-container" style="width: auto;">
                    <button type="button" class="layui-btn layui-btn-primary" id="LAY_avatarUpload">
                        <i class="layui-icon">&#xe67c;</i>上传图片
                    </button>
                    <button class="layui-btn layui-btn-primary" layadmin-event="avartatPreview">查看图片</button >
                </div>
            </div>-->
            <div class="layui-form-item">
                <label class="layui-form-label">手机号</label>
                <div class="layui-input-inline">
                    <input type="text" name="phone" placeholder="请输入手机号" value="${(user.phone)!}" lay-verify="phone" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-inline">
                    <input type="text" name="email" placeholder="请输入邮箱地址" value="${(user.email)!}" lay-verify="email" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="setmyinfo">确认修改</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/js/user/information.js"></script>

</body>
</html>