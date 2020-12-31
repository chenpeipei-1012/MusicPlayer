package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;
import service.UserService;
import service.impl.UserServiceImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 获取同户名和密码
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String whichValue = request.getParameter("which");
		
		// 从请求中获取Session
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
					
		UserService userService = new UserServiceImpl();
		User user = userService.verifyUser(userName, password);
		int userType = 0;
		
		if(user != null){
			// 用户信息存到Session会话对象中
			session.setAttribute("user", user);
			// 设置过期时间: 1h
			// session.setMaxInactiveInterval(60*60);
			userType = user.getUserType();
		}
		// 接收和解析类型不一致，会造成error
		
		switch(userType){
			case 0 :
				// 转发至登录页面
				request.getRequestDispatcher("").forward(request, response);
				break;
			case 1 :
				if(whichValue != null){
					response.setContentType("text/html; charset=utf-8");
					// 设置响应类型
					out.print("<script>self.opener.location.reload();window.close();</script>");
					out.flush();
			        out.close();
				}else{
					response.sendRedirect("/MusicPlayer/index.jsp");
				}
				
				break;
			case 2 :
				// 重定向到管理员用户主页
				response.sendRedirect("/MusicPlayer/admin/dashboard.html");
				break;
		}
	}
}
