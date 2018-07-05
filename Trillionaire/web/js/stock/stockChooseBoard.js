jQuery(function ($) {
    $("#select-menu button").click(function () {
        $("#select-menu button").removeClass("select-menu-item-active");
        $("#select-menu button").addClass("select-menu-item");
        $("this").removeClass("select-menu-item");
        $(this).addClass("select-menu-item-active");
    })
});

function set() {
    for (var i = 0; i < 6; i++) {
        if ($("#select-menu button:eq(" + i + ")").hasClass('select-menu-item-active')) {
            break;
        }
    }
    switch (i) {
        case 0:
        case 1:
        case 2:
            loadCandle();
            $("#k-chart").fadeIn();
            $("#kdj-chart").fadeOut();
            $("#bias-chart").fadeOut();
            $("#macd-chart").fadeOut();
            break;
        case 3:
            loadKDJ();
            $("#k-chart").fadeOut();
            $("#kdj-chart").fadeIn();
            $("#bias-chart").fadeOut();
            $("#macd-chart").fadeOut();

            $("#kdj-chart").resize();
            break;
        case 4:
            loadBIAS();
            $("#k-chart").fadeOut();
            $("#kdj-chart").fadeOut();
            $("#bias-chart").fadeIn();
            $("#macd-chart").fadeOut();

            $("#bias-chart").resize();
            break;
        case 5:
            loadMACD();
            $("#k-chart").fadeOut();
            $("#kdj-chart").fadeOut();
            $("#bias-chart").fadeOut();
            $("#macd-chart").fadeIn();

            $("#macd-chart").resize();
            break;
    }
}
