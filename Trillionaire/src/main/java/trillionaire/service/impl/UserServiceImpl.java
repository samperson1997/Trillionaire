package trillionaire.service.impl;

import org.springframework.stereotype.Service;
import trillionaire.service.UserService;
import trillionaire.util.State;

/**
 * Created by michaeltan on 2017/5/9.
 */
@Service
public class UserServiceImpl implements UserService{


    public State login(String id, String password) {
        return null;
    }

    public State logout(String id) {
        return null;
    }

    public State resetPassword(String id, String oldPassword, String newPassword) {
        return null;
    }
}
