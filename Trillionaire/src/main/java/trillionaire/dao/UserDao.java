package trillionaire.dao;

import trillionaire.model.Stock;
import trillionaire.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by michaeltan on 2017/5/9.
 */
public interface UserDao {

    /**
     *
     * @param userId
     * @return
     */
    public User getUser(int userId);

    /**
     *
     * @param email
     * @return
     */
    public User getUserByEmail(String email);

    //public void addUser(User user);

    /**
     *
     * @param user
     */
    public void saveOrUpdateUser(User user);

    /**
     *
     * @param userId
     * @param code
     */
    public void addConcernedStock(int userId, int code);

    /**
     *
     * @param userId
     * @param code
     */
    public void deleteConcernedStock(int userId, int code);

    /**
     *
     * @param userId
     * @return
     */
    public Set<Stock> getUserStocks(int userId);

}
