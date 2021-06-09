<!DOCTYPE html>
<html>
<head>
    <title>资源管理</title>
    <#include "*/common.ftl">
</head>
<body class="childrenBody">
    <table id="munu-table" class="layui-table" lay-filter="munu-table"></table>

    <!-- 操作列 -->
    <script type="text/html" id="auth-state">
        <#if optValues?seq_contains("503001")>
            {{#  if(d.grade != 2){ }}
                <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="add">
                    <i class="layui-icon">&#xe654;</i>添加子项
                </a>
            {{#  } else { }}
                <a class="layui-btn layui-btn-xs layui-btn-disabled">
                    <i class="layui-icon">&#xe654;</i>添加子项
                </a>
            {{# } }}
        </#if>
        <#if optValues?seq_contains("503003")>
            <a class="layui-btn layui-btn-xs" lay-event="edit">
                <i class="layui-icon">&#xe642;</i>编辑
            </a>
        </#if>
        <#if optValues?seq_contains("503004")>
            <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">
                <i class="layui-icon">&#xe640;</i>删除
            </a>
        </#if>
    </script>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-group">
            <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="expand">
                <i class="layui-icon">&#xe61a;</i>全部展开
            </button>
            <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="fold">
                <i class="layui-icon">&#xe619;</i>全部折叠
            </button>
            <#if optValues?seq_contains("503001")>
                <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="add">
                    <i class="layui-icon">&#xe654;</i>添加主目录
                </button>
            </#if>
            <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="refresh">
                <i class="layui-icon">&#xe669;</i>刷新
            </button>
        </div>
    </script>

    <script type="text/javascript" src="${ctx}/js/module/module.js"></script>
</body>
</html>