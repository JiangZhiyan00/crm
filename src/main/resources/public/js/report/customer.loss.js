layui.use(['table','layer','form','echarts'],function(){
    let layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        echarts = layui.echarts;

    let tableIns = table.render({
        elem: '#customerLossList',
        even:true,
        url : ctx+'/customerLoss/list?state=0',
        cellMinWidth : 20,
        height : "full",
        toolbar: "#toolbarDemo",
        id : "customerLossListTable",
        cols : [[
            {field: 'cusNo', title: '客户编号',align:"center"},
            {field: 'cusName', title: '客户名称',align:"center"},
            {field: 'lossReason', title: '流失原因',align:"center"},
            {field: 'confirmLossTime', title: '流失时间',align:"center"},
            {field: 'lastOrderTime', title: '最后下单时间',align:"center"},
            {field: 'cusManager', title: '客户经理',align:"center"}
        ]]
    });

    $(function(){
        showContributionsPie();
    })

    function showContributionsPie(d){
        $.ajax({
            type:"get",
            data:d,
            url:ctx+"/report/getLossCustomerForPie",
            dataType:'json',
            success:function (data) {
                // 基于准备好的dom，初始化echarts实例
                let myChart = echarts.init(document.getElementById('pie'));
                // 指定图表的配置项和数据
                option = {
                    title: {
                        text: '流失客户饼图',
                        subtext: '来自: CRM',
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    legend: {
                        top: 'bottom'
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {show: true},
                            dataView: {show: true, readOnly: false},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    series: [
                        {
                            name: '流失客户',
                            type: 'pie',
                            radius: [30, 180],
                            center: ['50%', '50%'],
                            roseType: 'area',
                            itemStyle: {
                                borderRadius: 2
                            },
                            data: data
                        }
                    ]
                };
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        })
    }

    $(".search_btn").on("click",function(){
        table.reload("customerLossListTable",{
            where: {
                cusNo: $("input[name='cusNo']").val(),
                cusName: $("input[name='cusName']").val()
            }
        })
    });

    // 头工具栏事件
    table.on('toolbar(customerLosses)',function (obj) {
        if (obj.event === "refresh"){
            table.reload("customerLossListTable")
        }
    });

});
