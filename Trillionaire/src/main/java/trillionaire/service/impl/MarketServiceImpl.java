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

    @Override
    public Map<String, List<RankTable>> getSquare(String category) {
        Map<String, List<RankTable>> map = new HashMap<>();
        List<RankTable> list = new ArrayList<>();
        if (category.equals("industry")) {

        } else if (category.equals("area")) {

        } else if (category.equals("concept")) {

        }else {

        }
        RankTable rankTable = new RankTable("金融业", 1.0, 2, 2, 2, "谭昕控股", 3.0);
        RankTable rankTable2 = new RankTable("金融业", 20.0, 2, 2, 2, "谭昕控股", 3.0);
        RankTable rankTable3 = new RankTable("金融业", 1.0, 2, 2, 2, "谭昕控股", 3.0);
        RankTable rankTable4 = new RankTable("金融业", 1.0, 2, 2, 2, "谭昕控股", 3.0);
        RankTable rankTable5 = new RankTable("金融业", 1.0, 2, 2, 2, "谭昕控股", 3.0);
        RankTable rankTable6 = new RankTable("金融业", 1.0, 2, 2, 2, "谭昕控股", 3.0);
        RankTable rankTable7 = new RankTable("金融业", 1.0, 2, 2, 2, "谭昕控股", 3.0);
        RankTable rankTable8 = new RankTable("金融业", 1.0, 2, 2, 2, "谭昕控股", 3.0);
        list.add(rankTable);
        list.add(rankTable2);
        list.add(rankTable3);
        list.add(rankTable4);
        list.add(rankTable5);
        list.add(rankTable6);
        list.add(rankTable7);
        list.add(rankTable8);
        map.put("up", list);
        map.put("down", list);

        return map;
    }

    @Override
    public Map<String, List<Stock>> getBoardRank(String board) {
        if (board.equals("SS")) {  //沪A

        } else if (board.equals("SZ")) { //深A

        } else if (board.equals("GEM")) { //创业板

        }else if (board.equals("SME")) {  //中小板

        }

        return null;
    }
}
