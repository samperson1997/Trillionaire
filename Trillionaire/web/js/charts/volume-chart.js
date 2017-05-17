var xAxisData = [];
var data1 = [];
for (var i = 0; i < 100; i++) {
    xAxisData.push(i);
    data1.push(Math.random() * i);
}

function calculateMA(dayCount) {
    var result = [];
    for (var i = 0, len = data1.length; i < len; i++) {
        if (i < dayCount) {
            result.push('-');
            continue;
        }
        var sum = 0;
        for (var j = 0; j < dayCount; j++) {
            sum += data1[i - j];
        }
        result.push(sum / dayCount);
    }
    return result;
}

option = {
    legend: {
        data: ['成交量', 'MA5', 'MA10', 'MA20', 'MA30'],
        position: 'bottom'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross'
        }
    },
    xAxis: {
        type: 'category',
        data: xAxisData,
        scale: true,
        silent: false,
        splitLine: {
            show: false
        }
    },
    yAxis: {
        scale: true,
        splitArea: {
            show: true
        }
    },
    dataZoom: [
        {
            type: 'inside',
            start: 50,
            end: 100
        },
        {
            show: true,
            type: 'slider',
            y: '90%',
            start: 50,
            end: 100
        }
    ],
    series: [
        {
            name: '成交量',
            type: 'bar',
            data: data1
        },
        {
            name: 'MA5',
            type: 'line',
            data: calculateMA(5),
            smooth: true,
            lineStyle: {
                normal: {
                    opacity: 0.5
                }
            }
        },
        {
            name: 'MA10',
            type: 'line',
            data: calculateMA(10),
            smooth: true,
            lineStyle: {
                normal: {
                    opacity: 0.5
                }
            }
        },
        {
            name: 'MA20',
            type: 'line',
            data: calculateMA(20),
            smooth: true,
            lineStyle: {
                normal: {
                    opacity: 0.5
                }
            }
        },
        {
            name: 'MA30',
            type: 'line',
            data: calculateMA(30),
            smooth: true,
            lineStyle: {
                normal: {
                    opacity: 0.5
                }
            }
         }
    ]

};

var volChart = echarts.init(document.getElementById('vol-chart'));
volChart.setOption(option);
