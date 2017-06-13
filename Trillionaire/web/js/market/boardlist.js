function loadAreaList() {
    var load = $.ajax({
        type: "GET",
        url: "/market/list/area",
        timeout: 180000,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            $.each(data, function (i, value) {
                if (value.id == getParam('aid')) {
                    $("#area-list").append('<li style="color: red;"><a style="color: red;" href=\"area.html?aid=' + value.id + '\">' + value.name + '</a></li>');
                } else {
                    $("#area-list").append('<li><a href=\"area.html?aid=' + value.id + '\">' + value.name + '</a></li>');
                }
            });
            console.log(data);
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })
}

function loadInduList() {
    var load = $.ajax({
        type: "GET",
        url: "/market/list/industry",
        timeout: 180000,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            $.each(data, function (i, value) {
                if (value.id == getParam('iid')) {
                    $("#indu-list").append('<li style="color: red;"><a style="color: red;" href=\"industry.html?iid=' + value.id + '\">' + value.name + '</a></li>');
                } else {
                    $("#indu-list").append('<li><a href=\"industry.html?iid=' + value.id + '\">' + value.name + '</a></li>');
                }
            });
            console.log(data);
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })
}

function loadConcList() {
    var load = $.ajax({
        type: "GET",
        url: "/market/list/concept",
        timeout: 180000,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            $.each(data, function (i, value) {
                if (value.id == getParam('cid')) {
                    $("#conc-list").append('<li style="color: red;"><a style="color: red;" href=\"concept.html?cid=' + value.id + '\">' + value.name + '</a></li>');
                } else {
                    $("#conc-list").append('<li><a href=\"concept.html?cid=' + value.id + '\">' + value.name + '</a></li>');
                }
            });
            console.log(data);
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })
}
