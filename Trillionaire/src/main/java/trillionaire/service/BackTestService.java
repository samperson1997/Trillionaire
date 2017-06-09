package trillionaire.service;

import trillionaire.model.Strategy;
import trillionaire.util.BackTestResult;
import trillionaire.vo.*;

import java.util.List;
import java.util.Map;

/**
 * Created by USER on 2017/5/31.
 */
public interface BackTestService {

    public List<StraIdName> getMyStrategy(int userId);

    public Strategy openStrategy(int sid);

    public int addStrategy(int userId, String strategyName, String content);

    public int saveStrategy(int sid, String strategyName, String content);

    public int deletStrategy(int sid);

    public Map<String, Object> startBackTest(BackTestParams params);

    public List<DateReturnsVO> getDateReturnsVOList(int sid);

    public BackTestSummary getBackTestSummary(int sid);

}
