var data = [
    [[1, 5, 20, '2Q'], [2, 2, 20, '3Q'], [3, 6, 20, '4Q'], [4, 10, 20, '1Q'], [5, 4, 20, '2Q']],
    [[1, 10, 20, '2Q'], [2, 12, 20, '3Q'], [3, 3, 20, '4Q'], [4, 6, 20, '1Q'], [5, 9, 20, '2Q']]
];

option = {
    title: {},
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross'
        }
    },
    legend: {
        right: 10,
        data: ['估计值', '实际值']
    },
    xAxis: {
        splitLine: {
            lineStyle: {
                type: 'dashed'
            }
        }
    },
    yAxis: {
        splitLine: {
            lineStyle: {
                type: 'dashed'
            }
        },
        scale: true
    },
    series: [{
        name: '估计值',
        data: data[0],
        type: 'scatter',
        symbolSize: function (data) {
            return 20;
        },
        label: {
            emphasis: {
                show: true,
                formatter: function (param) {
                    return param.data[3];
                },
                position: 'top'
            }
        },
        itemStyle: {
            normal: {
                shadowBlur: 10,
                shadowColor: 'rgba(120, 36, 50, 0.5)',
                shadowOffsetY: 5,
                color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                    offset: 0,
                    color: 'rgb(251, 118, 123)'
                }, {
                    offset: 1,
                    color: 'rgb(204, 46, 72)'
                }])
            }
        }
    }, {
        name: '实际值',
        data: data[1],
        type: 'scatter',
        symbolSize: function (data) {
            return 20;
        },
        label: {
            emphasis: {
                show: true,
                formatter: function (param) {
                    return param.data[3];
                },
                position: 'top'
            }
        },
        itemStyle: {
            normal: {
                shadowBlur: 10,
                shadowColor: 'rgba(25, 100, 150, 0.5)',
                shadowOffsetY: 5,
                color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                    offset: 0,
                    color: 'rgb(129, 227, 238)'
                }, {
                    offset: 1,
                    color: 'rgb(25, 183, 207)'
                }])
            }
        }
    }]
};

var eChart = echarts.init(document.getElementById('earning-chart'));
eChart.setOption(option);
