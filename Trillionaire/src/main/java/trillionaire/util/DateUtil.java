package trillionaire.util;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by michaeltan on 2017/5/6.
 */
public class DateUtil {
    public static int getMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        return month;
    }
}
