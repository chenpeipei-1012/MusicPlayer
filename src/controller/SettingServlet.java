package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
		
		// ��ȡ��ǰ��¼�û�
		int userId = 1;
		UserDao userDao = new UserDaoImpl();
		
		boolean isSuccess = false;
		try {
			 userDao.modifyUserInfo(userId, nickName, gender, desc, bir, iddr,pic);
			 isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JSONObject json = new JSONObject();
		json.put("isSuccess", isSuccess);
		
		out.print(json);
		
		// ��������Ӧ��AJAX
		out.flush();
        out.close();
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// չʾ�û����ݣ��ǳơ����ܡ��Ա����ա�����
		String header = request.getHeader("X-Requested-With");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		out = response.getWriter();
		// ��ȡ��ǰ��¼�û�
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
		
		// ��������Ӧ��AJAX
		out.flush();
        out.close();
	}
}