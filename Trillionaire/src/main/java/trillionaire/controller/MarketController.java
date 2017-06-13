package trillionaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import trillionaire.model.RealTimeStock;
import trillionaire.service.MarketService;
import trillionaire.vo.BoardVO;

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
    public Map<String, Object> getCategoryCondition(@RequestParam("board") String board) {
        return marketService.getSquare(board);
    }

    @RequestMapping(value = "/board", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getBoardCondition() {
        return marketService.getBoardRank();
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

    @RequestMapping(value = "/list/concept", method = RequestMethod.GET)
    @ResponseBody
    public List<BoardVO> getConceptList() {
        return marketService.getConceptList();
    }

    @RequestMapping(value = "/list/area", method = RequestMethod.GET)
    @ResponseBody
    public List<BoardVO> getAreaList() {
        return marketService.getAreaList();
    }

    @RequestMapping(value = "/list/industry", method = RequestMethod.GET)
    @ResponseBody
    public List<BoardVO> getIndustryList() {
        return marketService.getIndustryList();
    }


}
