<!DOCTYPE html>
<html>
<head>
    <#include "*/common.ftl">
</head>
<body class="childrenBody">
<center>
    <form class="layui-form" style="width:80%;">
        <input type="hidden" name="id" value="${(customerLinkMan.id)!}">
        <input type="hidden" name="cusId" value="${(cusId)!}">

        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">
                <span style="color: red">*</span>联系人姓名</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input"
                       lay-verify="required" lay-reqtext="联系人姓名不能为空" name="linkName" id="linkName" value="${(customerLinkMan.linkName)!}"  placeholder="请输入联系人姓名">
            </div>
        </div>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">
                <span style="color: red">*</span>联系人性别</label>
            <div class="layui-input-block">
                <select name="sex"  id="sex" lay-verify="required" lay-reqtext="联系人性别不能为空">
                    <option value="" >请选择联系人性别</option>
                    <option value="男" <#if customerLinkMan?? && customerLinkMan.sex=="男">selected="selected"</#if>>男</option>
                    <option value="女" <#if customerLinkMan?? && customerLinkMan.sex=="女">selected="selected"</#if>>女</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">
                <span style="color: red">*</span>联系人职位</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input"
                       name="position"  lay-verify="required" lay-reqtext="联系人职位不能为空"
                       placeholder="请输入联系人职位" value="${(customerLinkMan.position)!}">
            </div>
        </div>

        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">办公电话</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input"
                        name="officePhone" id="officePhone" placeholder="请输入办公电话" value="${(customerLinkMan.officePhone)!}">
            </div>
        </div>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">
                <span style="color: red">*</span>手机号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input"
                       lay-verify="phone" name="phone" id="phone" placeholder="请输入联系人手机号" value="${(customerLinkMan.phone)!}">
            </div>
        </div>

        <br/>
        <div class="layui-form-item layui-row layui-col-xs12">
            <div class="layui-input-block" style="float: left">
                <button class="layui-btn layui-btn-lg" lay-submit lay-filter="addOrUpdateCustomerLinkMan">
                    <i class="layui-icon">&#xe605;</i>提交
                </button>
            </div>
            <div class="layui-input-block" style="float: right">
                <button class="layui-btn layui-btn-lg layui-btn-normal" id="cancel">
                    <i class="layui-icon">&#x1006;</i>取消
                </button>
            </div>
        </div>
    </form>
</center>
<script type="text/javascript" src="${ctx}/js/customer/customer_link.js"></script>
</body>
</html>