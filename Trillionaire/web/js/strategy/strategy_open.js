var sid = getParam('sid');
var editor = ace.edit("code");

function loadStraContent() {
    if (sid > 0) {
        $("#stra-name").fadeIn(function () {
            namefade = 0;
        });

        var load = $.ajax({
            type: "GET",
            url: "/backtest/open_strategy",
            contentType: "application/x-www-form-urlencoded",
            data: {
                'sid': getParam('sid')
            },
            dataType: "json",
            success: function (data0) {
                editor.setValue(data0.strategyContent);
                editor.gotoLine(editor.session.getLength() + 1);
                $("#stra-name").text(data0.strategyName);
            },
            error: function (request, status, err) {
                if (status === "timeout") {
                    load.abort();
                    $("#stra-name").fadeOut(function () {
                        namefade = 1;
                    });
                }
            }
        })
    } else if (sid == -1) {
        editor.setValue("# 可以自己import我们平台支持的第三方python模块，比如pandas、numpy等。\n\n# 在这个方法中编写任何的初始化逻辑。context对象将会在你的算法策略的任何方法之间做传递。\ndef init(context):\n    context.s1 = \"000001.XSHE\"\n    # 实时打印日志\n    logger.info(\"Interested at stock: \" + str(context.s1))\n\n# before_trading此函数会在每天交易开始前被调用，当天只会被调用一次\ndef before_trading(context, bar_dict):\n    pass\n\n\n# 你选择的证券的数据更新将会触发此段逻辑，例如日或分钟历史数据切片或者是实时数据切片更新\ndef handle_bar(context, bar_dict):\n    # 开始编写你的主要的算法逻辑\n\n    # bar_dict[order_book_id] 可以拿到某个证券的bar信息\n    # context.portfolio 可以拿到现在的投资组合状态信息\n\n    # 使用order_shares(id_or_ins, amount)方法进行落单\n\n    # TODO: 开始编写你的算法吧！\n    order_shares(context.s1, 1000)");
        editor.gotoLine(editor.session.getLength() + 1);
    } else if (sid == -2) {
        editor.setValue("# 在这个方法中编写任何的初始化逻辑。context对象将会在你的算法策略的任何方法之间做传递。\ndef init(context):\n    context.s1 = \"000001.XSHE\"\n    update_universe(context.s1)\n    # 是否已发送了order\n    context.fired = False\n    context.cnt = 1\n\n\ndef before_trading(context, bar_dict):\n    context.cnt += 1\n\n\n# 你选择的证券的数据更新将会触发此段逻辑，例如日或分钟历史数据切片或者是实时数据切片更新\ndef handle_bar(context, bar_dict):\n    context.cnt += 1\n    # 开始编写你的主要的算法逻辑\n\n    # bar_dict[order_book_id] 可以拿到某个证券的bar信息\n    # context.portfolio 可以拿到现在的投资组合状态信息\n\n    # 使用order_shares(id_or_ins, amount)方法进行落单\n\n    # TODO: 开始编写你的算法吧！\n    if not context.fired:\n        # order_percent并且传入1代表买入该股票并且使其占有投资组合的100%\n        order_percent(context.s1, 1)\n        context.fired = True\n");
        editor.gotoLine(editor.session.getLength() + 1);
    } else if (sid == -3) {
        editor.setValue("import talib\n\n# 初始化函数\ndef init(context):\n    context.s1 = \"000001.XSHE\"\    context.SHORTPERIOD = 12\n    context.LONGPERIOD = 26\n    context.SMOOTHPERIOD = 9\n    context.OBSERVATION = 100\n    context.introduction = \'I am the most lovel\'\n\ndef handle_bar(context, bar_dict):\n    prices = history_bars(context.s1, context.OBSERVATION, '1d', 'close')\n\n    macd, signal, hist = talib.MACD(prices, context.SHORTPERIOD,context.LONGPERIOD, context.SMOOTHPERIOD)\n\n    if macd[-1] - signal[-1] > 0 and macd[-2] - signal[-2] < 0:\n        # 满仓入股\n        order_target_percent(context.s1, 1)\n        logger.info(context.introduction)\n\n    if macd[-1] - signal[-1] < 0 and macd[-2] - signal[-2] > 0:\n        # 获取该股票的仓位\n        curPosition = context.portfolio.positions[context.s1].quantity\n        # 清仓\n        if curPosition > 0:\n            order_target_value(context.s1, 0)");
        editor.gotoLine(editor.session.getLength() + 1);
    } else if (sid == -4) {
        editor.setValue("import talib\n\n# 在这个方法中编写任何的初始化逻辑。context对象将会在你的算法策略的任何方法之间做传递。\ndef init(context):\n    context.s1 = \"000001.XSHE\"\n\n    # 设置这个策略当中会用到的参数，在策略中可以随时调用，这个策略使用长短均线，我们在这里设定长线和短线的区间，在调试寻找最佳区间的时候只需要在这里进行数值改动\n    context.SHORTPERIOD = 20\n    context.LONGPERIOD = 120\n\n\n# 你选择的证券的数据更新将会触发此段逻辑，例如日或分钟历史数据切片或者是实时数据切片更新\ndef handle_bar(context, bar_dict):\n    # 开始编写你的主要的算法逻辑\n\n    # bar_dict[order_book_id] 可以拿到某个证券的bar信息\n    # context.portfolio 可以拿到现在的投资组合状态信息\n\n    # 使用order_shares(id_or_ins, amount)方法进行落单\n\n    # TODO: 开始编写你的算法吧！\n\n    # 因为策略需要用到均线，所以需要读取历史数据\n    prices = history_bars(context.s1, context.LONGPERIOD+1, '1d', 'close')\n\n    # 使用talib计算长短两根均线，均线以array的格式表达\n    short_avg = talib.SMA(prices, context.SHORTPERIOD)\n    long_avg = talib.SMA(prices, context.LONGPERIOD)\n\n    plot(\"short avg\", short_avg[-1])\n    plot(\"long avg\", long_avg[-1])\n\n    # 计算现在portfolio中股票的仓位\n    cur_position = context.portfolio.positions[context.s1].quantity\n    # 计算现在portfolio中的现金可以购买多少股票\n    shares = context.portfolio.cash/bar_dict[context.s1].close\n\n    # 如果短均线从上往下跌破长均线，也就是在目前的bar短线平均值低于长线平均值，而上一个bar的短线平均值高于长线平均值\n    if short_avg[-1] - long_avg[-1] < 0 and short_avg[-2] - long_avg[-2] > 0 and cur_position > 0:\n        # 进行清仓\n        order_target_value(context.s1, 0)\n\n    # 如果短均线从下往上突破长均线，为入场信号\n    if short_avg[-1] - long_avg[-1] > 0 and short_avg[-2] - long_avg[-2] < 0:\n        # 满仓入股\n        order_shares(context.s1, shares)");
        editor.gotoLine(editor.session.getLength() + 1);
    } else if (sid == -5) {
        editor.setValue("# 在这个方法中编写任何的初始化逻辑。context对象将会在你的算法策略的任何方法之间做传递。\ndef init(context):\n    #沪深300指数、中证500指数和国债指数\n    context.stocks = [\"000300.XSHG\",\"000905.XSHG\",\"000012.XSHG\"]\n# before_trading此函数会在每天交易开始前被调用，当天只会被调用一次\n# 你选择的证券的数据更新将会触发此段逻辑，例如日或分钟历史数据切片或者是实时数据切片更新\ndef handle_bar(context, bar_dict):\n    # 开始编写你的主要的算法逻辑\n    hs300 = history_bars(context.stocks[0],20,\"1d\",\"close\")\n    zz500 = history_bars(context.stocks[1],20,\"1d\",\"close\")\n    hsIncrease = hs300[19] - hs300[0]\n    zzIncrease = zz500[19] - zz500[0]\n    p = context.portfolio.positions\n    hsQuality = p[context.stocks[0]].quantity\n    zzQuality = p[context.stocks[1]].quantity\n    gzQuality = p[context.stocks[2]].quantity\n    if hsIncrease < 0 and zzIncrease < 0:\n        if hsQuality > 0:\n            order_target_percent(context.stocks[0],0)\n        if zzQuality > 0:\n            order_target_percent(context.stocks[1],0)\n        if gzQuality <= 0.001:\n            order_target_percent(context.stocks[2],1)\n    elif hsIncrease < zzIncrease:\n        if hsQuality > 0:\n            order_target_percent(context.stocks[0],0)\n        if gzQuality > 0:\n            order_target_percent(context.stocks[2],0)\n        if zzQuality <= 0.001:\n            order_target_percent(context.stocks[1],1)\n    else:\n        if zzQuality > 0:\n            order_target_percent(context.stocks[1],0)\n        if gzQuality > 0:\n            order_target_percent(context.stocks[2],0)\n        if hsQuality <= 0.01:\n            order_target_percent(context.stocks[0],1)\n");
        editor.gotoLine(editor.session.getLength() + 1);
    }


}
