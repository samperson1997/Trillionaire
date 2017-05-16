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

function load() {
    var code = getParam('code');
    var loadInfo = $.ajax({
        type: "GET",
        url: "/stock/" + code,
        timeout: 180000,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            //dataAnalyze(data,"Concept");
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
                loadInfo();
            }
        }
    })
}