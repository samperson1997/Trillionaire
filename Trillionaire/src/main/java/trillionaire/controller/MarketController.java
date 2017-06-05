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
        Map<String, List<RankTable>> map = marketService.getSquare(category);

        return map;
    }


    @RequestMapping(value = "board", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<DayRecord>> getBoardCondition(@RequestParam("board")String board) {

        return null;
    }

    @RequestMapping(value = "industry",method = RequestMethod.GET)
    @ResponseBody
    public List<DayRecord> getBoardRank(@RequestParam("industry") String board){

        return null;
    }


}
