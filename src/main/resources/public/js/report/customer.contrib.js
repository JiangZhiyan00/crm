layui.use(['table','layer',"form","laydate",'echarts'],function(){
    let layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        laydate = layui.laydate,
        echarts = layui.echarts;

    //用户列表展示
    let tableIns = table.render({
        even:true,
        elem: '#contribList',
        url : ctx+'/report/queryContributionByParams',
        cellMinWidth : 20,
        height : "full",
        id : "customerContribListTable",
        toolbar: "#toolbarDemo",
        cols : [[
            {field: 'cusName', title: '客户名', minWidth:50, align:"center"},
            {field: 'total', title: '总金额(￥)', minWidth:50, align:"center",sort:true}
        ]]
    });

    laydate.render({
        elem: '#startDate',//指定元素
        format: 'yyyy-MM-dd', //可任意组合
        min: '1990-1-1',
        max: 0
    })
    laydate.render({
        elem: '#endDate',//指定元素
        format: 'yyyy-MM-dd', //可任意组合
        min: '1990-1-1',
        max: 0
    })

    $(function(){
        showContributionsPie();
    })

    function showContributionsPie(d){
        $.ajax({
            type:"get",
            data:d,
            url:ctx+"/report/customerContrib",
            dataType:'json',
            success:function (data) {
                // 基于准备好的dom，初始化echarts实例
                let myChart = echarts.init(document.getElementById('pie'));
                // 指定图表的配置项和数据
                option = {
                    title: {
                        text: '客户贡献饼图',
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
                            name: '订单金额',
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


    // 多条件搜索
    $(".search_btn").on("click",function () {
        let searchData = {
            cusName:$("input[name='cusName']").val(),// 客户名
            moneyLevel:$("#moneyLevel").val(),// 金额区间
            startDate:$("input[name='startDate']").val(),
            endDate:$("input[name='endDate']").val()
        };
        table.reload("customerContribListTable",{
            where:searchData
        })
        showContributionsPie(searchData);
    });

    // 头工具栏事件
    table.on('toolbar(contrib)',function (obj) {
        if (obj.event === "refresh"){
            table.reload("customerContribListTable",{
                where:{
                    cusName:'',
                    moneyLevel:'',
                    startDate:'',
                    endDate:''
                }
            })
        }
    });
});
