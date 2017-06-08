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

        backTestService.saveStrategy(5, "time test","yyyyes\n\n\n");
        Strategy strategy = backTestService.openStrategy(5);
        System.out.println(strategy.getChangingTime());
//
//        backTestService.saveStrategy(5,"new", "new");
//        backTestService.deletStrategy(4);
//        System.out.println(backTestService.getMyStrategy(1).size());

    }

}
