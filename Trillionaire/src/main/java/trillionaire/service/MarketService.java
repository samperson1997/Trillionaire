package trillionaire.service;

import trillionaire.model.RankTable;
import trillionaire.model.Stock;

import java.util.Map;

/**
 * Created by michaeltan on 2017/5/16.
 */
public interface MarketService {
    /**
     * 获得涨跌幅版块的方块
     *
     * @param category 股票版块
     * @return 版块涨跌的map
     */
    public Map<String, RankTable> getCategoryCondition(String category);

    /**
     * 获得不同版块的涨跌幅排名
     *
     * @param board 股票版块
     * @return 版块涨跌的map
     */
    public Map<String, Stock> getBoardCondition(String board);

}
