package trillionaire.service.impl.apriori;

import org.springframework.beans.factory.annotation.Autowired;
import trillionaire.dao.DayRecordDao;
import trillionaire.dao.StockDao;
import trillionaire.model.DayRecord;
import trillionaire.model.Stock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaeltan on 2017/5/30.
 */
public class SimilarStockSelector {
    @Autowired
    StockDao stockDao;
    @Autowired
    DayRecordDao dayRecordDao;

    private Apriori apriori = new Apriori();

    public List<List<String>> getRecord(int code1, int code2) {
        List<List<String>> record = new ArrayList<List<String>>();
        List<List<DayRecord>> stockList = dayRecordDao.getAligningDayRecords(code1,code2,180);
        for (int i = 0; i < stockList.size(); i++) {//处理矩阵中的T、F、YES、NO
            List<String> lineList = new ArrayList<>();
            for (int j=0; j<stockList.get(0).size(); j++){
                if (stockList.get(j).get(i).getChange()>0){  //涨
                    lineList.add(stockList.get(j).get(i).getStock().getName());   //添加股票名称或代码(2选1)
                }
            }
            record.add(lineList);
        }
        return record;
    }

    public void selects(int code){
        List<Stock> stockList = stockDao.getAllStocks();
        for (int i = 0; i < stockList.size(); i++) {//处理矩阵中的T、F、YES、NO
            if (code!=stockList.get(i).getCode()) {
                List<List<String>> data = getRecord(code, stockList.get(i).getCode());
                apriori.setRecord(data);
                apriori.select();
            }
        }
    }
}
