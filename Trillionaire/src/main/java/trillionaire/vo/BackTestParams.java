package trillionaire.vo;

/**
 * Created by USER on 2017/5/31.
 */
public class BackTestParams {

    public int sid;
    public String cash;
    public String sDate;
    public String eDate;
    public String frequency;
    public String matchingType;
    public String benchmark;
    public String commissionMultiplier;
    public String slippage;


    public BackTestParams(){
        sid = 1;
        cash = "100000";
        sDate = "2014-01-01";
        eDate = "2015-01-01";
        frequency = "1d";
        matchingType = "current_bar";
        benchmark = "000300.XSHG";
        commissionMultiplier = "1";
        slippage = "0";
    }



    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String geteDate() {
        return eDate;
    }

    public void seteDate(String eDate) {
        this.eDate = eDate;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getMatchingType() {
        return matchingType;
    }

    public void setMatchingType(String matchingType) {
        this.matchingType = matchingType;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public String getCommissionMultiplier() {
        return commissionMultiplier;
    }

    public void setCommissionMultiplier(String commissionMultiplier) {
        this.commissionMultiplier = commissionMultiplier;
    }

    public String getSlippage() {
        return slippage;
    }

    public void setSlippage(String slippage) {
        this.slippage = slippage;
    }

    @Override
    public String toString() {
        return "BackTestParams{" +
                "sid=" + sid +
                ", cash='" + cash + '\'' +
                ", sDate='" + sDate + '\'' +
                ", eDate='" + eDate + '\'' +
                ", frequency='" + frequency + '\'' +
                ", matchingType='" + matchingType + '\'' +
                ", benchmark='" + benchmark + '\'' +
                ", commissionMultiplier='" + commissionMultiplier + '\'' +
                ", slippage='" + slippage + '\'' +
                '}';
    }


}
