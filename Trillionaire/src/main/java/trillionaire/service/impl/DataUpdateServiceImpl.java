package trillionaire.service.impl;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trillionaire.dao.AbilityDao;
import trillionaire.dao.DayRecordDao;
import trillionaire.dao.MeanPriceDao;
import trillionaire.dao.StockDao;
import trillionaire.model.*;
import trillionaire.service.DataUpdateService;
import trillionaire.util.CMDGetter;

import java.io.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by USER on 2017/5/25.
 */
@Service
public class DataUpdateServiceImpl implements DataUpdateService {

    @Autowired
    StockDao stockDao;

    @Autowired
    DayRecordDao dayRecordDao;

    @Autowired
    AbilityDao abilityDao;

    @Autowired
    MeanPriceDao meanPriceDao;


    public void updateAllData() {

        List<Stock> stockList = stockDao.getAllStocks();

        int num = 1;
        for(Stock stock: stockList){
            updateStock(stock);
            System.out.println("update " + num++ + "th");
        }

    }

    public void updateAbility(int year, int quarter) {
        try {
            String pythonPath = this.getClass().getResource("/python/abilityUpdater.py").getPath().substring(CMDGetter.getOSPathStarter());
            String classPath = this.getClass().getResource("/").getPath().substring(CMDGetter.getOSPathStarter()) +"TempFiles/Ability/";

            System.out.println(pythonPath);
            System.out.println(classPath);

            File f1 = new File(classPath);
            if(!f1.isDirectory()){
                f1.mkdirs();
            }
            creatFile(new File(classPath+"developing_ability_"+year+quarter+".csv"));
            creatFile(new File(classPath+"debt_paying_ability_"+year+quarter+".csv"));
            creatFile(new File(classPath+"operation_ability_"+year+quarter+".csv"));
            creatFile(new File(classPath+"profitability_"+year+quarter+".csv"));



            String[] cmd = CMDGetter.getCommand("python " + pythonPath + " " + year + " " + quarter + " " + classPath);
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            int value = p.exitValue();
            System.out.println(value);
            if(value != 0) {
                return;
            }
            System.out.println("get ability success");

            if(value == 0){

                BufferedReader developingReader = new BufferedReader(new InputStreamReader(new FileInputStream(classPath+"developing_ability_"+year+quarter+".csv"),"UTF-8"));
                BufferedReader debtPayingReader = new BufferedReader(new InputStreamReader(new FileInputStream(classPath+"debt_paying_ability_"+year+quarter+".csv"),"UTF-8"));
                BufferedReader operationReader = new BufferedReader(new InputStreamReader(new FileInputStream(classPath+"operation_ability_"+year+quarter+".csv"),"UTF-8"));
                BufferedReader profitabilityReader = new BufferedReader(new InputStreamReader(new FileInputStream(classPath+"profitability_"+year+quarter+".csv"),"UTF-8"));

                String line = null;
                developingReader.readLine();
                while((line = developingReader.readLine())!=null){
                    DevelopingAbility developingAbility = getDevelopingAbilityByLine(line);
                    developingAbility.setYear(year);
                    developingAbility.setQuarter(quarter);
                    abilityDao.saveDevelopingAbility(developingAbility);
                }

                line = null;
                operationReader.readLine();
                while((line = operationReader.readLine())!=null){
                    OperationAbility operationAbility = getOperationAbilityByLine(line);
                    operationAbility.setYear(year);
                    operationAbility.setQuarter(quarter);
                    abilityDao.saveOperationAbility(operationAbility);
                }

                line = null;
                profitabilityReader.readLine();
                while((line = profitabilityReader.readLine())!=null){
                    Profitability profitability = getProfitability(line);
                    profitability.setYear(year);
                    profitability.setQuarter(quarter);
                    abilityDao.saveProfitability(profitability);
                }

                line = null;
                debtPayingReader.readLine();
                while((line = debtPayingReader.readLine())!=null){
                    DebtPayingAbility debtPayingAbility = getDebtPayingAbilityByLine(line);
                    debtPayingAbility.setYear(year);
                    debtPayingAbility.setQuarter(quarter);
                    abilityDao.saveDebtPayingAbility(debtPayingAbility);
                }

                debtPayingReader.close();
                developingReader.close();
                operationReader.close();
                profitabilityReader.close();

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateStock(Stock stock){

        int newRecordNum = 0;
        int code = stock.getCode();
        Date lastDate = dayRecordDao.getLastDateOf(code);

        URL url = getDownloadURL(stock.getMarket(), code, lastDate);
        if(url==null) return;


        String fileDir = this.getClass().getResource("/").getPath().substring(CMDGetter.getOSPathStarter());

        File file = new File(fileDir + "TempFiles/TempDayRecord/" +  code+".csv");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        boolean hasDownloaded = false;
        while(!hasDownloaded){

            try {
                FileUtils.copyURLToFile(url, file, 5000,5000);
                hasDownloaded = true;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(stock.getCode() + " download error ");
            }
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GB2312"));
            String line = null;
            br.readLine();
            while((line=br.readLine())!=null){

                DayRecord dayRecord = getDayRecordByLine(line);
                if(dayRecord==null){
                    continue;
                }
                dayRecord.setStock(stock);

                dayRecordDao.save(dayRecord);
                newRecordNum++;
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        if(newRecordNum>0){
//            updateWeekRecord(stock);
//            updateMonthRecord(stock);
//            updateMeanPrice(stock);
//        }

        updateWeekRecord(stock);
        updateMonthRecord(stock);
        updateMeanPrice(stock);


    }

    private URL getDownloadURL(String market, int code, Date lastDate){

        String codeString = "";

        if(code<3000000){
            codeString = String.valueOf(code);
        }
        else {
            codeString = String.valueOf(code-3000000);
        }

        while(codeString.length()<6){
            codeString = "0" + codeString;
        }

        if(market.equals("sz")){
            codeString = "1" + codeString;
        }
        else if(market.equals("sh")){
            codeString = "0" + codeString;
        }

        String startDateString = "";
        if(lastDate==null){
            startDateString = "19910102";
        }
        else{
            String string = lastDate.toLocalDate().plusDays(1).toString();
            String[] strs = string.split("-");
            for(String s: strs){
                startDateString = startDateString + s;
            }
        }

        String endDateString = "";
        String[] todayStrings = LocalDate.now().toString().split("-");
        for(String s: todayStrings){
            endDateString = endDateString + s;
        }


        URL url = null;
        try {
            url = new URL("http://quotes.money.163.com/service/chddata.html?code=" + codeString  +
                        "&start=" + startDateString +"&end=" + endDateString + "&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        System.out.println(codeString+" "+startDateString+" "+endDateString);
        return url;
    }

    private DayRecord getDayRecordByLine(String line){

        DayRecord dayRecord = new DayRecord();
        String[] strs = line.split(",");
        int index = 0;
        for(String string : strs){
            if(index>=3 && index<=12){
                if(string.equals("0.0") || string.equals("None")){
                    return null;
                }
            }
            index++;
        }

        Date date = Date.valueOf(strs[0]);
        double open = Double.valueOf(strs[6]);
        double high = Double.valueOf(strs[4]);
        double low = Double.valueOf(strs[5]);
        double close = Double.valueOf(strs[3]);
        double adjClose = Double.valueOf(strs[7]);
        double change = Double.valueOf(strs[9]);
        long volume = Long.valueOf(strs[11]);
        double dealSum = Double.valueOf(strs[12]);
        double turnoverRate = strs[10].equals("")?-1:Double.valueOf(strs[10]);

        dayRecord.setDate(date);
        dayRecord.setOpen(open);
        dayRecord.setHigh(high);
        dayRecord.setLow(low);
        dayRecord.setClose(close);
        dayRecord.setAdjClose(adjClose);
        dayRecord.setChange(change);
        dayRecord.setVolume(volume);
        dayRecord.setDealSum(dealSum);
        dayRecord.setTurnoverRate(turnoverRate);

        return dayRecord;
    }


    private void updateWeekRecord(Stock stock){

        DecimalFormat   df   = new DecimalFormat("#.00");
        List<DayRecord> dayRecordList = dayRecordDao.getDayRecordsByCode(stock.getCode());
        //Date lastWeekDate = dayRecordDao.getLastWeekDateOf(stock.getCode());
        List<WeekRecord> weekRecordList = dayRecordDao.getWeekRecordsByCode(stock.getCode());

        WeekRecord lastWeekRecord = null;
        if(weekRecordList.size()>0){
            if(weekRecordList.size()>1){
                lastWeekRecord = weekRecordList.get(weekRecordList.size()-2);
            }
            WeekRecord w = weekRecordList.get(weekRecordList.size()-1);
            dayRecordDao.deletRecord(w);
        }

        int index = 0;
        double lastAdjClose = -1;
        if(lastWeekRecord != null ){

            while(dayRecordList.get(index).getDate().before(lastWeekRecord.getDate())){
                index++;
            }
            index++;
            lastAdjClose = lastWeekRecord==null?-1:lastWeekRecord.getAdjClose();
        }

        if(index >= dayRecordList.size()) return;

        int startIndex = index;
        int endIndex = -1;
        int preDayOfWeek = 0;
        while(index < dayRecordList.size()){
            DayRecord dayRecord = dayRecordList.get(index);
            LocalDate d = dayRecord.getDate().toLocalDate();
            if(d.getDayOfWeek().getValue() > preDayOfWeek){     //么有遇到新的一周
                preDayOfWeek = d.getDayOfWeek().getValue();
                endIndex = index;
                index++;
            }
            else{ //遇到新的一周

                WeekRecord weekRecord = new WeekRecord();
                Date date = dayRecordList.get(endIndex).getDate();
                double open = dayRecordList.get(startIndex).getOpen();
                double high = -1;
                double low = Double.MAX_VALUE;
                double close = dayRecordList.get(endIndex).getClose();
                double adjClose = dayRecordList.get(endIndex).getAdjClose();
                double change;
                if(lastAdjClose == -1){
                    change = 0;
                    lastAdjClose = adjClose;
                }
                else{
                    change = (adjClose - lastAdjClose)/adjClose*100;
                    change = Double.valueOf(df.format(change));
                    lastAdjClose = adjClose;
                }
                long volume = 0;
                double dealSum = 0;
                for(int tempIndex = startIndex; tempIndex <= endIndex; tempIndex++){
                    DayRecord temp = dayRecordList.get(tempIndex);
                    if(temp.getHigh() > high){
                        high = temp.getHigh();
                    }

                    if(temp.getLow() < low){
                        low = temp.getLow();
                    }

                    volume += temp.getVolume();
                    dealSum += temp.getDealSum();
                }

                weekRecord.setStock(stock);
                weekRecord.setDate(date);
                weekRecord.setOpen(open);
                weekRecord.setHigh(high);
                weekRecord.setLow(low);
                weekRecord.setClose(close);
                weekRecord.setAdjClose(adjClose);
                weekRecord.setChange(change);
                weekRecord.setVolume(volume);
                weekRecord.setDealSum(dealSum);


                dayRecordDao.saveWeekRecord(weekRecord);

                preDayOfWeek = d.getDayOfWeek().getValue();
                startIndex = index;
                endIndex = index;
                index++;

            }

        }

        if(endIndex != -1){
            WeekRecord weekRecord = new WeekRecord();
            Date date = dayRecordList.get(endIndex).getDate();
            double open = dayRecordList.get(startIndex).getOpen();
            double high = -1;
            double low = Double.MAX_VALUE;
            double close = dayRecordList.get(endIndex).getClose();
            double adjClose = dayRecordList.get(endIndex).getAdjClose();
            double change;
            if(lastAdjClose == -1){
                change = 0;
                lastAdjClose = adjClose;
            }
            else{
                change = (adjClose - lastAdjClose)/adjClose*100;
                change = Double.valueOf(df.format(change));
                lastAdjClose = adjClose;
            }
            long volume = 0;
            double dealSum = 0;
            for(int tempIndex = startIndex; tempIndex <= endIndex; tempIndex++){
                DayRecord temp = dayRecordList.get(tempIndex);
                if(temp.getHigh() > high){
                    high = temp.getHigh();
                }

                if(temp.getLow() < low){
                    low = temp.getLow();
                }

                volume += temp.getVolume();
                dealSum += temp.getDealSum();
            }

            weekRecord.setStock(stock);
            weekRecord.setDate(date);
            weekRecord.setOpen(open);
            weekRecord.setHigh(high);
            weekRecord.setLow(low);
            weekRecord.setClose(close);
            weekRecord.setAdjClose(adjClose);
            weekRecord.setChange(change);
            weekRecord.setVolume(volume);
            weekRecord.setDealSum(dealSum);


            dayRecordDao.saveWeekRecord(weekRecord);

        }


    }

    private void updateMonthRecord(Stock stock){

        DecimalFormat df   = new DecimalFormat("#.00");
        List<DayRecord> dayRecordList = dayRecordDao.getDayRecordsByCode(stock.getCode());
        //Date lastMonthDate = dayRecordDao.getLastMonthDateOf(stock.getCode());
        List<MonthRecord> monthRecordList = dayRecordDao.getMonthRecordsByCode(stock.getCode());

        MonthRecord lastMonthRecord = null;
        if(monthRecordList.size()>0){
            if(monthRecordList.size()>1){
                lastMonthRecord = monthRecordList.get(monthRecordList.size()-2);
            }
            MonthRecord m = monthRecordList.get(monthRecordList.size()-1);
            dayRecordDao.deletRecord(m);
        }


        int index = 0;
        double lastAdjClose = -1; //用来记录上一个月的adjClose,用来计算change
        if(lastMonthRecord!=null){ //size大于1,说明最后一个是不稳定的，可能要更新

            while(dayRecordList.get(index).getDate().before(lastMonthRecord.getDate())){
                index++;
            }
            index++;
            lastAdjClose = lastMonthRecord==null ? -1 : lastMonthRecord.getAdjClose();
        }


        if(index >= dayRecordList.size()) return;

        int startIndex = index;
        int endIndex = -1;
        int preDayOfMonth = 0;
        while(index < dayRecordList.size()){
            DayRecord dayRecord = dayRecordList.get(index);
            LocalDate d = dayRecord.getDate().toLocalDate();
            if(d.getDayOfMonth() > preDayOfMonth){     //么有遇到新的一月
                preDayOfMonth = d.getDayOfMonth();
                endIndex = index;
                index++;
            }
            else{ //遇到新的一月

                MonthRecord monthRecord = new MonthRecord();
                Date date = dayRecordList.get(endIndex).getDate();
                double open = dayRecordList.get(startIndex).getOpen();
                double high = -1;
                double low = Double.MAX_VALUE;
                double close = dayRecordList.get(endIndex).getClose();
                double adjClose = dayRecordList.get(endIndex).getAdjClose();
                double change;
                if(lastAdjClose == -1){
                    change = 0;
                    lastAdjClose = adjClose;
                }
                else{
                    change = (adjClose - lastAdjClose)/adjClose*100;
                    change = Double.valueOf(df.format(change));
                    lastAdjClose = adjClose;
                }
                long volume = 0;
                double dealSum = 0;
                for(int tempIndex = startIndex; tempIndex <= endIndex; tempIndex++){
                    DayRecord temp = dayRecordList.get(tempIndex);
                    if(temp.getHigh() > high){
                        high = temp.getHigh();
                    }

                    if(temp.getLow() < low){
                        low = temp.getLow();
                    }

                    volume += temp.getVolume();
                    dealSum += temp.getDealSum();
                }

                monthRecord.setStock(stock);
                monthRecord.setDate(date);
                monthRecord.setOpen(open);
                monthRecord.setHigh(high);
                monthRecord.setLow(low);
                monthRecord.setClose(close);
                monthRecord.setAdjClose(adjClose);
                monthRecord.setChange(change);
                monthRecord.setVolume(volume);
                monthRecord.setDealSum(dealSum);



                dayRecordDao.saveMonthRecord(monthRecord);


                preDayOfMonth = d.getDayOfMonth();
                startIndex = index;
                endIndex = index;
                index++;

            }

        }

        if(endIndex != -1 ){
            MonthRecord monthRecord = new MonthRecord();
            Date date = dayRecordList.get(endIndex).getDate();
            double open = dayRecordList.get(startIndex).getOpen();
            double high = -1;
            double low = Double.MAX_VALUE;
            double close = dayRecordList.get(endIndex).getClose();
            double adjClose = dayRecordList.get(endIndex).getAdjClose();
            double change;
            if(lastAdjClose == -1){
                change = 0;
                lastAdjClose = adjClose;
            }
            else{
                change = (adjClose - lastAdjClose)/adjClose*100;
                change = Double.valueOf(df.format(change));
                lastAdjClose = adjClose;
            }
            long volume = 0;
            double dealSum = 0;
            for(int tempIndex = startIndex; tempIndex <= endIndex; tempIndex++){
                DayRecord temp = dayRecordList.get(tempIndex);
                if(temp.getHigh() > high){
                    high = temp.getHigh();
                }

                if(temp.getLow() < low){
                    low = temp.getLow();
                }

                volume += temp.getVolume();
                dealSum += temp.getDealSum();
            }

            monthRecord.setStock(stock);
            monthRecord.setDate(date);
            monthRecord.setOpen(open);
            monthRecord.setHigh(high);
            monthRecord.setLow(low);
            monthRecord.setClose(close);
            monthRecord.setAdjClose(adjClose);
            monthRecord.setChange(change);
            monthRecord.setVolume(volume);
            monthRecord.setDealSum(dealSum);


            dayRecordDao.saveMonthRecord(monthRecord);

        }



    }

    private DebtPayingAbility getDebtPayingAbilityByLine(String line){
        line = line + ",--";
        String[] strs = line.split(",");

        int code = Integer.valueOf(strs[1]);
        double currentRatio = getDoubleByString(strs[3]);
        double quickRatio = getDoubleByString(strs[4]); //速动比率
        double cashRatio = getDoubleByString(strs[5]); //现金比率
        double icRatio = getDoubleByString(strs[6]); //利息支付倍数
        double sheqRatio = getDoubleByString(strs[7]); //股东权益比率
        double adRatio;//股东权益增长率
        if(strs.length>8){
            adRatio = getDoubleByString(strs[8]);
        }
        else {
            adRatio = Double.MIN_VALUE;
        }

        DebtPayingAbility debtPayingAbility = new DebtPayingAbility();
        debtPayingAbility.setCode(code);
        debtPayingAbility.setCurrentRatio(currentRatio);
        debtPayingAbility.setQuickRatio(quickRatio);
        debtPayingAbility.setCashRatio(cashRatio);
        debtPayingAbility.setIcRatio(icRatio);
        debtPayingAbility.setSheqRatio(sheqRatio);
        debtPayingAbility.setAdRatio(adRatio);
        return  debtPayingAbility;
    }

    private DevelopingAbility getDevelopingAbilityByLine(String line){
        line = line + ",--";
        String[] strs = line.split(",");

        int code = Integer.valueOf(strs[1]);
        double mbrg = getDoubleByString(strs[3]); //主营业务收入增长率(%)
        double nprg = getDoubleByString(strs[4]); //净利润增长率(%)
        double nav = getDoubleByString(strs[5]); //净资产增长率
        double targ = getDoubleByString(strs[6]); //总资产增长率
        double epsg = getDoubleByString(strs[7]); //每股收益增长率
        double seg; //股东权益增长率
        if(strs.length>8){
            seg = getDoubleByString(strs[8]);
        }
        else {
            seg = Double.MIN_VALUE;
        }

        DevelopingAbility developingAbility = new DevelopingAbility();
        developingAbility.setCode(code);
        developingAbility.setMbrg(mbrg);
        developingAbility.setNprg(nprg);
        developingAbility.setNav(nav);
        developingAbility.setTarg(targ);
        developingAbility.setEpsg(epsg);
        developingAbility.setSeg(seg);

        return developingAbility;

    }

    private OperationAbility getOperationAbilityByLine(String line){
        line = line + ",--";
        String[] strs = line.split(",");

        int code = Integer.valueOf(strs[1]);
        double arTurnover = getDoubleByString(strs[3]);  //应收账款周转率(次)
        double arTurnDays = getDoubleByString(strs[4]);   //应收账款周转天数(天)
        double inventoryTurnover = getDoubleByString(strs[5]); //存货周转率(次)
        double inventoryDays = getDoubleByString(strs[6]);   //存货周转天数(天)
        double currentAssetTurnover = getDoubleByString(strs[7]); //流动资产周转率(次)
        double currentAssetDays;   //流动资产周转天数(天)
        if(strs.length>8){
            currentAssetDays = getDoubleByString(strs[8]);
        }
        else {
            currentAssetDays = Double.MIN_VALUE;
        }

        OperationAbility operationAbility = new OperationAbility();
        operationAbility.setCode(code);
        operationAbility.setArTurnover(arTurnover);
        operationAbility.setArTurnDays(arTurnDays);
        operationAbility.setInventoryTurnover(inventoryTurnover);
        operationAbility.setInventoryDays(inventoryDays);
        operationAbility.setCurrentAssetTurnover(currentAssetTurnover);
        operationAbility.setCurrentAssetDays(currentAssetDays);
        return operationAbility;
    }

    private Profitability getProfitability(String line){
        line = line + ",--";
        String[] strs = line.split(",");

        int code = Integer.valueOf(strs[1]);
        double roe = getDoubleByString(strs[3]);   //净资产收益率
        double netProfitRatio = getDoubleByString(strs[4]); //净利率
        double grossProfitRate = getDoubleByString(strs[5]);  //毛利率
        double netProfit = getDoubleByString(strs[6]);   //净利润(万元)
        double esp = getDoubleByString(strs[7]);   //每股收益
        double income = getDoubleByString(strs[8]);   //营业收入(百万元)
        double bips;//每股主营业务收入(元)
        if(strs.length>9){
            bips = getDoubleByString(strs[9]);
        }
        else {
            bips = Double.MIN_VALUE;
        }

        Profitability profitability = new Profitability();
        profitability.setCode(code);
        profitability.setRoe(roe);
        profitability.setNetProfitRatio(netProfitRatio);
        profitability.setGrossProfitRate(grossProfitRate);
        profitability.setNetProfit(netProfit);
        profitability.setEsp(esp);
        profitability.setIncome(income);
        profitability.setBips(bips);
        return profitability;

    }

    private double getDoubleByString(String s){

        double result;
        if(s.equals("--") || s.equals("")){
            return Double.MIN_VALUE;
        }
        else return Double.valueOf(s);
    }


    private void updateMeanPrice(Stock stock){
        DecimalFormat   df   = new DecimalFormat("#.00");
        List<DayRecord> dayRecordList = dayRecordDao.getDayRecordsByCode(stock.getCode());
        List<MeanPrice> meanPriceList = meanPriceDao.getMeanPriceListByCode(stock.getCode());

        int index = 0;

        if(meanPriceList != null && meanPriceList.size() > 0){
            Date lastDate = meanPriceList.get(meanPriceList.size()-1).getDate();
            while (dayRecordList.get(index).getDate().before(lastDate)){
                index++;
            }
            index++;
        }

        while(index < dayRecordList.size()){

            MeanPrice meanPrice = new MeanPrice();

            double ma5 = 0;
            double ma10 = 0;
            double ma30 = 0;
            double ma60 = 0;
            double ma120 = 0;

            int startIndex = index - 4;
            if(startIndex < 0){
                startIndex = 0;
            }
            for(int i=startIndex; i<=index; i++){
                ma5 += dayRecordList.get(i).getClose();
            }
            ma5 = ma5/(index-startIndex+1);
            ma5 = Double.valueOf(df.format(ma5));

            startIndex = index - 9;
            if(startIndex < 0){
                startIndex = 0;
            }
            for(int i=startIndex; i<=index; i++){
                ma10 += dayRecordList.get(i).getClose();
            }
            ma10 = ma10/(index-startIndex+1);
            ma10 = Double.valueOf(df.format(ma10));

            startIndex = index - 29;
            if(startIndex < 0){
                startIndex = 0;
            }
            for(int i=startIndex; i<=index; i++){
                ma30 += dayRecordList.get(i).getClose();
            }
            ma30 = ma30/(index-startIndex+1);
            ma30 = Double.valueOf(df.format(ma30));

            startIndex = index - 59;
            if(startIndex < 0){
                startIndex = 0;
            }
            for(int i=startIndex; i<=index; i++){
                ma60 += dayRecordList.get(i).getClose();
            }
            ma60 = ma60/(index-startIndex+1);
            ma60 = Double.valueOf(df.format(ma60));

            startIndex = index - 119;
            if(startIndex < 0){
                startIndex = 0;
            }
            for(int i=startIndex; i<=index; i++){
                ma120 += dayRecordList.get(i).getClose();
            }
            ma120 = ma120/(index-startIndex+1);
            ma120 = Double.valueOf(df.format(ma120));

            meanPrice.setCode(stock.getCode());
            meanPrice.setDate(dayRecordList.get(index).getDate());
            meanPrice.setMA5(ma5);
            meanPrice.setMA10(ma10);
            meanPrice.setMA30(ma30);
            meanPrice.setMA60(ma60);
            meanPrice.setMA120(ma120);

            meanPriceDao.saveMeanPrice(meanPrice);

            index++;

        }


    }


    private void creatFile(File file){
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
