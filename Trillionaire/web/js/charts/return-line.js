var base = +new Date(1968, 9, 3);
var oneDay = 24 * 3600 * 1000;
var date = [];

var data1 = [Math.random() * 300];
var data2 = [Math.random() * 300];

for (var i = 1; i < 20000; i++) {
    var now = new Date(base += oneDay);
    date.push([now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'));
    data1.push(Math.round((Math.random() - 0.5) * 20 + data1[i - 1]));
    data2.push(Math.round((Math.random() - 0.5) * 20 + data2[i - 1]));
}

option = {
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data: ['基准收益率', '策略收益率']
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
            name: '基准收益率',
            type: 'line',
            smooth: true,
            symbol: 'none',
            sampling: 'average',

            data: data1
        },
        {
            name: '策略收益率',
            type: 'line',
            smooth: true,
            symbol: 'none',
            sampling: 'average',

            data: data2
        }
    ]
};


var retChart = echarts.init(document.getElementById('return-chart'));
retChart.setOption(option);

var params = {
    'sid':99,
    'cash':"10000",
    'sDate':"1990-01-01",
    'eDate':"1991-02-02",
    'frequency':"1d",
    'matchingType':"current_bar",
    'benchmark':"000300.XGSH",
    'commissionMultiplier':"1",
    'slippage':"0",
};

function loadReturnLine() {
    var load = $.ajax({
        type: "GET",
        url: "/backtest/run",
        //contentType: "application/x-www-form-urlencoded",
        data:params,
        dataType: "json",
        success:function (result) {
            alert("success");
            op = getOP(result.datelist, result.data1, result.data2);
            retChart.setOption(op);
        },
        error:function (XMLHttpRequest) {
            alert(XMLHttpRequest.status);
        }
    })
}

function getOP(datelist, d1, d2) {
    op = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['基准收益率', '策略收益率']
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: datelist
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
                name: '基准收益率',
                type: 'line',
                smooth: true,
                symbol: 'none',
                sampling: 'average',

                data: d1
            },
            {
                name: '策略收益率',
                type: 'line',
                smooth: true,
                symbol: 'none',
                sampling: 'average',

                data: d2
            }
        ]
    };

    return op;
}