package trillionaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import trillionaire.model.Stock;
import trillionaire.service.StockService;
import trillionaire.vo.Earnings;
import trillionaire.vo.PriceTarget;
import trillionaire.vo.RecommendationTrends;
import trillionaire.vo.StockAbility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
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
    public Map<String, Object> getDailyCandle(@PathVariable("code") String code, @PathVariable("span") String span) {

        Map<String, Object> map = stockService.getStockInfo(code, span);
        return map;
    }


    @RequestMapping(value = "/associate", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> associate(String input) {

        return null;
    }

    @RequestMapping(value = "code", method = RequestMethod.GET)
    public void search(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code) throws Exception {
        request.getRequestDispatcher("stock.html").forward(request, response);
    }

    @RequestMapping(value = "/ability", method = RequestMethod.GET)
    @ResponseBody
    public StockAbility getAbility(@RequestParam("code") String code) {
        //StockAbility stockAbility = stockService.getStockAbility(code);
        StockAbility stockAbility1 = new StockAbility(1.1, 1.2, 1.3, 1.4);
        return stockAbility1;
    }

    @RequestMapping(value = "/prevail", method = RequestMethod.GET)
    @ResponseBody
    public double getPrevailTrend(String code) {
        double obv = stockService.getOBV(code);
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
        PriceTarget priceTarget = stockService.getPriceTarget(code);
        return priceTarget;
    }

    @RequestMapping(value = "/earnings", method = RequestMethod.GET)
    @ResponseBody
    public List<Earnings> getEarnings(String code) {
        List<Earnings> earnings = stockService.getEarnings(code);
        return earnings;
    }

    @RequestMapping(value = "/kdj", method = RequestMethod.GET)
    @ResponseBody
    public List<Double> getKDJ(String code) {

        return null;
    }

    @RequestMapping(value = "/bias", method = RequestMethod.GET)
    @ResponseBody
    public List<Double> getBIAS(String code) {

        return null;
    }

    @RequestMapping(value = "/macd", method = RequestMethod.GET)
    @ResponseBody
    public List<Double> getMACD(String code) {

        return null;
    }

    @RequestMapping(value = "/similar", method = RequestMethod.GET)
    @ResponseBody
    public List<Stock> getSimilarStock(String input) {

        return null;
    }

}
