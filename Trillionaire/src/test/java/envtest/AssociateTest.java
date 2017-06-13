package envtest;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import trillionaire.service.StockService;
import trillionaire.service.impl.StockServiceImpl;
import trillionaire.vo.AssociateStock;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by USER on 2017/6/13.
 */
public class AssociateTest {

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


        StockService stockService = ctx.getBean(StockServiceImpl.class);
        long t1 = System.currentTimeMillis();
        List<AssociateStock> list = stockService.associate("000001");
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        t1 = System.currentTimeMillis();
        List<AssociateStock> list2 = stockService.associate("0");
        for(AssociateStock a: list2){
            System.out.println(a.getName());
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
    }

}
