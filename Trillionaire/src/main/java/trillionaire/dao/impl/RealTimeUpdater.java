package trillionaire.dao.impl;

import trillionaire.model.RealTimeStock;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by USER on 2017/5/17.
 */
public class RealTimeUpdater {


    public Map<Integer, RealTimeStock> getNewInfo(){

        //String dirPath = "C:\\Users\\USER\\project3\\Trillionaire\\Trillionaire\\src\\main\\java\\";
        BufferedReader br = null;
        try {
            String[] cmd = new String[] { "cmd.exe", "/C", "python src\\main\\resources\\python\\updater.py" };
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            System.out.println("get realtime success");

            br = new BufferedReader(new InputStreamReader(new FileInputStream("src\\main\\resources\\TempFiles\\RealTime\\realtime.csv"),"UTF-8"));
            Map<Integer, RealTimeStock> result = new LinkedHashMap<Integer, RealTimeStock>();

            br.readLine();

            String line = null;

            while((line=br.readLine())!=null){
                String[] strs = line.split(",");
                RealTimeStock realTimeStock = getRealTimeStockByLine(strs);
                int code = Integer.valueOf(strs[1]);

                result.put(code, realTimeStock);
            }

            br.close();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }

    private RealTimeStock getRealTimeStockByLine(String[] strs){



        String code = strs[1];

        String name = strs[2];
        double changepercent = Double.valueOf(strs[3]);
        double trade = Double.valueOf(strs[4]);
        double open = Double.valueOf(strs[5]);
        double high = Double.valueOf(strs[6]);
        double low = Double.valueOf(strs[7]);
        double settlement = Double.valueOf(strs[8]);
        long volume = Long.valueOf(strs[9]);
        double turnoverratio = Double.valueOf(strs[10]);
        long amount = Long.valueOf(strs[11]);
        double per = Double.valueOf(strs[12]);
        double pb = Double.valueOf(strs[13]);
        double mktcap = Double.valueOf(strs[14]);
        double nmc = Double.valueOf(strs[15]);

        RealTimeStock realTimeStock = new RealTimeStock(code, name,changepercent, trade, open, high, low, settlement, volume, turnoverratio,
                                                        amount, per, pb, mktcap, nmc);

        return  realTimeStock;
    }

}
