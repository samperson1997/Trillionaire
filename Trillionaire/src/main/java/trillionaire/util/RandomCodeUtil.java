package trillionaire.util;

import java.util.UUID;

/**
 * Created by michaeltan on 2017/5/15.
 */
public class RandomCodeUtil {
    public static String generateUniqueCode(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
