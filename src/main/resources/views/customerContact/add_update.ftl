<!DOCTYPE html>
<html>
<head>
    <#include "*/common.ftl">
</head>
<body class="childrenBody">
<div align="justify">
    <form class="layui-form" style="width:95%;">
        <input type="hidden" name="id"  value="${(customerContact.id)!}">
        <input type="hidden" name="cusId"  value="${(cusId)!}">

        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">
                <span style="color: red">*</span>交流时间</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input userName" lay-verify="required"
                       lay-reqtext="交流时间不能为空!" name="contactTime" id="contactTime"
                        <#if (customerContact.contactTime)??>
                            value="${(customerContact.contactTime)?string("yyyy-MM-dd HH:mm:ss")}"
                        </#if>
                       placeholder="请选择交流时间">
            </div>
        </div>

        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">
                <span style="color: red">*</span>交流地址</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" lay-reqtext="交流地址不能为空!"
                       name="address"  lay-verify="required"
                       placeholder="请输入交流地址" value="${(customerContact.address)!}">
            </div>
        </div>

        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">
                <span style="color: red">*</span>交流概要</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入交流内容概要" name="overview" lay-verify="required"
                          lay-reqtext="交流地址不能为空!" class="layui-textarea">${(customerContact.overview)!}</textarea>
            </div>
        </div>

        <br/>
        <div class="layui-form-item layui-row layui-col-xs12">
            <div class="layui-input-block" style="float: left">
                <button class="layui-btn layui-btn-lg" lay-submit lay-filter="addOrUpdateCustomerContact">
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
</div>
<script type="text/javascript" src="${ctx}/js/customerContact/addOrUpdate.js"></script>
<script>
    layui.use('laydate', function(){
        let laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#contactTime',//指定元素
            type:"datetime",
            format: 'yyyy-MM-dd HH:mm:ss', //可任意组合
            min: '1990-1-1 00:00:00',
            max: 0
        });
    });
</script>
</body>
</html>