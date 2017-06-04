package trillionaire.util;

/**
 * Created by michaeltan on 2017/5/9.
 */
public enum UserState {
    SUCCESS,
    FAIL,
    LOGOUT;

    @Override
    public String toString() {
        switch (this) {
            case SUCCESS:
                return  "success";
            case FAIL:
                return "fail";
            case LOGOUT:
                return "logout";
            default:
                return null;
        }
    }
}
