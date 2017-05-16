package trillionaire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import trillionaire.model.RankTable;
import trillionaire.model.Stock;

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

    @RequestMapping(value = "category", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<RankTable>> getCategoryCondition(@RequestParam("category") String category){
        RankTable rankTable = new RankTable("金融业", 1.0, 2,2,2,"谭昕控股",3.0);
        RankTable rankTable2 = new RankTable("金融业", 20.0,2,2,2,"谭昕控股",3.0);
        RankTable rankTable3 = new RankTable("金融业", 1.0,2,2,2,"谭昕控股",3.0);
        RankTable rankTable4 = new RankTable("金融业", 1.0,2,2,2,"谭昕控股",3.0);
        RankTable rankTable5 = new RankTable("金融业", 1.0,2,2,2,"谭昕控股",3.0);
        RankTable rankTable6 = new RankTable("金融业", 1.0,2,2,2,"谭昕控股",3.0);
        RankTable rankTable7 = new RankTable("金融业", 1.0,2,2,2,"谭昕控股",3.0);
        RankTable rankTable8 = new RankTable("金融业", 1.0,2,2,2,"谭昕控股",3.0);
        List<RankTable> list = new ArrayList<RankTable>() ;
        list.add(rankTable);
        list.add(rankTable2);
        list.add(rankTable3);
        list.add(rankTable4);
        list.add(rankTable5);
        list.add(rankTable6);
        list.add(rankTable7);
        list.add(rankTable8);
        Map<String, List<RankTable>> map = new HashMap<String, List<RankTable>>();
        map.put("up",list);
        map.put("down",list);

        return map;
    }


    @RequestMapping(value = "/board", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Stock> getBoardCondition(String board) {
        return null;
    }

}
