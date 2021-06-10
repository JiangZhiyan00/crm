<!DOCTYPE html>
<html>
<head>
    <#include "*/common.ftl">
    <title>添加/更新客户开发计划</title>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="id" type="hidden" value="${(cusDevPlan.id)!}"/>
    <input name="saleChanceId" type="hidden" value="${saleChanceId!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">计划项内容</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="planItem" id="planItem" lay-reqtext="计划项内容不能为空!"
                   value="${(cusDevPlan.planItem)!}" placeholder="请输入计划项内容">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">计划时间</label>
        <div class="layui-input-block">
            <#if (cusDevPlan.planDate)??>
                <input type="text" class="layui-input userName"
                   lay-verify="required" name="planDate" id="planDate" lay-reqtext="计划时间不能为空!"
                       value="${(cusDevPlan.planDate)?string("yyyy-MM-dd")}" placeholder="请选择/输入计划时间">
                <#else>
                <input type="text" class="layui-input userName" lay-reqtext="计划时间不能为空!"
                   lay-verify="required" name="planDate" id="planDate"  placeholder="请选择/输入计划时间">
            </#if>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">执行效果</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail"
                   name="exeAffect" value="${(cusDevPlan.exeAffect)!}"
                   id="exeAffect" placeholder="请输入执行效果">
        </div>
    </div>


    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block" style="float: left">
            <button class="layui-btn" lay-submit lay-filter="addOrUpdateCusDevPlan">
                <i class="layui-icon">&#xe605;</i>提交
            </button>
        </div>
        <div class="layui-input-block" style="float: right">
            <button class="layui-btn layui-btn-normal" id="cancel">
                <i class="layui-icon">&#x1006;</i>取消
            </button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/cusDevPlan/add.update.js"></script>
<script>
    layui.use('laydate', function(){
        let laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#planDate',//指定元素
            type:"datetime",
            format: 'yyyy-MM-dd HH:mm' //可任意组合
        });
    });
</script>
</body>
</html>