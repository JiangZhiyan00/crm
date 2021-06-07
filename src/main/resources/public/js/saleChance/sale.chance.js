layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    //加载并渲染数据表格
    let tableIns = table.render({
        //待渲染的table的id
        elem: '#saleChanceList',
        //访问数据的url
        url : ctx+'/saleChance/list',
        //单元格最小宽度
        cellMinWidth : 20,
        //开启分页
        page : true,
        //高度
        height : "full-120",
        //默认每页显示10条数据
        limit : 10,
        //每页显示数据条数的数量选项
        limits : [10,20,30,40,50],
        toolbar: "#toolbarDemo",
        id : "saleChanceListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',align:"center",fixed:true,sort:true},
            {field: 'customerName', title: '客户名称',align:'center',sort:true},
            {field: 'chanceSource', title: '机会来源',align:"center",sort:true},
            {field: 'successPossibility', title: '成功几率(%)', align:'center',sort:true},
            {field: 'overview', title: '概要', align:'center'},
            {field: 'linkMan', title: '联系人',  align:'center',sort:true},
            {field: 'linkPhone', title: '联系电话', align:'center',sort:true},
            {field: 'description', title: '描述', align:'center'},
            {field: 'createMan', title: '创建人', align:'center',sort:true},
            {field: 'createDate', title: '创建时间', align:'center',sort:true},
            {field: 'updateDate', title: '更新时间', align:'center',sort:true},
            {field: 'assignManTrueName', title: '指派人', align:'center',sort:true},
            {field: 'assignTime', title: '分配时间', align:'center',sort:true},
            {field: 'state', title: '分配状态', align:'center',sort:true,templet:function(d){
                    return formatterState(d.state);
                }},
            {field: 'devResult', title: '开发状态', align:'center',sort:true,templet:function (d) {
                    return formatterDevResult(d.devResult);
                }},
            {title: '操作', templet:'#saleChanceListBar',fixed:"right",align:"center", minWidth:112}
        ]]
    });

    function formatterState(state){
        if(state===0){
            return "<div style='color: red'><b>未分配</b></div>";
        }else if(state===1){
            return "<div style='color: #00B83F'><b>已分配</b></div>";
        }else{
            return "<div style='color: #505050'><b>未知</b></div>";
        }
    }

    function formatterDevResult(value){
        /**
         * 0-未开发
         * 1-开发中
         * 2-开发成功
         * 3-开发失败
         */
        if(value===0){
            return "<div style='color: #0937bd'><b>未开发</b></div>";
        }else if(value===1){
            return "<div style='color: #ea8e02;'><b>开发中</b></div>";
        }else if(value===2){
            return "<div style='color: #00B83F'><b>开发成功</b></div>";
        }else if(value===3){
            return "<div style='color: red'><b><del><i>开发失败</i></del></b></div>";
        }else {
            return "<div style='color: #505050'><b>未知</b></div>"
        }
    }

    // 多条件搜索
    $(".search_btn").on("click",function () {
        table.reload("saleChanceListTable",{
            page:{curr:1},
            where:{
                customerName:$("input[name='customerName']").val(),// 客户名
                createMan:$("input[name='createMan']").val(),// 创建人
                state:$("#state").val()//分配状态
            }
        })
    });

    // 头工具栏事件
    table.on('toolbar(saleChances)',function (obj) {
        if (obj.event === "add"){
            openAddOrUpdateSaleChanceDialog();
        }else if (obj.event === "del"){
            delSaleChance(table.checkStatus(obj.config.id).data);
        }else if (obj.event === "refresh"){
            table.reload("saleChanceListTable",{
                page:{curr:1},
                where:{
                    customerName:'',// 客户名
                    createMan:'',// 创建人
                    state:''//分配状态
                }
            })
        }
    });

    /**
     * 批量删除
     *   datas:选择的待删除记录数组
     */
    function delSaleChance(datas){
        if(datas.length===0){
            layer.msg("<div align='center' style='color: red'><b><h3>请先选择要删除的数据!</h3></b></div>",
                {icon: 2});
            return;
        }
        layer.confirm("<div align='center'><b><h3>确定删除选中的记录?</h3></b></div>",
            {icon: 3, title: "提示"},
            function (index) {
            layer.close(index);
            var ids="ids=";
            for(var i=0;i<datas.length;i++){
                if(i<datas.length-1){
                    ids=ids+datas[i].id+"&ids=";
                }else{
                    ids=ids+datas[i].id;
                }
            }

            $.ajax({
                type:"post",
                url:ctx+"/saleChance/delete",
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

    table.on('tool(saleChances)',function (obj) {
          var layEvent =obj.event;
          if(layEvent === "edit"){
              openAddOrUpdateSaleChanceDialog(obj.data.id);
          }else if(layEvent === "del"){
              layer.confirm("<div align='center'><b><h3>确定删除此条数据吗?</h3></b></div>",
              {icon: 3, title: "提示"},
              function (index) {
                  layer.close(index);
                  $.post(ctx+"/saleChance/delete",{ids:obj.data.id},function (data) {
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

    /**
     * 打开添加或更新对话框
     */
    function openAddOrUpdateSaleChanceDialog(sid) {
        let title = "<h2>营销机会管理-机会添加</h2>";
        let url = ctx + "/saleChance/toAddOrUpdatePage";
        if(sid){
            title="<h2>营销机会管理-机会更新</h2>";
            url=url+"?id="+sid;
        }
        layui.layer.open({
            title:title,
            type:2,
            area:["600px","600px"],
            maxmin:true,
            content:url
        })
    }
});
