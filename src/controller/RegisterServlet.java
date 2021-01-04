package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import service.UserService;
import service.impl.UserServiceImpl;
import util.DBUtils;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");
		String whichValue = request.getParameter("which");
		
		// 从请求中获取Session
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
				
		UserService service = new UserServiceImpl();
		UserDao userDao = new UserDaoImpl();
		User user = new User();

		if(password.equals(password1)){
			
			try {
				user = userDao.queryUserInfoByName(userName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//用户名不存在，可以注册
			if(user == null){
				
				//插入新用户信息
				service.saveUser(userName, password);
				//转跳到主页
				response.sendRedirect("/MusicPlayer/index.jsp");
			} else{
				System.out.println("该用户名已存在，注册失败");
				response.sendRedirect("/MusicPlayer/register.html");
			}
		}
	}
}


