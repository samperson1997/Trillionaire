package trillionaire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import trillionaire.model.RankTable;
import trillionaire.model.Stock;

import java.util.Map;

/**
 * Created by michaeltan on 2017/5/13.
 */
@Controller
@RequestMapping("/market")
public class MarketController {

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, RankTable> getCategoryCondition(String category){

        return null;
    }


    @RequestMapping(value = "/board", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Stock> getBoardCondition(String board) {
        return null;
    }

}
