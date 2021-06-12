<!DOCTYPE html>
<html>
<head>
    <title>客户管理</title>
    <#include "*/common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <input name="cusId" type="hidden" value="${(cusId)!}"/>

    <table id="customerContactList" class="layui-table"  lay-filter="customerContacts"></table>

    <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-group">
                    <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="add">
                        <i class="layui-icon">&#xe654;</i>添加
                    </button>
                    <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="del">
                        <i class="layui-icon">&#xe640;</i>删除
                    </button>
                <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="refresh">
                    <i class="layui-icon">&#xe669;</i>刷新
                </button>
            </div>
    </script>

    <!--操作-->
    <script id="customerListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>


</form>
<script type="text/javascript" src="${ctx}/js/customerContact/customerContact.js"></script>

</body>
</html>