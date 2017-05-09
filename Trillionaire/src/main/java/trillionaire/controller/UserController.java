package trillionaire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import trillionaire.service.UserService;
import trillionaire.util.State;

/**
 * Created by michaeltan on 2017/5/6.
 */
@Controller
@RequestMapping("/login")
public class UserController {
    private UserService userService;

    public State login(String id, String password){
        if (id.equals("123")){
            return State.LOGIN_SUCCESS;
        }else {
            return  State.LOGIN_FAIL;
        }
    }


}
