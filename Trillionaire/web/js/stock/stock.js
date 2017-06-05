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
