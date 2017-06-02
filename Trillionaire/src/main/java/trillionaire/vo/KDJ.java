package trillionaire.vo;

import java.sql.Date;

/**
 * Created by michaeltan on 2017/6/2.
 */
public class KDJ {
    private String K;
    private String D;
    private String J;
    private Date date;

    public String getK() {
        return K;
    }

    public void setK(String k) {
        K = k;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getJ() {
        return J;
    }

    public void setJ(String j) {
        J = j;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public KDJ(String k, String d, String j, Date date) {

        K = k;
        D = d;
        J = j;
        this.date = date;
    }
}
