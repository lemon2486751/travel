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
 * @date 2020/12/30 20:26
 */
@WebServlet("/userRegister")
public class UserRegisterServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		this.doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResultInfo info = new ResultInfo();
		//验证验证码
		String check = request.getParameter("check");
		//获取session中的验证码
		HttpSession session = request.getSession();
		String checkcode = (String) session.getAttribute("CHECKCODE_SERVER");
		session.removeAttribute("CHECKCODE_SERVER");
		if (check==null||!check.equalsIgnoreCase(checkcode)){
			info.setFlag(false);
			info.setErrorMsg("验证码错误！");
			data2Json(response,info);
			return;
		}
		//获取表单数据
		Map<String, String[]> map = request.getParameterMap();
		//将数据存入对象
		User user = new User();
		try {
			BeanUtils.populate(user,map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		//调用service新增用户
		UserService service = new UserServiceImpl();
		ResultInfo resultInfo = service.insert(user);
		if (!resultInfo.isFlag()){
			info.setFlag(false);
			info.setErrorMsg(resultInfo.getErrorMsg());
		}else {
			info.setFlag(true);
		}
		data2Json(response,info);
	}

	//将info对象序列化为json并返回给客户端
	private void data2Json(HttpServletResponse response,ResultInfo info) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(info);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
	}
}



