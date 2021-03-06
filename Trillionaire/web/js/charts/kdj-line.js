function loadKDJ() {
    var code = getParam('code');
    var load = $.ajax({
        type: "GET",
        url: "/stock/kdj",
        contentType: "application/x-www-form-urlencoded",
        data: {
            "code": code
        },
        dataType: "json",
        success: function (data0) {
            var data = splitKDJData(data0);
            $("#kdj-spin").html('');
            var option = {
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
                    data: ['K', 'D', 'J'],
                    top: '6%'
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: data.categoryData
                },
                yAxis: {
                    type: 'value',
                    min: 'dataMin',
                    max: 'dataMax',
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
                        name: 'K',
                        type: 'line',
                        smooth: true,
                        symbol: 'none',
                        sampling: 'average',

                        data: data.k,
        },
                    {
                        name: 'D',
                        type: 'line',
                        smooth: true,
                        symbol: 'none',
                        sampling: 'average',

                        data: data.d,
        },
                    {
                        name: 'J',
                        type: 'line',
                        smooth: true,
                        symbol: 'none',
                        sampling: 'average',

                        data: data.j,
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

function splitKDJData(rawData) {
    var categoryData = [];
    var k = [];
    var d = [];
    var j = [];
    for (var i = 0; i < rawData.length; i++) {
        categoryData.push(rawData[i].date);
        k.push(rawData[i].k);
        d.push(rawData[i].d);
        j.push(rawData[i].j)
    }
    return {
        categoryData: categoryData,
        k: k,
        d: d,
        j: j,
    };
}
