package trillionaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import trillionaire.model.DayRecord;
import trillionaire.model.Stock;
import trillionaire.service.StockService;

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

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    @ResponseBody
    public List<DayRecord> getStockInfo(@PathVariable("code") String code) {
        System.out.println("123");
        List<DayRecord> list = stockService.getStockInfo(code);
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

}
