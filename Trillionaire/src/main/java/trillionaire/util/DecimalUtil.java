package trillionaire.util;

import java.text.DecimalFormat;

/**
 * Created by michaeltan on 2017/5/16.
 */
public class DecimalUtil {
    public static String RemainTwoDecimal(double num){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String string = decimalFormat.format(num);
        return string;
    }

    public static String TransferToPercent(double num){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String string = decimalFormat.format(num);
        string = string + "%";
        return string;
    }

}
