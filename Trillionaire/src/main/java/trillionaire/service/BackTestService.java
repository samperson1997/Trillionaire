package trillionaire.service;

import trillionaire.util.BackTestResult;
import trillionaire.vo.BackTestParams;
import trillionaire.vo.BackTestSummary;
import trillionaire.vo.DateReturnsVO;
import trillionaire.vo.StrategySimple;

import java.util.List;

/**
 * Created by USER on 2017/5/31.
 */
public interface BackTestService {

    public List<StrategySimple> getMyStrategy(int userId);

    public int addStrategy(int userId, String strategyName);

    public int saveStrategy(int sid, String newContents);

    public int deletStrategy(int sid);

    public BackTestResult startBackTest(BackTestParams params);

    public List<DateReturnsVO> getDateReturnsVOList(int sid);

    public BackTestSummary getBackTestSummary(int sid);

}
