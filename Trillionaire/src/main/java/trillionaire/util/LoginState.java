package trillionaire.util;

/**
 * Created by michaeltan on 2017/5/9.
 */
public enum LoginState {
    LOGIN_SUCCESS,
    LOGIN_FAIL,
    LOGOUT;

    @Override
    public String toString() {
        switch (this) {
            case LOGIN_SUCCESS:
                return  "success";
            case LOGIN_FAIL:
                return "fail";
            case LOGOUT:
                return "logout";
            default:
                return null;
        }
    }
}
