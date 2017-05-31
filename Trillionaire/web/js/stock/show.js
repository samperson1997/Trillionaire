function showMore() {
    $("#more").fadeToggle();
    if ($("#show-more-button").text().indexOf("查看更多") >= 0) {
        $("#show-more-button").html("<i class=\"fa fa-chevron-up\"></i> 查看更少");

    } else if ($("#show-more-button").text().indexOf("查看更少") >= 0) {
        $("#show-more-button").html("<i class=\"fa fa-chevron-down\"></i> 查看更多");

    }
}
