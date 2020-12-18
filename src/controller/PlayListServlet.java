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
 * @author 华为MateBook 13
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
		// 增加歌单
		boolean isSuccess = false;
		int listId = -1;
		PrintWriter out = response.getWriter();
		
		String listName = request.getParameter("listname");
		// 先固定用户
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
		
		// JSON对象
		JSONObject json = new JSONObject();
		json.put("isSuccess", isSuccess);
		json.put("curList", curList);
		
		out.print(json);
		
		// 把数据响应给AJAX
		out.flush();
        out.close();
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 从Session会话对象中获取当前登录用户
		String id = request.getParameter("id");
		
		System.out.println("id : " + id);
		int listId = -1;//Integer.parseInt(request.getParameter("id"));

		// 根据头信息判断是AJAX请求还是URL请求
		String header = request.getHeader("X-Requested-With");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		// 先固定用户查询
		int userId = 1;
		
		// 音乐列表
		ListMusicDao listMusicDao = new ListMusicDaoImpl();
		List<Music> musicList = null;
		
		// 歌单列表
		UserMusicListDao userMusicListDao = new UserMusicListDaoImpl();
		List<UserMusicList> userMusicList = null;
		
		try {
			// 查询所有歌单信息
			userMusicList = userMusicListDao.queryMusicList(1);
			// 得到该用户的喜欢歌单列表ID
			
			
			// 查询我喜欢的音乐：根据歌单ID得到歌曲列表
			if(id == null){
				System.out.println("id == null");
				listId = userMusicListDao.getLoveMusicListId(userId);
			}else{
				System.out.println("id != null");
				listId = Integer.parseInt(id);
			}
			
			musicList = listMusicDao.getMusicListById(listId);
			
			// JSON对象
			JSONObject json = new JSONObject();
			json.put("playlist", userMusicList);
			json.put("musiclist", musicList);
			
			// 无法跳转，需要ajax做跳转
			// response.sendRedirect("/MusicPlayer/user/playlist.html");
			// 如果是通过url直接访问的
	        if(header == null){
	        	response.sendRedirect("/MusicPlayer/user/playlist.jsp");
	        }
			
			out.print(json);
			
			// 把数据响应给AJAX
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
		 
		 // JSON对象
		 JSONObject json = new JSONObject();
		 json.put("isSuccess", isSuccess);
		 out.print(json);
			
		 // 把数据响应给AJAX
		 out.flush();
	 }

}
