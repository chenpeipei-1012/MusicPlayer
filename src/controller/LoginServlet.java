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
		// ��ȡͬ����������
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String whichValue = request.getParameter("which");
		
		// �������л�ȡSession
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
					
		UserService userService = new UserServiceImpl();
		User user = userService.verifyUser(userName, password);
		int userType = 0;
		
		if(user != null){
			// �û���Ϣ�浽Session�Ự������
			session.setAttribute("user", user);
			// ���ù���ʱ��: 1h
			// session.setMaxInactiveInterval(60*60);
			userType = user.getUserType();
		}
		// ���պͽ������Ͳ�һ�£������error
		
		switch(userType){
			case 0 :
				// ת������¼ҳ��
				request.getRequestDispatcher("").forward(request, response);
				break;
			case 1 :
				if(whichValue != null){
					response.setContentType("text/html; charset=utf-8");
					// ������Ӧ����
					out.print("<script>self.opener.location.reload();window.close();</script>");
					out.flush();
			        out.close();
				}else{
					response.sendRedirect("/MusicPlayer/index.jsp");
				}
				
				break;
			case 2 :
				// �ض��򵽹���Ա�û���ҳ
				response.sendRedirect("/MusicPlayer/admin/dashboard.html");
				break;
		}
	}
}
