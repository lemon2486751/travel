package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

/**
 * @author lemon
 * @date 2021/1/1 20:31
 */
public interface RouteService {
    /**
     * 分页查询线路
     *
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean findAll(int cid, int currentPage, int pageSize, String rname);

    /**
     * 根据rid查询线路详情
     * @param rid
     * @return
     */
    Route findDetail(int rid);
}
