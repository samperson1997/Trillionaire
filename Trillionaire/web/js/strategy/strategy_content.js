function loadStraContent() {
    var load = $.ajax({
        type: "GET",
        url: "/backtest/get_strategy_list",
        contentType: "application/x-www-form-urlencoded",
        data: {
            'userId': 1
        },
        dataType: "json",
        success: function (data0) {
            $.each(data0.nameList, function (i, value) {
                $("#stra-table").append("<tr><td><a href=\"strategy-edit.html?sid=" + value.sid + "\">" + value.strategName + "</a></td><td>2017-05-25 22:11</td><td>2017-05-25 22:12</td><td><a href=\"strategy-edit.html?sid=" + value.sid + "\"><div class=\"button-green\"><i class=\"fa fa-eye\"></i> 查看</div></a><div class=\"button-red\"><i class=\"fa fa-close\"></i> 删除</div></td></tr>");
            })
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })
}
