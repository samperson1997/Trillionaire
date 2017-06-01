package trillionaire.util;

/**
 * Created by USER on 2017/5/31.
 */
public enum BackTestResult {

    SUCCESS,
    ERROR;

    @Override
    public String toString(){

        switch (this){
            case ERROR:
                return "error";
            case SUCCESS:
                return "success";
            default:
                return "none";
        }

    }

}
