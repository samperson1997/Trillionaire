function getOP(datelist, d1, d2) {
    op = {
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
        legend: {
            data: ['基准收益率', '策略收益率']
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: datelist
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
                name: '基准收益率',
                type: 'line',
                smooth: true,
                symbol: 'none',
                sampling: 'average',

                data: d1
            },
            {
                name: '策略收益率',
                type: 'line',
                smooth: true,
                symbol: 'none',
                sampling: 'average',

                data: d2
            }
        ]
    };

    return op;
}
