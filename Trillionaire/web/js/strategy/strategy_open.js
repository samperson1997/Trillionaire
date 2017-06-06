function loadStraContent() {
    var load = $.ajax({
        type: "GET",
        url: "/backtest/open_strategy",
        contentType: "application/x-www-form-urlencoded",
        data: {
            'sid': getParam('sid')
        },
        dataType: "json",
        success: function (data0) {
            var editor = ace.edit("code");
            editor.setValue(data0.strategyContent);
            editor.gotoLine(editor.session.getLength() + 1);
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })
}
