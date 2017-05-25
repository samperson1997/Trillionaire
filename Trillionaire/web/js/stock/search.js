function stockSearch() {
    var code = getParam('code');
    var info = $.ajax({
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
