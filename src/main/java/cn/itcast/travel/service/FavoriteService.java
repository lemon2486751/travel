package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;

/**
 * @author lemon
 * @date 2021/1/6 17:58
 */
public interface FavoriteService {
    /**
     * 插入数据
     * @param rid
     * @param uid
     * @return
     */
    int insert(int rid,int uid);

    /**
     * 查询线路是否被收藏
     * @param rid
     * @param uid
     * @return
     */
    boolean isFavorite(int rid,int uid);

    PageBean findAll(int uid,int currentPage,int pageSize);
}
