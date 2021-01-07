package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.PageBean;

import java.util.List;

/**
 * @author lemon
 * @date 2021/1/6 17:17
 */
public interface FavoriteDao {

    /**
     * 根据rid查询多条数据
     *
     * @param rid
     * @return
     */
    int findCount(int rid);

    int insert(int rid,int uid);

    /**
     * 根据用户id查询线路是否被收藏
     * @param rid
     * @param uid
     * @return
     */
    Favorite findData(int rid, int uid);

    /**
     * 根据uid分页查询rid集合
     * @param uid
     * @param start
     * @param pageSize
     * @return
     */
    List<Integer> findAll(int uid,int start,int pageSize);

    /**
     * 查询该用户下所有收藏线路条数
     * @param uid
     * @return
     */
    int findTotal(int uid);
}
