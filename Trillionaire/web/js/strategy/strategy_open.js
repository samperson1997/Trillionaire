var sid = getParam('sid');
var editor = ace.edit("code");

function loadStraContent() {
    if (sid > 0) {
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
            },
            error: function (request, status, err) {
                if (status == "timeout") {
                    load.abort();
                }
            }
        })
    } else if (sid == -1) {
        editor.setValue("# 可以自己import我们平台支持的第三方python模块，比如pandas、numpy等。\n\n# 在这个方法中编写任何的初始化逻辑。context对象将会在你的算法策略的任何方法之间做传递。\ndef init(context):\n    # 在context中保存全局变量\n    context.s1 = \"000001.XSHE\"\n    # 实时打印日志\n    logger.info(\"RunInfo: {}\".format(context.run_info))\n\n# before_trading此函数会在每天策略交易开始前被调用，当天只会被调用一次\ndef before_trading(context):\n    pass\n\n\n# 你选择的证券的数据更新将会触发此段逻辑，例如日或分钟历史数据切片或者是实时数据切片更新\ndef handle_bar(context, bar_dict):\n    # 开始编写你的主要的算法逻辑\n\n    # bar_dict[order_book_id] 可以拿到某个证券的bar信息\n    # context.portfolio 可以拿到现在的投资组合信息\n\n    # 使用order_shares(id_or_ins, amount)方法进行落单\n\n    # TODO: 开始编写你的算法吧！\n    order_shares(context.s1, 1000)603580.XSHG\n\n# after_trading函数会在每天交易结束后被调用，当天只会被调用一次\ndef after_trading(context):\n    pass");
        editor.gotoLine(editor.session.getLength() + 1);
    } else if (sid == -2) {
        editor.setValue("# 可以自己import我们平台支持的第三方python模块，比如pandas、numpy等。\n\n# 在这个方法中编写任何的初始化逻辑。context对象将会在你的算法策略的任何方法之间做传递。\ndef init(context):\n    context.s1 = \"000001.XSHE\"\n    # 实时打印日志\n    logger.info(\"Interested at stock: \" + str(context.s1))\n\n# before_trading此函数会在每天交易开始前被调用，当天只会被调用一次\ndef before_trading(context, bar_dict):\n    pass\n\n\n# 你选择的证券的数据更新将会触发此段逻辑，例如日或分钟历史数据切片或者是实时数据切片更新\ndef handle_bar(context, bar_dict):\n    # 开始编写你的主要的算法逻辑\n\n    # bar_dict[order_book_id] 可以拿到某个证券的bar信息\n    # context.portfolio 可以拿到现在的投资组合状态信息\n\n    # 使用order_shares(id_or_ins, amount)方法进行落单\n\n    # TODO: 开始编写你的算法吧！\n    order_shares(context.s1, 1000)");
        editor.gotoLine(editor.session.getLength() + 1);
    } else if (sid == -3) {
        editor.setValue("import talib\n\n# 初始化函数\ndef init(context):\n    context.s1 = \"000001.XSHE\"\    context.SHORTPERIOD = 12\n    context.LONGPERIOD = 26\n    context.SMOOTHPERIOD = 9\n    context.OBSERVATION = 100\n    context.introduction = \'I am the most lovel\'\n\ndef handle_bar(context, bar_dict):\n    prices = history_bars(context.s1, context.OBSERVATION, '1d', 'close')\n\n    macd, signal, hist = talib.MACD(prices, context.SHORTPERIOD,context.LONGPERIOD, context.SMOOTHPERIOD)\n\n    if macd[-1] - signal[-1] > 0 and macd[-2] - signal[-2] < 0:\n        # 满仓入股\n        order_target_percent(context.s1, 1)\n        logger.info(context.introduction)\n\n    if macd[-1] - signal[-1] < 0 and macd[-2] - signal[-2] > 0:\n        # 获取该股票的仓位\n        curPosition = context.portfolio.positions[context.s1].quantity\n        # 清仓\n        if curPosition > 0:\n            order_target_value(context.s1, 0)");
        editor.gotoLine(editor.session.getLength() + 1);
    } else if (sid == -4) {
        editor.setValue("# 可以自己import我们平台支持的第三方python模块，比如pandas、numpy等。\nimport pandas as pd\nimport numpy as np\nimport datetime\nimport math\n# 在这个方法中编写任何的初始化逻辑。context对象将会在你的算法策略的任何方法之间做传递。\ndef init(context):\n    scheduler.run_monthly(rebalance,8)\n# 你选择的证券的数据更新将会触发此段逻辑，例如日或分钟历史数据切片或者是实时数据切片更新\ndef handle_bar(context, bar_dict):\n    pass\ndef before_trading(context):\n    num_stocks = 10\n    fundamental_df = get_fundamentals(\n        query(\n            fundamentals.eod_derivative_indicator.pb_ratio,\n            fundamentals.eod_derivative_indicator.pe_ratio,\n        )\n        .filter(\n           fundamentals.eod_derivative_indicator.pe_ratio<20\n        )\n        .filter(\n            fundamentals.eod_derivative_indicator.pb_ratio<1.8\n        )\n        .order_by(\n            fundamentals.eod_derivative_indicator.market_cap.desc()\n        ).limit(\n            num_stocks\n        )\n    )\n    context.fundamental_df = fundamental_df\n    context.stocks = context.fundamental_df.columns.values\n\ndef rebalance(context,bar_dict):\n    for stock in context.portfolio.positions:\n        if stock not in context.fundamental_df:\n            order_target_percent(stock, 0)\n    weight = update_weights(context, context.stocks)\n    for stock in context.fundamental_df:\n        if weight != 0 and stock in context.fundamental_df:\n            order_target_percent(stock,weight)\n\ndef update_weights(context,stocks):\n\n    if len(stocks) == 0:\n\n        return 0\n\n    else:\n\n        weight = .95/len(stocks)\n        return weight\n");
        editor.gotoLine(editor.session.getLength() + 1);
    } else if (sid == -5) {
        editor.setValue("# 在这个方法中编写任何的初始化逻辑。context对象将会在你的算法策略的任何方法之间做传递。\ndef init(context):\n    #沪深300指数、中证500指数和国债指数\n    context.stocks = [\"000300.XSHG\",\"000905.XSHG\",\"000012.XSHG\"]\n# before_trading此函数会在每天交易开始前被调用，当天只会被调用一次\n# 你选择的证券的数据更新将会触发此段逻辑，例如日或分钟历史数据切片或者是实时数据切片更新\ndef handle_bar(context, bar_dict):\n    # 开始编写你的主要的算法逻辑\n    hs300 = history_bars(context.stocks[0],20,\"1d\",\"close\")\n    zz500 = history_bars(context.stocks[1],20,\"1d\",\"close\")\n    hsIncrease = hs300[19] - hs300[0]\n    zzIncrease = zz500[19] - zz500[0]\n    p = context.portfolio.positions\n    hsQuality = p[context.stocks[0]].quantity\n    zzQuality = p[context.stocks[1]].quantity\n    gzQuality = p[context.stocks[2]].quantity\n    if hsIncrease < 0 and zzIncrease < 0:\n        if hsQuality > 0:\n            order_target_percent(context.stocks[0],0)\n            logger.info(\"卖出沪深300\")\n        if zzQuality > 0:\n            order_target_percent(context.stocks[1],0)\n            logger.info(\"卖出中证500\")\n        if gzQuality <= 0.001:\n            order_target_percent(context.stocks[2],1)\n            logger.info(\"买入国债\")\n    elif hsIncrease < zzIncrease:\n        if hsQuality > 0:\n            order_target_percent(context.stocks[0],0)\n            logger.info(\"卖出沪深300\")\n        if gzQuality > 0:\n            order_target_percent(context.stocks[2],0)\n            logger.info(\"卖出国债\")\n        if zzQuality <= 0.001:\n            order_target_percent(context.stocks[1],1)\n            logger.info(\"买入中证500\")\n    else:\n        if zzQuality > 0:\n            order_target_percent(context.stocks[1],0)\n            logger.info(\"卖出中证500\")\n        if gzQuality > 0:\n            order_target_percent(context.stocks[2],0)\n            logger.info(\"卖出国债\")\n        if hsQuality <= 0.01:\n            order_target_percent(context.stocks[0],1)\n            logger.info(\"买入沪深300\")");
        editor.gotoLine(editor.session.getLength() + 1);
    }


}
