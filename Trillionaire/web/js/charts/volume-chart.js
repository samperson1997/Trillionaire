var xAxisData = [];
var data1 = [];
for (var i = 0; i < 100; i++) {
    xAxisData.push('类目' + i);
    data1.push((Math.sin(i / 5) * (i / 5 - 10) + i / 6) * 5);
}

option = {
    title: {
        text: ''
    },
    legend: {
        data: ['成交量'],
        align: 'left'
    },
    toolbox: {
        // y: 'bottom',
        feature: {
            dataView: {},
            saveAsImage: {
                pixelRatio: 2
            }
        }
    },
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
        name: '成交量',
        type: 'bar',
        data: data1,
        animationDelay: function (idx) {
            return idx * 10;
        }
    }],
    animationEasing: 'elasticOut',
    animationDelayUpdate: function (idx) {
        return idx * 5;
    }
};

var volChart = echarts.init(document.getElementById('vol-chart'));
volChart.setOption(option);
