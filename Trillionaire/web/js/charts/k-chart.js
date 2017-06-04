var dataK;
function loadCandle() {
    var code = getParam('code');
    var urll;
    if ($("#daily").attr('class').indexOf("active") >= 0) {
        urll = "/stock/" + code + "/daily";
    } else if ($("#weekly").attr('class').indexOf("active") >= 0) {
        urll = "/stock/" + code + "/weekly";
    } else if ($("#monthly").attr('class').indexOf("active") >= 0) {
        urll = "/stock/" + code + "/monthly";
    }

    var load = $.ajax({
        type: "GET",
        url: urll,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data0) {
            dataK = splitData(data0);
            $("#candle-spin").html('');
            var option = {
                backgroundColor: '#FFF',
                animation: false,
                legend: {
                    left: 'center',
                    data: ['日K', 'MA5', 'MA10', 'MA30']
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
                    position: function (pos, params, el, elRect, size) {
                        var obj = {
                            top: 10
                        };
                        obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 30;
                        return obj;
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
                xAxis: [
                    {
                        type: 'category',
                        data: dataK.categoryData,
                        scale: true,
                        boundaryGap: false,
                        axisLine: {
                            onZero: false
                        },
                        splitLine: {
                            show: false
                        },
                        splitNumber: 20,
                        min: 'dataMin',
                        max: 'dataMax',
                        axisPointer: {
                            z: 100
                        }
                    },
                    {
                        type: 'category',
                        gridIndex: 1,
                        data: dataK.categoryData,
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
                    }
                ],
                yAxis: [
                    {
                        scale: true,
                        splitArea: {
                            show: true
                        }
                    },
                    {
                        scale: true,
                        gridIndex: 1,
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
                    }
                ],
                dataZoom: [
                    {
                        type: 'inside',
                        xAxisIndex: [0, 1],
                        start: 50,
                        end: 100
        },
                    {
                        show: true,
                        xAxisIndex: [0, 1],
                        top: '85%',
                        type: 'slider',
                        y: '90%',
                        start: 50,
                        end: 100
        }
    ],
                series: [
                    {
                        name: '日K',
                        type: 'candlestick',
                        data: dataK.values,
                        itemStyle: {
                            normal: {
                                borderColor: null,
                                borderColor0: null
                            }
                        },
                        tooltip: {
                            formatter: function (param) {
                                param = param[0];
                                return [
                                    '日期: ' + param.name + '<hr size=1 style="margin: 3px 0">',
                                    '开盘价: ' + param.data[0] + '<br/>',
                                    '收盘价: ' + param.data[1] + '<br/>',
                                    '最高价: ' + param.data[3] + '<br/>',
                                    '最低价: ' + param.data[2] + '<br/>'
                                ].join('');
                            }
                        }
                    },
                    {
                        name: 'MA5',
                        type: 'line',
                        data: data0.ma5,
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
                        data: data0.ma10,
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
                        data: data0.ma30,
                        smooth: true,
                        lineStyle: {
                            normal: {
                                opacity: 0.5
                            }
                        }
                    },
                    {
                        name: 'Volumn',
                        type: 'bar',
                        xAxisIndex: 1,
                        yAxisIndex: 1,
                        data: dataK.volumns,
                    }
                ]
            };
            var kChart = echarts.init(document.getElementById('k-chart'));
            kChart.setOption(option, true);
            kChart.dispatchAction({
                type: 'brush',
                areas: [
                    {
                        brushType: 'lineX',
                        coordRange: ['2016-06-02', '2016-06-20'],
                        xAxisIndex: 0
                    }
                ]
            });
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })
}

function splitData(rawData) {
    var categoryData = [];
    var values = [];
    var volumns = [];
    var candle = [];
    var ma5 = rawData.ma5;
    var ma10 = rawData.ma10;
    var ma30 = rawData.ma30;
    var array = rawData.candle;
    for (var i = 0; i < array.length; i++) {
        categoryData.push(array[i].date);
        candle = [array[i].open, array[i].adjClose, array[i].low, array[i].high];
        values.push(candle);
        volumns.push(array[i].volume);
    }
    return {
        categoryData: categoryData,
        values: values,
        volumns: volumns,
        ma5: ma5,
        ma10: ma10,
        ma30: ma30
    };
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
