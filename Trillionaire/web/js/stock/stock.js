

function loadTargetPrice() {
    var code = getParam('code');

    var load = $.ajax({
        type: "GET",
        url: "/stock/target",
        contentType: "application/x-www-form-urlencoded",
        data: {
            "code": code
        },
        dataType: "json",
        success: function (data0) {
            $("#target-spin").html('');
            $("#max-tprice").html('<p>' + String(data0.high) + '</p>');
            $("#min-tprice").html('<p>' + String(data0.low) + '</p>');
            $("#close-tprice").html('<p>' + String(data0.close) + '</p>');
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })
}

function loadPrevail() {
    var code = getParam('code');

    var load = $.ajax({
        type: "GET",
        url: "/stock/prevail",
        contentType: "application/x-www-form-urlencoded",
        data: {
            "code": code
        },
        dataType: "json",
        success: function (data0) {
            $("#prevail").text("热度" + data0.vr);
            $("#spec-concept").text(data0.concept);
            $("#spec-industry").text(data0.industry);
            $("#spec-area").text(data0.area);
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })
}

function updateInfo() {
    var code = getParam('code');

    var load = $.ajax({
        type: "GET",
        url: "/stock/updateRealtime",
        contentType: "application/x-www-form-urlencoded",
        data: {
            "code": code
        },
        dataType: "json",
        success: function (data0) {
            $("#name").text(String(data0.name));
            $("#code").text(' | ' + String(data0.code));
            if (data0.changepercent > 0) {
                $(".margin").css('backgroundColor', 'red');
                $("#change").html('<i class=\"fa fa-arrow-up\"></i>' + " +" + String(data0.changepercent) + "%");
            } else if (data0.changepercent == 0) {
                $(".margin").css('backgroundColor', '#777');
                $("#change").html("0");
            } else if (data0.changepercent < 0) {
                $(".margin").css('backgroundColor', 'green');
                $("#change").html('<i class=\"fa fa-arrow-down\"></i>' + " " + String(data0.changepercent) + "%");
            }
            $("#high").html('<p>' + String(data0.high) + '</p>');
            $("#low").html('<p>' + String(data0.low) + '</p>');
            $("#open").html('<p>' + String(data0.open) + '</p>');
            $("#settlement").html('<p>' + String(data0.settlement) + '</p>');
            $("#volume").html('<p>' + String(data0.volume) + '</p>');
            $("#turnoverrate").html('<p>' + String(data0.turnoverratio) + '</p>');
            $("#per").html('<p>' + String(data0.per) + '</p>');
            $("#amount").html('<p>' + String(data0.amount) + '</p>');
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
                updateInfo();
            }
        }
    })
}

function loadSimilarStock() {
    var code = getParam('code');

    var load = $.ajax({
        type: "GET",
        url: "/stock/similar",
        contentType: "application/x-www-form-urlencoded",
        data: {
            "code": code
        },
        dataType: "json",
        timeout: 180000,
        success: function (data) {
            $("#rel-spin").html('');
            if (data.subject.length == 0) {
                $("#subject").append('<p>无关联股票</p>');
            } else {
                $.each(data.subject, function (i, value) {
                    $("#subject").append('<p><a href=\"stock.html?code=' + value.code + '\" target=\"_blank\">' + value.stock2 + '</a></p>');
                })
            }

            if (data.object.length == 0) {
                $("#object").append('<p>无被关联股票</p>');
            } else {
                $.each(data.object, function (i, value) {
                    $("#object").append('<p><a href=\"stock.html?code=' + value.code + '\" target=\"_blank\">' + value.stock1 + '</a></p>');
                })
            }
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
            $("#subject").append('<p>无关联股票</p>');
            $("#object").append('<p>无被关联股票</p>');
            $("#ability-spin").html('暂无数据');
        }
    })
}
