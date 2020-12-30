package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author lemon
 * @date 2020/12/30 20:25
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(User user) {
        User u = null;
        try {
            //sql语句
            String sql = "select * from tab_user where username = ?";
            //执行sql
            u = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user.getUsername());
        } catch (Exception e) {

        }
        return u;
    }

    @Override
    public void insert(User user) {
        String sql = "insert into tab_user(username,password,email,name,telephone,sex,birthday)" +
                "values(?,?,?,?,?,?,?)";
        template.update(sql, user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getName(),
                user.getTelephone(),
                user.getSex(),
                user.getBirthday());
    }
}
