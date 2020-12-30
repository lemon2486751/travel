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
}
