package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lemon
 * @date 2021/1/1 20:30
 */
public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Route> findList(int cid, int start, int pageSize, String rname) {
        String sql = "select * from tab_route where 1=1 ";
        StringBuilder builder = new StringBuilder(sql);
        List list = new ArrayList();
        if (cid != 0) {
            list.add(cid);
            builder.append(" and cid = ? ");
        }
        if (rname != null && rname.length() > 0 && !"null".equals(rname)) {
            list.add("%" + rname + "%");
            builder.append(" and rname like ? ");
        }
        list.add(start);
        list.add(pageSize);
        builder.append(" limit ?,? ");
        sql = builder.toString();
        return template.query(sql, new BeanPropertyRowMapper<>(Route.class), list.toArray());
    }

    @Override
    public int count(int cid, String rname) {
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder builder = new StringBuilder(sql);
        List list = new ArrayList();
        if (cid != 0) {
            list.add(cid);
            builder.append(" and cid = ? ");
        }
        if (rname != null && rname.length() > 0 && !"null".equals(rname)) {
            list.add("%" + rname + "%");
            builder.append(" and rname like ? ");
        }
        sql = builder.toString();
        return template.queryForObject(sql, Integer.class, list.toArray());
    }

    @Override
    public Route findByRid(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(Route.class),rid);
    }
}
