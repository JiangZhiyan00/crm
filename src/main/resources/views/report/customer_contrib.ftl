<!--
   客户贡献分析页面
-->
<!DOCTYPE html>
<html>
<head>
    <title>客户贡献分析</title>
    <#include "*/common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
    <blockquote class="layui-elem-quote quoteBox">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="cusName"
                           class="layui-input
					searchVal" placeholder="客户名" />
                </div>
                <div class="layui-input-inline">
                    <select name="moneyLevel" id="moneyLevel">
                        <option value="">请选择金额区间</option>
                        <option value="1">0-50000</option>
                        <option value="2">50000-100000</option>
                        <option value="3">100000-500000</option>
                        <option value="4">500000以上</option>
                    </select>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input userName" id="startDate" name="startDate" placeholder="请选择下单日期范围(开始)">
                    </div>
                    -
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input userName" id="endDate" name="endDate" placeholder="请选择下单日期范围(结束)">
                    </div>
                </div>
                <a class="layui-btn search_btn" data-type="reload"><i
                            class="layui-icon">&#xe615;</i> 搜索</a>
            </div>
    </blockquote>
</form>
<div class="layui-card">
    <table id="contribList" class="layui-table"  lay-filter="contrib"></table>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-group">
            <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="refresh">
                <i class="layui-icon">&#xe669;</i>刷新
            </button>
        </div>
    </script>
    <div class="layui-card-body" id="pie" style="width: 100%;min-height:500px"></div>

</div>
<script type="text/javascript" src="${ctx}/js/report/customer.contrib.js"></script>
</body>
</html>