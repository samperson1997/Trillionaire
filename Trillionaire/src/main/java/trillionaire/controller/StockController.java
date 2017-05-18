package trillionaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import trillionaire.model.DayRecord;
import trillionaire.service.StockService;
import trillionaire.vo.Earnings;
import trillionaire.vo.PriceTarget;
import trillionaire.vo.RecommendationTrends;
import trillionaire.vo.StockAbility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by michaeltan on 2017/5/11.
 */
@Controller
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/{code}/{span}", method = RequestMethod.GET)
    @ResponseBody
    public List<DayRecord> getDailyCandle(@PathVariable("code") String code, @PathVariable("span") String span) {
        List<DayRecord> list = stockService.getStockInfo(code, "1");
        return list;
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    @ResponseBody
    public String getHistoryInfo(String code) {
        String prefix = "http://hq.sinajs.cn/list=";
        String url = prefix.concat(code);
        return "s";
    }

    @RequestMapping(value = "/associate", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> getSimilarStock(String input) {

        return null;
    }

    @RequestMapping(value = "code", method = RequestMethod.GET)
    public void search(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code) throws Exception {
        System.out.println("1");
        request.getRequestDispatcher("stock.html").forward(request, response);
    }

    @RequestMapping(value = "/ability", method = RequestMethod.GET)
    @ResponseBody
    public StockAbility getAbility(String code) {
        StockAbility stockAbility = stockService.getStockAbility(code);
        return stockAbility;
    }

    @RequestMapping(value = "/prevail", method = RequestMethod.GET)
    @ResponseBody
    public String getPrevailTrend(String code) {
        String obv = stockService.getOBV(code);
        return obv;
    }

    @RequestMapping(value = "/trends", method = RequestMethod.GET)
    @ResponseBody
    public RecommendationTrends getRecommendationTrends(String code) {
        RecommendationTrends recommendationTrends = stockService.getRecommendationTrends(code);
        return recommendationTrends;
    }

    @RequestMapping(value = "/target", method = RequestMethod.GET)
    @ResponseBody
    public PriceTarget getPriceTarget(String code) {
        PriceTarget priceTarget = null;
        return priceTarget;
    }

    @RequestMapping(value = "/earnings", method = RequestMethod.GET)
    @ResponseBody
    public List<Earnings> getEarnings(String code){
        List<Earnings> earnings = stockService.getEarnings(code);
        return earnings;
    }

    @RequestMapping(value = "/kdj", method = RequestMethod.GET)
    @ResponseBody
    public void getKDJ(String code){

    }

    @RequestMapping(value = "/bias", method = RequestMethod.GET)
    @ResponseBody
    public void getBIAS(String code){

    }

    @RequestMapping(value = "/macd", method = RequestMethod.GET)
    @ResponseBody
    public void getMACD(String code){

    }

}
