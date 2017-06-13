package envtest;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import trillionaire.dao.ConceptDao;
import trillionaire.dao.impl.ConceptDaoImpl;
import trillionaire.service.BackTestService;
import trillionaire.service.impl.backtest.BackTestServiceImpl;
import trillionaire.vo.BackTestParams;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by USER on 2017/6/12.
 */
public class AbilityTest {

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

        ConceptDao conceptDao = ctx.getBean(ConceptDaoImpl.class);
        System.out.println(conceptDao.getAllArea());


    }

}
