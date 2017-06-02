package trillionaire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import trillionaire.vo.BackTestParams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by USER on 2017/6/2.
 */
@Controller
@RequestMapping("/backtest")
public class BackTestController {

    @RequestMapping(value = "/run", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> runBackTest( BackTestParams params){

        Map<String, Object> result = new HashMap<>();

        List<String> date = new ArrayList<>();
        List<Double> data1 = new ArrayList<>();
        List<Double> data2 = new ArrayList<>();

        LocalDate t = LocalDate.now();
        for(int i=0; i<100; i++){
            date.add(t.toString());
            data1.add(i+0.5);
            data2.add(i+1.5);
            t = t.plusDays(1);
        }

        result.put("datelist", date);
        result.put("data1",data1);
        result.put("data2", data2);
        System.out.println("exe...");

        return result;
    }

}
