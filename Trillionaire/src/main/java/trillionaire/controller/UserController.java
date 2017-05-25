package trillionaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import trillionaire.service.UserService;
import trillionaire.util.FollowState;
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
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest request, String username, String password) {
        if (username.equals("123@qq.com")) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            return LoginState.LOGIN_SUCCESS.toString();
        } else {
            return LoginState.LOGIN_FAIL.toString();
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request, String email){
        LoginState state = userService.logout(email);
        HttpSession session = request.getSession();
        session.invalidate();
        return state.toString();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(String email, String password) {
        boolean ifexist = userService.find(email);
        if (ifexist){
            return "exists";
        } else {
            String code = RandomCodeUtil.generateUniqueCode();
            new Thread(new MailUtil(email, code)).start();
            return "success";
        }
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public String check(String email) {
        boolean ifexist = userService.find(email);
        if (ifexist){
            return "exists";
        } else {
            return "success";
        }
    }


    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    @ResponseBody
    public String follow(String email, String code) {
        return FollowState.FOLLOW_SUCCESS.toString();
    }

    @RequestMapping(value = "/ifFollow", method = RequestMethod.POST)
    @ResponseBody
    public String checkFollow(String email, String code) {
        return FollowState.HAS_FOLLOW.toString();

    }

    //验证注册
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
