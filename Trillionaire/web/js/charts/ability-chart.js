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
            data0 = data;
            option = {
                title: {},
                tooltip: {},
                legend: {
                    data: ['深发展']
                },
                radar: {
                    // shape: 'circle',
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
                loadAbility();
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
