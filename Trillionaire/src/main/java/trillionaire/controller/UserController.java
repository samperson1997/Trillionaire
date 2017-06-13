package trillionaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import trillionaire.model.Stock;
import trillionaire.service.UserService;
import trillionaire.util.FollowState;
import trillionaire.util.UserState;
import trillionaire.vo.FollowListVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
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
        Map<String, Object> map = userService.login(email, password);
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

    @RequestMapping(value = "/follow", method = RequestMethod.GET)
    @ResponseBody
    public boolean follow(int id, String code) {
        System.out.print("!!!!!!!!!!!!!!!!");
        return userService.follow(id, code);
    }

    @RequestMapping(value = "/cancelFollow", method = RequestMethod.GET)
    @ResponseBody
    public boolean cancelFollow(int id, String code) {
        return userService.cancelFollow(id, code);
    }

    @RequestMapping(value = "/ifFollow", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkFollow(int id, String code) {
        return userService.checkfollow(id, code);

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

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    @ResponseBody
    public String check(String email) {
        boolean exist = userService.check(email);
        if (exist) {
            return "exist";
        } else {
            return "none";
        }
    }

    @RequestMapping(value = "/followlist", method = RequestMethod.GET)
    @ResponseBody
    public List<FollowListVO> getFollowList(int id) {
        return userService.getFollowList(id);
    }

}
