package trillionaire.service;

import trillionaire.model.DayRecord;
import trillionaire.model.RealTimeStock;
import trillionaire.vo.RankTable;
import trillionaire.model.Stock;

import java.util.List;
import java.util.Map;

/**
 * Created by michaeltan on 2017/5/16.
 */

public interface MarketService {
    /**
     * 获得涨跌幅版块的方块
     *
     * @param board 股票版块
     * @return 版块涨跌的map
     */
    public List<RankTable> getSquare(String board);

    /**
     * 获得不同版块的涨跌幅排名
     *
     * @param board 股票版块
     * @return 版块涨跌的map
     */
    public Map<String, Object> getBoardRank();

    /**
     * 获得行业的涨跌幅排名
     *
     * @param industry 股票版块
     * @return 行业涨跌股票
     */
    public List<RealTimeStock> getIndustryRank(String industry);

    /**
     * 获得行业的涨跌幅排名
     *
     * @param concept 股票版块
     * @return 概念涨跌股票
     */
    public List<RealTimeStock> getConceptRank(String concept);

    /**
     * 获得行业的涨跌幅排名
     *
     * @param area 股票版块
     * @return 地域涨跌股票
     */
    public List<RealTimeStock> getAreaRank(String area);
}
