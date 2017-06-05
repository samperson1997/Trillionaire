function loadBIAS() {
    var code = getParam('code');
    var load = $.ajax({
        type: "GET",
        url: "/stock/bias",
        contentType: "application/x-www-form-urlencoded",
        data: {
            "code": code
        },
        dataType: "json",
        success: function (data0) {
            $("#bias-spin").html('');
            option = {
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
                legend: {
                    data: ['BIAS6', 'BIAS12', 'BIAS24']
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: data0.date,
                },
                yAxis: {
                    type: 'value',
                    boundaryGap: [0, '100%']
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
                        name: 'BIAS6',
                        type: 'line',
                        smooth: true,
                        symbol: 'none',
                        sampling: 'average',

                        data: data0.bias6,
                    },
                    {
                        name: 'BIAS12',
                        type: 'line',
                        smooth: true,
                        symbol: 'none',
                        sampling: 'average',

                        data: data0.bias12,
                    },
                    {
                        name: 'BIAS24',
                        type: 'line',
                        smooth: true,
                        symbol: 'none',
                        sampling: 'average',

                        data: data0.bias24,
                    }
                ]
            };


            var biasChart = echarts.init(document.getElementById('bias-chart'));
            biasChart.setOption(option);
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
