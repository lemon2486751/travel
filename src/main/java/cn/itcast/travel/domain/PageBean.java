package cn.itcast.travel.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author lemon
 * @date 2021/1/1 20:37
 */
public class PageBean implements Serializable {
    private int totalPage;
    private int total;
    private int currentPage;
    private int pageSize;
    private List<Route> routeList;

    public PageBean() {
    }

    public PageBean(int totalPage, int total, int currentPage, int pageSize, List<Route> routeList) {
        this.totalPage = totalPage;
        this.total = total;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.routeList = routeList;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }
}
