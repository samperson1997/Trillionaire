var getParam = function (name) {
    var search = document.location.search;
    var pattern = new RegExp("[?&]" + name + "\=([^&]+)", "g");
    var matcher = pattern.exec(search);
    var items = null;
    if (null != matcher) {
        try {
            items = decodeURIComponent(decodeURIComponent(matcher[1]));
        } catch (e) {
            try {
                items = decodeURIComponent(matcher[1]);
            } catch (e) {
                items = matcher[1];
            }
        }
    }
    return items;
};

//function dataAnalyze(data) {
//    var array = data;
//    var finalList;
//    for (var i = 0; i < array.length; i++) {
//        //对数组遍历
//        finalList = '';
//        $("#up-List").append(finalList);
//    }
//
//}

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
            $("#max-tprice").html('<p>' + String(data0.high) + '</p>');
            $("#min-tprice").html('<p>' + String(data0.low) + '</p>');
            $("#av-tprice").html('<p>' + String(data0.average) + '</p>');
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
            $("#prevail").text('市场热度' + String(data0));
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
                updateInfo()
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
        success: function (data0) {
            $("#prevail").text('市场热度' + String(data0));
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })
}