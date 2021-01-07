package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.FavoriteService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lemon
 * @date 2021/1/6 17:58
 */
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao dao = new FavoriteDaoImpl();
    private RouteDao routeDao = new RouteDaoImpl();

    @Override
    public int insert(int rid, int uid) {
        return dao.insert(rid,uid);
    }

    @Override
    public boolean isFavorite(int rid, int uid) {
        return dao.findData(rid,uid)!=null;
    }

    @Override
    public PageBean findAll(int uid, int currentPage, int pageSize) {
        PageBean bean = new PageBean();
        bean.setPageSize(pageSize);
        bean.setCurrentPage(currentPage);
        int total = dao.findTotal(uid);
        bean.setTotal(total);
        int totalPage = total%pageSize==0?total/pageSize:total/pageSize+1;
        bean.setTotalPage(totalPage);
        int start = (currentPage-1)*pageSize;
        List<Integer> rids = dao.findAll(uid, start, pageSize);
        List<Route> list = new ArrayList<>();
        for (int rid : rids) {
            Route route = routeDao.findByRid(rid);
            list.add(route);
        }
        bean.setRouteList(list);
        return bean;
    }
}
