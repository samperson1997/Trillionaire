package trillionaire.service.impl;

import org.springframework.stereotype.Service;
import trillionaire.vo.RankTable;
import trillionaire.model.Stock;
import trillionaire.service.MarketService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by michaeltan on 2017/5/16.
 */
@Service
public class MarketServiceImpl implements MarketService {


    public Map<String, List<RankTable>> getCategoryRank(String board) {
        return null;
    }

    public Map<String, List<Stock>> getBoardRank(String category) {
        return null;
    }
}
