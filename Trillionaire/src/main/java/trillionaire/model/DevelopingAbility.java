package trillionaire.model;

import javax.persistence.*;

/**
 * Created by USER on 2017/5/24.
 */
@Entity
@Table(name = "developing_ability")
@IdClass(CodeYearQuarterKey.class)
public class DevelopingAbility {

    private int code;
    private int year;
    private int quarter;

    private double mbrg; //主营业务收入增长率(%)
    private double nprg; //净利润增长率(%)
    private double nav; //净资产增长率
    private double targ; //总资产增长率
    private double epsg; //每股收益增长率
    private double seg; //股东权益增长率


    @Id
    @Column(name = "code")
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Id
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Id
    @Column(name = "quarter")
    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    @Column(name = "mbrg")
    public double getMbrg() {
        return mbrg;
    }

    public void setMbrg(double mbrg) {
        this.mbrg = mbrg;
    }

    @Column(name = "nprg")
    public double getNprg() {
        return nprg;
    }

    public void setNprg(double nprg) {
        this.nprg = nprg;
    }

    @Column(name = "nav")
    public double getNav() {
        return nav;
    }

    public void setNav(double nav) {
        this.nav = nav;
    }

    @Column(name = "targ")
    public double getTarg() {
        return targ;
    }

    public void setTarg(double targ) {
        this.targ = targ;
    }

    @Column(name = "epsg")
    public double getEpsg() {
        return epsg;
    }

    public void setEpsg(double epsg) {
        this.epsg = epsg;
    }

    @Column(name = "seg")
    public double getSeg() {
        return seg;
    }

    public void setSeg(double seg) {
        this.seg = seg;
    }



}
