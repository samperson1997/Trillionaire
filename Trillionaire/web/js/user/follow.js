jQuery(function () {
    var email = window.sessionStorage.getItem('userId');
    var code = getParam('code');
    if (sessionStorage.getItem("log_state") === "true") {
        var check = $.ajax({
            type: "GET",
            url: "/user/ifFollow",
            data: {
                "id": email,
                "code": code
            },
            timeout: 180000,
            contentType: "application/x-www-form-urlencoded",
            dataType: "json",
            success: function (data) {
                if (data === true) {
                    $("#follow-button").html("<i class=\"fa fa-star\"></i> 移出关注列表&nbsp;");
                }
            },
            error: function (request, status, err) {
                if (status === "timeout") {
                    check.abort();
                }
            }
        })
    }
});

function follow() {
    //关注
    if (sessionStorage.getItem("log_state") === null) { //如果未登陆就跳转
        window.location.href = "../../login.html";
    } else {
        if ($("#follow-button").text().indexOf("移出") >= 0) {
            cancelfollow_ajax(window.sessionStorage.getItem("userId"), getParam('code'));
        } else {
            follow_ajax(window.sessionStorage.getItem("userId"), getParam('code'));
        }
    }
}

function follow_ajax(username, code) {
    this.username = username;
    this.code = code;

    $.ajax({
        type: "GET",
        url: "/user/follow",
        data: {
            "id": this.username,
            "code": this.code
        },
        contentType: "application/x-www-form-urlencoded",
        success: function (data) {
            $("#follow-button").html("<i class=\"fa fa-star\"></i> 移出关注列表&nbsp;");
        }
    });
}

function cancelfollow_ajax(username, code) {
    this.username = username;
    this.code = code;

    $.ajax({
        type: "GET",
        url: "/user/cancelFollow",
        data: {
            "id": this.username,
            "code": this.code
        },
        contentType: "application/x-www-form-urlencoded",
        success: function (data) {
            $("#follow-button").html("<i class=\"fa fa-star-o\"></i> 加入关注列表&nbsp;");
            loadFollowList();
        }
    });
}
