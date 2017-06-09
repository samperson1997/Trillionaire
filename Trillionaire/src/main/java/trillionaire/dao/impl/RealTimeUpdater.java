package trillionaire.dao.impl;

import trillionaire.model.RealTimeStock;
import trillionaire.util.CMDGetter;

import java.io.*;
import java.util.*;

/**
 * Created by USER on 2017/5/17.
 */
public class RealTimeUpdater {


    public boolean updateNewInfo(Map<Integer, RealTimeStock> stockMap) {

        BufferedReader br = null;
        ClearThread ct = null;
        try {
            String path = this.getClass().getResource("/python/updater.py").getPath().substring(CMDGetter.getOSPathStarter());
            //System.out.println(path);
            //String outPath = this.getClass().getResource("/").getPath().substring(CMDGetter.getOSPathStarter()) + "TempFiles/RealTime/realtime.csv";
            //System.out.println(outPath);
            String[] cmd = CMDGetter.getCommand("python " + path );
            Process p = Runtime.getRuntime().exec(cmd);
            ct = new ClearThread(p);
            ct.start();
            p.waitFor();
            Thread.sleep(100);
            ct.setEnd(true);
            int processValue = p.exitValue();
            if(processValue != 0){
                System.out.println("update realtime error");
                return false;
            }

            List<String> res = ct.getRes();
            int index = 0;
            while(index < res.size() && !res.get(index).equals("data start!")){
                index++;
            }
            index++;

            while (index < res.size() ) {
                if(res.get(index).equals("data end")){
                    break;
                }

                RealTimeStock realTimeStock = getRealTimeStockByLine(res.get(index));

                if(realTimeStock==null){
                    index++;
                    continue;
                }
                else {
                    int code = Integer.valueOf(realTimeStock.getCode());
                    stockMap.put(code, realTimeStock);
                    index++;
                }

            }

            System.out.println("update realtime success");
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

    }

    private RealTimeStock getRealTimeStockByLine(String line) {
        String[] strs = line.split(",");

        for(int i=2; i<15; i++){
            if(strs[i].equals("0") || strs[i].equals("0.0"))
                return null;
        }

        String code = strs[0];
        String name = strs[1];
        double changepercent = Double.valueOf(strs[2]);
        double trade = Double.valueOf(strs[3]);
        double open = Double.valueOf(strs[4]);
        double high = Double.valueOf(strs[5]);
        double low = Double.valueOf(strs[6]);
        double settlement = Double.valueOf(strs[7]);
        long volume = Long.valueOf(strs[8]);
        double turnoverratio = Double.valueOf(strs[9]);
        long amount = Long.valueOf(strs[10]);
        double per = Double.valueOf(strs[11]);
        double pb = Double.valueOf(strs[12]);
        double mktcap = Double.valueOf(strs[13]);
        double nmc = Double.valueOf(strs[14]);

        RealTimeStock realTimeStock = new RealTimeStock(code, name, changepercent, trade, open, high, low, settlement, volume, turnoverratio,
                amount, per, pb, mktcap, nmc);

        return realTimeStock;
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
