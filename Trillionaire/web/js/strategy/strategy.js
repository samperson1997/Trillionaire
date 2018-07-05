var retChart = echarts.init(document.getElementById('return-chart'));
// var overretChart = echarts.init(document.getElementById('overreturn-chart'));
// var winChart = echarts.init(document.getElementById('win-chart'));
var sid = getParam('sid');

angular.module("mainapp", [])
    .controller("BackTestController", function ($scope) {
        $scope.cash = "10000";
        $scope.sDate = "2017-05-01";
        $scope.eDate = "2017-06-04";
        $scope.matchingType = "current_bar";
        $scope.benchmark = "000300.XSHG";
        $scope.commissionMultiplier = "1";
        $scope.slippage = "0";
        $scope.lowp = "5";
        $scope.highp = "10";

        //保存1:点击“保存”，保存
        $scope.saveStra = function () {
            if (sid < 0 && $("#stra-name-input").val() == "") {
                $("#stra-name-input").fadeIn();
            } else {
                $("#stra-name-input").hide();
                $("#save-button").hide();

                if (namefade == 1) {
                    saveStra_ajax(sid, $("#stra-name-input").val(), editor.getValue(), sessionStorage.getItem("userId"));
                } else {
                    saveStra_ajax(sid, $("#stra-name").text(), editor.getValue(), sessionStorage.getItem("userId"));
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
                    console.log('save.');
                    $("#save-button").fadeOut(function () {
                        $("#save-already").fadeIn().delay(1000).fadeOut(function () {
                            $("#save-button").fadeIn();
                            $("#stra-name").fadeIn(function () {
                                namefade = 0;
                            });
                            $("#stra-name").text(strategyName);
                        });
                    });
                    if (sid < 0) {
                        sid = result.sid;
                        window.location.href = "strategy-edit.html?sid=" + sid;
                    }
                },
                error: function (request, status, err) {
                    load.abort();
                }
            });
        }

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
                    saveAndLoad_ajax(sid, $("#stra-name").text(), editor.getValue(), sessionStorage.getItem("userId"));
                }
            }

            $("#result-area").fadeOut();
            $("#return-area").fadeIn();
            $("#return-chart").fadeOut();
            $("#return-spin").fadeIn();
            $("#overreturn-area").fadeOut();
            $("#overreturn-chart").fadeOut();
            $("#win-area").fadeOut();
            $("#win-chart").fadeOut();
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
                    $("#more-res-buttons").fadeOut(function () {
                        $("#res-figure-area").show();
                    });

                    $("#save-button").fadeOut(function () {
                        $("#save-already").fadeIn().delay(1000).fadeOut(function () {
                            $("#save-button").fadeIn();
                            $("#stra-name").fadeIn(function () {
                                namefade = 0;
                            });
                            $("#stra-name").text(strategyName);
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
                            $("#return-spin").fadeOut();
                            $("#overreturn-area").fadeOut();
                            $("#win-area").fadeOut();
                            $("#overreturn-chart").fadeOut();
                            $("#win-chart").fadeOut();
                        }
                    }
                    if ($scope.benchmark == "") {
                        $("#stra-page-hint").html("已将基准合约设为默认值000300.XSHG");
                        $("#stra-page-hint").fadeIn().delay(1000).fadeOut();
                        $scope.benchmark = "000300.XSHG";

                        loadReturnLine_ajax(sid, $scope.cash, $scope.sDate, $scope.eDate, "1d", $scope.matchingType, $scope.benchmark, $scope.commissionMultiplier, $scope.slippage);
                    }
                    if ($scope.commissionMultiplier == "") {
                        $("#stra-page-hint").html("已将佣金倍率设为默认值1");
                        $("#stra-page-hint").fadeIn().delay(1000).fadeOut();
                        $scope.commissionMultiplier = "1";

                        loadReturnLine_ajax(sid, $scope.cash, $scope.sDate, $scope.eDate, "1d", $scope.matchingType, $scope.benchmark, $scope.commissionMultiplier, $scope.slippage);
                    }
                    if ($scope.slippage == "") {
                        $("#stra-page-hint").html("已将滑点设为默认值0");
                        $("#stra-page-hint").fadeIn().delay(1000).fadeOut();
                        $scope.slippage = "0";

                        loadReturnLine_ajax(sid, $scope.cash, $scope.sDate, $scope.eDate, "1d", $scope.matchingType, $scope.benchmark, $scope.commissionMultiplier, $scope.slippage);
                    }

                },
                error: function (request, status, err) {
                    load.abort();
                }
            });
        }

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
                    'slippage': this.slippage
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

                        // $("#result-area").fadeIn();
                        // $("#return-area").show();
                        $("#return-chart").show();
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


        //--------------------------------------------------------------//
        //参数调优1:点击“运行回测”，保存并加载第一个图片
        $scope.loadOverReturnLine = function () {
            // 保存
            if (sid < 0 && $("#stra-name-input").val() == "") {
                $("#stra-name-input").fadeIn();
            } else {
                $("#stra-name-input").fadeOut();
                $("#save-button").fadeOut();

                if (namefade == 1) {
                    saveAndLoadOver_ajax(sid, $("#stra-name-input").val(), editor.getValue(), sessionStorage.getItem("userId"));
                } else {
                    saveAndLoadOver_ajax(sid, $("#stra-name").text(), editor.getValue(), sessionStorage.getItem("userId"));
                }
            }

            $("#result-area").fadeOut();
            $("#return-area").fadeOut();
            $("#return-chart").fadeOut();
            $("#overreturn-area").fadeIn();
            $("#overreturn-chart").fadeOut();
            $("#overreturn-spin").fadeIn();
            $("#win-area").fadeIn();
            $("#win-chart").fadeOut();
            $("#win-spin").fadeIn();
        };

        //参数调优2:保存并加载第2、3个图片ajax方法，会调用加载第2、3个图片ajax方法
        function saveAndLoadOver_ajax(sid, strategyName, content, userId) {
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
                            $("#stra-name").text(strategyName);
                        });
                    });
                    sid = result.sid;

                    if ($scope.cash != "" &&
                        $scope.sDate != "" &&
                        $scope.eDate != "" &&
                        $scope.benchmark != "" &&
                        $scope.commissionMultiplier != "" &&
                        $scope.slippage != "" &&
                        $scope.lowp != "" &&
                        $scope.highp != "") {
                        loadOverReturnLine_ajax(sid, $scope.cash, $scope.sDate, $scope.eDate, "1d", $scope.matchingType, $scope.benchmark, $scope.commissionMultiplier, $scope.slippage, $scope.lowp, $scope.highp);

                    } else {
                        if ($scope.cash == "" || $scope.sDate == "" || $scope.eDate == "") {
                            $("#stra-page-hint").html("请将回测设置填写完整");
                            $("#stra-page-hint").fadeIn().delay(1000).fadeOut();

                            $("#result-area").fadeOut();
                            $("#return-area").fadeOut();
                            $("#return-chart").fadeOut();
                            $("#overreturn-area").fadeOut();
                            $("#overreturn-spin").fadeOut();
                            $("#win-area").fadeOut();
                            $("#win-spin").fadeOut();
                            $("#overreturn-chart").fadeOut();
                            $("#win-chart").fadeOut();
                        }
                    }
                    if ($scope.benchmark == "") {
                        $("#stra-page-hint").html("已将基准合约设为默认值000300.XSHG");
                        $("#stra-page-hint").fadeIn().delay(1000).fadeOut();
                        $scope.benchmark = "000300.XSHG";

                        loadOverReturnLine_ajax(sid, $scope.cash, $scope.sDate, $scope.eDate, "1d", $scope.matchingType, $scope.benchmark, $scope.commissionMultiplier, $scope.slippage, $scope.lowp, $scope.highp);
                    }
                    if ($scope.commissionMultiplier == "") {
                        $("#stra-page-hint").html("已将佣金倍率设为默认值1");
                        $("#stra-page-hint").fadeIn().delay(1000).fadeOut();
                        $scope.commissionMultiplier = "1";

                        loadOverReturnLine_ajax(sid, $scope.cash, $scope.sDate, $scope.eDate, "1d", $scope.matchingType, $scope.benchmark, $scope.commissionMultiplier, $scope.slippage, $scope.lowp, $scope.highp);
                    }
                    if ($scope.slippage == "") {
                        $("#stra-page-hint").html("已将滑点设为默认值0");
                        $("#stra-page-hint").fadeIn().delay(1000).fadeOut();
                        $scope.slippage = "0";

                        loadOverReturnLine_ajax(sid, $scope.cash, $scope.sDate, $scope.eDate, "1d", $scope.matchingType, $scope.benchmark, $scope.commissionMultiplier, $scope.slippage, $scope.lowp, $scope.highp);
                    }
                    if ($scope.lowp == "") {
                        $("#stra-page-hint").html("已将参数下限设为默认值5");
                        $("#stra-page-hint").fadeIn().delay(1000).fadeOut();
                        $scope.lowp = "5";

                        loadOverReturnLine_ajax(sid, $scope.cash, $scope.sDate, $scope.eDate, "1d", $scope.matchingType, $scope.benchmark, $scope.commissionMultiplier, $scope.slippage, $scope.lowp, $scope.highp);
                    }
                    if ($scope.highp == "") {
                        $("#stra-page-hint").html("已将参数上限设为默认值10");
                        $("#stra-page-hint").fadeIn().delay(1000).fadeOut(function () {
                            $scope.highp = "10";
                        });


                        loadOverReturnLine_ajax(sid, $scope.cash, $scope.sDate, $scope.eDate, "1d", $scope.matchingType, $scope.benchmark, $scope.commissionMultiplier, $scope.slippage, $scope.lowp, $scope.highp);
                    }

                },
                error: function (request, status, err) {
                    load.abort();
                }
            });
        };

        //参数调优3:加载第2、3个图片ajax方法，会调用echart的js方法
        function loadOverReturnLine_ajax(sid, cash, sDate, eDate, frequency, matchingType, benchmark, commissionMultiplier, slippage, lowp, highp) {

            this.sid = sid;
            this.cash = cash;
            this.sDate = sDate;
            this.eDate = eDate;
            this.frequency = frequency;
            this.matchingType = matchingType;
            this.benchmark = benchmark;
            this.commissionMultiplier = commissionMultiplier;
            this.slippage = slippage;
            this.lowp = lowp;
            this.highp = highp;

            $.ajax({
                type: "GET",
                url: "/backtest/find",
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
                    'low': this.lowp,
                    'high': this.highp
                },
                contentType: "application/x-www-form-urlencoded",
                dataType: "json",
                success: function (result) {
                    console.log(result);

                    if (result.msg == "success") {
                        overop = getOverOP(result.paramList, result.winRateList);
                        // overretChart.setOption(overop);
                        winop = getWinOP(result.paramList, result.overReturnsList);
                        // winChart.setOption(winop);

                        $("#log-content").html('<p>暂无错误 <i class="fa fa-smile-o"></i></p>');

                        $("#overreturn-area").fadeIn();
                        $("#win-area").fadeIn();
                        $("#overreturn-chart").fadeIn();
                        $("#win-chart").fadeIn();
                    } else {

                        if (result.msg == "error6") {
                            $("#log-content").html('<p>代码有语法错误 <i class="fa fa-frown-o"></i><br>' + result.errorLog + '</p>');
                        } else {
                            $("#log-content").html('<p>代码有误，错误编码：' + result.msg + ' <i class="fa fa-frown-o"></i></p>');
                        }

                        $("#overreturn-area").fadeOut();
                        $("#win-area").fadeOut();
                        $("#overreturn-chart").fadeOut();
                        $("#win-chart").fadeOut();
                    }

                    $("#result-area").fadeOut();
                    $("#return-area").fadeOut();
                    $("#return-chart").fadeOut();

                    $("#overreturn-spin").fadeOut();
                    $("#win-spin").fadeOut();

                },
                error: function (request, status, err) {
                    load.abort();
                }
            });
        }

    });

function showMoreButtons() {
    if ($("#show-more-buttons").html().indexOf("更多") >= 0) {
        $("#res-figure-area").fadeOut(function () {
            $("#more-res-buttons").fadeIn();
        });
        $("#show-more-buttons").html("<i class=\"fa fa-caret-up\"></i> 隐藏");
    } else {
        $("#more-res-buttons").fadeOut(function () {
            $("#res-figure-area").show();
        });
        $("#show-more-buttons").html("<i class=\"fa fa-caret-down\"></i> 更多");
    }

}