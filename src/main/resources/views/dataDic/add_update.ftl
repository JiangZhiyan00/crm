<!DOCTYPE html>
<html>
<head>
    <#include "*/common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:85%;">
    <input type="hidden" name="id"  value="${(dataDic.id)!}">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label required">
            <span style="color: red">*</span>数据名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="required" lay-reqtext="数据名称不能为空!" name="dataDicName" id="dataDicName"  value="${(dataDic.dataDicName)!}"  placeholder="请输数据名称">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label required">
            <span style="color: red">*</span>数据值</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="required" lay-reqtext="数据值不能为空!" name="dataDicValue" id="dataDicValue"  value="${(dataDic.dataDicValue)!}"  placeholder="请输入数据值">
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block" style="float: left">
            <button class="layui-btn layui-btn-lg" lay-submit lay-filter="addOrUpdateDataDic">
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
<script type="text/javascript" src="${ctx}/js/dataDic/add.update.js"></script>
</body>
</html>