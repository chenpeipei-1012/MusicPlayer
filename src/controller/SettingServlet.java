package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;

@WebServlet("/update")
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickName = request.getParameter("nickname");
		String desc = request.getParameter("desc");
		String gender = request.getParameter("gender");
		String bir = request.getParameter("bir");
		String iddr = request.getParameter("iddr");
		String pic = request.getParameter("pic");
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		out = response.getWriter();
		
		// 获取当前登录用户
		int userId = 1;
		UserDao userDao = new UserDaoImpl();
		try {
			boolean isSuccess = userDao.modifyUserInfo(userId, nickName, gender, desc, pic, bir, iddr);
			JSONObject json = new JSONObject();
			json.put("isSuccess", isSuccess);
			
			out.print(json);
			
			// 把数据响应给AJAX
			out.flush();
	        out.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 展示用户数据：昵称、介绍、性别、生日、地区
		String header = request.getHeader("X-Requested-With");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		out = response.getWriter();
		// 获取当前登录用户
		int userId = 1;
		
		UserDao userDao = new UserDaoImpl();
		User user = null;
		try {
			user = userDao.queryUserInfoById(userId);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		
		json.put("user", user);
		
		if(header == null){
        	response.sendRedirect("/MusicPlayer/user/setting.html");
        }
		
		out.print(json);
		
		// 把数据响应给AJAX
		out.flush();
        out.close();
	}

}
