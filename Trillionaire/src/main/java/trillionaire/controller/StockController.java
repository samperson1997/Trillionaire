package trillionaire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by michaeltan on 2017/5/11.
 */
@Controller
@RequestMapping("/spec")
public class StockController {

    @RequestMapping(value = "realtime", method = RequestMethod.GET)
    @ResponseBody
    public String getStockInfo(String code){
        String prefix = "http://hq.sinajs.cn/list=";
        String url = prefix.concat(code);
        return "s";
    }

    @RequestMapping(value = "history", method = RequestMethod.GET)
    @ResponseBody
    public String getHistoryInfo(String code){
        String prefix = "http://hq.sinajs.cn/list=";
        String url = prefix.concat(code);
        return "s";
    }


}
