package trillionaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import trillionaire.model.DayRecord;
import trillionaire.model.RealTimeStock;
import trillionaire.service.MarketService;
import trillionaire.vo.RankTable;

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

    @RequestMapping(value = "/rank/category", method = RequestMethod.GET)
    @ResponseBody
    public List<RankTable> getCategoryCondition(@RequestParam("board") String board) {
        return marketService.getSquare(board);
    }


    @RequestMapping(value = "/board", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getBoardCondition() {
        return marketService.getBoardRank();
    }

    @RequestMapping(value = "industry", method = RequestMethod.GET)
    @ResponseBody
    public List<DayRecord> getBoardRank(@RequestParam("industry") String board) {

        return null;
    }

    @RequestMapping(value = "/rank/industry", method = RequestMethod.GET)
    @ResponseBody
    public List<RealTimeStock> getIndustryRank(String industry) {
        return marketService.getIndustryRank(industry);
    }

    @RequestMapping(value = "/rank/concept", method = RequestMethod.GET)
    @ResponseBody
    public List<RealTimeStock> getConceptRank(String concept) {
        return marketService.getConceptRank(concept);
    }

    @RequestMapping(value = "/rank/area", method = RequestMethod.GET)
    @ResponseBody
    public List<RealTimeStock> getAreaRank(String area) {
        return marketService.getAreaRank(area);
    }

}
