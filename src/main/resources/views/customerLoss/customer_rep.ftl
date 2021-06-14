<!DOCTYPE html>
<html>
<head>
	<title>暂缓管理</title>
	<#include "*/common.ftl">
</head>
<body class="childrenBody">
	<div class="layui-col-md12">
		<div class="layui-card">
			<div class="layui-card-body">
				<form class="layui-form" >
					<input name="lossId" type="hidden" value="${(customerLoss.id)!}"/>
					<div class="layui-form-item layui-row">
						<div class="layui-col-xs6">
							<label class="layui-form-label">客户名称</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="customerName" id="customerName"  value="${(customerLoss.cusName)!}" readonly="readonly">
							</div>
						</div>
						<div class="layui-col-xs6">
							<label class="layui-form-label">客户编号</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="chanceSource" id="chanceSource" value="${(customerLoss.cusNo)!}" readonly="readonly">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="layui-col-md12">
		<table id="customerRepList" class="layui-table"  lay-filter="customerReps"></table>
	</div>



	<script type="text/html" id="toolbarDemo">
		<div class="layui-btn-group">
			<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="add">
				<i class="layui-icon">&#xe654;</i>添加措施
			</button>
			<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="del">
				<i class="layui-icon">&#xe640;</i>删除措施
			</button>
			<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="refresh">
				<i class="layui-icon">&#xe669;</i>刷新
			</button>
		</div>
	</script>

	<!--行操作-->
	<script id="customerRepListBar" type="text/html">
		<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>



	<script type="text/javascript" src="${ctx}/js/customerLoss/customer.rep.js"></script>
</body>
</html>