package trillionaire.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by USER on 2017/5/27.
 */
public class SimpleKey implements Serializable{

    private static final long serialVersionUID = 1L;

    private int code;
    private Date date;

    public SimpleKey(){

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + Integer.valueOf(code).hashCode();
        result = PRIME * result + date.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj){

        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof SimpleKey)) return false;

        SimpleKey simpleKey = (SimpleKey)obj;

        if(code==simpleKey.getCode() && date.equals(simpleKey.getDate()) ) return true;

        return false;
    }

}
