option = {
    tooltip: {
        trigger: 'axis',
        axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        right: 10,
        data: ['买入', '增持', '观望', '减持', '卖出']
    },
    xAxis: {
        type: 'category',
        data: ['二月', '三月', '四月', '五月']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name: '买入',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'inside'
                }
            },
            itemStyle: {
                normal: {
                    color: 'red',
                }
            },
            data: [320, 302, 301, 334]
                },
        {
            name: '增持',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'inside'
                }
            },
            itemStyle: {
                normal: {
                    color: 'salmon',
                }
            },
            data: [120, 132, 101, 134]
                },
        {
            name: '观望',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'inside'
                }
            },
            itemStyle: {
                normal: {
                    color: 'orange',
                }
            },
            data: [220, 182, 191, 234]
                },
        {
            name: '减持',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'inside'
                }
            },
            itemStyle: {
                normal: {
                    color: 'yellowgreen',
                }
            },
            data: [150, 212, 201, 154]
                },
        {
            name: '卖出',
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'inside'
                }
            },
            itemStyle: {
                normal: {
                    color: 'green',
                }
            },
            data: [820, 832, 901, 934]
                }
                ]
};
var recoTrend = echarts.init(document.getElementById('reco-trend'));
recoTrend.setOption(option);
