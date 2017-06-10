package trillionaire.vo;

import trillionaire.util.CodeUtil;

/**
 * Created by michaeltan on 2017/6/10.
 */
public class SimilarStock {
    private String stock1;
    private String stock2;
    private double confidence;
    private double support;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SimilarStock(String stock1, String stock2, String support, String confidence, int code) {
        this.confidence = Double.valueOf(confidence);
        this.support = Double.valueOf(support);
        this.stock1 = stock1;
        this.stock2 = stock2;
        this.code = CodeUtil.TransferCode(code);
    }

    public String getStock1() {
        return stock1;
    }

    public void setStock1(String stock1) {
        this.stock1 = stock1;
    }

    public String getStock2() {
        return stock2;
    }

    public void setStock2(String stock2) {
        this.stock2 = stock2;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public double getSupport() {
        return support;
    }

    public void setSupport(double support) {
        this.support = support;
    }

}
