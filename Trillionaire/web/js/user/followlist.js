function loadFollowList() {

    if (sessionStorage.getItem("log_state") == null) {
        window.location.href = "login.html";
    } else {

        $.ajax({
            type: "GET",
            url: "/user/followlist",
            contentType: "application/x-www-form-urlencoded",
            data: {
                'id': sessionStorage.getItem("userId")
            },
            dataType: "json",
            success: function (data) {

                $.each(data, function (i, value) {
                    $("#follow-table").append('<tr><td><a href=\"stock.html?code=' + value.code + '\">' + value.code + '</a></td><td><a href=\"stock.html?code=' + value.code + '\">' + value.name + '</a></td><td>' + String(value.changepercent).substr(0, String(value.changepercent).indexOf('.') + 3) + '</td><td>' + value.open + '</td><td>' + value.settlement + '</td><td>' + value.high + '</td><td>' + value.low + '</td><td>' + value.volume + '</td><td>' + value.amount + '</td><td>' + String(value.turnoverratio).substr(0, String(value.turnoverratio).indexOf('.') + 3) + '</td><td>' + String(value.per).substr(0, String(value.per).indexOf('.') + 3) + '</td><td>' + String(value.pb).substr(0, String(value.pb).indexOf('.') + 3) + '</td><td>' + String(value.mktcap).substr(0, 2) + '.' + String(value.mktcap).substr(2, 4) + '</td><td>' + String(value.nmc).substr(0, 2) + '.' + String(value.nmc).substr(2, 4) + '</td><td>');
                });
                console.log(data);

                pagination("follow-table", 15);

            },
            error: function (request, status, err) {

            }
        })
    }
}
