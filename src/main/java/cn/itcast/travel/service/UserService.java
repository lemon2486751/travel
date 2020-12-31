package cn.itcast.travel.service;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;

/**
 * @author lemon
 * @date 2020/12/30 20:26
 */
public interface UserService {

    /**
     * 新增用户
     * @param user
     * @return
     */
    ResultInfo insert(User user);

    /**
     * 用户激活
     * @param code
     * @return
     */
    boolean active(String code);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);
}
