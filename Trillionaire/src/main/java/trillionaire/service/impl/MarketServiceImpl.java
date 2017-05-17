package trillionaire.service.impl;

import org.springframework.stereotype.Service;
import trillionaire.vo.RankTable;
import trillionaire.model.Stock;
import trillionaire.service.MarketService;

import java.util.Map;

/**
 * Created by michaeltan on 2017/5/16.
 */
@Service
public class MarketServiceImpl implements MarketService {

    public Map<String, RankTable> getCategoryCondition(String category) {

        return null;
    }

    public Map<String, Stock> getBoardCondition(String board) {

        return null;
    }
}
