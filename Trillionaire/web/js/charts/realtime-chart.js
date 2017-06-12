var timeTicket;

function loadRealTimeChart() {
    var code = getParam('code');
    var load = $.ajax({
        type: "GET",
        url: "/stock/getRealtime",
        contentType: "application/x-www-form-urlencoded",
        data: {
            "code": code
        },
        dataType: "json",
        success: function (data0) {
            $("#candle-spin").html('');
            if (data0.msg == "success") {
                var time = data0.time;
                var data = data0.price;
                var data2 = data0.meanPrice;
                var data3 = data0.volume;

                var realOption = {
                    legend: {
                        data: ['价格', '均线', '成交量']
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
                            data: time,
                            type: 'category',
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
                            data: time,
                            type: 'category',
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
                            start: 0,
                            end: 100
        },
                        {
                            show: true,
                            type: 'slider',
                            xAxisIndex: [0, 1],
                            y: '90%',
                            start: 0,
                            end: 100
        }
    ],
                    series: [{
                        name: '价格',
                        type: 'line',
                        smooth: true,
                        symbol: 'none',
                        data: data
    }, {
                        name: '均线',
                        type: 'line',
                        smooth: true,
                        symbol: 'none',
                        data: data2
    }, {
                        name: '成交量',
                        type: 'bar',
                        xAxisIndex: 1,
                        yAxisIndex: 1,
                        data: data3
    }]
                };
                var realChart = echarts.init(document.getElementById('real-chart'));
                realChart.setOption(realOption, true);

                if ($("#realtime").attr('class').indexOf("active") >= 0) {
                    $("#real-chart").fadeIn();
                } else {
                    $("#real-chart").fadeOut();
                }


                clearInterval(timeTicket);
                timeTicket = setInterval(function () {
                    //                    data.shift();
                    //                    data.push(randomData());
                    //                    data2.shift();
                    //                    data2.push(randomData2());
                    //                    data3.shift();
                    //                    data3.push(randomData3());

                    realChart.setOption({
                        series: [{
                            name: '价格',
                            data: data
                }, {
                            name: '均线',
                            data: data2
                }, {
                            name: '成交量',
                            data: data3
                }]
                    })
                }, 1000);

            }
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })

}
