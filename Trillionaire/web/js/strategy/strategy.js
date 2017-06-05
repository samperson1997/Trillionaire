var retChart = echarts.init(document.getElementById('return-chart'));

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
                $("#stra_hint").html("<p style=\"color: #000; display: inline-block;\">注意: 选择\"参数调优\"功能可能导致回测速度变慢。</p>");

            } else {
                if ($scope.cash == "" || $scope.sDate == "" || $scope.eDate == "") {
                    $("#stra_hint").html("<p style=\"color: red; display: inline-block;\">请将回测设置填写完整!</p>");
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
                $("#stra_hint").html("<p style=\"color: red; display: inline-block;\">已将基准合约设为默认值。</p>");
                loadReturnLine_ajax(99, $scope.cash, $scope.sDate, $scope.eDate, "1d", $scope.matchingType, $scope.benchmark, $scope.commissionMultiplier, $scope.slippage);
            }
            if ($scope.commissionMultiplier == "") {
                $scope.commissionMultiplier = "1";
                $("#stra_hint").html("<p style=\"color: red; display: inline-block;\">已将佣金倍率设为默认值。</p>");
                loadReturnLine_ajax(99, $scope.cash, $scope.sDate, $scope.eDate, "1d", $scope.matchingType, $scope.benchmark, $scope.commissionMultiplier, $scope.slippage);
            }
            if ($scope.slippage == "") {
                $scope.slippage = "0";
                $("#stra_hint").html("<p style=\"color: red; display: inline-block;\">已将滑点设为默认值。</p>");
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
                error: function (XMLHttpRequest) {
                    alert(XMLHttpRequest.status);
                }
            });
        };

        $scope.saveStra = function () {
            $("#save-button").fadeOut();
            $("#save-spin").fadeIn();
            saveStra_ajax(99, $scope.straCode);

        };


        function saveStra_ajax(sid, straCode) {
            this.sid = sid;
            this.straCode = straCode;

            $.ajax({
                type: "GET",
                url: "/backtest/save",
                data: {
                    'sid': this.sid,
                    'straCode': this.straCode
                },
                contentType: "application/x-www-form-urlencoded",
                dataType: "json",
                success: function (result) {
                    $("#save-spin").fadeOut();
                    $("#save-already").fadeIn(2000).fadeOut();
                    $("#save-spin").fadeIn();
                },
                error: function (XMLHttpRequest) {
                    $("#save-spin").fadeOut();
                    $("#save-spin").fadeIn();
                    alert(XMLHttpRequest.status);
                }
            });
        };
    })