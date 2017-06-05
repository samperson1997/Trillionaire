package trillionaire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trillionaire.dao.UserDao;
import trillionaire.model.User;
import trillionaire.service.UserService;
import trillionaire.util.MailUtil;
import trillionaire.util.RandomCodeUtil;
import trillionaire.util.UserState;

/**
 * Created by michaeltan on 2017/5/9.
 */
@Service
public class UserServiceImpl implements UserService {

    private static RegisterUserMap registerUserMap = new RegisterUserMap();//存储未验证用户

    @Autowired
    UserDao userDao;

    @Override
    public UserState login(String email, String password) {
        User user = userDao.getUserByEmail(email);
        if (password.equals(user.getPassword())) {
            return UserState.SUCCESS;
        } else {
            return UserState.FAIL;
        }
    }

    @Override
    public UserState register(String email, String password) {
        User user = userDao.getUserByEmail(email);
        if (user == null) {
            User newUser = new User(email, password);
            String randomCode = RandomCodeUtil.generateUniqueCode();
            registerUserMap.put(randomCode, newUser);
            registerUserMap.setTime(randomCode);
            new Thread(new MailUtil(email, randomCode)).start();
            return UserState.SUCCESS;
        } else {
            return UserState.FAIL;
        }
    }

    @Override
    public UserState logout(String email) {
        return null;
    }

    @Override
    public UserState resetPassword(String email, String newPassword) {
        return null;
    }

    @Override
    public boolean check(String email) {
        User user = userDao.getUserByEmail(email);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean verify(String randomCode) {
        User user = registerUserMap.get(randomCode);
        if (user != null) {
            userDao.saveOrUpdateUser(user);
            registerUserMap.remove(randomCode);
            return true;
        } else {
            return false;
        }
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
