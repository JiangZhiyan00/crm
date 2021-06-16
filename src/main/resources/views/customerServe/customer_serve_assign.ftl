<!DOCTYPE html>
<html>
<head>
    <title>服务分配</title>
    <#include "*/common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="cusName"
                           class="layui-input
					searchVal" placeholder="客户名" />
                </div>
                <div class="layui-input-inline">
                    <select name="serveType"  id="serveType">
                        <option value="" >请选择服务类型</option>
                        <option value="6">咨询</option>
                        <option value="7">建议</option>
                        <option value="8">投诉</option>
                    </select>
                </div>

                <a class="layui-btn search_btn" data-type="reload"><i
                            class="layui-icon">&#xe615;</i> 搜索</a>
            </div>
        </form>
    </blockquote>
    <table id="customerServeList" class="layui-table"  lay-filter="customerServes"></table>


    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-group">
            <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="refresh">
                <i class="layui-icon">&#xe669;</i>刷新
            </button>
        </div>
    </script>

    <script id="customerServeListBar" type="text/html">
        <#if optValues?seq_contains("302001")>
            <a href="javascript:;" class="layui-btn layui-btn-xs"  lay-event="assign">分配</a>
        </#if>
    </script>

</form>
<script type="text/javascript" src="${ctx}/js/customerServe/customer.serve.assign.js"></script>

</body>
</html>