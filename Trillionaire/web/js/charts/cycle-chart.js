var xAxisData = [];
var data1 = [];
var data2 = [];
var data3 = [];
for (var i = 0; i < 10; i++) {
    xAxisData.push('类目' + i);
    data1.push(Math.random() * i);
    data2.push(Math.random() * i);
    data3.push(Math.random() * i);
}

option = {
    legend: {
        data: ['bar', 'bar2'],
        align: 'left'
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
    tooltip: {},
    xAxis: {
        data: xAxisData,
        silent: false,
        splitLine: {
            show: false
        }
    },
    yAxis: {},
    series: [{
        name: 'bar',
        type: 'bar',
        data: data1,
        animationDelay: function (idx) {
            return idx * 10;
        }
    }, {
        name: 'bar2',
        type: 'bar',
        data: data2,
        animationDelay: function (idx) {
            return idx * 10 + 100;
        }
    }],
    animationEasing: 'elasticOut',
    animationDelayUpdate: function (idx) {
        return idx * 5;
    }
};

var cycleChart = echarts.init(document.getElementById('cycle-chart'));
cycleChart.setOption(option);
