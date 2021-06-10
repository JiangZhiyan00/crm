<!DOCTYPE html>
<html>
<head>
	<title>客户管理</title>
	<#include "*/common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
	<blockquote class="layui-elem-quote quoteBox">
		<#if optValues?seq_contains("201004")>
			<form class="layui-form">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="text" name="customerId" class="layui-input
						searchVal" placeholder="客户编号" />
					</div>
					<div class="layui-input-inline">
						<input type="text" name="name"
							   class="layui-input
						searchVal" placeholder="客户名" />
					</div>
					<div class="layui-input-inline">
						<select name="level" id="level">
							<option value="">客户级别</option>
							<option value="战略合作伙伴">战略合作伙伴</option>
							<option value="大客户">大客户</option>
							<option value="重点开发客户">重点开发客户</option>
							<option value="普通客户">普通客户</option>
						</select>
					</div>
					<a class="layui-btn search_btn" data-type="reload"><i
								class="layui-icon">&#xe615;</i> 搜索</a>
				</div>
			</form>
		</#if>
	</blockquote>
	<table id="customerList" class="layui-table"  lay-filter="customers"></table>


	<script type="text/html" id="toolbarDemo">
		<#if optValues??>
			<div class="layui-btn-group">
				<#if optValues?seq_contains("201001")>
					<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="add">
						<i class="layui-icon">&#xe654;</i>添加客户
					</button>
				</#if>
				<#if optValues?seq_contains("201003")>
					<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="del">
						<i class="layui-icon">&#xe640;</i>删除客户
					</button>
				</#if>
					<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="link">
						<i class="layui-icon">&#xe613;</i>客户联系人管理
					</button>
					<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="contact">
						<i class="layui-icon">&#xe60e;</i>交流记录
					</button>
					<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="order">
						<i class="layui-icon">&#xe65e;</i>查看订单
					</button>
				<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="refresh">
					<i class="layui-icon">&#xe669;</i>刷新
				</button>
			</div>
		</#if>
	</script>
	<!--操作-->
	<script id="customerListBar" type="text/html">
		<#if optValues?seq_contains("201002")>
		<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
		</#if>
		<#if optValues?seq_contains("201003")>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
		</#if>
	</script>


</form>
<script type="text/javascript" src="${ctx}/js/customer/customer.js"></script>

</body>
</html>