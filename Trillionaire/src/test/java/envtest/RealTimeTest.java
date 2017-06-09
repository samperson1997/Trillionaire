package envtest;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import trillionaire.dao.RealTimeStockDao;
import trillionaire.dao.impl.RealTimeStockDaoImpl;
import trillionaire.service.MinutePriceDataService;
import trillionaire.service.impl.MinutePriceDataServiceImpl;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by USER on 2017/6/6.
 */
public class RealTimeTest {

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

        RealTimeStockDao realTimeStockDao = ctx.getBean(RealTimeStockDaoImpl.class);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(realTimeStockDao.getRealTimeByCode(603833).getCode() + "  " + realTimeStockDao.getRealTimeByCode(603833).getName() + "  " + realTimeStockDao.getRealTimeByCode(603833).getHigh());
        System.out.println(realTimeStockDao.getAll().size());
    }

}
