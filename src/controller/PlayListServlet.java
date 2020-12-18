package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import dao.ListMusicDao;
import dao.UserMusicListDao;
import dao.impl.ListMusicDaoImpl;
import dao.impl.UserMusicListDaoImpl;
import entity.Music;
import entity.UserMusicList;

/**
 * @WebServlet(name = "myUserServlet", 
	urlPatterns = "/user/test",
 * @author ��ΪMateBook 13
 *
 */
@WebServlet(name = "/playlist",
			urlPatterns = {"/user/playlist", "/playlist"}
			)
public class PlayListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// ���Ӹ赥
		boolean isSuccess = false;
		int listId = -1;
		PrintWriter out = response.getWriter();
		
		String listName = request.getParameter("listname");
		// �ȹ̶��û�
		int userId = 1;
		UserMusicListDao userMusicListDao = new UserMusicListDaoImpl();
		try {
			listId = userMusicListDao.addMusicList(userId,listName);
			isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// listId,String listName,Timestamp listTime,int listUid,int listLove
		Timestamp curTime = new Timestamp(System.currentTimeMillis()); 
		UserMusicList curList = new UserMusicList(listId,listName,curTime,userId,0,0);
		
		// JSON����
		JSONObject json = new JSONObject();
		json.put("isSuccess", isSuccess);
		json.put("curList", curList);
		
		out.print(json);
		
		// ��������Ӧ��AJAX
		out.flush();
        out.close();
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// ��Session�Ự�����л�ȡ��ǰ��¼�û�
		String id = request.getParameter("id");
		
		System.out.println("id : " + id);
		int listId = -1;//Integer.parseInt(request.getParameter("id"));

		// ����ͷ��Ϣ�ж���AJAX������URL����
		String header = request.getHeader("X-Requested-With");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		// �ȹ̶��û���ѯ
		int userId = 1;
		
		// �����б�
		ListMusicDao listMusicDao = new ListMusicDaoImpl();
		List<Music> musicList = null;
		
		// �赥�б�
		UserMusicListDao userMusicListDao = new UserMusicListDaoImpl();
		List<UserMusicList> userMusicList = null;
		
		try {
			// ��ѯ���и赥��Ϣ
			userMusicList = userMusicListDao.queryMusicList(1);
			// �õ����û���ϲ���赥�б�ID
			
			
			// ��ѯ��ϲ�������֣����ݸ赥ID�õ������б�
			if(id == null){
				System.out.println("id == null");
				listId = userMusicListDao.getLoveMusicListId(userId);
			}else{
				System.out.println("id != null");
				listId = Integer.parseInt(id);
			}
			
			musicList = listMusicDao.getMusicListById(listId);
			
			// JSON����
			JSONObject json = new JSONObject();
			json.put("playlist", userMusicList);
			json.put("musiclist", musicList);
			
			// �޷���ת����Ҫajax����ת
			// response.sendRedirect("/MusicPlayer/user/playlist.html");
			// �����ͨ��urlֱ�ӷ��ʵ�
	        if(header == null){
	        	response.sendRedirect("/MusicPlayer/user/playlist.jsp");
	        }
			
			out.print(json);
			
			// ��������Ӧ��AJAX
			out.flush();
	        out.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	 protected void doDelete(HttpServletRequest request,HttpServletResponse response)
			 	throws ServletException, IOException {
		 boolean isSuccess = false;
		 String id = request.getParameter("id");

		 System.out.println("delete id:" + id);
		 
		 UserMusicListDao userMusicListDao = new UserMusicListDaoImpl();
		 try {
			userMusicListDao.deleteMusicList(id == null? -1 : Integer.parseInt(id));
			isSuccess = true;
		 } catch (NumberFormatException e) {
			e.printStackTrace();
		 } catch (SQLException e) {
			e.printStackTrace();
		 }
		 
		 response.setContentType("application/json; charset=utf-8");
		 PrintWriter out = response.getWriter();
		 
		 // JSON����
		 JSONObject json = new JSONObject();
		 json.put("isSuccess", isSuccess);
		 out.print(json);
			
		 // ��������Ӧ��AJAX
		 out.flush();
	 }

}
