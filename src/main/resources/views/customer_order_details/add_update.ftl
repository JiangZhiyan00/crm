<!DOCTYPE html>
<html>
<head>
    <#include "*/common.ftl">
</head>
<body class="childrenBody">
<div align="justify">
    <form class="layui-form" style="width:95%;">
        <input type="hidden" name="id"  value="${(orderDetails.id)!}">
        <input type="hidden" name="orderId"  value="${(orderId)!}">

        <div class="layui-form-item layui-row">
            <div class="layui-col-xs6">
                <label class="layui-form-label">
                    <span style="color: red">*</span>商品名称</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" lay-reqtext="商品名称不能为空!"
                           name="goodsName"  lay-verify="required" placeholder="请输入商品名称"
                           value="${(orderDetails.goodsName)!}" <#if orderDetails.goodsName??>disabled</#if>>
                </div>
            </div>
            <div class="layui-col-xs6">
                <label class="layui-form-label">
                    <span style="color: red">*</span>商品单价</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" lay-reqtext="商品单价不能为空!"
                           name="price" lay-verify="number" placeholder="请输入商品单价"
                           value="${(orderDetails.price?c)!}" <#if orderDetails.price??>disabled</#if>>
                </div>
            </div>
        </div>

        <div class="layui-form-item layui-row">
            <div class="layui-col-xs6">
                <label class="layui-form-label">
                    <span style="color: red">*</span>订购数量</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" lay-reqtext="订购数量不能为空!"
                           name="goodsNum"  lay-verify="number" placeholder="请输入订购数量"
                           value="${(orderDetails.goodsNum)!}">
                </div>
            </div>
            <div class="layui-col-xs6">
                <label class="layui-form-label">
                    <span style="color: red">*</span>数量单位</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" lay-reqtext="数量单位不能为空!"
                           name="unit" lay-verify="required" placeholder="请输入数量单位"
                           value="${(orderDetails.unit)!}" <#if orderDetails.unit??>disabled</#if>>
                </div>
            </div>
        </div>

        <br/>
        <div class="layui-form-item layui-row layui-col-xs12">
            <div class="layui-input-block" style="float: left">
                <button class="layui-btn layui-btn-lg" lay-submit lay-filter="addOrUpdateOrderDetails">
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
</div>
<script type="text/javascript" src="${ctx}/js/customer_order_details/addOrUpdate.js"></script>
</body>
</html>