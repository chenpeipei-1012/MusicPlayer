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

import dao.AlbumDao;
import dao.MusicCommentDao;
import dao.MusicDao;
import dao.UserDao;
import dao.impl.AlbumDaoImpl;
import dao.impl.MusicCommentDaoImpl;
import dao.impl.MusicDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Music;
import entity.User;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

@WebServlet(name = "/display",
			urlPatterns = {"/user/display"}
)
public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String header = request.getHeader("X-Requested-With");
		
		// JSON����
		JSONObject json = new JSONObject();
		
		// ��ȡ����
		String musicId = request.getParameter("musicId");

		MusicDao musicDao = new MusicDaoImpl();
		AlbumDao albumDao = new AlbumDaoImpl();
		
		UserDao userDao = new UserDaoImpl();

		//��ȡ������ ��� �������� ���� ר��
		Music music = null;

		try {
			// ͨ������ID��ȡMusic
			music = musicDao.getMusicById(Integer.parseInt(musicId));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			// �����ͨ��ˢ��urlֱ�ӷ��ʵ�
	        if(header == null){
	        	// ��music����ŵ�Request������
	        	JSONObject musicJson = JSONObject.fromObject(music);
	        	
	        	//json.put("music", music);
				// out.print(json);
	        	// JSON.parse(music);
	        	request.setAttribute("music", musicJson);
	        	// System.out.println("music: " + music);
	        	request.getRequestDispatcher("./display.jsp").forward(request, response);
	        }
	        
			
			
			// ��������Ӧ��AJAX
			out.flush();
	        out.close();
		}

		
	}

	// Ϊ�����������
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String musicId = request.getParameter("musicId");
		String comment = request.getParameter("comment");
		
		System.out.println("musicId: " + musicId + " comment: " + comment);
		// Session ��ȡ�û�
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int userId = user.getUserId();
		
		boolean isSuccess = false;
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		// JSON����
		JSONObject json = new JSONObject();
		
		MusicCommentDao musicCommentDao = new MusicCommentDaoImpl();
		try {
			musicCommentDao.addMusicComment(Integer.parseInt(musicId), userId, comment);
			isSuccess = true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			// ��������Ӧ��AJAX
			json.put("isSuccess", isSuccess);
			
			out.print(json);
			out.flush();
	        out.close();
		}
	}

}
