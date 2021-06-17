layui.use(['layer','echarts'], function () {
    var $ = layui.jquery,
        echarts = layui.echarts;



    $.ajax({
        type:"get",
        url:ctx+"/report/countCustomerLevelForHistogram",
        dataType:'json',
        success:function (data) {
            // 基于准备好的dom，初始化echarts实例
            let myChart = echarts.init(document.getElementById('Histogram'));
            // 指定图表的配置项和数据
            option = {
                title: {
                    text: '客户构成柱状图',
                    subtext: '来自: CRM',
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        data: data.names,
                        axisTick: {
                            alignWithLabel: true
                        }
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        minInterval: 1
                    }
                ],
                series: [
                    {
                        name: '数量',
                        type: 'bar',
                        barWidth: '50%',
                        data: data.values
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    })


    $.ajax({
        type:"get",
        url:ctx+"/report/countCustomerLevelForPie",
        dataType:'json',
        success:function (data) {
            // 基于准备好的dom，初始化echarts实例
            let myChart = echarts.init(document.getElementById('pie'));
            // 指定图表的配置项和数据
            option = {
                title: {
                    text: '客户构成饼图',
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
                        name: '客户数量',
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
});