package trillionaire.vo;

import java.util.Date;

/**
 * Created by USER on 2017/6/4.
 */
public class StraIdName {

    private int sid;
    private String strategName;
    private Date updateTime;

    public StraIdName(){

    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getStrategName() {
        return strategName;
    }

    public void setStrategName(String strategName) {
        this.strategName = strategName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
