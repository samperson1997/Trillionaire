function loadMACD() {
    var code = getParam('code');
    var load = $.ajax({
        type: "GET",
        url: "/stock/macd",
        contentType: "application/x-www-form-urlencoded",
        data: {
            "code": code
        },
        dataType: "json",
        success: function (data0) {
            $("#macd-spin").html('');
            option = {
                legend: {
                    data: ['MACD', 'DIFF', 'DEA']
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
                xAxis: {
                    type: 'category',
                    data: data0.date,
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
                        start: 98,
                        end: 100
        },
                    {
                        show: true,
                        type: 'slider',
                        y: '90%',
                        start: 98,
                        end: 100
        }
    ],
                series: [
                    {
                        name: 'MACD',
                        type: 'bar',
                        data: data0.macd,
                    },
                    {
                        name: 'DIFF',
                        type: 'line',
                        data: data0.diff,
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
                        data: data0.dea,
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
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })
}


var getParam = function (name) {
    var search = document.location.search;
    var pattern = new RegExp("[?&]" + name + "\=([^&]+)", "g");
    var matcher = pattern.exec(search);
    var items = null;
    if (null != matcher) {
        try {
            items = decodeURIComponent(decodeURIComponent(matcher[1]));
        } catch (e) {
            try {
                items = decodeURIComponent(matcher[1]);
            } catch (e) {
                items = matcher[1];
            }
        }
    }
    return items;
};
