function loadStraList() {
    $("#stra-table").html("<tr><th>名称</th><th>最新修改时间</th><th>操作</th></tr>");
    var load = $.ajax({
        type: "GET",
        url: "/backtest/get_strategy_list",
        contentType: "application/x-www-form-urlencoded",
        data: {
            'userId': sessionStorage.getItem('userId')
        },
        dataType: "json",
        success: function (data0) {
            $.each(data0.nameList, function (i, value) {
                $("#stra-table").append("<tr><td><a href=\"strategy-edit.html?sid=" + value.sid + "\">" + value.strategName + "</a></td><td>" + value.updateTime + "</td><td><a href=\"strategy-edit.html?sid=" + value.sid + "\"><div class=\"button-green\"><i class=\"fa fa-eye\"></i> 查看</div></a><div class=\"button-red\" id=\"" + value.sid + "delete-button\" onclick=\"deleteStra(this)\"><i class=\"fa fa-close\"></i> 删除</div></td></tr>");
                sessionStorage.setItem(value.strategName, value.sid);
            })
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })
}

function deleteStra(ob) {
    var load = $.ajax({
        type: "GET",
        url: "/backtest/delete_strategy",
        contentType: "application/x-www-form-urlencoded",
        data: {
            'sid': parseInt($(ob).attr("id"))
        },
        dataType: "json",
        success: function (data0) {
            loadStraList();
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })
}
