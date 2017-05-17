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
                value: [3, 4, 6, 9]
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
