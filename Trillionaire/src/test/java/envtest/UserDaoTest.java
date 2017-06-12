package envtest;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import trillionaire.dao.UserDao;
import trillionaire.dao.impl.UserDaoImpl;

import javax.sql.DataSource;

/**
 * Created by USER on 2017/6/12.
 */
public class UserDaoTest {

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

        UserDao userDao = ctx.getBean(UserDaoImpl.class);
        userDao.deleteConcernedStock(1,1);


    }

}
