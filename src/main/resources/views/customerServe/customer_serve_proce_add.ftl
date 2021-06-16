<!DOCTYPE html>
<html>
<head>
    <#include "*/common.ftl">
</head>
<body class="childrenBody">
<div align="justify">
    <form class="layui-form" style="width:80%;">
        <input name="id" type="hidden" value="${customerServe.id}"/>
        <input name="state" type="hidden" value="fw_003"/>
        <div class="layui-form-item layui-row">
            <div class="layui-col-xs6">
                <label class="layui-form-label">服务类型</label>
                <div class="layui-input-block">
                    <select id="serveType" disabled>
                        <#if customerServe.serveType == "6"><option value="6" selected>咨询</option></#if>
                        <#if customerServe.serveType == "7"><option value="7" selected>建议</option></#if>
                        <#if customerServe.serveType == "8"><option value="8" selected>投诉</option></#if>
                    </select>
                </div>
            </div>
            <div class="layui-col-xs6">
                <label class="layui-form-label">客户名</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                           id="cusName"  value="${(customerServe.cusName)!}" disabled>
                </div>
            </div>
        </div>

        <div class="layui-form-item layui-row">
            <label class="layui-form-label">服务内容</label>
            <div class="layui-input-block">
                <textarea class="layui-textarea" disabled>${(customerServe.serviceRequest)!}</textarea>
            </div>
        </div>

        <div class="layui-form-item layui-row">
            <label class="layui-form-label">服务概要</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input"
                       id="overview"  value="${(customerServe.overview)!}" disabled>
            </div>
        </div>


        <div class="layui-form-item layui-row">
            <div class="layui-col-xs6">
                <label class="layui-form-label">分配给</label>
                <div class="layui-input-block">
                    <div id="allCustomerManagers" class="xm-select-demo"></div>
                </div>
            </div>
            <div class="layui-col-xs6">
                <label class="layui-form-label">分配时间</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                           id="assignTime"  value="${(customerServe.assignTime?string("yyyy-MM-dd HH:mm"))}" disabled>
                </div>
            </div>
        </div>

        <div class="layui-form-item layui-row">
            <label class="layui-form-label">
                <span style="color: red">*</span>处理措施</label>
            <div class="layui-input-block">
                <textarea  name="serviceProce" class="layui-textarea" lay-verify="required"
                           lay-reqtext="处理措施不能为空!"></textarea>
            </div>
        </div>

        <br/>
        <div class="layui-form-item layui-row layui-col-xs12">
            <div class="layui-input-block" style="float: left">
                <button class="layui-btn layui-btn-lg" lay-submit lay-filter="proce">
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
<script type="text/javascript" src="${ctx}/js/customerServe/customer.serve.proce.add.js"></script>
</body>
</html>