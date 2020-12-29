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
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import dao.ListMusicDao;
import dao.UserMusicListDao;
import dao.impl.ListMusicDaoImpl;
import dao.impl.UserMusicListDaoImpl;
import entity.Music;
import entity.User;
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

		// Session 中取用户
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		int userId = user.getUserId();
		UserMusicListDao userMusicListDao = new UserMusicListDaoImpl();
		try {
			listId = userMusicListDao.addMusicList(userId,listName);
			isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
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
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 从Session会话对象中获取当前登录用户
		String id = request.getParameter("id");
		
		int listId = -1;

		// 根据头信息判断是AJAX请求还是URL请求
		String header = request.getHeader("X-Requested-With");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();

		// Session 中取用户
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		int userId = user.getUserId();
		
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
		 
		 UserMusicListDao userMusicListDao = new UserMusicListDaoImpl();
		 try {
			userMusicListDao.deleteMusicList(id == null? -1 : Integer.parseInt(id));
			isSuccess = true;
		 } catch (NumberFormatException e) {
			e.printStackTrace();
		 } catch (SQLException e) {
			e.printStackTrace();
		 } finally{
			 response.setContentType("application/json; charset=utf-8");
			 PrintWriter out = response.getWriter();
			 
			 // JSON对象
			 JSONObject json = new JSONObject();
			 json.put("isSuccess", isSuccess);
			 out.print(json);
			 
			 // 把数据响应给AJAX
			 out.flush();
			 out.close();
		 }

	 }

	 protected void doPut(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException{
		 String listId = request.getParameter("id");
		 String listName = request.getParameter("listName");
		 
		 response.setContentType("application/json; charset=utf-8");
		 PrintWriter out = response.getWriter();
		 
		 boolean isSuccess = false;
		 
		 UserMusicListDao userMusicListDao = new UserMusicListDaoImpl();
		 try {
			userMusicListDao.modifyMusicList(Integer.parseInt(listId), listName);
			isSuccess = true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			// JSON对象
			 JSONObject json = new JSONObject();
			 json.put("isSuccess", isSuccess);
			 out.print(json);
				
			 // 把数据响应给AJAX
			 out.flush();
			 out.close();
		}
	 }
	 
}
