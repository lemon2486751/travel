package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.service.FavoriteService;

/**
 * @author lemon
 * @date 2021/1/6 17:58
 */
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao dao = new FavoriteDaoImpl();

    @Override
    public int insert(int rid, int uid) {
        return dao.insert(rid,uid);
    }

    @Override
    public boolean isFavorite(int rid, int uid) {
        return dao.findData(rid,uid)!=null;
    }
}
