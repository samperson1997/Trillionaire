package trillionaire.vo;

import trillionaire.model.Concept;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by michaeltan on 2017/6/7.
 */
public class StockSquare {
    private double vr;
    private String concept;
    private String area;
    private String industry;

    public StockSquare(double vr, Set<Concept> concept, String area, String industry) {
        this.vr = vr;
        this.concept = transfer(concept);
        this.area = area;
        this.industry = industry;
    }

    public double getVr() {
        return vr;
    }

    public void setVr(double vr) {
        this.vr = vr;
    }

    private String transfer(Set<Concept> concept) {
        String s = "";
        Iterator<Concept> it = concept.iterator();
        while (it.hasNext()) {
            s = s + "," + it.next().getName();
            System.out.println(s);
        }
        s = s.substring(1);
        return s;
    }

    public String getConcept() {
        return concept;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
