package trillionaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import trillionaire.service.UserService;
import trillionaire.util.FollowState;
import trillionaire.util.UserState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

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
    public Map<String, Object> login(HttpServletRequest request, String email, String password) {
        Map<String, Object> map = userService.login(email,password);
        if (map.get("msg") == "success") {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
        }
        return map;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(String email, String password) {
        UserState userState = userService.register(email, password);
        return userState.toString();
    }

    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    @ResponseBody
    public String follow(String email, String code, int id) {
        return FollowState.FOLLOW_SUCCESS.toString();
    }

    @RequestMapping(value = "/ifFollow", method = RequestMethod.POST)
    @ResponseBody
    public String checkFollow(String email, String code, int id) {
        return FollowState.HAS_FOLLOW.toString();

    }

    //验证注册
    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public void verify(HttpServletResponse response, @RequestParam("code") String code) throws Exception {
        boolean ifsuccess = userService.verify(code);
        if (ifsuccess) {
            response.sendRedirect("/login.html");
        } else {
            response.sendRedirect("/index.html");
        }
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public String check(String email) {
        boolean exist = userService.check(email);
        if (exist) {
            return "exist";
        } else {
            return "none";
        }
    }

}
