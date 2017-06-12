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
                    min: 'dataMin',
                    max: 'dataMax',
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
                visualMap: {
                    seriesIndex: 0,
                    show: false,
                    pieces: [{
                        gt: 0,
                        color: 'red'
                    }, {
                        lte: 0,
                        color: 'green'
                    }]
                },
                color: ['dodgerblue', 'yellow'],
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
