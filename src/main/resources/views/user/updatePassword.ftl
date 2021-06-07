<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <#include "*/common.ftl">
    <style>
        .layui-form-item .layui-input-company {width: auto;padding-right: 10px;line-height: 38px;}
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <div class="layui-form layuimini-form">
            <div class="layui-form-item">
                <label class="layui-form-label required">原始密码</label>
                <div class="layui-input-block">
                    <input type="password" name="oldPassword" lay-verify="required" lay-reqtext="原始密码不能为空" placeholder="请输入原始密码"  value="" class="layui-input">
                    <tip>填写当前账号的原始密码</tip>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label required">新密码</label>
                <div class="layui-input-block">
                    <input type="password" name="newPassword" lay-verify="required" lay-reqtext="新密码不能为空" placeholder="请输入新密码"  value="" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">确认密码</label>
                <div class="layui-input-block">
                    <input type="password" name="confirmPassword" lay-verify="required" lay-reqtext="确认密码不能为空" placeholder="请再次输入新的密码"  value="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="saveBtn">保存并重新登录</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/js/user/updatePassword.js"></script>

</body>
</html>