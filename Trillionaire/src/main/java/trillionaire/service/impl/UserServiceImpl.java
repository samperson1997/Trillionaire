package trillionaire.service.impl;

import org.springframework.stereotype.Service;
import trillionaire.service.UserService;
import trillionaire.util.LoginState;

/**
 * Created by michaeltan on 2017/5/9.
 */
@Service
public class UserServiceImpl implements UserService{


    public LoginState login(String id, String password) {
        return null;
    }

    public LoginState logout(String id) {
        return null;
    }

    public LoginState resetPassword(String email, String newPassword) {
        return null;
    }

    public LoginState resetPassword(String id, String oldPassword, String newPassword) {
        return null;
    }

    public boolean find(String email) {
        return false;
    }

    public boolean verify(String randomCode) {
        return false;
    }

    public boolean follow(String email, String code) {
        return false;
    }

    public boolean checkfollow(String email, String code) {
        return false;
    }
}
