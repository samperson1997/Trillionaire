var timestamp = 0;

angular.module("searchApp", [])
    .controller("StockController", function ($scope) {
        $scope.searchBox = "";

        $scope.associate = function ($event) {
            //    if ($event.keyCode == 13) { //回车
            //        login();
            //    }


            var newTimestamp = (new Date()).valueOf();
            if (newTimestamp - timestamp > 1000) {

                var load = $.ajax({
                    type: "GET",
                    url: "/stock/associate",
                    contentType: "application/x-www-form-urlencoded",
                    data: {
                        "input": $scope.inputString
                    },
                    dataType: "json",
                    success: function (data0) {
                        $("#search-results").html("");
                        if (data0.length == 0) {
                            $("#search-results").append("<li>未搜索到该股票</li>");
                        } else {
                            $.each(data0, function (i, value) {
                                $("#search-results").append("<li>" + value.name + "</li>");
                            })
                        }
                    },
                    error: function (request, status, err) {
                        if (status == "timeout") {
                            load.abort();
                        }
                    }
                })
                timestamp = (new Date()).valueOf();
            }
        }
    })

function search() {
    window.location.href = "stock.html?code=" + $("#searchBox").val();
}
