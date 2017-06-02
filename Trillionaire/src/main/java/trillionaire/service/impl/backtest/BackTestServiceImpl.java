package trillionaire.service.impl.backtest;

import org.springframework.stereotype.Service;
import trillionaire.service.BackTestService;
import trillionaire.util.BackTestResult;
import trillionaire.vo.BackTestParams;
import trillionaire.vo.BackTestSummary;
import trillionaire.vo.DateReturnsVO;
import trillionaire.vo.StrategySimple;

import java.io.IOException;
import java.util.List;

/**
 * Created by USER on 2017/5/31.
 */
@Service
public class BackTestServiceImpl implements BackTestService{

    private String runnerPath = "src\\main\\resources\\python\\backtest_runner.py";
    private String readerPath = "src\\main\\resources\\python\\backtest_reader.py";


    public List<StrategySimple> getMyStrategy(int userId) {
        return null;
    }

    public int addStrategy(int userId, String strategyName) {
        return 0;
    }

    public int saveStrategy(int sid, String newContents) {
        return 0;
    }

    public int deletStrategy(int sid) {
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
