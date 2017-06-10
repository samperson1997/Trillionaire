package trillionaire.vo;

/**
 * Created by michaeltan on 2017/6/10.
 */
public class AssociateStock {
    private String name;
    private String code;

    public AssociateStock(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
