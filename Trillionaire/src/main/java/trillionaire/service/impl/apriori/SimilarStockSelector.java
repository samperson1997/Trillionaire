package trillionaire.service.impl.apriori;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import trillionaire.dao.DayRecordDao;
import trillionaire.dao.StockDao;
import trillionaire.model.DayRecord;
import trillionaire.model.Stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by michaeltan on 2017/5/30.
 */
@Component
public class SimilarStockSelector {
    @Autowired
    StockDao stockDao;
    @Autowired
    DayRecordDao dayRecordDao;

    private Apriori apriori = new Apriori();

    public List<List<String>> getRecord(int code1, int code2) {
        List<List<String>> record = new ArrayList<>();
        List<List<DayRecord>> stockList = dayRecordDao.getAligningDayRecords(code1, code2, 200);

        for (int i = 0; i < stockList.get(0).size(); i++) {//处理矩阵中的T、F、YES、NO
            List<String> lineList = new ArrayList<>();
            lineList.add(String.valueOf(i));   //添加股票名称或代码(2选1)
            for (int j = 0; j < stockList.size(); j++) {
                if (stockList.get(j).get(i).getChange() > 0) {  //涨
                    lineList.add(stockList.get(j).get(i).getStock().getName());   //添加股票名称或代码(2选1)
                }
            }
            record.add(lineList);
        }
        return record;
    }

    public List<Map<Integer, Object>> selects(int code) {
        List<Stock> stockList = stockDao.getAllStocks();
        List<Map<Integer, Object>> result = new ArrayList<>();
        for (int i = 0; i < stockList.size(); i++) {
            if (code != stockList.get(i).getCode()) {
                List<List<String>> data = getRecord(code, stockList.get(i).getCode());
                Map<Integer, Object> map = apriori.select(data, stockList.get(i).getCode());
                if (map.containsKey(0)) {
                    result.add(map);
                }
            }
        }
        return result;
    }
}