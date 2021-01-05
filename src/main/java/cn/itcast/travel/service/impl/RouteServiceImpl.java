package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;

import java.util.List;

/**
 * @author lemon
 * @date 2021/1/1 20:31
 */
public class RouteServiceImpl implements RouteService {
    private RouteDao dao = new RouteDaoImpl();

    @Override
    public PageBean findAll(int cid, int currentPage, int pageSize, String rname) {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        int start = (currentPage - 1) * pageSize;
        List<Route> routeList = dao.findList(cid, start, pageSize, rname);
        pageBean.setRouteList(routeList);
        int total = dao.count(cid, rname);
        pageBean.setTotal(total);
        pageBean.setTotalPage(total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
        return pageBean;
    }
}
