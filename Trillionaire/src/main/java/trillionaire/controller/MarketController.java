package trillionaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import trillionaire.model.DayRecord;
import trillionaire.vo.RankTable;
import trillionaire.model.Stock;
import trillionaire.service.MarketService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by michaeltan on 2017/5/13.
 */
@Controller
@RequestMapping("/market")
public class MarketController {
    @Autowired
    private MarketService marketService;

    @RequestMapping(value = "category", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<RankTable>> getCategoryCondition(@RequestParam("category") String category) {
        Map<String, List<RankTable>> map = new HashMap<String, List<RankTable>>();
        List<RankTable> list = new ArrayList<RankTable>();

        if (category.equals("industry")) {

        } else if (category.equals("area")) {

        } else if (category.equals("concept")) {

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


    @RequestMapping(value = "board", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<DayRecord>> getBoardCondition(@RequestParam("board")String board) {
        if (board.equals("SS")) {  //沪A

        } else if (board.equals("SZ")) { //深A

        } else if (board.equals("GEM")) { //创业板

        }else if (board.equals("SME")) {  //中小板

        }
        return null;
    }

    @RequestMapping(value = "industry",method = RequestMethod.GET)
    @ResponseBody
    public List<DayRecord> getBoardRank(@RequestParam("industry") String board){

        return null;
    }


}
