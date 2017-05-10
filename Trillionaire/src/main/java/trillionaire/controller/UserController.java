package trillionaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import trillionaire.service.UserService;
import trillionaire.util.LoginState;

/**
 * Created by michaeltan on 2017/5/6.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/login",method= RequestMethod.POST)
    @ResponseBody
    public LoginState login(String username, String password){
        if (username.equals("123")){
            return LoginState.LOGIN_SUCCESS;
        }else {
            return  LoginState.LOGIN_FAIL;
        }
    }

    /*
    @RequestMapping(value="/register",method= RequestMethod.POST)
    @ResponseBody
    public LoginState register(String id, String username, String password){
        return LoginState.LOGIN_SUCCESS;
    }
    */




}
