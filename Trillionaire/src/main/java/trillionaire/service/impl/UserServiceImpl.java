package trillionaire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trillionaire.dao.DayRecordDao;
import trillionaire.dao.UserDao;
import trillionaire.model.DayRecord;
import trillionaire.model.Stock;
import trillionaire.model.User;
import trillionaire.service.UserService;
import trillionaire.util.CodeUtil;
import trillionaire.util.MailUtil;
import trillionaire.util.RandomCodeUtil;
import trillionaire.util.UserState;
import trillionaire.vo.FollowListVO;

import java.util.*;

/**
 * Created by michaeltan on 2017/5/9.
 */
@Service
public class UserServiceImpl implements UserService {

    private static RegisterUserMap registerUserMap = new RegisterUserMap();//存储未验证用户

    @Autowired
    UserDao userDao;
    @Autowired
    DayRecordDao dayRecordDao;

    @Override
    public Map<String, Object> login(String email, String password) {
        User user = userDao.getUserByEmail(email);
        Map<String, Object> map = new HashMap<>();
        if (password.equals(user.getPassword())) {
            map.put("msg", UserState.SUCCESS.toString());
            map.put("id", user.getId());
            map.put("email", user.getEmail());
        } else {
            map.put("msg", UserState.FAIL.toString());
        }
        return map;
    }

    @Override
    public UserState register(String email, String password) {
        User user = userDao.getUserByEmail(email);
        if (user == null) {
            User newUser = new User(email, password);
            String randomCode = RandomCodeUtil.generateUniqueCode();
            registerUserMap.put(randomCode, newUser);
            registerUserMap.setTime(randomCode);
            new Thread(new MailUtil(email, randomCode)).start();
            return UserState.SUCCESS;
        } else {
            return UserState.FAIL;
        }
    }

    @Override
    public UserState resetPassword(String email, String newPassword) {
        return null;
    }

    @Override
    public boolean check(String email) {
        User user = userDao.getUserByEmail(email);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean cancelFollow(int id, String code) {
        Set<Stock> list = userDao.getUserStocks(id);
        boolean iffollow = false;
        Iterator<Stock> it = list.iterator();
        while (it.hasNext()) {
            if (code.equals(it.next().getCode())) {
                iffollow = true;
            }
        }
        if (iffollow) {
            int stock = Integer.parseInt(code);
            userDao.deleteConcernedStock(id, stock);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<FollowListVO> getFollowList(int id) {
        Set<Stock> list = userDao.getUserStocks(id);
        List<FollowListVO> followList = new ArrayList<>();
        Iterator<Stock> iterator = list.iterator();
        while (iterator.hasNext()) {
            List<DayRecord> recordList = dayRecordDao.getDayRecords(iterator.next().getCode(), 1);
            if (recordList.size() != 0) {
                DayRecord dayRecord = recordList.get(0);
                followList.add(new FollowListVO(dayRecord.getStock().getName(), CodeUtil.TransferCode(dayRecord.getStock().getCode()), dayRecord));
            }
        }
        return followList;
    }

    @Override
    public boolean verify(String randomCode) {
        User user = registerUserMap.get(randomCode);
        if (user != null) {
            userDao.saveOrUpdateUser(user);
            registerUserMap.remove(randomCode);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean follow(int id, String code) {
        Set<Stock> list = userDao.getUserStocks(id);
        boolean follow = false;
        Iterator<Stock> it = list.iterator();
        while (it.hasNext()) {
            if (code.equals(it.next().getCode())) {
                follow = true;
            }
        }
        if (!follow) {
            int stock = Integer.parseInt(code);
            userDao.addConcernedStock(id, stock);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkfollow(int id, String code) {
        Set<Stock> list = userDao.getUserStocks(id);
        boolean iffollow = false;
        Iterator<Stock> it = list.iterator();
        while (it.hasNext()) {
            if (code.equals(it.next().getCode())) {
                iffollow = true;
            }
        }
        return iffollow;
    }
}
