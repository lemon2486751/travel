package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * @author lemon
 * @date 2020/12/30 20:24
 */
public interface UserDao {
    /**
     * 根据用户名查询用户
     * @param user
     * @return
     */
    User findByUsername(User user);

    /**
     * 新增用户
     * @param user
     * @return
     */
    void insert(User user);

    /**
     * 根据激活码查询用户是否存在
     * @param code
     * @return
     */
    User findByCode(String code);

    /**
     * 更新激活状态
     * @param user
     */
    void updateStatus(User user);
}
