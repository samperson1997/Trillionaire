package trillionaire.dao;

import trillionaire.model.User;

/**
 * Created by michaeltan on 2017/5/9.
 */
public interface UserDao {

    public User getUserByEmail(String email);

    public void addUser(User user);

    public void updateUser(User user);

}
