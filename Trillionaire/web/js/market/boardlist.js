function loadAreaList() {
    var load = $.ajax({
        type: "GET",
        url: "/market/list/area",
        timeout: 180000,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            var areaList = $.map(data, function (values, key) {

                return {
                    "areaId": key,
                    "areaName": values
                }

            });

            $("#area-list").append('<li>' + areaList.areaName + '</li>');

            console.log(data);
        },
        error: function (request, status, err) {
            if (status == "timeout") {
                load.abort();
            }
        }
    })
}
