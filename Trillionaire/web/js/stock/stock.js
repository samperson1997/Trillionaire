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

function dataAnalyze(data) {
    var array = data;
    var finalList;
    for (var i = 0; i < array.length; i++) {
        //对数组遍历
        finalList = '';
        $("#up-List").append(finalList);
    }

}

function loadCandle(span) {
    var code = getParam('code');
    console.log(code);
    var loadInfo = $.ajax({
        type: "GET",
        url: "/stock/" + code + "/" + span,
        timeout: 180000,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            //dataAnalyze(data);
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
                loadInfo();
            }
        }
    })
}

function load() {
    loadCandle("daily");
}
