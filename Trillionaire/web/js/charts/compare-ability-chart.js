var data0;
jQuery(function ($) {
    loadAbility();
});

function loadAbility() {
    var code = getParam('code');
    var load = $.ajax({
        type: "GET",
        url: "/stock/ability",
        data: {
            "code": code
        },
        timeout: 180000,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            $("#ability-spin").html('');
            data0 = data;
            option = {
                title: {},
                tooltip: {},
                legend: {
                    data: ['深发展']
                },
                radar: {
                    shape: 'circle',
                    indicator: [
                        {
                            name: '盈利能力',
                            max: 10
                        },
                        {
                            name: '营运能力',
                            max: 10
                        },
                        {
                            name: '成长能力',
                            max: 10
                        },
                        {
                            name: '偿债能力',
                            max: 10
                        }
                    ]
                },
                series: [{
                    type: 'radar',
                    // areaStyle: {normal: {}},
                    data: [
                        {
                            value: [data0.profitAbility, data0.operationAbility, data0.growthAbility, data0.debtPayingAbility]
                        }
                    ],
                    itemStyle: {
                        normal: {
                            label: {
                                show: true
                            }
                        }
                    }
                }]
            };
            var aChart = echarts.init(document.getElementById('ability-chart'));
            aChart.setOption(option);
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
            $("#ability-spin").html('暂无数据');
        }
    })
}
