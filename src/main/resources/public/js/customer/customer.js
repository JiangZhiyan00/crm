layui.use(['table','layer',"form"],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        form = layui.form;

    //客户列表展示
    let tableIns = table.render({
        elem: '#customerList',
        url : ctx+'/customer/list',
        cellMinWidth : 80,
        page : true,
        height : "full-120",
        limits : [10,20,30,40],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "customerListTable",
        cols : [[
            {type: "checkbox", fixed:"left",align:"center"},
            {field: "id", title:'编号',align:"center",hide:true},
            {field: 'customerId', title: '客户编号', align:'center'},
            {field: 'name', title: '客户名',align:"center"},
            {field: 'legalPerson', title: '法人',  align:'center'},
            {field: 'area', title: '地区', align:'center'},
            {field: 'cusManager', title: '客户经理',  align:'center'},
            {field: 'satisfaction', title: '满意度', align:'center'},
            {field: 'level', title: '客户级别', align:'center'},
            {field: 'reputation', title: '信用度', align:'center'},
            {field: 'address', title: '详细地址', align:'center'},
            {field: 'postCode', title: '邮编', align:'center'},
            {field: 'phone', title: '电话', align:'center'},
            {field: 'webSite', title: '网站', align:'center'},
            {field: 'fax', title: '传真', align:'center'},
            {field: 'registeredCapital', title: '注册资金', align:'center'},
            {field: 'businessLicense', title: '营业执照', align:'center'},
            {field: 'bank', title: '开户银行', align:'center'},
            {field: 'accountNumber', title: '开户账号', align:'center'},
            {field: 'nationalTaxNum', title: '国税', align:'center'},
            {field: 'localTaxNum', title: '地税', align:'center'},
            {field: 'createDate', title: '创建时间', align:'center'},
            {field: 'updateDate', title: '更新时间', align:'center'},
            {title: '操作', templet:'#customerListBar',fixed:"right",align:"center", minWidth:112}
        ]]
    });


    // 多条件搜索
    $(".search_btn").on("click",function () {
        table.reload("customerListTable",{
            page:{
                curr:1
            },
            where:{
                Name:$("input[name='name']").val(),// 客户名
                customerId:$("input[name='customerId']").val(),// 客户编号
                level:$("#level").val() //客户级别
            }
        })
    });


    // 头工具栏事件
    table.on('toolbar(customers)',function (obj) {
        if (obj.event === "add"){
            openAddOrUpdateCustomerDialog();
        }else if (obj.event === "del") {
            delCustomers(table.checkStatus(obj.config.id).data);
        }else if (obj.event === "link") {
            openLinkManageDialog(table.checkStatus(obj.config.id).data);
        }else if (obj.event === "contact") {
            openCustomerContactDialog(table.checkStatus(obj.config.id).data);
        }else if (obj.event === "order") {
            openOrderInfoDialog(table.checkStatus(obj.config.id).data);
        }else if (obj.event === "refresh") {
            table.reload("customerListTable",{
                page:{curr:1},
                where:{
                    customerId:'',
                    name:'',
                    level:''
                }
            })
        }
    });

    /**
     * 批量删除客户信息
     * @param datas 选中的客户信息记录
     */
    function delCustomers(datas){
        if(datas.length < 1){
            layer.msg("<div align='center' style='color: red'><b><h3>请先选择要删除的数据!</h3></b></div>",
                {icon: 2});
            return;
        }
        layer.confirm("<div align='center'><b><h3>确定删除选中的记录?</h3></b></div>",
            {icon: 3, title: "提示"},
            function (index) {
                layer.close(index);
                let ids="ids=";
                for(let i=0;i<datas.length;i++){
                    if(i<datas.length-1){
                        ids=ids+datas[i].id+"&ids=";
                    }else{
                        ids=ids+datas[i].id;
                    }
                }

            $.ajax({
                type:"post",
                url:ctx+"/customer/delete",
                data:ids,
                dataType:"json",
                success:function (data) {
                    if(data.code===200){
                        layer.msg("<div align='center' style='color: #00B83F'><b><h3>删除成功!</h3></b></div>",
                            {icon: 6})
                        tableIns.reload();
                    }else{
                        layer.msg("<div align='center' style='color: red'><b><h3>"+data.msg+"</h3></b></div>",
                            {icon: 5});
                    }
                }
            })
        })
    }

    /**
     * 行工具栏操作
     */
    table.on('tool(customers)',function (obj) {
        let layEvent =obj.event;
        if(layEvent === "edit"){
            openAddOrUpdateCustomerDialog(obj.data.id);
        }else if(layEvent === "del"){
            layer.confirm("<div align='center'><b><h3>确定删除此条记录吗?</h3></b></div>",
                {icon: 3, title: "提示"},
            function (index) {
                layer.close(index);
                $.post(ctx+"/customer/delete",{ids:obj.data.id},function (data) {
                    if(data.code===200){
                        layer.msg("<div align='center' style='color: #00B83F'><b><h3>删除成功!</h3></b></div>",
                            {icon: 6})
                        tableIns.reload();
                    }else{
                        layer.msg("<div align='center' style='color: red '><b><h3>"+data.msg+"</h3></b></div>",
                            {icon: 5});
                    }
                })
            })
        }
    });


    function openLinkManageDialog(data) {
        if(data.length===0){
            layer.msg("请选择需要查看的客户!");
            return;
        }
        if(data.length>1){
            layer.msg("暂不支持批量查看!");
            return;
        }
        let title="客户管理-联系人管理";
        layui.layer.open({
            title:title,
            type:2,
            area:["700px","500px"],
            maxmin:true,
            content:ctx+"/customer/linkCustomerPage?cid="+data[0].id
        })
    }

    function openCustomerContactDialog(data) {
        //console.log(data[0].id)
        if(data.length==0){
            layer.msg("请选择需要查看的客户!");
            return;
        }
        if(data.length>1){
            layer.msg("暂不支持批量查看!");
            return;
        }
        var title="客户管理-交往记录";
        layui.layer.open({
            title:title,
            type:2,
            area:["800px","600px"],
            maxmin:true,
            content:ctx+"/customer_contact/customerContactPage?cid="+data[0].id
        })
    }

    function openAddOrUpdateCustomerDialog(id) {
        let title="<h2>客户管理-客户添加</h2>";
        let url=ctx+"/customer/addOrUpdateCustomerPage";
        if(id){
            title="<h2>客户管理-客户更新</h2>";
            url=url+"?id="+id;
        }
        layui.layer.open({
            title:title,
            type:2,
            area:["1000px","500px"],
            maxmin:true,
            content:url
        })
    }

    function openOrderInfoDialog(data) {
        if(data.length==0){
            layer.msg("请选择查看订单对应客户!");
            return;
        }
        if(data.length>1){
            layer.msg("暂不支持批量查看!");
            return;
        }
        layui.layer.open({
            title:"客户管理-订单信息查看",
            type:2,
            area:["800px","600px"],
            maxmin:true,
            content:ctx+"/customer/orderInfoPage?cid="+data[0].id
        })

    }
});
