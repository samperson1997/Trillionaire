package envtest;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import trillionaire.dao.RealTimeStockDao;
import trillionaire.dao.StrategyDao;
import trillionaire.dao.impl.RealTimeStockDaoImpl;
import trillionaire.dao.impl.StrategyDaoImpl;
import trillionaire.model.Strategy;
import trillionaire.service.BackTestService;
import trillionaire.service.impl.backtest.BackTestServiceImpl;

import javax.sql.DataSource;

/**
 * Created by USER on 2017/6/8.
 */
public class StrategyDaoTest {

    private ApplicationContext ctx = null;

    @Test
    public void test(){

        //检查spring配置
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        //检查数据库连接
        DataSource dataSource = ctx.getBean(DataSource.class);

        //检查hibernate配置
        SessionFactory sessionFactory = ctx.getBean(SessionFactory.class);
        System.out.println(sessionFactory);

        //StrategyDao strategyDao = ctx.getBean(StrategyDaoImpl.class);
        BackTestService backTestService = ctx.getBean(BackTestServiceImpl.class);
        //backTestService.addStrategy(1, "TabStra", "java1\np\tython\n\np y thon\n");
        //backTestService.addStrategy(1, "secondStra", "java2\npython\n\np y thon\n");
        //backTestService.addStrategy(1, "3thStra", "java3\npython33\n\np y thon\n");

        backTestService.saveStrategy(3, "full", "import talib\n" +
                "\n" +
                "# 初始化函数\n" +
                "def init(context):\n" +
                "    context.s1 = \"000001.XSHE\"\n    context.SHORTPERIOD = 12\n" +
                "    context.LONGPERIOD = 26\n" +
                "    context.SMOOTHPERIOD = 9\n" +
                "    context.OBSERVATION = 100\n" +
                "    context.introduction = \'I am the most lovel\'\n" +
                "\n" +
                "def handle_bar(context, bar_dict):\n" +
                "    prices = history_bars(context.s1, context.OBSERVATION, '1d', 'close')\n" +
                "\n" +
                "    macd, signal, hist = talib.MACD(prices, context.SHORTPERIOD,context.LONGPERIOD, context.SMOOTHPERIOD)\n" +
                "\n" +
                "    if macd[-1] - signal[-1] > 0 and macd[-2] - signal[-2] < 0:\n" +
                "        # 满仓入股\n" +
                "        order_target_percent(context.s1, 1)\n" +
                "        logger.info(context.introduction)\n" +
                "\n" +
                "    if macd[-1] - signal[-1] < 0 and macd[-2] - signal[-2] > 0:\n" +
                "        # 获取该股票的仓位\n" +
                "        curPosition = context.portfolio.positions[context.s1].quantity\n" +
                "        # 清仓\n" +
                "        if curPosition > 0:\n" +
                "            order_target_value(context.s1, 0)");
        Strategy strategy = backTestService.openStrategy(3);
        System.out.println(strategy.getChangingTime());
//
//        backTestService.saveStrategy(5,"new", "new");
//        backTestService.deletStrategy(4);
//        System.out.println(backTestService.getMyStrategy(1).size());

    }

}
