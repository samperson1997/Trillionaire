package envtest;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import trillionaire.service.DataUpdateService;
import trillionaire.service.MinutePriceDataService;
import trillionaire.service.impl.DataUpdateServiceImpl;
import trillionaire.service.impl.MinutePriceDataServiceImpl;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by USER on 2017/6/6.
 */
public class DataUpdaterTest {

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

//        MinutePriceDataService minutePriceDataService = ctx.getBean(MinutePriceDataServiceImpl.class);
//        Map<String, Object> map = minutePriceDataService.getMinutePriceDate("000001");

        DataUpdateService dataUpdateService = ctx.getBean(DataUpdateServiceImpl.class);
        dataUpdateService.updateAllData();

    }

}
