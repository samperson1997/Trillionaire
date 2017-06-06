var kChart = echarts.init(document.getElementById('k-chart'));

function randomData() {
    now = new Date(+now + oneDay);
    value = value + Math.random() * 21 - 10;
    return {
        name: now.toString(),
        value: [
            [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'),
            Math.round(value)
        ]
    }
}

function randomData2() {
    now = new Date(+now + oneDay);
    value2 = value2 + Math.random() * 20 - 10;
    return {
        name: now.toString(),
        value: [
            [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'),
            Math.round(value2)
        ]
    }
}

var data = [];
var data2 = [];
var now = +new Date(1997, 9, 3);
var oneDay = 24 * 3600 * 1000;
var value = Math.random() * 100;
var value2 = Math.random() * 100;
for (var i = 0; i < 1000; i++) {
    data.push(randomData());
    data2.push(randomData2());
}

option = {
    legend: {
        data: ['模拟数据1', '模拟数据2', 'v']
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross'
        },
        backgroundColor: 'rgba(245, 245, 245, 0.8)',
        borderWidth: 1,
        borderColor: '#ccc',
        padding: 10,
        textStyle: {
            color: '#000'
        },
        extraCssText: 'width: 170px'
    },
    axisPointer: {
        link: {
            xAxisIndex: 'all'
        },
        label: {
            backgroundColor: '#777'
        }
    },
    grid: [
        {
            left: '10%',
            right: '8%',
            height: '50%'
    },
        {
            left: '10%',
            right: '8%',
            top: '63%',
            height: '16%'
    }
    ],
    xAxis: [{
            type: 'time',
            splitLine: {
                show: false
            },
            scale: true,
            boundaryGap: false,
            axisLine: {
                onZero: false
            },
            splitNumber: 20,
            min: 'dataMin',
            max: 'dataMax',
            axisPointer: {
                z: 100
            }
    },
        {
            type: 'time',
            gridIndex: 1,
            splitLine: {
                show: false
            },
            scale: true,
            boundaryGap: false,
            axisLine: {
                onZero: false
            },
            axisTick: {
                show: false
            },
            splitLine: {
                show: false
            },
            axisLabel: {
                show: false
            },
            splitNumber: 20,
            min: 'dataMin',
            max: 'dataMax',
            axisPointer: {
                label: {
                    formatter: function (params) {
                        var seriesValue = (params.seriesData[0] || {}).value;
                        return params.value +
                            (seriesValue != null ?
                                '\n' + echarts.format.addCommas(seriesValue) :
                                ''
                            );
                    }
                }
            }
    }],
    yAxis: [{
            type: 'value',
            splitLine: {
                show: false
            }
    },
        {
            type: 'value',
            gridIndex: 1,
            boundaryGap: [0, '100%'],
            splitLine: {
                show: false
            },
            splitNumber: 2,
            axisLabel: {
                show: false
            },
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            splitLine: {
                show: false
            }
    }],
    dataZoom: [
        {
            type: 'inside',
            xAxisIndex: [0, 1],
            start: 98,
            end: 100
        },
        {
            show: true,
            type: 'slider',
            xAxisIndex: [0, 1],
            y: '90%',
            start: 98,
            end: 100
        }
    ],
    series: [{
        name: '模拟数据1',
        type: 'line',
        showSymbol: false,
        hoverAnimation: false,
        data: data
    }, {
        name: '模拟数据2',
        type: 'line',
        showSymbol: false,
        hoverAnimation: false,
        data: data2
    }, {
        name: 'v',
        type: 'bar',
        xAxisIndex: 1,
        yAxisIndex: 1,
        showSymbol: false,
        hoverAnimation: false,
        data: data2
    }]
};

function loadRealTimeChart() {
    kChart.setOption(option, true);
    setInterval(loadRealTime(), 1000);
}

function loadRealTime() {
    data.shift();
    data.push(randomData());
    data2.shift();
    data2.push(randomData2());
    var newOption = {
        series: [{
            type: 'line',
            data: data
            }, {
            type: 'line',
            data: data2
            }, {
            type: 'bar',
            data: data2
            }]
    };
    kChart.setOption(newOption, true);
}
