var retChart = echarts.init(document.getElementById('return-chart'));
var sid = getParam('sid');

angular.module("mainapp", [])
    .controller("BackTestController", function ($scope) {
        $scope.cash = "10000";
        $scope.sDate = "2016-09-01";
        $scope.eDate = "2016-09-04";
        $scope.matchingType = "current_bar";
        $scope.benchmark = "000300.XSHG";
        $scope.commissionMultiplier = "1";
        $scope.slippage = "0";

        $scope.loadReturnLine = function () {
            if ($scope.cash != "" &&
                $scope.sDate != "" &&
                $scope.eDate != "" &&
                $scope.benchmark != "" &&
                $scope.commissionMultiplier != "" &&
                $scope.slippage != "") {
                loadReturnLine_ajax(99, $scope.cash, $scope.sDate, $scope.eDate, "1d", $scope.matchingType, $scope.benchmark, $scope.commissionMultiplier, $scope.slippage);

            } else {
                if ($scope.cash == "" || $scope.sDate == "" || $scope.eDate == "") {
                    $("#stra-page-hint").html("请将回测设置填写完整");
                    $("#stra-page-hint").fadeIn().delay(1000).fadeOut();

                    $("#result-area").fadeOut();
                    $("#return-area").fadeOut();
                    $("#return-chart").fadeOut();
                    $("#overreturn-area").fadeOut();
                    $("#win-area").fadeOut();
                    $("#overreturn-chart").fadeOut();
                    $("#win-chart").fadeOut();
                }
            }
            if ($scope.benchmark == "") {
                $scope.benchmark = "000300.XSHG";
                $("#stra-page-hint").html("已将基准合约设为默认值");
                $("#stra-page-hint").fadeIn().delay(1000).fadeOut();

                loadReturnLine_ajax(99, $scope.cash, $scope.sDate, $scope.eDate, "1d", $scope.matchingType, $scope.benchmark, $scope.commissionMultiplier, $scope.slippage);
            }
            if ($scope.commissionMultiplier == "") {
                $scope.commissionMultiplier = "1";
                $("#stra-page-hint").html("已将佣金倍率设为默认值");
                $("#stra-page-hint").fadeIn().delay(1000).fadeOut();

                loadReturnLine_ajax(99, $scope.cash, $scope.sDate, $scope.eDate, "1d", $scope.matchingType, $scope.benchmark, $scope.commissionMultiplier, $scope.slippage);
            }
            if ($scope.slippage == "") {
                $scope.slippage = "0";
                $("#stra-page-hint").html("已将滑点设为默认值");
                $("#stra-page-hint").fadeIn().delay(1000).fadeOut();

                loadReturnLine_ajax(99, $scope.cash, $scope.sDate, $scope.eDate, "1d", $scope.matchingType, $scope.benchmark, $scope.commissionMultiplier, $scope.slippage);
            }

        };


        function loadReturnLine_ajax(sid, cash, sDate, eDate, frequency, matchingType, benchmark, commissionMultiplier, slippage) {

            this.sid = sid;
            this.cash = cash;
            this.sDate = sDate;
            this.eDate = eDate;
            this.frequency = frequency;
            this.matchingType = matchingType;
            this.benchmark = benchmark;
            this.commissionMultiplier = commissionMultiplier;
            this.slippage = slippage;

            $.ajax({
                type: "GET",
                url: "/backtest/run",
                data: {
                    'sid': this.sid,
                    'cash': this.cash,
                    'sDate': this.sDate,
                    'eDate': this.eDate,
                    'frequency': this.frequency,
                    'matchingType': this.matchingType,
                    'benchmark': this.benchmark,
                    'commissionMultiplier': this.commissionMultiplier,
                    'slippage': this.slippage,
                },
                contentType: "application/x-www-form-urlencoded",
                dataType: "json",
                success: function (result) {
                    op = getOP(result.datelist, result.data1, result.data2);
                    retChart.setOption(op);
                    $("#result-area").fadeIn();
                    $("#return-area").fadeIn();
                    $("#return-chart").fadeIn();

                    $("#overreturn-area").fadeOut();
                    $("#win-area").fadeOut();
                    $("#overreturn-chart").fadeOut();
                    $("#win-chart").fadeOut();
                },
                error: function (request, status, err) {
                    load.abort();
                }
            });
        };

        $scope.saveStra = function () {
            if (sid < 0 && $("#stra-name-input").val() == "") {
                $("#stra-name-input").fadeIn();
            } else {
                $("#stra-name-input").fadeOut();
                $("#save-button").fadeOut();

                saveStra_ajax(sid, $("#stra-name-input").val(), editor.getValue(), sessionStorage.getItem("userId"));
            }
        };

        function saveStra_ajax(sid, strategyName, content) {
            this.sid = sid;
            this.content = content;

            $.ajax({
                type: "POST",
                url: "/backtest/save_strategy",
                data: {
                    'sid': this.sid,
                    'strategyName': this.strategyName,
                    'content': this.content,
                    'userId': this.userId
                },
                contentType: "application/x-www-form-urlencoded",
                dataType: "json",
                success: function (result) {
                    $("#save-button").fadeOut(function () {
                        $("#save-already").fadeIn().delay(1000).fadeOut(function () {
                            $("#save-button").fadeIn();

                        });
                    });
                    sid = result.sid;
                    //                    window.location.href = "strategy-edit?sid=" + sid;
                },
                error: function (request, status, err) {
                    load.abort();
                }
            });
        };
    })
