function loadMargin() {
    $("#compare-spin").html('<i class="fa fa-circle-o-notch fa-spin fa-3x fa-fw"></i><span>加载中...</span>');

    var load = $.ajax({
        type: "GET",
        url: "/stock/margin",
        contentType: "application/x-www-form-urlencoded",
        data: {
            "code1": $("#code1").text(),
            "code2": $("#code2").text(),
            "code3": $("#code3").text()
        },
        dataType: "json",
        success: function (data0) {
            $("#compare-spin").html('');
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
                    data: ['股票1', '股票2', '股票3']
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: data0.date
                },
                yAxis: {
                    type: 'value',
                    boundaryGap: [0, '100%'],
                    min: -1.5,
                    max: 1.5
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
                        name: '股票1',
                        type: 'line',
                        smooth: true,
                        symbol: 'none',
                        sampling: 'average',

                        data: data0.margin1
        },
                    {
                        name: '股票2',
                        type: 'line',
                        smooth: true,
                        symbol: 'none',
                        sampling: 'average',

                        data: data0.margin2
        },
                    {
                        name: '股票3',
                        type: 'line',
                        smooth: true,
                        symbol: 'none',
                        sampling: 'average',

                        data: data0.margin3
        }
    ]
            };


            var comChart = echarts.init(document.getElementById('compare-chart'));
            comChart.setOption(option);
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })
}

function quitCompare1() {
    $("#name1").text('选择股票加入对比');
    $("#code1").text('000000');
    $("#code1").css('display', "none");

    $("#quit1").css('display', "none");
    $("#a1").removeAttr('href');

    loadMargin();
}

function quitCompare2() {
    $("#name2").text('选择股票加入对比');
    $("#code2").text('000000');
    $("#code2").css('display', "none");

    $("#quit2").css('display', "none");
    $("#a2").removeAttr('href');

    loadMargin();
}

function quitCompare3() {
    $("#name3").text('选择股票加入对比');
    $("#code3").text('000000');
    $("#code3").css('display', "none");

    $("#quit3").css('display', "none");
    $("#a3").removeAttr('href');

    loadMargin();
}

function addCompare() {
    //需要判断股票代码是不是有效，这里测试统一默认输入000001

    if ($("#name1").text() == '选择股票加入对比') {
        $("#name1").text('深发展');
        $("#code1").text($("#search-box").val());
        $("#code1").css('display', "inline-block");

        $("#quit1").css('display', "inline-block");
        $("#a1").addAttr('href', "stock.html?code=" + $("#search-box").val()); //?????

        loadMargin(); //?????
    }
}
