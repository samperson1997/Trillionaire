function loadKDJ() {
    var code = getParam('code');
    var load = $.ajax({
        type: "GET",
        url: "/stock/" + code + "/kdj",
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data0) {
            data = splitData(data0);
            $("#kdj-spin").html('');
            option = {
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['K', 'D', 'J']
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
                        name: 'K',
                        type: 'line',
                        smooth: true,
                        symbol: 'none',
                        sampling: 'average',

                        data: data.k
        },
                    {
                        name: 'D',
                        type: 'line',
                        smooth: true,
                        symbol: 'none',
                        sampling: 'average',

                        data: data.d
        },
                    {
                        name: 'J',
                        type: 'line',
                        smooth: true,
                        symbol: 'none',
                        sampling: 'average',

                        data: data.j
        }
    ]
            };


            var kdjChart = echarts.init(document.getElementById('kdj-chart'));
            kdjChart.setOption(option);
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })
}

function splitData(rawData) {
    return {
        k: rawData.k,
        d: rawData.d,
        j: rawData.j
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
