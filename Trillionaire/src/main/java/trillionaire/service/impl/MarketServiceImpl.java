package trillionaire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trillionaire.dao.RealTimeStockDao;
import trillionaire.model.RealTimeStock;
import trillionaire.service.MarketService;
import trillionaire.vo.RankTable;

import java.util.*;

/**
 * Created by michaeltan on 2017/5/16.
 */
@Service
public class MarketServiceImpl implements MarketService {
    @Autowired
    RealTimeStockDao realTimeStockDao;

    @Override
    public Map<String, Object> getSquare(String category) {
        Map<String, Object> map = new HashMap<>();
        List<RankTable> list = new ArrayList<>();
        if (category.equals("industry")) {

        } else if (category.equals("area")) {

        } else if (category.equals("concept")) {

        } else {

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
    public Map<String, Object> getBoardRank() {
        Map<String, Object> map = new HashMap<>();
        List<RealTimeStock> list = realTimeStockDao.getAll();
        List<RealTimeStock> ss = new ArrayList<>();
        List<RealTimeStock> sz = new ArrayList<>();
        List<RealTimeStock> gem = new ArrayList<>();
        List<RealTimeStock> sme = new ArrayList<>();
            Collections.sort(list, new Comparator<RealTimeStock>() {
            @Override
            public int compare(RealTimeStock o1, RealTimeStock o2) {
                return new Double(o2.getChangepercent()).compareTo(o1.getChangepercent());
            }
        });

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCode().startsWith("6")) { //沪A
                ss.add(list.get(i));
            } else if (list.get(i).getCode().startsWith("000")) { //深A
                sz.add(list.get(i));
            } else if (list.get(i).getCode().startsWith("3")) {  //创业板
                gem.add(list.get(i));
            } else if (list.get(i).getCode().startsWith("002")) {  //中小板
                sme.add(list.get(i));
            }
        }

        Collections.sort(list, new Comparator<RealTimeStock>() {
            @Override
            public int compare(RealTimeStock o1, RealTimeStock o2) {
                return new Double(o1.getChangepercent()).compareTo(o2.getChangepercent());
            }
        });

        map.put("ss", ss);
        map.put("sz", sz);
        map.put("gem", gem);
        map.put("sme", sme);
        return map;
    }
}
