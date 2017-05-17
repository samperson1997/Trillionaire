package trillionaire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trillionaire.dao.DayRecordDao;
import trillionaire.model.DayRecord;
import trillionaire.vo.Earnings;
import trillionaire.vo.RecommendationTrends;
import trillionaire.vo.StockAbility;
import trillionaire.service.StockService;

import java.util.List;
import java.util.Map;

/**
 * Created by michaeltan on 2017/5/6.
 */
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private DayRecordDao dayRecordDao;

    public List<DayRecord> getStockInfo(String code) {
        int stock = Integer.parseInt(code);
        List<DayRecord> list = dayRecordDao.getDayRecordsByCode(stock);
        return list;
    }

    public Map<String, Object> getSimilarStock(String input) {

        return null;
    }

    public StockAbility getStockAbility(String code) {

        return null;
    }

    public Map<String, Earnings> getEarnings(String code) {

        return null;
    }

    public double getRecommendationRating(String code) {

        return 0;
    }

    public RecommendationTrends getRecommendationTrends(String code) {

        return null;
    }

    public double getPriceTarget(String code) {

        return 0;
    }
}
