package envtest;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import trillionaire.service.MinutePriceDataService;
import trillionaire.service.impl.MinutePriceDataServiceImpl;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by USER on 2017/6/6.
 */
public class MinuteTest {

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

        MinutePriceDataService minutePriceDataService = ctx.getBean(MinutePriceDataServiceImpl.class);
        long t1 = System.currentTimeMillis();
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

        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);

    }

}
