<!DOCTYPE html>
<html>
<head>
    <#include "*/common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <#if module.grade == 0>
            <label class="layui-form-label">主目录名</label>
        </#if>
        <#if module.grade == 1>
            <label class="layui-form-label">菜单名</label>
        </#if>
        <#if module.grade == 2>
            <label class="layui-form-label">按钮名</label>
        </#if>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" lay-reqtext="资源名称不能为空!" name="moduleName" id="moduleName" value="${module.moduleName}" placeholder="请输入资源名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <#if module.grade == 0>
            <label class="layui-form-label">主目录样式</label>
        </#if>
        <#if module.grade == 1>
            <label class="layui-form-label">菜单样式</label>
        </#if>
        <#if module.grade == 2>
            <label class="layui-form-label">按钮样式</label>
        </#if>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   name="moduleStyle" id="moduleStyle" value="${(module.moduleStyle)!""}" placeholder="请输入资源样式 (可空)">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">排序值</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                    name="orders" id="orders" placeholder="请输入排序值 (可空)" value="${(module.orders)!""}">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">权限码</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" lay-reqtext="权限码不能为空!" name="optValue" id="optValue" placeholder="请输入菜单权限码" value="${module.optValue}">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">资源级别</label>
        <div class="layui-input-block">
            <input type="hidden" name="grade" value="${(module.grade)}">
            <input type="text" class="layui-input userName" value=级别${(module.grade+1)} disabled>
        </div>
    </div>

    <#if module.grade==1>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">菜单地址</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input userName"
                       lay-verify="required" lay-reqtext="菜单地址不能为空!" name="url" id="url" placeholder="请输入菜单地址" value="${(module.url)!""}">
            </div>
        </div>
    </#if>

    <input name="parentId" type="hidden" value="${module.parentId}"/>
    <input name="id" type="hidden" value="${module.id}"/>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block" style="float: left">
            <button class="layui-btn layui-btn-lg" lay-submit lay-filter="updateModule">
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
<script type="text/javascript" src="${ctx}/js/module/update.js"></script>
</body>
</html>