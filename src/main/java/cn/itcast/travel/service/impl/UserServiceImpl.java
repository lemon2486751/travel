package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;

/**
 * @author lemon
 * @date 2020/12/30 20:26
 */
public class UserServiceImpl implements UserService {

    @Override
    public ResultInfo insert(User user) {
        ResultInfo info = new ResultInfo();
        UserDao dao = new UserDaoImpl();
        //判断用户名是否重复
        User u = dao.findByUsername(user);
        if (u!=null){
            info.setFlag(false);
            info.setErrorMsg("用户名已存在！");
            return info;
        }
        //新增用户
        dao.insert(user);
        info.setFlag(true);
        return info;
    }
}
