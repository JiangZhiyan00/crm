<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${ctx}/js/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx}/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/zTree_v3/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${ctx}/js/zTree_v3/js/jquery.ztree.excheck.js"></script>
	<#include "*/common.ftl">
</head>
<body>
<input type="hidden" name="roleId" value="${roleId!}"/>

<div id="moduleTree" class="ztree"></div>

<br><br><br><br><br><br><br><br><br><br><br>
<div>
	<button type="button" class="layui-btn layui-btn-fluid" id="addPermission">
		<i class="layui-icon">&#xe605;</i>提交
	</button>
	<br><br>
	<button type="button" class="layui-btn layui-btn-primary layui-btn-fluid" id="cancel">
		<i class="layui-icon">&#x1006;</i>取消
	</button>
</div>

<script type="text/javascript" src="${ctx}/js/role/grant.js"></script>
</body>
</html>