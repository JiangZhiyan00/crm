<!DOCTYPE html>
<html>
<head>
    <#include "*/common.ftl">
</head>
<body class="childrenBody">
<center>
    <form class="layui-form" style="width:90%;">
        <input name="id" type="hidden" value="${(customer.id)!}"/>
        <div class="layui-form-item layui-row">
            <div class="layui-col-md4">
                <label class="layui-form-label">
                    <span style="color: red">*</span>客户名称</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                           name="name" id="name"  lay-verify="required" lay-reqtext="客户名称不能为空" value="${(customer.name)!}" placeholder="请输入客户名称">
                </div>
            </div>
            <div class="layui-col-md4">
                <label class="layui-form-label">
                    <span style="color: red">*</span>法人</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                           name="legalPerson" id="legalPerson" lay-verify="required" lay-reqtext="法人不能为空" value="${(customer.legalPerson)!}" placeholder="请输入法人">
                </div>
            </div>
            <div class="layui-col-md4">
                <label class="layui-form-label">客户所属地</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                           name="area" value="${(customer.area)!}" placeholder="请输入客户所属地">
                </div>
            </div>
        </div>

        <div class="layui-form-item layui-row">
            <div class="layui-col-md4">
                <label class="layui-form-label">客户经理</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                           name="cusManager" value="${(customer.cusManager)!}" placeholder="请输入客户经理">
                </div>
            </div>
            <div class="layui-col-md4">
                <label class="layui-form-label">客户级别</label>
                <div class="layui-input-block">
                    <select name="level"  id="level">
                        <option value="" >请选择客户级别</option>
                        <option value="普通客户" <#if customer?? && customer.level=="普通客户">selected="selected"</#if>>普通客户</option>
                        <option value="重点开发客户" <#if customer?? && customer.level=="重点开发客户">selected="selected"</#if>>重点开发客户</option>
                        <option value="大客户" <#if customer?? && customer.level=="大客户">selected="selected"</#if>>大客户</option>
                        <option value="合作伙伴" <#if customer?? && customer.level=="合作伙伴">selected="selected"</#if>>合作伙伴</option>
                        <option value="战略合作伙伴" <#if customer?? && customer.level=="战略合作伙伴">selected="selected"</#if>>战略合作伙伴</option>
                    </select>
                </div>
            </div>
            <div class="layui-col-md4">
                <label class="layui-form-label">信用度</label>
                <div class="layui-input-block">
                    <select name="reputation"  id="reputation">
                        <option value="">请选择客户信用度</option>
                        <option value="☆" <#if customer?? && customer.reputation=="☆">selected="selected"</#if>>☆</option>
                        <option value="☆☆" <#if customer?? && customer.reputation=="☆☆">selected="selected"</#if>>☆☆</option>
                        <option value="☆☆☆" <#if customer?? && customer.reputation=="☆☆☆">selected="selected"</#if>>☆☆☆</option>
                        <option value="☆☆☆☆" <#if customer?? && customer.reputation=="☆☆☆☆">selected="selected"</#if>>☆☆☆☆</option>
                        <option value="☆☆☆☆☆" <#if customer?? && customer.reputation=="☆☆☆☆☆">selected="selected"</#if>>☆☆☆☆☆</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="layui-form-item layui-row">
            <div class="layui-col-md4">
                <label class="layui-form-label">邮编</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                           name="postCode" value="${(customer.postCode)!}"  placeholder="请输入客户邮编">
                </div>
            </div>
            <div class="layui-col-md4">
                <label class="layui-form-label">
                    <span style="color: red">*</span>联系电话</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                           name="phone" lay-verify="required" lay-reqtext="联系电话不能为空" value="${(customer.phone)!}"  placeholder="请输入客户联系电话">
                </div>
            </div>
            <div class="layui-col-md4">
                <label class="layui-form-label">客户地址</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                           name="address" value="${(customer.address)!}"  placeholder="请输入客户地址">
                </div>
            </div>
        </div>

        <div class="layui-form-item layui-row">
            <div class="layui-col-md4">
                <label class="layui-form-label">传真</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                           name="fax" value="${(customer.fax)!}"  placeholder="请输入客户传真">
                </div>
            </div>
            <div class="layui-col-md4">
                <label class="layui-form-label">客户网站</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                           name="webSite" value="${(customer.webSite)!}"  placeholder="请输入客户网站地址">
                </div>
            </div>
            <div class="layui-col-md4">
                <label class="layui-form-label">注册资金</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                           name="registeredCapital" value="${(customer.registeredCapital)!}"  placeholder="请输入客户注册资金(元)">
                </div>
            </div>
        </div>

        <div class="layui-form-item layui-row">
            <div class="layui-col-md4">
                <label class="layui-form-label">开户银行</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                           name="bank" value="${(customer.bank)!}"  placeholder="请输入客户开户银行">
                </div>
            </div>
            <div class="layui-col-md4">
                <label class="layui-form-label">银行账户</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                           name="accountNumber" value="${(customer.accountNumber)!}"  placeholder="请输入客户银行账户">
                </div>
            </div>
            <div class="layui-col-md4">
                <label class="layui-form-label">客户国税</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="nationalTaxNum" value="${(customer.nationalTaxNum)!}" placeholder="请输入客户国税">
                </div>
            </div>
        </div>

        <div class="layui-form-item layui-row">
            <div class="layui-col-md4">
                <label class="layui-form-label">客户地税</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                           name="localTaxNum" value="${(customer.localTaxNum)!}"  placeholder="请输入客户地税">
                </div>
            </div>
            <div class="layui-col-md4">
                <label class="layui-form-label">年营业额</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input"
                           name="turnover" value="${(customer.turnover)!}"  placeholder="请输入客户年营业额">
                </div>
            </div>
        </div>

        <br/>
        <div class="layui-form-item layui-row layui-col-xs12">
            <div class="layui-input-block" style="float: left">
                <button class="layui-btn layui-btn-lg" lay-submit lay-filter="addOrUpdateCustomer">
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
</center>
<script type="text/javascript" src="${ctx}/js/customer/add.update.js"></script>
</body>
</html>