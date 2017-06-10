package trillionaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import trillionaire.model.Strategy;
import trillionaire.service.BackTestService;
import trillionaire.vo.BackTestParams;
import trillionaire.vo.StraIdName;
import trillionaire.vo.StrategySimple;

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

    @Autowired
    BackTestService backTestService;


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

        if(userId < 0){

            Map<String, Object> result = new HashMap<>();
            result.put("msg", "error");

            return result;

        }

        List<StraIdName> list = backTestService.getMyStrategy(userId);

        if(list==null){
            Map<String, Object> result = new HashMap<>();
            result.put("msg", "error");

            return result;
        }

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
    @RequestMapping(value = "/save_strategy", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveStrategy(int sid, String strategyName, String content, int userId){
        userId = 1;
        System.out.println(content);
//            int returnSid = -1;
//
//            if(sid < 0){
//                returnSid = backTestService.addStrategy(userId, strategyName, content);
//            }
//            else {
//                returnSid = backTestService.saveStrategy(sid, strategyName, content);
//            }
//
            Map<String, Object> result = new HashMap<>();

//            if(returnSid > 0){
//                result.put("msg", "success");
//                result.put("sid", returnSid);
//            }
//            else {
//                result.put("msg", "error");
//            }

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

        Strategy strategy = backTestService.openStrategy(sid);

        if(strategy==null){
            result.put("msg", "error");
        }
        else {
            result.put("msg", "success");
            result.put("sid", strategy.getSid());
            result.put("strategyName", strategy.getStrategyName());
            result.put("strategyContent", strategy.getContent());
        }

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

        backTestService.deletStrategy(sid);

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
