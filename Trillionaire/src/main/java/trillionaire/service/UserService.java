package trillionaire.service;

import trillionaire.model.RealTimeStock;
import trillionaire.model.Stock;
import trillionaire.util.FollowState;
import trillionaire.util.UserState;
import trillionaire.vo.FollowListVO;

import java.util.List;
import java.util.Map;

/**
 * Created by michaeltan on 2017/5/9.
 */
public interface UserService {

    /**
     * 登录
     *
     * @param email 用户账号
     * @param password 用户密码
     * @return 当前登录状态集
     */
    public Map<String, Object> login(String email, String password);

    /**
     * 注册
     *
     * @param email 用户账号
     * @param password 用户密码
     * @return 注册状态
     */
    public UserState register(String email, String password);

    /**
     * 重置密码
     *
     * @param email 用户账号
     * @param newPassword 新的密码
     * @return 重置是否成功
     */
    public UserState resetPassword(String email, String newPassword);

    /**
     * 用户邮箱验证
     *
     * @param randomCode 验证码
     * @return 验证是否成功
     */
    public boolean verify(String randomCode);

    /**
     * 关注股票
     *
     * @param id 用户ID
     * @param code 股票代码
     * @return 关注是否成功
     */
    public boolean follow(int id, String code);

    /**
     * 用户是否已关注股票
     *
     * @param id 用户ID
     * @param code 股票代码
     * @return 是否已关注
     */
    public boolean checkfollow(int id, String code);

    /**
     * 用户是否已存在
     *
     * @param email 用户邮箱
     * @return 是否已存在
     */
    public boolean check(String email);

    /**
     * 取消关注股票
     *
     * @param id 用户ID
     * @param code 股票代码
     * @return 取消关注
     */
    public boolean cancelFollow(int id, String code);

    /**
     * 用户关注股票列表
     *
     * @param id 用户邮箱
     * @return 股票列表
     */
    public List<RealTimeStock> getFollowList(int id);

}
