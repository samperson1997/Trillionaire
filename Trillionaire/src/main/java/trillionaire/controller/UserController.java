package trillionaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import trillionaire.service.UserService;
import trillionaire.util.LoginState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by michaeltan on 2017/5/6.
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping(value = "/login",method= RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest request, String username, String password){
        if (username.equals("123")){
            HttpSession session = request.getSession();
            session.setAttribute("userId", username);
            return LoginState.LOGIN_SUCCESS.toString();
        }else {
            return  LoginState.LOGIN_FAIL.toString();
        }
    }

    @RequestMapping(value="/register",method= RequestMethod.POST)
    @ResponseBody
    public LoginState register(String id, String username, String password){
        return LoginState.LOGIN_SUCCESS;
    }




}
