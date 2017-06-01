package trillionaire.vo;

/**
 * Created by USER on 2017/5/31.
 */
public class BackTestParams {

    public String cash;
    public String sDate;
    public String eDate;
    public String frequency;
    public String matchingType;
    public String benchmark;
    public String commissionMultiplier;
    public String slippage;

    public BackTestParams(){
        cash = "100000";
        sDate = "2014-01-01";
        eDate = "2015-01-01";
        frequency = "1d";
        matchingType = "current_bar";
        benchmark = "000300.XSHG";
        commissionMultiplier = "1";
        slippage = "0";
    }


}
