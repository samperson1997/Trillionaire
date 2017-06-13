function loadTrends() {
    var code = getParam('code');
    var load = $.ajax({
        type: "GET",
        url: "/stock/trends",
        contentType: "application/x-www-form-urlencoded",
        data: {
            "code": code
        },
        dataType: "json",
        timeout: 180000,
        success: function (data0) {
            $("#trend-spin").html('');
            option = {
                legend: {
                    data: ['买入', '增持', '观望', '减持', '卖出']
                },
                yAxis: {
                    type: 'category',
                    data: [''],
                    show: false
                },
                xAxis: {
                    type: 'value',
                    show: false
                },
                series: [
                    {
                        name: '买入',
                        type: 'bar',
                        stack: '总量',
                        label: {
                            normal: {
                                show: true,
                                position: 'inside'
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: 'red',
                            }
                        },
                        data: [data0.strongBuy]
                },
                    {
                        name: '增持',
                        type: 'bar',
                        stack: '总量',
                        label: {
                            normal: {
                                show: true,
                                position: 'inside'
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: 'salmon',
                            }
                        },
                        data: [data0.buy]
                },
                    {
                        name: '观望',
                        type: 'bar',
                        stack: '总量',
                        label: {
                            normal: {
                                show: true,
                                position: 'inside'
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: 'orange',
                            }
                        },
                        data: [data0.hold]
                },
                    {
                        name: '减持',
                        type: 'bar',
                        stack: '总量',
                        label: {
                            normal: {
                                show: true,
                                position: 'inside'
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: 'yellowgreen',
                            }
                        },
                        data: [data0.sell]
                },
                    {
                        name: '卖出',
                        type: 'bar',
                        stack: '总量',
                        label: {
                            normal: {
                                show: true,
                                position: 'inside'
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: 'green',
                            }
                        },
                        data: [data0.strongSell]
                }
        ]
            };
            var recoTrend = echarts.init(document.getElementById('reco-trend'));
            recoTrend.setOption(option);
            $("#reco-sum-content").text("综合推荐：" + data0.trends);
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
            $("#ability-spin").html('暂无数据');
        }
    })
}
