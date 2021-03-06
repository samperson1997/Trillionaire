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
    private String paramsRunnerPath;
    private String simpleReaderPath;

    @Autowired
    StrategyDao strategyDao;

    @Autowired
    UserDao userDao;

    public BackTestServiceImpl(){

        runnerPath = this.getClass().getResource("/python/backtest_runner.py").getPath().substring(CMDGetter.getOSPathStarter());
        readerPath = this.getClass().getResource("/python/backtest_reader.py").getPath().substring(CMDGetter.getOSPathStarter());
        paramsRunnerPath = this.getClass().getResource("/python/params_runner.py").getPath().substring(CMDGetter.getOSPathStarter());
        simpleReaderPath = this.getClass().getResource("/python/simple_reader.py").getPath().substring(CMDGetter.getOSPathStarter());
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
            straIdName.setUpdateTime(s.getChangingTime().toString());
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
            ClearThread errorCt = new ClearThread(runnerProcess, true);
            errorCt.start();
            runnerProcess.waitFor();
            int runValue = runnerProcess.exitValue();
            Thread.sleep(200);
            ct.setEnd(true);
            errorCt.setEnd(true);

            boolean checkTag = false;
            if(errorCt.getRes().size() > 0 && errorCt.getRes().get(0).contains("Traceback")){
                checkTag = true;
            }
            System.out.println("process value:"+runValue);
            if(runValue==1){
                for (String s: errorCt.getRes()){
                    System.out.println(s);
                }
            }

            if(runValue == 0 && !checkTag){
                System.out.println("run2 before waitfor");
                String[] cmd2 = CMDGetter.getCommand("python " + readerPath + " " + outPklFIle);
                Process readerProcess = Runtime.getRuntime().exec(cmd2);
                ClearThread ct2 = new ClearThread(readerProcess);
                ct2.start();
                readerProcess.waitFor();
                System.out.println("run2 after waitfor");
                int readValue = readerProcess.exitValue();
                System.out.println("run2 value:"+readValue);
                Thread.sleep(200);
                ct2.setEnd(true);

                if(readValue == 0){


                    System.out.println("start get result");
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

                ClearThread clearThread = null;
                if(checkTag){
                    clearThread = errorCt;
                }
                else {
                    clearThread = ct;
                }

                for(String str: clearThread.getRes()){
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

    @Override
    public Map<String, Object> startFindBestParams(BackTestParams params, int low, int high) {

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
        try {
            creatFile(strategyFile);
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


        Map<String, Object> map = new HashMap<>();
        List<Integer> paramList = new ArrayList<>();
        List<Double> overReturnsList = new ArrayList<>();
        List<Double> winRateList = new ArrayList<>();
        map.put("paramList", paramList);
        map.put("overReturnsList", overReturnsList);
        map.put("winRateList", winRateList);


        for(int i=low; i<=high; i++){

            File outPklFIle = new File(outputDir + params.sid + "_" + i + ".pkl");
            try {
                creatFile(outPklFIle);
            } catch (IOException e) {
                Map<String, Object> result = new HashMap<>();
                result.put("msg", "error3");
                return  result;
            }

            try {
                String paramString = "";
                paramString = params.sid + " " + params.cash + " " + params.sDate + " " + params.eDate + " " + params.frequency + " " + params.matchingType + " " +
                        params.benchmark + " " + params.commissionMultiplier + " " + params.slippage + " " + strategyFile.getPath() + " " + outPklFIle.getPath() + " " + i;
                String[] cmd = CMDGetter.getCommand("python " + paramsRunnerPath + " " + paramString);
                Process runnerProcess = Runtime.getRuntime().exec(cmd);
                ClearThread ct = new ClearThread(runnerProcess);
                ct.start();
                ClearThread errorCt = new ClearThread(runnerProcess, true);
                errorCt.start();
                runnerProcess.waitFor();
                int runValue = runnerProcess.exitValue();
                Thread.sleep(200);
                ct.setEnd(true);
                errorCt.setEnd(true);

                boolean checkTag = false;
                if(errorCt.getRes().size() > 0 && errorCt.getRes().get(0).contains("Traceback")){
                    checkTag = true;
                }
                System.out.println("process value:"+runValue);
                if(runValue==1){
                    for (String s: errorCt.getRes()){
                        System.out.println(s);
                    }
                }

                if(runValue == 0 && !checkTag){
                    System.out.println("run2 before waitfor");
                    String[] cmd2 = CMDGetter.getCommand("python " + simpleReaderPath + " " + outPklFIle);
                    Process readerProcess = Runtime.getRuntime().exec(cmd2);
                    ClearThread ct2 = new ClearThread(readerProcess);
                    ct2.start();
                    readerProcess.waitFor();
                    System.out.println("run2 after waitfor");
                    int readValue = readerProcess.exitValue();
                    System.out.println("run2 value:"+readValue);
                    Thread.sleep(200);
                    ct2.setEnd(true);

                    if(readValue == 0){

                        addParamResult(map, ct2.getRes(),i);


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

                    ClearThread clearThread = null;
                    if(checkTag){
                        clearThread = errorCt;
                    }
                    else {
                        clearThread = ct;
                    }

                    for(String str: clearThread.getRes()){
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


        }

        map.put("msg","success");
        return map;
    }


    private void creatFile(File file) throws IOException {

        File f1 = new File(outputDir);
        if(!f1.isDirectory()){
            f1.mkdirs();
        }

        File f2 = new File(inputDir);
        if(!f2.isDirectory()){
            f2.mkdirs();
        }

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
        summary.setBacktestReturns((!res.get(index++).equals("nan")?Double.valueOf(res.get(index-1)):-1));
        summary.setBacktestAnnualizedReturns((!res.get(index++).equals("nan")?Double.valueOf(res.get(index-1)):-1));
        summary.setBenchReturns((!res.get(index++).equals("nan")?Double.valueOf(res.get(index-1)):-1));
        summary.setBenchAnnualizedReturns((!res.get(index++).equals("nan")?Double.valueOf(res.get(index-1)):-1));
        summary.setAlpha((!res.get(index++).equals("nan")?Double.valueOf(res.get(index-1)):-1));
        summary.setBeta((!res.get(index++).equals("nan")?Double.valueOf(res.get(index-1)):-1));
        summary.setSharpe((!res.get(index++).equals("nan")?Double.valueOf(res.get(index-1)):-1));
        summary.setSortino((!res.get(index++).equals("nan")?Double.valueOf(res.get(index-1)):-1));
        summary.setInfoRatio((!res.get(index++).equals("nan")?Double.valueOf(res.get(index-1)):-1));
        summary.setVolatility((!res.get(index++).equals("nan")?Double.valueOf(res.get(index-1)):-1));
        summary.setMaxDrawdown((!res.get(index++).equals("nan")?Double.valueOf(res.get(index-1)):-1));
        summary.setTrackingError((!res.get(index++).equals("nan")?Double.valueOf(res.get(index-1)):-1));
        summary.setDownsideRisk((!res.get(index++).equals("nan")?Double.valueOf(res.get(index-1)):-1));
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

    private void addParamResult(Map<String, Object> map, List<String> res, int param){

        double overReturns = Double.valueOf(res.get(1));
        double winRate = Double.valueOf(res.get(3));

        List<Integer> paramList = (List<Integer>) map.get("paramList");
        List<Double> overReturnsList = (List<Double>) map.get("overReturnsList");
        List<Double> winRateList = (List<Double>) map.get("winRateList");
        paramList.add(param);
        overReturnsList.add(overReturns);
        winRateList.add(winRate);

    }

    class ClearThread extends Thread {
        Process process;
        boolean end;
        List<String> res;
        boolean isErrorType = false;

        public ClearThread(Process process) {
            this.process = process;
            end = false;
            res = new ArrayList<>();
        }

        public ClearThread(Process process, boolean isError) {
            this.process = process;
            end = false;
            res = new ArrayList<>();
            isErrorType = isError;
        }

        @Override
        public void run() {
            if (process == null) {
                return;
            }

            Scanner scanner = null;
            if(!isErrorType){
                scanner = new Scanner(process.getInputStream(), CMDGetter.getCmdCharSet());
            }
            else {
                scanner = new Scanner(process.getErrorStream(), CMDGetter.getCmdCharSet());
            }
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
