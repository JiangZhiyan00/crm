<!DOCTYPE html>
<html>
<head>
    <#include "*/common.ftl">
</head>
<body class="childrenBody">
<div align="justify">
    <form class="layui-form" style="width:80%;">
        <input name="state" type="hidden" value="fw_001"/>
        <input name="id" type="hidden" value="${(customerServe.id)!}">
        <div class="layui-form-item layui-row">
            <div class="layui-col-xs6">
                <label class="layui-form-label">
                    <span style="color: red">*</span>服务类型</label>
                <div class="layui-input-block">
                    <select name="serveType"  id="serveType" lay-verify="required"
                            lay-reqtext="服务类型不能为空!">
                        <option value="" >请选择</option>
                        <option value="6" <#if customerServe.serveType == 6>selected</#if>>咨询</option>
                        <option value="7" <#if customerServe.serveType == 7>selected</#if>>建议</option>
                        <option value="8" <#if customerServe.serveType == 8>selected</#if>>投诉</option>
                    </select>
                </div>
            </div>
            <div class="layui-col-xs6">
                <label class="layui-form-label">
                    <span style="color: red">*</span>客户</label>
                <div class="layui-input-block">
                    <div id="allCustomers" class="xm-select-demo"></div>
                </div>
            </div>
        </div>

        <div class="layui-form-item layui-row">
            <label class="layui-form-label">
                <span style="color: red">*</span>服务内容</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入服务内容" name="serviceRequest" lay-verify="required"
                          lay-reqtext="服务内容不能为空!" class="layui-textarea">${(customerServe.serviceRequest)!}</textarea>
            </div>
        </div>

        <div class="layui-form-item layui-row">
            <label class="layui-form-label">服务概要</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                           name="overview" id="overview" placeholder="请输入服务概要" value="${(customerServe.overview)!}">
                </div>
            </div>

        <br/>
        <div class="layui-form-item layui-row layui-col-xs12">
            <div class="layui-input-block" style="float: left">
                <button class="layui-btn layui-btn-lg" lay-submit lay-filter="addOrUpdateCustomerServe">
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
<script type="text/javascript" src="${ctx}/js/customerServe/customer.serve.add.update.js"></script>
</body>
</html>