package envtest;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import trillionaire.dao.DayRecordDao;
import trillionaire.dao.impl.DayRecordDaoImpl;
import trillionaire.model.DayRecord;
import trillionaire.model.Strategy;
import trillionaire.model.WeekRecord;
import trillionaire.service.BackTestService;
import trillionaire.service.impl.backtest.BackTestServiceImpl;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by USER on 2017/6/8.
 */
public class DayRecordTest {

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

        DayRecordDao dayRecordDao = ctx.getBean(DayRecordDaoImpl.class);
//        WeekRecord weekRecord = dayRecordDao.getWeekRecordsByCode(2).get(dayRecordDao.getWeekRecordsByCode(2).size()-1);
//        weekRecord.setDate(Date.valueOf(LocalDate.of(2020,1,2)));
//        dayRecordDao.saveWeekRecord(weekRecord);

        long t1 = System.currentTimeMillis();
        //System.out.println(dayRecordDao.getDayRecords(2,100).size());

        List<List<DayRecord>> l = dayRecordDao.getAligningDayRecords(1,1,150);
        System.out.println(l.get(0).get(0).getClass());
//
//        List<DayRecord> list = dayRecordDao.getDayRecordBeforeDate(1,LocalDate.of(2017,5,1), 30);
//        System.out.println(list.get(0).getChange());

        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

    }

}
