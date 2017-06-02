package trillionaire.service.impl.backtest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import trillionaire.dao.DayRecordDao;
import trillionaire.dao.StockDao;
import trillionaire.dao.impl.DayRecordDaoImpl;
import trillionaire.dao.impl.StockDaoImpl;
import trillionaire.model.DayRecord;
import trillionaire.model.Stock;
import trillionaire.model.User;
import trillionaire.service.BackTestService;
import trillionaire.service.DataUpdateService;
import trillionaire.service.impl.DataUpdateServiceImpl;
import trillionaire.vo.BackTestParams;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Created by michaeltan on 2017/5/14.
 */

public class STest {
    private ApplicationContext ctx = null;

    @Test
    public void testDataSource() throws SQLException, ParseException {
        //检查spring配置
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        //检查数据库连接
        DataSource dataSource = ctx.getBean(DataSource.class);

        //检查hibernate配置
        SessionFactory sessionFactory = ctx.getBean(SessionFactory.class);
        System.out.println(sessionFactory);

        BackTestService backTestService = ctx.getBean(BackTestServiceImpl.class);
        BackTestParams params = new BackTestParams();
        backTestService.startBackTest(params);

    }
}
