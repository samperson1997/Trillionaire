function loadStraList() {
    $("#stra-table").html("<caption><p>策略列表</p>" +
        "<a href=\"strategy-edit.html?sid=-1\">\n" +
        "                <div class=\"stra-button\"><i class='fas fa-lightbulb'></i> 创建新策略</div>\n" +
        "            </a>" +
        "<a href=\"api.html\">\n" +
        "                <div class=\"stra-button\"><i class=\"fa fa-book\"></i> API文档</div>\n" +
        "            </a>" +
        "</caption><tr><th>策略</th><th>操作</th><th>最新修改时间</th></tr>");
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
                $("#stra-table").append("<tr><td><a href=\"strategy-edit.html?sid=" + value.sid + "\">"
                    + value.strategName + "</a><td>" + "<a href=\"strategy-edit.html?sid=" + value.sid + "\">" +
                    "<div class=\"stra-delete-button\">" +
                    "<i class='fa fa-eye'></i> 详情</div></a> | " +
                    "<div class=\"stra-delete-button\" id=\"" + value.sid + "delete-button\" onclick=\"deleteStra(this)\">" +
                    "<i class=\"fa fa-trash\"></i> 删除</div></td></td><td>" + value.updateTime.substr(0, 16)
                    + "</tr>");
                sessionStorage.setItem(value.strategName, value.sid);
            })
        },
        error: function (request, status, err) {
            if (status === "timeout") {
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
