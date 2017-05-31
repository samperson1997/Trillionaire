package trillionaire.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import trillionaire.dao.UserDao;
import trillionaire.model.User;

/**
 * Created by michaeltan on 2017/5/9.
 */
@Repository
public class UserDaoImpl implements UserDao{

    SessionFactory sessionFactory;

    public User getUserByEmail(String email) {
        return null;
    }

    public void addUser(User user) {

    }

    public void updateUser(User user) {

    }
}
