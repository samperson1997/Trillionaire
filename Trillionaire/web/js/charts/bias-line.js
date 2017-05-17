var base = +new Date(1968, 9, 3);
var oneDay = 24 * 3600 * 1000;
var date = [];

var data1 = [Math.random() * 300];
var data2 = [Math.random() * 300];
var data3 = [Math.random() * 300];

for (var i = 1; i < 20000; i++) {
    var now = new Date(base += oneDay);
    date.push([now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'));
    data1.push(Math.round((Math.random() - 0.5) * 20 + data1[i - 1]));
    data2.push(Math.round((Math.random() - 0.5) * 20 + data2[i - 1]));
    data3.push(Math.round((Math.random() - 0.5) * 20 + data3[i - 1]));
}

option = {
    tooltip: {
        trigger: 'axis'
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: date
    },
    yAxis: {
        type: 'value',
        boundaryGap: [0, '100%']
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
            name: 'BIAS',
            type: 'line',
            smooth: true,
            symbol: 'none',
            sampling: 'average',

            data: data1
        }
    ]
};


var biasChart = echarts.init(document.getElementById('bias-chart'));
biasChart.setOption(option);
