package trillionaire.util;


/**
 * Created by michaeltan on 2017/6/10.
 */
public class CodeUtil {
    public static String TransferCode(int code) {
        StringBuilder result = new StringBuilder(String.valueOf(code));
            int times = 6 - result.length();
            for (int i = 0; i < times; i++) {
                result.insert(0, "0");
            }
        return result.toString();
    }
}
