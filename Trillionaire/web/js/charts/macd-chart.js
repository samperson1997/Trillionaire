var xAxisData = [];
var data1 = [];
for (var i = 0; i < 100; i++) {
    xAxisData.push(i);
    data1.push(Math.pow(-1, (Math.floor(Math.random() * 10) % 2)) * Math.random() * i);
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
        data: ['MACD', 'DIFF', 'DEA']
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
            name: 'MACD',
            type: 'bar',
            data: data1
        },
        {
            name: 'DIFF',
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
            name: 'DEA',
            type: 'line',
            data: calculateMA(10),
            smooth: true,
            lineStyle: {
                normal: {
                    opacity: 0.5
                }
            }
        }
    ]

};

var macdChart = echarts.init(document.getElementById('macd-chart'));
macdChart.setOption(option);
