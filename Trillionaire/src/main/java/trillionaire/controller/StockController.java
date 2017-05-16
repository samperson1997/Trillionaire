package trillionaire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import trillionaire.model.Stock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by michaeltan on 2017/5/11.
 */
@Controller
@RequestMapping("/stock")
public class StockController {

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getStockInfo(@PathVariable("code") String code) {
        Stock stock = new Stock("2017/5/13", 1.0, 2.0, 3.0, 4.0, 5, 6, 7.0);
        Stock stock1 = new Stock("2017/5/12", 1.0, 2.0, 3.0, 4.0, 5, 6, 7.0);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(stock.getDate(), stock);
        map.put(stock1.getDate(), stock1);
        return map;

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
