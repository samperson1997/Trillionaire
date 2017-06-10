package trillionaire.dao;

import trillionaire.model.Strategy;

import java.util.List;
import java.util.Set;

/**
 * Created by USER on 2017/6/8.
 */
public interface StrategyDao {

    public int saveStrategy(Strategy strategy);

    public void deletStrategy(int sid);

    public Set<Strategy> getUserStrategy(int userId);

    public Strategy getStrategy(int sid);

}
