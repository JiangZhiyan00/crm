<!DOCTYPE html>
<html>
<head>
    <#include "*/common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input type="hidden" name="id"  value="${(saleChance.id)!}">
    <input type="hidden" name="createMan"  value="${(saleChance.createMan)!}">
    <input type="hidden" id="assignManId"  value="${(saleChance.assignMan)!}">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">客户名称</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="required" lay-reqtext="客户名称不能为空" name="customerName" id="customerName"  value="${(saleChance.customerName)!}"  placeholder="请输入客户名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">机会来源</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="chanceSource" id="chanceSource" placeholder="请输入机会来源" value="${(saleChance.chanceSource)!}">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">联系人</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="linkMan"  lay-verify="required" lay-reqtext="联系人不能为空"
                   placeholder="请输入联系人" value="${(saleChance.linkMan)!}">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   lay-verify="phone" name="linkPhone" id="phone" placeholder="请输入联系电话" value="${(saleChance.linkPhone)!}">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">概要</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="overview"  id="phone" placeholder="请输入概要" value="${(saleChance.overview)!}">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">成功几率(%)</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"
                   name="successPossibility" placeholder="请输入成功几率" value="${(saleChance.successPossibility)!}">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">机会描述</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入机会描述信息" name="description" class="layui-textarea">${(saleChance.description)!}</textarea>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">指派给</label>
        <div class="layui-input-block">
            <select name="assignMan" id="assignManSelect">
                <option value="" >无</option>
            </select>
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block" style="float: left">
            <button class="layui-btn layui-btn-lg" lay-submit lay-filter="addOrUpdateSaleChance">
                <i class="layui-icon">&#xe605;</i>提交
            </button>
        </div>
        <div class="layui-input-block" style="float: right">
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="cancel">
                <i class="layui-icon">&#x1006;</i>取消
            </button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/saleChance/add.update.js"></script>
</body>
</html>