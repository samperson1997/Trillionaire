function getWinOP(plist, wlist) {
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
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: plist
        },
        yAxis: {
            type: 'value',
            //            min: 'dataMin',
            //            max: 'dataMax',
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
                name: '策略胜率',
                type: 'line',
                smooth: true,
                symbol: 'none',
                sampling: 'average',
                itemStyle: {
                    normal: {
                        color: 'rgb(255, 70, 131)'
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgb(255, 158, 68)'
                    }, {
                            offset: 1,
                            color: 'rgb(255, 70, 131)'
                    }])
                    }
                },
                data: wlist
        }
    ]
    };

    return option;
}
