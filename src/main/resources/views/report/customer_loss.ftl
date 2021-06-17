<!DOCTYPE html>
<html>
<head>
	<title>流失客户统计</title>
	<#include "*/common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" name="cusNo"
						   class="layui-input
					searchVal" placeholder="客户编号" />
				</div>
				<div class="layui-input-inline">
					<input type="text" name="cusName" class="layui-input
					searchVal" placeholder="客户名" />
				</div>
				<a class="layui-btn search_btn" data-type="reload"><i
							class="layui-icon">&#xe615;</i> 搜索</a>
			</div>
		</form>
	</blockquote>
</form>

<div class="layui-card">
	<table id="customerLossList" class="layui-table"  lay-filter="customerLosses"></table>
	<script type="text/html" id="toolbarDemo">
		<div class="layui-btn-group">
			<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="refresh">
				<i class="layui-icon">&#xe669;</i>刷新
			</button>
		</div>
	</script>
	<div class="layui-card-body" id="pie" style="width: 100%;min-height:500px"></div>
</div>
<script type="text/javascript" src="${ctx}/js/report/customer.loss.js"></script>

</body>
</html>