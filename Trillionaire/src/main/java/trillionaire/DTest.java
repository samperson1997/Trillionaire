package trillionaire;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import trillionaire.model.User;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by michaeltan on 2017/5/14.
 */

public class DTest {
    private ApplicationContext ctx = null;

    @Test
    public void testDataSource() throws SQLException {
        //检查spring配置
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        //检查数据库连接
        DataSource dataSource = ctx.getBean(DataSource.class);

        //检查hibernate配置
        SessionFactory sessionFactory = ctx.getBean(SessionFactory.class);
        System.out.println(sessionFactory);

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //测试数据库
        User user = new User("nju123", "tx", "123456");
        session.save(user);
        tx.commit();
        session.close();

    }
}
