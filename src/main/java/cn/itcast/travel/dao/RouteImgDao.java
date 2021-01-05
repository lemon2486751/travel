package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

/**
 * @author lemon
 * @date 2021/1/5 21:48
 */
public interface RouteImgDao {
    /**
     * 根据rid查询图片列表
     * @param rid
     * @return
     */
    List<RouteImg> findList(int rid);
}
