package trillionaire.vo;

/**
 * Created by michaeltan on 2017/6/13.
 */
public class BoardVO {
    private int id;
    private String name;

    public BoardVO(int id, String name) {

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
