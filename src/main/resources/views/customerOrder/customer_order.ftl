<!DOCTYPE html>
<html>
<head>
	<title>客户订单查看</title>
	<#include "*/common.ftl">
</head>
<body class="childrenBody">
	<div class="layui-col-md12">
		<div class="layui-card">
			<div class="layui-card-body">
				<form class="layui-form" >
					<input name="id" type="hidden" value="${(customer.id)!}"/>
					<div class="layui-form-item layui-row">
						<div class="layui-col-xs6">
							<label class="layui-form-label">客户名称</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="name" id="name"  value="${(customer.name)!}" disabled>
							</div>
						</div>
						<div class="layui-col-xs6">
							<label class="layui-form-label">客户地址</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="address" value="${(customer.address)!}" disabled>
							</div>
						</div>
					</div>

					<div class="layui-form-item layui-row">
						<div class="layui-col-xs6">
							<label class="layui-form-label">客户联系人</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="linkName" id="linkName" value="${(customerLinkMan.linkName)!}" disabled>
							</div>
						</div>
						<div class="layui-col-xs6">
							<label class="layui-form-label">联系电话</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									    name="phone" value="${(customerLinkMan.phone)!}" id="phone" disabled>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="layui-col-md12">
		<table id="customerOrderList" class="layui-table"  lay-filter="customerOrders"></table>
	</div>

	<script type="text/html" id="toolbarDemo">
		<div class="layui-btn-group">
			<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="add">
				<i class="layui-icon">&#xe654;</i>添加订单
			</button>
			<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="del">
				<i class="layui-icon">&#xe640;</i>删除订单
			</button>
			<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="refresh">
				<i class="layui-icon">&#xe669;</i>刷新
			</button>
		</div>
	</script>

	<!--操作-->
	<script id="customerOrderListBar" type="text/html">
		<a class="layui-btn layui-btn-xs layui-btn-normal" id="details" lay-event="details">详情</a>
		{{#  if(d.actualDate == null){ }}
			<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
		{{#  } else { }}
			<a class="layui-btn layui-btn-xs layui-btn-disabled">编辑</a>
		{{# } }}
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>

	<script type="text/javascript" src="${ctx}/js/customerOrder/customer.order.js"></script>
</body>
</html>