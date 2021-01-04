package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * @author lemon
 * @date 2021/1/1 20:30
 */
public interface RouteDao {
    /**
     * 查询当前页数据
     * @param cid
     * @param start
     * @param pageSize
     * @return
     */
    List<Route> findList(int cid, int start, int pageSize);

    /**
     * 查询当前分类下所有线路
     * @param cid
     * @return
     */
    int count(int cid);
}
