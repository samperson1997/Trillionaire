package trillionaire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import trillionaire.util.LoginState;
import trillionaire.util.MailUtil;
import trillionaire.util.RandomCodeUtil;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by michaeltan on 2017/5/6.
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest request, String username, String password) {
        if (username.equals("123")) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            return LoginState.LOGIN_SUCCESS.toString();
        } else {
            return LoginState.LOGIN_FAIL.toString();
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(String email, String password) {
        if(!email.matches("^\\w+@(\\w+\\.)+\\w+$")){  //验证是否符合邮箱格式
            return "error";
        }else {
            String code = RandomCodeUtil.generateUniqueCode();
            new Thread(new MailUtil(email, code)).start();
        }
        return LoginState.LOGIN_SUCCESS.toString();
    }

    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    public void setFollow(String email, String code) {

    }

    @RequestMapping(value = "/attention", method = RequestMethod.GET)
    public void checkFollow(String email, String code) {


    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public void verify(HttpServletResponse response, @RequestParam("code")String code) throws Exception{
        System.out.println(code);
        if (code.equals("123")) {
            response.sendRedirect("/login.html");
        } else {
            response.sendRedirect("/index.html");
        }
    }

}
