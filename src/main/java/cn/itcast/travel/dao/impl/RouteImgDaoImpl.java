package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author lemon
 * @date 2021/1/5 21:49
 */
public class RouteImgDaoImpl implements RouteImgDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<RouteImg> findList(int rid) {
        String sql = "select * from tab_route_img where rid = ?";
        return template.query(sql,new BeanPropertyRowMapper<>(RouteImg.class),rid);
    }
}
