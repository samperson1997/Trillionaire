package trillionaire.dao;

import trillionaire.model.Strategy;

import java.util.List;
import java.util.Set;

/**
 * Created by USER on 2017/6/8.
 */
public interface StrategyDao {

    /**
     *
     * @param strategy
     * @return
     */
    public int saveStrategy(Strategy strategy);

    /**
     *
     * @param sid
     */
    public void deletStrategy(int sid);

    /**
     *
     * @param userId
     * @return
     */
    public Set<Strategy> getUserStrategy(int userId);

    /**
     *
     * @param sid
     * @return
     */
    public Strategy getStrategy(int sid);

}
