jQuery(function () {
    var email = window.sessionStorage.getItem('userId');
    var code = getParam('code');
    if (sessionStorage.getItem("log_state") == "true") {
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
                if (data == true) {
                    alert('已关注');
                    $("#follow-button").html("<i class=\"fa fa-minus\"></i> 取消关注&nbsp;");
                    $("#follow-button").addClass('button-red');
                    $("#follow-button").removeClass('button-page');
                }
            },
            error: function (request, status, err) {
                if (status == "timeout") {
                    check.abort();
                    checkFollow();
                }
            }
        })
    }
});

function follow() {
    //关注

    if (sessionStorage.getItem("log_state") == null) { //如果未登陆就跳转
        window.location.href = "../../login.html";
    } else {
        if ($("#follow-button").text().indexOf("取消") >= 0) {
            cancelfollow_ajax(window.sessionStorage.getItem("userId"), getParam('code'));
        } else {
            follow_ajax(window.sessionStorage.getItem("userId"), getParam('code'));
        }
    }
};

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
            $("#follow-button").html("<i class=\"fa fa-minus\"></i> 取消关注&nbsp;");
            $("#follow-button").addClass('button-red');
            $("#follow-button").removeClass('button-page');
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
            $("#follow-button").html("<i class=\"fa fa-plus\"></i> 关注&nbsp;");
            $("#follow-button").addClass('button-page');
            $("#follow-button").removeClass('button-red');
        }
    });
}
