<!DOCTYPE html>
<html>
<head>
    <#include "*/common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="lossId" type="hidden" value="${lossId!}"/>
    <input name="id" type="hidden" value="${customerRep.id!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">
            <span style="color: red">*</span>暂缓措施</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" lay-reqtext="暂缓措施不能为空!" name="measure" id="measure"  value="${(customerRep.measure)!}" placeholder="请输入暂缓措施">
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block" style="float: left">
            <button class="layui-btn layui-btn-lg" lay-submit lay-filter="firstAdd">
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
<script type="text/javascript" src="${ctx}/js/customerLoss/customer.rep.firstAdd.js"></script>
</body>
</html>