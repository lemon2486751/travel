package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author lemon
 * @date 2021/1/1 15:30
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();
    ResultInfo info = new ResultInfo();

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证验证码
        String check = request.getParameter("check");
        //获取session中的验证码
        HttpSession session = request.getSession();
        String checkcode = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (check == null || !check.equalsIgnoreCase(checkcode)) {
            info.setFlag(false);
            info.setErrorMsg("验证码错误！");
            writeValue(info,response);
            return;
        }
        //获取表单数据
        Map<String, String[]> map = request.getParameterMap();
        //将数据存入对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service新增用户
        ResultInfo resultInfo = service.insert(user);
        if (!resultInfo.isFlag()) {
            info.setFlag(false);
            info.setErrorMsg(resultInfo.getErrorMsg());
        } else {
            info.setFlag(true);
        }
        writeValue(info,response);
    }

    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取唯一标识激活码
        String code = request.getParameter("code");
        //调用service激活
        boolean flag = service.active(code);
        String msg = null;
        if (flag) {
            //激活成功
            msg = "激活成功，请<a href='http://localhost/travel/login.html'>登录</a>";
        } else {
            msg = "激活失败，请联系管理员！";
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(msg);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证验证码
        String check = request.getParameter("check");
        //获取session中的验证码
        HttpSession session = request.getSession();
        String checkcode = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (check == null || !check.equalsIgnoreCase(checkcode)) {
            info.setFlag(false);
            info.setErrorMsg("验证码错误！");
            writeValue(info,response);
            return;
        }
        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service验证用户信息
        User u = service.login(user);
        //根据返回结果处理数据
        if (u == null) {
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误！");
        }
        if (u != null && !"Y".equals(u.getStatus())) {
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请前往邮箱激活！");
        }
        if (u != null && "Y".equals(u.getStatus())) {
            request.getSession().setAttribute("user", u);
            info.setFlag(true);
        }
        writeValue(info,response);
    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        writeValue(user,response);
    }

    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/login.html");
    }
}
