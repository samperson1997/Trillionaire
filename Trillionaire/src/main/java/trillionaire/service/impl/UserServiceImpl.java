package trillionaire.service.impl;

import org.springframework.stereotype.Service;
import trillionaire.service.UserService;
import trillionaire.util.LoginState;

/**
 * Created by michaeltan on 2017/5/9.
 */
@Service
public class UserServiceImpl implements UserService{


    @Override
    public LoginState login(String email, String password) {
        return null;
    }

    @Override
    public LoginState logout(String email) {
        return null;
    }

    @Override
    public LoginState resetPassword(String email, String newPassword) {
        return null;
    }

    @Override
    public boolean find(String email) {
        return false;
    }

    @Override
    public boolean verify(String randomCode) {
        return false;
    }

    @Override
    public boolean follow(String email, String code) {
        return false;
    }

    @Override
    public boolean checkfollow(String email, String code) {
        return false;
    }
}
