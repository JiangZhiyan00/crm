<!DOCTYPE html>
<html>
<head>
	<title>角色管理</title>
	<#include "*/common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
	<blockquote class="layui-elem-quote quoteBox">
		<#if optValues?seq_contains("502002")>
			<form class="layui-form">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="text" name="roleName"
							   class="layui-input
						searchVal" placeholder="职位名称" />
					</div>
					<a class="layui-btn search_btn" data-type="reload"><i
								class="layui-icon">&#xe615;</i> 搜索</a>
				</div>
			</form>
		</#if>
	</blockquote>
	<table id="roleList" class="layui-table"  lay-filter="roles"></table>

	<script type="text/html" id="toolbarDemo">
			<div class="layui-btn-group">
				<#if optValues?seq_contains("502001")>
					<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="add">
						<i class="layui-icon">&#xe654;</i>添加职位
					</button>
				</#if>
				<#if optValues?seq_contains("502004")>
					<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="del">
						<i class="layui-icon">&#xe640;</i>删除职位
					</button>
				</#if>
				<#if optValues?seq_contains("502005")>
					<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="grant">
						<i class="layui-icon">&#xe672;</i>授权
					</button>
				</#if>
				<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="refresh">
					<i class="layui-icon">&#xe669;</i>刷新
				</button>
			</div>
	</script>
	<!--操作-->
	<script id="roleListBar" type="text/html">
		<#if optValues?seq_contains("502003")>
			<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
		</#if>
		<#if optValues?seq_contains("502004")>
			<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
		</#if>
	</script>
</form>
<script type="text/javascript" src="${ctx}/js/role/role.js"></script>

</body>
</html>