package trillionaire.util;

import org.junit.Test;

import java.sql.Date;

/**
 * Created by michaeltan on 2017/6/1.
 */
public class UtilTest {

    @Test
    public void test(){
        int code = 600435;
        System.out.println(CodeUtil.TransferCode(code));

    }
}
