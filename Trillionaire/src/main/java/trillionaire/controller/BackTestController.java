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

    /**
     *
     * @param userId
     * @return result{
     *              'msg':
     *              'nameList':[{
     *                  'sid',
     *                  'strategyName'
     *              }...]
     *          }
     */
    @RequestMapping(value = "/get_strategy_list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUserStrategies(int userId){

        StraIdName straIdName = new StraIdName();
        straIdName.setSid(1);
        straIdName.setStrategName("sA");
        StraIdName straIdName2 = new StraIdName();
        straIdName2.setSid(2);
        straIdName2.setStrategName("sB");

        List<StraIdName> list = new ArrayList<>();
        list.add(straIdName);
        list.add(straIdName2);

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "success");
        result.put("nameList", list);

        return result;
    }



    /**
     *
     * @param sid
     * @param strategyName
     * @param content
     * @return result{
     *              'msg'
     *              'sid'
     *          }
     */
    @RequestMapping(value = "/save_strategy", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> saveStrategy(int sid, String strategyName, String content){

            System.out.println(sid);
            System.out.println(strategyName);
            System.out.println(content);

            Map<String, Object> result = new HashMap<>();
            result.put("msg", "success");
            result.put("sid", 55);

        return result;
    }

    /**
     *
     * @param sid
     * @return result{
     *              'msg'
     *              'sid'
     *              'strategyName'
     *              'strategyContent'
     *          }
     */
    @RequestMapping(value = "/open_strategy", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> openStrategy(int sid){

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "success");
        result.put("sid", 55);
        result.put("strategyName","testStrategy");
        result.put("strategyContent", "#python content!!!>>>!!!");

        return result;
    }


    /**
     *
     * @param sid
     * @return result{
     *              'msg'
     *          }
     */
    @RequestMapping(value = "/delete_strategy", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> deleteStrategy(int sid){

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "success");

        return result;
    }

    /**
     *
     * @param params
     * @return result{
     *              'msg'
     *              'datelist':[{
     *                  (string)
     *              }...]
     *              'data1':[{
     *                  (double)
     *              }...]
     *              'data2':[{
     *                  (double)
     *              }...]
     *          }
     */
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
