package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

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
        //设置用户初始状态
        user.setStatus("N");
        //设置激活码
        user.setCode(UuidUtil.getUuid());
        //新增用户
        dao.insert(user);
        //向用户发送激活邮件
        String msg = "<a href='http://localhost/travel/user/active?code="+user.getCode()+"'>点击激活【旅游网】</a>";
        MailUtils.sendMail(user.getEmail(),msg,"激活邮件");
        info.setFlag(true);
        return info;
    }

    @Override
    public boolean active(String code) {
        //根据激活码查询用户是否存在
        UserDao dao = new UserDaoImpl();
        User user = dao.findByCode(code);
        if (user!=null){
            dao.updateStatus(user);
            return true;
        }
        return false;
    }

    @Override
    public User login(User user) {
        UserDao dao = new UserDaoImpl();
        return dao.findByUsernameAndPassword(user);
    }
}
