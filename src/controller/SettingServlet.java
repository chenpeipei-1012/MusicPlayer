package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		// Session ��ȡ�û�
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		System.out.println("pic:" + pic);
		System.out.println("user.pic:" + user.getUserPic());
		
		int userId = user.getUserId();

		UserDao userDao = new UserDaoImpl();
		
		boolean isSuccess = false;
		boolean isPicChange = false;
		String picPath = "";
		
		try {
			 userDao.modifyUserInfo(userId, nickName, gender, desc, bir, iddr,pic);
			 isSuccess = true;
			 if(!user.getUserPic().equals(pic)){
				 isPicChange = true;
				 user.setUserPic(pic);
				 picPath = pic;
			 }
			 
			 // �޸�Session�д洢���û���Ϣ
			 
			 session.setAttribute("user", user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JSONObject json = new JSONObject();
		json.put("isSuccess", isSuccess);
		json.put("isPicChange", isPicChange);
		json.put("picPath", picPath);
		
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
		
		// Session ��ȡ�û�
		HttpSession session = request.getSession();
		User curUser = (User) session.getAttribute("user");
		
		int userId = curUser.getUserId();
		
		UserDao userDao = new UserDaoImpl();
		User user = null;
		try {
			user = userDao.queryUserInfoById(userId);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		
		json.put("user", curUser);
		
		if(header == null){
        	response.sendRedirect("/MusicPlayer/user/setting.jsp");
        }
		
		out.print(json);
		
		// ��������Ӧ��AJAX
		out.flush();
        out.close();
	}
}