<!DOCTYPE html>
<html>
<head>
	<title>数据字典管理</title>
	<#include "*/common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" name="dataDicName" class="layui-input
					searchVal" placeholder="数据名称" />
				</div>
				<div class="layui-input-inline">
					<input type="text" name="dataDicValue" class="layui-input
					searchVal" placeholder="数据值" />
				</div>
				<a class="layui-btn search_btn" data-type="reload"><i
							class="layui-icon">&#xe615;</i> 搜索</a>
			</div>
		</form>
	</blockquote>

	<table id="dataDicList" class="layui-table"  lay-filter="dataDic"></table>

	<script type="text/html" id="toolbarDemo">
		<#if optValues??>
			<div class="layui-btn-group">
				<#if optValues?seq_contains("504001")>
					<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="add">
						<i class="layui-icon">&#xe654;</i>添加
					</button>
				</#if>
				<#if optValues?seq_contains("504002")>
					<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="del">
						<i class="layui-icon">&#xe640;</i>删除
					</button>
				</#if>
				<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="refresh">
					<i class="layui-icon">&#xe669;</i>刷新
				</button>
			</div>
		</#if>
	</script>

	<!--操作-->
	<script id="dataDicListBar" type="text/html">
		<#if optValues?seq_contains("504002")>
			<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
		</#if>
		<#if optValues?seq_contains("504003")>
			<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
		</#if>
	</script>
</form>
<script type="text/javascript" src="${ctx}/js/dataDic/dataDic.js"></script>

</body>
</html>