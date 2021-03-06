<!DOCTYPE html>
<html>
<head>
	<title>用户管理</title>
	<#include "*/common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >
	<blockquote class="layui-elem-quote quoteBox">
		<#if optValues?seq_contains("501002")>
			<form class="layui-form">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="text" name="trueName" class="layui-input
						searchVal" placeholder="真实姓名" />
					</div>
					<div class="layui-input-inline">
						<input type="text" name="email" class="layui-input
						searchVal" placeholder="邮箱" />
					</div>
					<div class="layui-input-inline">
						<input type="text" name="phone" class="layui-input
						searchVal" placeholder="手机号" />
					</div>
					<a class="layui-btn search_btn" data-type="reload"><i
								class="layui-icon">&#xe615;</i> 搜索</a>
				</div>
			</form>
		</#if>
	</blockquote>

	<!--用户信息表-->
	<table id="userList" class="layui-table"  lay-filter="users"></table>

	<!--头部工具栏-->
	<script type="text/html" id="toolbarDemo">
		<div class="layui-btn-group">
			<#if optValues?seq_contains("501001")>
				<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="add">
					<i class="layui-icon">&#xe654;</i>添加用户
				</button>
			</#if>
			<#if optValues?seq_contains("501004")>
				<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="del">
					<i class="layui-icon">&#xe640;</i>删除用户
				</button>
			</#if>
			<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="refresh">
				<i class="layui-icon">&#xe669;</i>刷新
			</button>
		</div>
	</script>

	<!--操作-->
	<script id="userListBar" type="text/html">
		<#if optValues?seq_contains("501003")>
			<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
		</#if>
		<#if optValues?seq_contains("501004")>
			<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
		</#if>
	</script>
</form>
<script type="text/javascript" src="${ctx}/js/user/user.js"></script>

</body>
</html>