<!DOCTYPE html>
<html>
<head>
    <title>订单详情查看</title>
    <#include "*/../common.ftl">
</head>
<body class="childrenBody">
<div class="layui-col-md12">
    <div class="layui-card">
        <div class="layui-card-body">
            <form class="layui-form" >
                <input name="orderId" type="hidden" value="${(order.id)!}"/>
                <div class="layui-form-item layui-row">
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">订单编号</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input"
                                   name="orderNo" id="orderNo"  value="${(order.orderNo)!}" disabled>
                        </div>
                    </div>
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">下单日期</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input"
                                   name="orderDate" id="orderDate" value="${(order.orderDate)?string("yyyy-MM-dd")}" disabled>
                        </div>
                    </div>
                </div>

                <div class="layui-form-item layui-row">
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">要求交付</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input"
                                   name="requiredDate"  value="${(order.requiredDate)?string("yyyy-MM-dd")}" disabled>
                        </div>
                    </div>
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">收货地址</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input"
                                    name="address" value="${(order.address)!}" disabled>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="layui-col-md12">
    <table id="orderDetailList" class="layui-table"  lay-filter="orderDetails"></table>
</div>

<!--数据表头部左侧工具栏-->
<#if !order.actualDate??>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-group">
            <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="add">
                <i class="layui-icon">&#xe654;</i>添加
            </button>
            <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="del">
                <i class="layui-icon">&#xe640;</i>删除
            </button>
            <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="refresh">
                <i class="layui-icon">&#xe669;</i>刷新
            </button>
        </div>
    </script>
</#if>

<!--数据表"操作"列按钮-->
<#if !order.actualDate??>
    <script id="orderDetailsListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>
</#if>
<#if order.actualDate??>
    <script id="orderDetailsListBar" type="text/html">
        <div>/</div>
    </script>
</#if>

<script type="text/javascript" src="${ctx}/js/customer_order_details/customer.order.details.js"></script>
</body>
</html>