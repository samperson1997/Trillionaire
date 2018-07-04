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
                    $("#follow-table").append('<tr><td><a href=\"stock.html?code='
                        + value.code + '\">' + value.code + '</a></td><td><a href=\"stock.html?code='
                        + value.code + '\">' + value.name + '</a></td><td>'
                        + String(value.changepercent).substr(0, String(value.changepercent).indexOf('.') + 3)
                        + '</td><td>' + value.open + '</td><td>' + value.settlement
                        + '</td><td>' + value.high + '</td><td>' + value.low
                        + '</td><td>' + value.volume + '</td><td>' + value.amount
                        + '</td></tr>');
                });

                pagination("follow-table", 15);

            },
            error: function (request, status, err) {

            }
        })
    }
}
