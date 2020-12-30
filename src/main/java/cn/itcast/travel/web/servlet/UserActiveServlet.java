package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lemon
 * @date 2020/12/30 20:26
 */
@WebServlet("/userActive")
public class UserActiveServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		this.doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取唯一标识激活码
		String code = request.getParameter("code");
		//调用service激活
		UserService service = new UserServiceImpl();
		boolean flag = service.active(code);
		String msg = null;
		if (flag){
			//激活成功
			msg = "激活成功，请<a href='http://localhost/travel/login.html'>登录</a>";
		}else {
			msg = "激活失败，请联系管理员！";
		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(msg);
	}
}



