<!DOCTYPE html>
<html>
<head>
    <#include "*/common.ftl">
</head>
<body class="childrenBody">
<div align="justify">
    <form class="layui-form" style="width:95%;">
        <input type="hidden" name="id"  value="${(customerOrder.id)!}">
        <input type="hidden" name="cusId"  value="${(cusId)!}">

        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">
                <span style="color: red">*</span>下单日期</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input userName" lay-verify="required"
                       lay-reqtext="下单日期不能为空!" name="orderDate" id="orderDate"
                        <#if (customerOrder.orderDate)??>
                            value="${(customerOrder.orderDate)?string("yyyy-MM-dd")}"
                        </#if>
                       placeholder="请填写下单日期"
                        <#if (customerOrder.id)??>disabled</#if>>
            </div>
        </div>

        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">
                <span style="color: red">*</span>要求交付</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input userName" lay-verify="required"
                       lay-reqtext="要求交付日期不能为空!" name="requiredDate" id="requiredDate"
                        <#if (customerOrder.requiredDate)??>
                            value="${(customerOrder.requiredDate)?string("yyyy-MM-dd")}"
                        </#if>
                       placeholder="请填写要求交付日期"
                        <#if (customerOrder.id)??>disabled</#if>>
            </div>
        </div>

        <#if (customerOrder.id)??>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">实际交付</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userName" name="actualDate" id="actualDate"
                            <#if (customerOrder.actualDate)??>
                                value="${(customerOrder.actualDate)?string("yyyy-MM-dd")}"
                            </#if>
                           placeholder="请填写实际交付日期">
                </div>
            </div>
        </#if>

        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">
                <span style="color: red">*</span>收货地址</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" lay-reqtext="收货地址不能为空!"
                       name="address"  lay-verify="required"
                       placeholder="请输入收货地址" value="${(customerOrder.address)!}">
            </div>
        </div>

        <br/>
        <div class="layui-form-item layui-row layui-col-xs12">
            <div class="layui-input-block" style="float: left">
                <button class="layui-btn layui-btn-lg" lay-submit lay-filter="addOrUpdateCustomerOrder">
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
<script type="text/javascript" src="${ctx}/js/customerOrder/addOrUpdate.js"></script>
<script>
    layui.use('laydate', function(){
        let laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#orderDate',//指定元素
            format: 'yyyy-MM-dd', //可任意组合
            min: '1990-1-1',
            max: 0
        })

        laydate.render({
            elem: '#requiredDate',//指定元素
            format: 'yyyy-MM-dd', //可任意组合
            min: '1990-1-1'
        })

        laydate.render({
            elem: '#actualDate',//指定元素
            format: 'yyyy-MM-dd', //可任意组合
            min: '1990-1-1',
            max: 0
        })
    });
</script>
</body>
</html>