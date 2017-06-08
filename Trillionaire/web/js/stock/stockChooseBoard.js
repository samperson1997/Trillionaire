jQuery(function ($) {
    $("#select-menu button").click(function () {
        $("#select-menu button").removeClass("active");
        $(this).addClass("active");
    })
});

function set() {
    for (var i = 0; i < 4; i++) {
        if ($("#select-menu button:eq(" + i + ")").hasClass('active')) {
            console.log(i);
            break;
        }
    }
    loadCandle();
};
