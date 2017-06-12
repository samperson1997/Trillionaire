package trillionaire.dao;

import trillionaire.model.Stock;
import trillionaire.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by michaeltan on 2017/5/9.
 */
public interface UserDao {

    public User getUser(int userId);

    public User getUserByEmail(String email);

    //public void addUser(User user);

    public void saveOrUpdateUser(User user);

    public void addConcernedStock(int userId, int code);

    public void deleteConcernedStock(int userId, int code);

    public Set<Stock> getUserStocks(int userId);

}
