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
                $("#area-table").append('<tr><td>' + (i + 1) + '</td><td><a href=\"stock.html?code=' + value.code + '\">' + value.code + '</a></td><td><a href=\"stock.html?code=' + value.code + '\">' + value.name + '</a></td><td>' + String(value.changepercent).substr(0, String(value.changepercent).indexOf('.') + 3) + '</td><td>' + value.open + '</td><td>' + value.settlement + '</td><td>' + value.high + '</td><td>' + value.low + '</td><td>' + value.volume + '</td><td>' + value.amount + '</td><td>' + String(value.turnoverratio).substr(0, String(value.turnoverratio).indexOf('.') + 3) + '</td><td>' + String(value.per).substr(0, String(value.per).indexOf('.') + 3) + '</td><td>' + String(value.pb).substr(0, String(value.pb).indexOf('.') + 3) + '</td><td>' + String(value.mktcap).substr(0, 2) + '.' + String(value.mktcap).substr(2, 4) + '</td><td>' + String(value.nmc).substr(0, 2) + '.' + String(value.nmc).substr(2, 4) + '</td></tr>');
            });
            console.log(data);

            pagination("area-table", 15);

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
            var currentIndu;
            $.each(data, function (i, value) {
                if (value.id == getParam('iid')) {
                    $("#indu-list").append('<li style="color: red;"><a style="color: red;" href=\"industry.html?iid=' + value.id + '\">' + value.name + '</a></li>');
                    currentIndu = value.name;
                } else {
                    $("#indu-list").append('<li><a href=\"industry.html?iid=' + value.id + '\">' + value.name + '</a></li>');
                }
            });
            console.log(data);
            loadIndu_ajax(currentIndu);
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })
}

function loadIndu_ajax(indu) {
    this.indu = indu;
    $.ajax({
        type: "GET",
        url: "/market/rank/industry",
        contentType: "application/x-www-form-urlencoded",
        data: {
            'industry': this.indu
        },
        dataType: "json",
        success: function (data) {

            $.each(data, function (i, value) {
                $("#indu-table").append('<tr><td>' + (i + 1) + '</td><td><a href=\"stock.html?code=' + value.code + '\">' + value.code + '</a></td><td><a href=\"stock.html?code=' + value.code + '\">' + value.name + '</a></td><td>' + String(value.changepercent).substr(0, String(value.changepercent).indexOf('.') + 3) + '</td><td>' + value.open + '</td><td>' + value.settlement + '</td><td>' + value.high + '</td><td>' + value.low + '</td><td>' + value.volume + '</td><td>' + value.amount + '</td><td>' + String(value.turnoverratio).substr(0, String(value.turnoverratio).indexOf('.') + 3) + '</td><td>' + String(value.per).substr(0, String(value.per).indexOf('.') + 3) + '</td><td>' + String(value.pb).substr(0, String(value.pb).indexOf('.') + 3) + '</td><td>' + String(value.mktcap).substr(0, 2) + '.' + String(value.mktcap).substr(2, 4) + '</td><td>' + String(value.nmc).substr(0, 2) + '.' + String(value.nmc).substr(2, 4) + '</td></tr>');
            });
            console.log(data);
            pagination("indu-table", 65);
        },
        error: function (request, status, err) {

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
            var currentConc;
            $.each(data, function (i, value) {
                if (value.id == getParam('cid')) {
                    $("#conc-list").append('<li style="color: red;"><a style="color: red;" href=\"concept.html?cid=' + value.id + '\">' + value.name + '</a></li>');
                    currentConc = value.name;
                } else {
                    $("#conc-list").append('<li><a href=\"concept.html?cid=' + value.id + '\">' + value.name + '</a></li>');
                }
            });
            console.log(data);
            loadConc_ajax(currentConc);
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })
}

function loadConc_ajax(conc) {
    this.conc = conc;
    $.ajax({
        type: "GET",
        url: "/market/rank/concept",
        contentType: "application/x-www-form-urlencoded",
        data: {
            'concept': this.conc
        },
        dataType: "json",
        success: function (data) {

            $.each(data, function (i, value) {
                $("#conc-table").append('<tr><td>' + (i + 1) + '</td><td><a href=\"stock.html?code=' + value.code + '\">' + value.code + '</a></td><td><a href=\"stock.html?code=' + value.code + '\">' + value.name + '</a></td><td>' + String(value.changepercent).substr(0, String(value.changepercent).indexOf('.') + 3) + '</td><td>' + value.open + '</td><td>' + value.settlement + '</td><td>' + value.high + '</td><td>' + value.low + '</td><td>' + value.volume + '</td><td>' + value.amount + '</td><td>' + String(value.turnoverratio).substr(0, String(value.turnoverratio).indexOf('.') + 3) + '</td><td>' + String(value.per).substr(0, String(value.per).indexOf('.') + 3) + '</td><td>' + String(value.pb).substr(0, String(value.pb).indexOf('.') + 3) + '</td><td>' + String(value.mktcap).substr(0, 2) + '.' + String(value.mktcap).substr(2, 4) + '</td><td>' + String(value.nmc).substr(0, 2) + '.' + String(value.nmc).substr(2, 4) + '</td></tr>');
            });
            console.log(data);

            pagination("conc-table", 100);
        },
        error: function (request, status, err) {

        }
    })
}
