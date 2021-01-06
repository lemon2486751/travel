package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

/**
 * @author lemon
 * @date 2021/1/6 17:17
 */
public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findCount(int rid) {
        String sql = "select count(*) from tab_favorite where rid = ?";
        return template.queryForObject(sql, Integer.class, rid);
    }

    @Override
    public int insert(int rid, int uid) {
        String sql = "insert into tab_favorite values(?,?,?)";
        Date date = new Date();
        return template.update(sql, rid, date, uid);
    }

    @Override
    public Favorite findData(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql = "select * from tab_favorite where rid=? and uid=?";
            favorite = template.queryForObject(sql,new BeanPropertyRowMapper<>(Favorite.class),rid,uid);
        } catch (DataAccessException e) {
            System.out.println("数据查询为null，线路未被收藏");
        }
        return favorite;
    }
}
