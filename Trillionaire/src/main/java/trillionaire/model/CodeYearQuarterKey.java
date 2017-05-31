package trillionaire.model;

import java.io.Serializable;

/**
 * Created by USER on 2017/5/24.
 */
public class CodeYearQuarterKey implements Serializable{

    private static final long serialVersionUID = 1L;

    private int code;
    private int year;
    private int quarter;

    public CodeYearQuarterKey(){

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + Integer.valueOf(code).hashCode();
        result = PRIME * result + Integer.valueOf(year).hashCode();
        result = PRIME * result + Integer.valueOf(quarter).hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj){

        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof CodeYearQuarterKey)) return false;

        CodeYearQuarterKey codeYearQuarterKey = (CodeYearQuarterKey)obj;

        if(code==codeYearQuarterKey.getCode() && year==codeYearQuarterKey.getYear() && quarter==codeYearQuarterKey.getQuarter()) return true;

        return false;
    }

}
