layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    //字典列表展示
    let tableIns = table.render({
        elem: '#dataDicList',
        url : ctx+'/dataDic/list',
        cellMinWidth : 20,
        page : true,
        height : "full-120",
        limits : [10,20,30,40],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "dataDicListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',align:"center"},
            {field: 'dataDicName', title: '数据名称',align:"center"},
            {field: 'dataDicValue', title: '数据值',  align:'center'},
            {field: 'createDate', title: '创建时间',  align:'center'},
            {field: 'updateDate', title: '更新时间',  align:'center'},
            {title: '操作', templet:'#dataDicListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });

    // 多条件搜索
    $(".search_btn").on("click",function () {
        table.reload("dataDicListTable",{
            page:{
                curr:1
            },
            where:{
                dataDicName:$("input[name='dataDicName']").val(),
                dataDicValue:$("input[name='dataDicValue']").val(),
            }
        })
    });

    // 头工具栏事件
    table.on('toolbar(dataDic)',function (obj) {
        if (obj.event === "add"){
            openAddOrUpdateDataDicDialog();
        }else if (obj.event === "del"){
            delDic(table.checkStatus(obj.config.id).data);
        }else if (obj.event === "refresh"){
            table.reload("dataDicListTable",{
                page:{curr:1},
                where:{
                    dataDicName:'',
                    dataDicValue:''
                }
            })
        }
    });


    function delDic(datas){
        if(datas.length < 1){
            layer.msg("<div align='center' style='color: red'><b><h3>请先选择要删除的记录!</h3></b></div>",
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
                url:ctx+"/dataDic/delete",
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

    table.on('tool(dataDic)',function (obj) {
          if(obj.event === "edit"){
              openAddOrUpdateDataDicDialog(obj.data.id);
          }else if(obj.event === "del") {
              layer.confirm("<div align='center'><b><h3>确定删除此条记录吗?</h3></b></div>",
                  {icon: 3, title: "提示"},
              function (index) {
                  layer.close(index);
                  $.post(ctx + "/dataDic/delete", {ids: obj.data.id}, function (data) {
                      if (data.code === 200) {
                          layer.msg("<div align='center' style='color: #00B83F'><b><h3>删除成功!</h3></b></div>",
                              {icon: 6})
                          tableIns.reload();
                      } else {
                          layer.msg("<div align='center' style='color: red '><b><h3>" + data.msg + "</h3></b></div>",
                              {icon: 5});
                      }
                  })
              })
          }
    });


    /**
     * 打开添加或更新对话框
     */
    function openAddOrUpdateDataDicDialog(id) {
        let title="<h2>字典管理-字典添加</h2>";
        let url=ctx+"/dataDic/addOrUpdateDataDicPage";
        if(id){
            title="<h2>字典管理-字典更新</h2>";
            url=url+"?id="+id;
        }
        layui.layer.open({
            title:title,
            type:2,
            area:["700px","250px"],
            maxmin:true,
            content:url
        })
    }
});
