package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lemon
 * @date 2021/1/7 12:37
 */
@WebServlet("/favorite/*")
public class FavoriteServlet extends BaseServlet {
    private FavoriteService service = new FavoriteServiceImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return;
        }
        String currentPageStr = request.getParameter("currentPage");
        int currentPage = 1;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        }
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = 12;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        PageBean bean = service.findAll(user.getUid(), currentPage, pageSize);
        writeValue(bean, response);
    }
}
