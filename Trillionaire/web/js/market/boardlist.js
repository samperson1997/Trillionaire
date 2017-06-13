function loadAreaList() {
    var load = $.ajax({
        type: "GET",
        url: "/market/list/area",
        timeout: 180000,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            var currentArea;
            $.each(data, function (i, value) {
                if (value.id == getParam('aid')) {
                    $("#area-list").append('<li style="color: red;"><a style="color: red;" href=\"area.html?aid=' + value.id + '\">' + value.name + '</a></li>');
                    currentArea = value.name;
                } else {
                    $("#area-list").append('<li><a href=\"area.html?aid=' + value.id + '\">' + value.name + '</a></li>');
                }
            });
            console.log(data);
            loadArea_ajax(currentArea);
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })
}

function loadArea_ajax(area) {
    this.area = area;
    $.ajax({
        type: "GET",
        url: "/market/rank/area",
        contentType: "application/x-www-form-urlencoded",
        data: {
            'area': this.area
        },
        dataType: "json",
        success: function (data) {

            $.each(data, function (i, value) {
                $("#area-table").append('<tr><td>' + (i + 1) + '</td><td>' + value.code + '</td><td>' + value.name + '</td><td>' + String(value.changepercent).substr(0, String(value.changepercent).indexOf('.') + 3) + '</td><td>' + value.open + '</td><td>' + value.settlement + '</td><td>' + value.high + '</td><td>' + value.low + '</td><td>' + value.volume + '</td><td>' + value.amount + '</td><td>' + String(value.turnoverratio).substr(0, String(value.turnoverratio).indexOf('.') + 3) + '</td><td>' + String(value.per).substr(0, String(value.per).indexOf('.') + 3) + '</td><td>' + String(value.pb).substr(0, String(value.pb).indexOf('.') + 3) + '</td><td>' + String(value.mktcap).substr(0, 2) + '.' + String(value.mktcap).substr(2, 4) + '</td><td>' + String(value.nmc).substr(0, 2) + '.' + String(value.nmc).substr(2, 4) + '</td></tr>');
            });
            console.log(data);

            //页面标签变量
            blockTable = document.getElementById("area-table");
            preSpan = document.getElementById("spanPre");
            firstSpan = document.getElementById("spanFirst");
            nextSpan = document.getElementById("spanNext");
            lastSpan = document.getElementById("spanLast");
            pageNumSpan = document.getElementById("spanTotalPage");
            currPageSpan = document.getElementById("spanPageNum");

            numCount = document.getElementById("area-table").rows.length - 1; //取table的行数作为数据总数量（减去标题行1）
            columnsCounts = blockTable.rows[0].cells.length;
            pageCount = 15;
            pageNum = parseInt(numCount / pageCount);
            if (0 != numCount % pageCount) {
                pageNum += 1;
            }

            firstPage();

        },
        error: function (request, status, err) {

        }
    })
}

function loadInduList() {
    var load = $.ajax({
        type: "GET",
        url: "/market/list/industry",
        timeout: 180000,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            $.each(data, function (i, value) {
                if (value.id == getParam('iid')) {
                    $("#indu-list").append('<li style="color: red;"><a style="color: red;" href=\"industry.html?iid=' + value.id + '\">' + value.name + '</a></li>');
                } else {
                    $("#indu-list").append('<li><a href=\"industry.html?iid=' + value.id + '\">' + value.name + '</a></li>');
                }
            });
            console.log(data);
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })
}

function loadConcList() {
    var load = $.ajax({
        type: "GET",
        url: "/market/list/concept",
        timeout: 180000,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            $.each(data, function (i, value) {
                if (value.id == getParam('cid')) {
                    $("#conc-list").append('<li style="color: red;"><a style="color: red;" href=\"concept.html?cid=' + value.id + '\">' + value.name + '</a></li>');
                } else {
                    $("#conc-list").append('<li><a href=\"concept.html?cid=' + value.id + '\">' + value.name + '</a></li>');
                }
            });
            console.log(data);
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })
}
