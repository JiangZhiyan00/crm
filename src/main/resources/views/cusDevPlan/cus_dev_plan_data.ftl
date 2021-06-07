<!DOCTYPE html>
<html>
<head>
	<title>客户开发计划管理</title>
	<#include "*/common.ftl">
</head>
<body class="childrenBody">
	<div class="layui-col-md12">
		<div class="layui-card">
			<div class="layui-card-body">
				<form class="layui-form" >
					<input name="saleChanceId" type="hidden" value="${(saleChance.id)!}"/>
					<div class="layui-form-item layui-row">
						<div class="layui-col-xs6">
							<label class="layui-form-label">客户名称</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="customerName" id="customerName"  value="${(saleChance.customerName)!}" disabled>
							</div>
						</div>
						<div class="layui-col-xs6">
							<label class="layui-form-label">机会来源</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="chanceSource" id="chanceSource" value="${(saleChance.chanceSource)!}" disabled>
							</div>
						</div>
					</div>

					<div class="layui-form-item layui-row">
						<div class="layui-col-xs6">
							<label class="layui-form-label">联系人</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="linkMan"  lay-verify="required"  value="${(saleChance.linkMan)!}" disabled>
							</div>
						</div>
						<div class="layui-col-xs6">
							<label class="layui-form-label">联系电话</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   lay-verify="phone" name="linkPhone" value="${(saleChance.linkPhone)!}" id="phone" disabled>
							</div>
						</div>
					</div>

					<div class="layui-form-item layui-row">
						<div class="layui-col-xs6">
							<label class="layui-form-label">概要</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="overview" value="${(saleChance.overview)!}" id="overview" disabled>
							</div>
						</div>
						<div class="layui-col-xs6">
							<label class="layui-form-label">成功几率</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="successPossibility" value="${(saleChance.successPossibility)!}%" disabled>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="layui-col-md12">
		<!--devPlan数据表-->
		<table id="cusDevPlanList" class="layui-table"  lay-filter="cusDevPlans"></table>
	</div>

	<#if saleChance.devResult==0 || saleChance.devResult==1>
		<!--数据表头部左侧工具栏-->
		<script type="text/html" id="toolbarDemo">
			<div class="layui-btn-group">
				<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="add">
					<span style="color: #0000FF"><b><i class="layui-icon">&#xe61f;</i>添加计划项</b></span>
				</button>
				<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="success">
					<span style="color: #00FF00"><b><i class="layui-icon">&#xe6af;</i>设置为开发成功</b></span>
				</button>
				<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="failed">
					<span style="color: red"><b><i class="layui-icon">&#xe69c;</i>设置为开发失败</b></span>
				</button>
				<button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="refresh">
					<b><i class="layui-icon">&#xe669;</i>刷新</b>
				</button>
			</div>
		</script>

		<!--操作-->
		<script id="cusDevPlanListBar" type="text/html">
			<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
			<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
		</script>
	</#if>

	<#if saleChance.devResult==2 || saleChance.devResult==3>
		<!--操作-->
		<script id="cusDevPlanListBar" type="text/html">
			<div style="color: grey">/</div>
		</script>
	</#if>

	<script type="text/javascript" src="${ctx}/js/cusDevPlan/cus.dev.plan.data.js"></script>
</body>
</html>