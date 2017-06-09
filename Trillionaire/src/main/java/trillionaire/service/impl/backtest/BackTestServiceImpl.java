package trillionaire.service.impl.backtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trillionaire.dao.StrategyDao;
import trillionaire.dao.UserDao;
import trillionaire.model.Strategy;
import trillionaire.model.User;
import trillionaire.service.BackTestService;
import trillionaire.util.BackTestResult;
import trillionaire.util.CMDGetter;
import trillionaire.vo.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by USER on 2017/5/31.
 */
@Service
public class BackTestServiceImpl implements BackTestService{

    private String runnerPath;
    private String readerPath;
    private String inputDir;
    private String outputDir;

    @Autowired
    StrategyDao strategyDao;

    @Autowired
    UserDao userDao;

    public BackTestServiceImpl(){

        runnerPath = this.getClass().getResource("/python/backtest_runner.py").getPath().substring(CMDGetter.getOSPathStarter());
        readerPath = this.getClass().getResource("/python/backtest_reader.py").getPath().substring(CMDGetter.getOSPathStarter());
        inputDir = this.getClass().getResource("/").getPath().substring(CMDGetter.getOSPathStarter()) + "TempFiles/TempStrategy/BackTestStrategy/";
        outputDir = this.getClass().getResource("/").getPath().substring(CMDGetter.getOSPathStarter()) + "TempFiles/TempStrategy/BacktestResult/";

    }


    public List<StraIdName> getMyStrategy(int userId) {

        Set<Strategy> strategies = strategyDao.getUserStrategy(userId);

        if(strategies==null) return null;

        List<StraIdName> result = new ArrayList<>();
        for(Strategy s: strategies){
            StraIdName straIdName = new StraIdName();
            straIdName.setStrategName(s.getStrategyName());
            straIdName.setSid(s.getSid());
            result.add(straIdName);
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

    public Map<String, Object> startBackTest(BackTestParams params) {

        if(params.sid < 0){
            Map<String, Object> result = new HashMap<>();
            result.put("msg", "error1");
            return  result;
        }

        Strategy strategy = strategyDao.getStrategy(params.sid);
        if(strategy == null){
            Map<String, Object> result = new HashMap<>();
            result.put("msg", "error2");
            return  result;
        }

        File strategyFile = new File(inputDir + params.sid + ".py");
        File outPklFIle = new File(outputDir + params.sid + ".pkl");
        try {
            creatFile(strategyFile);
            creatFile(outPklFIle);
        } catch (IOException e) {
            e.printStackTrace();
            Map<String, Object> result = new HashMap<>();
            result.put("msg", "error3");
            return  result;
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(strategyFile));
            bw.write(strategy.getContent());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            Map<String, Object> result = new HashMap<>();
            result.put("msg", "error4");
            return  result;
        }



        try {
            String paramString = "";
            paramString = params.sid + " " + params.cash + " " + params.sDate + " " + params.eDate + " " + params.frequency + " " + params.matchingType + " " +
                    params.benchmark + " " + params.commissionMultiplier + " " + params.slippage + " " + strategyFile.getPath() + " " + outPklFIle.getPath();
            String[] cmd = CMDGetter.getCommand("python " + runnerPath + " " + paramString);
            Process runnerProcess = Runtime.getRuntime().exec(cmd);
            ClearThread ct = new ClearThread(runnerProcess);
            ct.start();
            runnerProcess.waitFor();
            System.out.println("after 1 exec");
            int runValue = runnerProcess.exitValue();
            Thread.sleep(200);
            ct.setEnd(true);

            if(runValue == 0){

                String[] cmd2 = CMDGetter.getCommand("python " + readerPath + " " + outPklFIle);
                Process readerProcess = Runtime.getRuntime().exec(cmd2);
                ClearThread ct2 = new ClearThread(readerProcess);
                ct2.start();
                readerProcess.waitFor();
                System.out.println("after 1 exec");
                int readValue = readerProcess.exitValue();
                Thread.sleep(200);
                ct2.setEnd(true);

                if(readValue == 0){

                    return getResult(ct2.getRes());

                }
                else {
                    Map<String, Object> result = new HashMap<>();
                    result.put("msg", "error5");
                    return  result;
                }

            }
            else {
                Map<String, Object> result = new HashMap<>();
                result.put("msg", "error6");
                String errorLog = "";
                for(String str: ct.getRes()){
                    errorLog += str + "\n";
                }
                result.put("errorLog", errorLog);
                return  result;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    public List<DateReturnsVO> getDateReturnsVOList(int sid) {
        return null;
    }

    public BackTestSummary getBackTestSummary(int sid) {
        return null;
    }


    private void creatFile(File file) throws IOException {
        if(!file.exists()){
            file.createNewFile();
        }
    }

    private Map<String, Object> getResult(List<String> res){

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "success");
        BackTestSummary summary = new BackTestSummary();
        List<String> datelist = new ArrayList<>();
        List<Double> data1 = new ArrayList<>();
        List<Double> data2 = new ArrayList<>();

        int index = 0;

        while(!res.get(index).equals("--------------------")){
            index++;
        }
        index++;

        summary.setBacktestReturns(Double.valueOf(res.get(index++)));
        summary.setBacktestAnnualizedReturns(Double.valueOf(res.get(index++)));
        summary.setBenchReturns(Double.valueOf(res.get(index++)));
        summary.setBenchAnnualizedReturns(Double.valueOf(res.get(index++)));
        summary.setAlpha(Double.valueOf(res.get(index++)));
        summary.setBeta(Double.valueOf(res.get(index++)));
        summary.setSharpe(Double.valueOf(res.get(index++)));
        summary.setSortino(Double.valueOf(res.get(index++)));
        summary.setInfoRatio(Double.valueOf(res.get(index++)));
        summary.setVolatility(Double.valueOf(res.get(index++)));
        summary.setMaxDrawdown(Double.valueOf(res.get(index++)));
        summary.setTrackingError(Double.valueOf(res.get(index++)));
        summary.setDownsideRisk(Double.valueOf(res.get(index++)));

        index++;

        while(!res.get(index).equals("--------------------")){
            datelist.add(res.get(index).split(" ")[0]);
            index++;
        }

        index++;

        while(!res.get(index).equals("--------------------")){
            data1.add(Double.valueOf(res.get(index)));
            index++;
        }

        index++;

        while(!res.get(index).equals("--------------------")){
            data2.add(Double.valueOf(res.get(index)));
            index++;
        }

        result.put("summary", summary);
        result.put("datelist", datelist);
        result.put("data1", data1);
        result.put("data2", data2);

        return result;
    }

    class ClearThread extends Thread {
        Process process;
        boolean end;
        List<String> res;

        public ClearThread(Process process) {
            this.process = process;
            end = false;
            res = new ArrayList<>();
        }

        @Override
        public void run() {
            if (process == null) {
                return;
            }

            Scanner scanner = new Scanner(process.getInputStream(), CMDGetter.getCmdCharSet());
            while (process != null && !end) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    res.add(line);
                }
            }
        }

        public void setEnd(boolean end) {
            this.end = end;
        }

        public List<String> getRes() {
            return res;
        }
    }

}
