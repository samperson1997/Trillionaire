$(document).ready(function () {

    $("#log-label").click(function () {

        $("#log-part").fadeOut(800, function () {
            $("#register-part").fadeIn(800);
        });
    });

    $("#register-label").click(function () {
        $("#register-part").fadeOut(800, function () {
            $("#log-part").fadeIn(800);
        });

    });
});
