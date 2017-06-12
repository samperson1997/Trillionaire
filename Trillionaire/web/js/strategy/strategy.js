var retChart = echarts.init(document.getElementById('return-chart'));
var sid = getParam('sid');
var headerfade = 0;

angular.module("mainapp", [])
    .controller("BackTestController", function ($scope) {
        $scope.cash = "10000";
        $scope.sDate = "2017-05-01";
        $scope.eDate = "2017-06-04";
        $scope.matchingType = "current_bar";
        $scope.benchmark = "000300.XSHG";
        $scope.commissionMultiplier = "1";
        $scope.slippage = "0";

        //保存1:点击“保存”，保存
        $scope.saveStra = function () {
            if (sid < 0 && $("#stra-name-input").val() == "") {
                $("#stra-name-input").fadeIn();
            } else {
                $("#stra-name-input").fadeOut();
                $("#save-button").fadeOut();

                if (namefade == 1) {
                    saveStra_ajax(sid, $("#stra-name-input").val(), editor.getValue(), sessionStorage.getItem("userId"));
                } else {
                    saveStra_ajax(sid, $("#stra-name").text().substr(7), editor.getValue(), sessionStorage.getItem("userId"));
                }
            }
        };

        //保存2:保存ajax方法
        function saveStra_ajax(sid, strategyName, content, userId) {
            this.sid = sid;
            this.content = content;
            this.strategyName = strategyName;
            this.userId = userId;

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
                            $("#stra-name").fadeIn(function () {
                                namefade = 0;
                            });
                            $("#stra-name").text("策略名称 | " + strategyName);
                        });
                    });
                    sid = result.sid;
                    window.location.href = "strategy-edit.html?sid=" + sid;
                },
                error: function (request, status, err) {
                    load.abort();
                }
            });
        };

        //运行回测1:点击“运行回测”，保存并加载第一个图片
        $scope.loadReturnLine = function () {
            // 保存
            if (sid < 0 && $("#stra-name-input").val() == "") {
                $("#stra-name-input").fadeIn();
            } else {
                $("#stra-name-input").fadeOut();
                $("#save-button").fadeOut();

                if (namefade == 1) {
                    saveAndLoad_ajax(sid, $("#stra-name-input").val(), editor.getValue(), sessionStorage.getItem("userId"));
                } else {
                    saveAndLoad_ajax(sid, $("#stra-name").text().substr(7), editor.getValue(), sessionStorage.getItem("userId"));
                }
            }

            $("#result-area").fadeOut();
            $("#return-area").fadeIn();
            $("#return-chart").fadeOut();
            $("#return-spin").fadeIn();
        };

        //运行回测2:保存并加载第一个图片ajax方法，会调用加载第一个图片ajax方法
        function saveAndLoad_ajax(sid, strategyName, content, userId) {
            this.sid = sid;
            this.content = content;
            this.strategyName = strategyName;
            this.userId = userId;

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
                            $("#stra-name").fadeIn(function () {
                                namefade = 0;
                            });
                            $("#stra-name").text("策略名称 | " + strategyName);
                        });
                    });
                    sid = result.sid;

                    if ($scope.cash != "" &&
                        $scope.sDate != "" &&
                        $scope.eDate != "" &&
                        $scope.benchmark != "" &&
                        $scope.commissionMultiplier != "" &&
                        $scope.slippage != "") {
                        loadReturnLine_ajax(sid, $scope.cash, $scope.sDate, $scope.eDate, "1d", $scope.matchingType, $scope.benchmark, $scope.commissionMultiplier, $scope.slippage);

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

                        loadReturnLine_ajax(sid, $scope.cash, $scope.sDate, $scope.eDate, "1d", $scope.matchingType, $scope.benchmark, $scope.commissionMultiplier, $scope.slippage);
                    }
                    if ($scope.commissionMultiplier == "") {
                        $scope.commissionMultiplier = "1";
                        $("#stra-page-hint").html("已将佣金倍率设为默认值");
                        $("#stra-page-hint").fadeIn().delay(1000).fadeOut();

                        loadReturnLine_ajax(sid, $scope.cash, $scope.sDate, $scope.eDate, "1d", $scope.matchingType, $scope.benchmark, $scope.commissionMultiplier, $scope.slippage);
                    }
                    if ($scope.slippage == "") {
                        $scope.slippage = "0";
                        $("#stra-page-hint").html("已将滑点设为默认值");
                        $("#stra-page-hint").fadeIn().delay(1000).fadeOut();

                        loadReturnLine_ajax(sid, $scope.cash, $scope.sDate, $scope.eDate, "1d", $scope.matchingType, $scope.benchmark, $scope.commissionMultiplier, $scope.slippage);
                    }

                },
                error: function (request, status, err) {
                    load.abort();
                }
            });
        };

        //运行回测3:加载第一个图片ajax方法，会调用echart的js方法
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
                    console.log(result);

                    if (result.msg == "success") {
                        op = getOP(result.datelist, result.data1, result.data2);
                        retChart.setOption(op);

                        $("#log-content").html('<p>暂无错误 <i class="fa fa-smile-o"></i></p>');

                        $("#backtestReturns").text(result.summary.backtestReturns);
                        $("#backtestAnnualizedReturns").text(result.summary.backtestAnnualizedReturns);
                        $("#benchReturns").text(result.summary.benchReturns);
                        $("#benchAnnualizedReturns").text(result.summary.benchAnnualizedReturns);
                        $("#alpha").text(result.summary.alpha);
                        $("#beta").text(result.summary.beta);
                        $("#sharpe").text(result.summary.sharpe);
                        $("#sortino").text(result.summary.sortino);
                        $("#infoRatio").text(result.summary.infoRatio);
                        $("#volatility").text(result.summary.volatility);
                        $("#maxDrawdown").text(result.summary.maxDrawdown);
                        $("#trackingError").text(result.summary.trackingError);
                        $("#downsideRisk").text(result.summary.downsideRisk);

                        $("#result-area").fadeIn();
                        $("#return-area").fadeIn();
                        $("#return-chart").fadeIn();
                    } else {

                        if (result.msg == "error6") {
                            $("#log-content").html('<p>代码有语法错误 <i class="fa fa-frown-o"></i><br>' + result.errorLog + '</p>');
                        } else {
                            $("#log-content").html('<p>代码有误，错误编码：' + result.msg + ' <i class="fa fa-frown-o"></i></p>');
                        }

                        $("#result-area").fadeOut();
                        $("#return-area").fadeOut();
                        $("#return-chart").fadeOut();
                    }

                    $("#return-spin").fadeOut();
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

    })
