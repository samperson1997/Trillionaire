package trillionaire;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jcp.xml.dsig.internal.dom.DOMBase64Transform;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import trillionaire.dao.DayRecordDao;
import trillionaire.dao.StockDao;
import trillionaire.dao.impl.DayRecordDaoImpl;
import trillionaire.dao.impl.StockDaoImpl;
import trillionaire.model.DayRecord;
import trillionaire.model.Stock;
import trillionaire.model.Strategy;
import trillionaire.model.User;
import trillionaire.service.DataUpdateService;
import trillionaire.service.MinutePriceDataService;
import trillionaire.service.impl.DataUpdateServiceImpl;
import trillionaire.service.impl.MinutePriceDataServiceImpl;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by michaeltan on 2017/5/14.
 */

public class DTest {
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

//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();

//        User user = session.get(User.class, 1);
//        System.out.println(user.getConcernedStocks().size() + "!!!!!!");
//        System.out.println(user.getStrategies().size()+ "!!!!!!");

//        Strategy strategy = session.get(Strategy.class,1);
//        session.delete(strategy);

//          session.save(user);
//
//        tx.commit();
//        session.close();
//        sessionFactory.close();


        MinutePriceDataService minutePriceDataService = ctx.getBean(MinutePriceDataServiceImpl.class);
        Map<String, Object> map = minutePriceDataService.getMinutePriceDate("000001");

        List<String> time = (List<String>) (map.get("time"));
        List<Double> price = (List<Double>) (map.get("price"));
        List<Double> meanPrice = (List<Double>) (map.get("meanPrice"));
        List<Double> volume = (List<Double>) (map.get("volume"));

        System.out.println(time.size() + " " + price.size() + " " + meanPrice.size() + " " + volume.size());

        for(int i=0; i<time.size(); i++){
            System.out.print(time.get(i) + " ");
            System.out.print(price.get(i) + " ");
            System.out.print(meanPrice.get(i) + " ");
            System.out.println(volume.get(i) + " ");
        }

//        DataUpdateService dataUpdateService = ctx.getBean(DataUpdateServiceImpl.class);
//        dataUpdateService.updateAllData();


    }
}
