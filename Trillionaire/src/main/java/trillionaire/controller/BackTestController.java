package trillionaire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import trillionaire.vo.BackTestParams;
import trillionaire.vo.StraIdName;

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

    @RequestMapping(value = "/get_strategy_list", method = RequestMethod.GET)
    @ResponseBody
    public List<StraIdName> getUserStrategies(int userId){

        StraIdName straIdName = new StraIdName();
        straIdName.setSid(1);
        straIdName.setStrategName("sA");
        StraIdName straIdName2 = new StraIdName();
        straIdName.setSid(2);
        straIdName.setStrategName("sB");

        List<StraIdName> result = new ArrayList<>();
        result.add(straIdName);
        result.add(straIdName2);

        return result;
    }

    public String creatStrategy(int userId, String strategyname){

        return "this is default strategy content!";

    }

    public Map<String, Object> saveStrategy(int sid, String content){

        return null;
    }

    public Map<String, Object> openStrategy(int sid, String content){

        return null;
    }

    public Map<String, Object> deleteStrategy(int sid, String content){

        return null;
    }

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
