layui.use(['layer','echarts'], function () {
    var $ = layui.jquery,
        echarts = layui.echarts;



    $.ajax({
        type:"get",
        url:ctx+"/report/getServeTypeForPie",
        dataType:'json',
        success:function (data) {
            // 基于准备好的dom，初始化echarts实例
            let myChart = echarts.init(document.getElementById('serveType'));
            // 指定图表的配置项和数据
            option = {
                title: {
                    text: '服务类型饼图',
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
                        name: '服务类型',
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


    $.ajax({
        type:"get",
        url:ctx+"/report/getSatisfactionForPie",
        dataType:'json',
        success:function (data) {
            // 基于准备好的dom，初始化echarts实例
            let myChart = echarts.init(document.getElementById('satisfaction'));
            // 指定图表的配置项和数据
            option = {
                title: {
                    text: '服务满意度饼图',
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
                        name: '服务满意度',
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