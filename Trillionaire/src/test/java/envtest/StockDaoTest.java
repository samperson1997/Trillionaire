package envtest;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import trillionaire.dao.RealTimeStockDao;
import trillionaire.dao.StockDao;
import trillionaire.dao.impl.RealTimeStockDaoImpl;
import trillionaire.dao.impl.StockDaoImpl;
import trillionaire.model.Stock;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by USER on 2017/6/10.
 */
public class StockDaoTest {

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

        StockDao stockDao = ctx.getBean(StockDaoImpl.class);
        List<Stock> list = stockDao.getAllStocks();
        System.out.println(list.size());
        System.out.println(list.get(0).getName());
    }

}
