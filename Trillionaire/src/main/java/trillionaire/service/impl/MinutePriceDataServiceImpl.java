package trillionaire.service.impl;

import org.springframework.stereotype.Service;
import trillionaire.service.MinutePriceDataService;

import javax.ejb.Local;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.*;

/**
 * Created by USER on 2017/6/4.
 */
@Service
public class MinutePriceDataServiceImpl implements MinutePriceDataService {



    @Override
    public Map<String, Object> getMinutePriceDate(String code) {
        Map<String, Object> result = new HashMap<>();

        //
        int processValue = 1;

        if(processValue != 0){

            try{

                String lastDate = "2017-06-02";

                String[] cmd = new String[] { "cmd.exe", "/C", "python src\\main\\resources\\python\\get_history_ticks.py " + code + " " + lastDate };
                Process p = Runtime.getRuntime().exec(cmd);
                ClearThread ct = new ClearThread(p);
                ct.start();
                p.waitFor();
                int processValue2 = p.exitValue();

                Thread.sleep(250);
                ct.setEnd(true);

                if(processValue2 != 0) return  null;

                result = getResultMapByRes(ct.getRes());
                return result;

            }
            catch (IOException e){
                e.printStackTrace();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }


        }


        return null;
    }

    private Map<String, Object> getResultMapByRes(List<String> res){

        DecimalFormat df   = new DecimalFormat("#.00");

        Map<String, Object> result = new HashMap<>();
        List<String> time = new ArrayList<>();
        List<Double> price = new ArrayList<>();
        List<Double> meanPrice = new ArrayList<>();
        List<Double> volume = new ArrayList<>();
        List<Double> amount = new ArrayList<>();

        int startIndex = 0;
        while(!res.get(startIndex).equals("data start!")){
            startIndex++;
        }
        startIndex++;

        LocalTime lastTime = LocalTime.of(8,0,0);
        double lastVolume = 0;
        double lastAmount = 0;
        boolean firstTag = true;
        for(int i=startIndex; i<res.size()-1; i++){
            //System.out.println(res.get(i));
            if(res.get(i).equals("data end")){
                break;
            }

            String[] strs = res.get(i).split(" ");
            LocalTime t = LocalTime.parse(strs[0]);
            double p = Double.valueOf(strs[1]);
            double v = Double.valueOf(strs[2]);
            double a = Double.valueOf(strs[3]);

            if(lastTime.getHour()==t.getHour() && lastTime.getMinute()==t.getMinute()){
                lastVolume += v;
                lastAmount += a;
            }
            else{
                if(firstTag){
                    lastTime = LocalTime.of(t.getHour(),t.getMinute(),0);
                    time.add(lastTime.toString());
                    price.add(p);
                    lastVolume = v;
                    lastAmount = a;
                    firstTag = false;

                }
                else{
                    lastTime = LocalTime.of(t.getHour(),t.getMinute(),0);
                    time.add(lastTime.toString());
                    price.add(p);
                    volume.add(lastVolume);
                    amount.add(lastAmount);
                    lastVolume = v;
                    lastAmount = a;
                }
            }

        }
        volume.add(lastVolume);
        amount.add(lastAmount);

        for(int i=0; i<volume.size(); i++){
//            System.out.println(amount.get(i));
//            System.out.println(volume.get(i));

            if(volume.get(i).equals(0.0)){
                meanPrice.add(price.get(i));

            }else{
                meanPrice.add(Double.valueOf(df.format(amount.get(i)/volume.get(i)/100)));
            }

        }

        result.put("msg","success");
        result.put("time", time);
        result.put("price", price);
        result.put("meanPrice", meanPrice);
        result.put("volume", volume);


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

            Scanner scanner = new Scanner(process.getInputStream());
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
