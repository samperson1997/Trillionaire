package trillionaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import trillionaire.model.RealTimeStock;
import trillionaire.model.Stock;
import trillionaire.service.MinutePriceDataService;
import trillionaire.service.StockService;
import trillionaire.vo.*;

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
    @Autowired
    private MinutePriceDataService minutePriceDataService;

    @RequestMapping(value = "/{code}/{span}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCandle(@PathVariable("code") String code, @PathVariable("span") String span) {
        Map<String, Object> map = stockService.getStockInfo(code, span);
        return map;
    }

    @RequestMapping(value = "/updateRealtime", method = RequestMethod.GET)
    @ResponseBody
    public RealTimeStock updateRealTime(String code) {
        return stockService.updateRealTime(code);
    }

    @RequestMapping(value = "/getRealtime", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getRealTime(String code) {
        return minutePriceDataService.getMinutePriceDate(code);
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
        return stockService.getVR(code);
    }

    @RequestMapping(value = "/trends", method = RequestMethod.GET)
    @ResponseBody
    public RecommendationTrends getRecommendationTrends(String code) {
        return stockService.getRecommendationTrends(code);
    }

    @RequestMapping(value = "/target", method = RequestMethod.GET)
    @ResponseBody
    public PriceTarget getPriceTarget(String code) {
        return stockService.getPriceTarget(code);
    }

    @RequestMapping(value = "/earnings", method = RequestMethod.GET)
    @ResponseBody
    public List<Earnings> getEarnings(String code) {
        return stockService.getEarnings(code);
    }

    @RequestMapping(value = "/kdj", method = RequestMethod.GET)
    @ResponseBody
    public List<KDJ> getKDJ(String code) {
        return stockService.getKDJ(code);
    }

    @RequestMapping(value = "/bias", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getBIAS(String code) {
        return stockService.getBIAS(code);
    }

    @RequestMapping(value = "/macd", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getMACD(String code) {
        return stockService.getMACD(code);
    }

    @RequestMapping(value = "/similar", method = RequestMethod.GET)
    @ResponseBody
    public List<Stock> getSimilarStock(String input) {

        return null;
    }

    @RequestMapping(value = "/margin", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getMargin(@RequestParam("code1") String code1, @RequestParam("code2") String code2, @RequestParam("code3") String code3) {
        return stockService.getMargin(code1, code2, code3);
    }

}
