package trillionaire.service;

import trillionaire.util.LoginState;

/**
 * Created by michaeltan on 2017/5/9.
 */
public interface UserService {

    /**
     * 登录
     *
     * @param id 用户账号
     * @param password 用户密码
     * @return 当前登录状态
     */
    public LoginState login(String id, String password);

    /**
     * 登出
     *
     * @param id 用户账号
     * @return 当前登录状态
     */
    public LoginState logout(String id);

    /**
     * 重置密码
     *
     * @param id 用户账号
     * @param oldPassword 旧的密码
     * @param newPassword 新的密码
     * @return 返回的结果状态
     */
    public LoginState resetPassword(String id, String oldPassword, String newPassword);



}
