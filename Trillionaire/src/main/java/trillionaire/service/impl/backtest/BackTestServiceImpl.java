package trillionaire.service.impl.backtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trillionaire.dao.StrategyDao;
import trillionaire.dao.UserDao;
import trillionaire.model.Strategy;
import trillionaire.model.User;
import trillionaire.service.BackTestService;
import trillionaire.util.BackTestResult;
import trillionaire.vo.BackTestParams;
import trillionaire.vo.BackTestSummary;
import trillionaire.vo.DateReturnsVO;
import trillionaire.vo.StrategySimple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by USER on 2017/5/31.
 */
@Service
public class BackTestServiceImpl implements BackTestService{

    private String runnerPath = "src\\main\\resources\\python\\backtest_runner.py";
    private String readerPath = "src\\main\\resources\\python\\backtest_reader.py";

    @Autowired
    StrategyDao strategyDao;

    @Autowired
    UserDao userDao;


    public List<StrategySimple> getMyStrategy(int userId) {

        Set<Strategy> strategies = strategyDao.getUserStrategy(userId);

        if(strategies==null) return null;

        List<StrategySimple> result = new ArrayList<>();
        for(Strategy s: strategies){
            StrategySimple strategySimple = new StrategySimple();
            strategySimple.name = s.getStrategyName();
            strategySimple.sid = s.getSid();
            result.add(strategySimple);
        }

        return result;
    }

    public Strategy openStrategy(int sid){


        return strategyDao.getStrategy(sid);
    }

    public int addStrategy(int userId, String strategyName, String content) {

        User user = userDao.getUser(userId);
        Strategy strategy = new Strategy();
        strategy.setStrategyName(strategyName);
        strategy.setContent(content);
        strategy.setOwner(user);

        return strategyDao.saveStrategy(strategy);
    }

    public int saveStrategy(int sid, String strategyName, String content) {

        Strategy strategy = strategyDao.getStrategy(sid);
        strategy.setStrategyName(strategyName);
        strategy.setContent(content);

        return strategyDao.saveStrategy(strategy);
    }

    public int deletStrategy(int sid) {

        strategyDao.deletStrategy(sid);

        return 0;
    }

    public BackTestResult startBackTest(BackTestParams params) {
        String paramString = "";
        paramString = params.sid + " " + params.cash + " " + params.sDate + " " + params.eDate + " " + params.frequency + " " + params.matchingType + " " +
                        params.benchmark + " " + params.commissionMultiplier + " " + params.slippage;
        String[] cmd = new String[] { "cmd.exe", "/C", "activate python36 && python " + runnerPath + " " + paramString };
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            int value = p.exitValue();
            if(value == 0){
                System.out.println("run success.");
                return BackTestResult.SUCCESS;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return BackTestResult.ERROR;
    }

    public List<DateReturnsVO> getDateReturnsVOList(int sid) {
        return null;
    }

    public BackTestSummary getBackTestSummary(int sid) {
        return null;
    }
}
