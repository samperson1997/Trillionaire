package trillionaire.service.impl;

import trillionaire.model.User;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by michaeltan on 2017/6/4.
 */
public class RegisterUserMap extends HashMap<String, User> {

    public void setTime(final String randomCode){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                User user = get(randomCode);
                if (user!=null){
                    remove(randomCode);
                }
            }
        };
        timer.schedule(timerTask,3600000);
    }
}
