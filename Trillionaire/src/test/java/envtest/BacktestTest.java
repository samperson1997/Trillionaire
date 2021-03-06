package envtest;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import trillionaire.model.Strategy;
import trillionaire.service.BackTestService;
import trillionaire.service.impl.backtest.BackTestServiceImpl;
import trillionaire.vo.BackTestParams;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by USER on 2017/6/9.
 */
public class BacktestTest {

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

        BackTestService backTestService = ctx.getBean(BackTestServiceImpl.class);

        BackTestParams params = new BackTestParams();
        params.setSid(7);

        long t1 = System.currentTimeMillis();
        Map<String,Object> map = backTestService.startFindBestParams(params, 18, 21);
        System.out.println(map.get("msg"));
        System.out.println(map.get("winRateList"));
        List<Double> list = (List<Double>) map.get("overReturnsList");
        for(double d:list){
            System.out.println(d);
        }
        System.out.println(list.size());
        long t2 = System.currentTimeMillis();

//        System.out.println(map.get("errorLog"));

//        System.out.println(backTestService.addStrategy(1, "sss","ddd"));

//        List<String> date = (List<String>) map.get("datelist");
//        for(String s: date){
//            System.out.println(s);
//        }

    }

}
