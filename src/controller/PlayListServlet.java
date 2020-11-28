package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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

@WebServlet("/playlist")
public class PlayListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 从Session会话对象中获取当前登录用户
		
		
		// 根据头信息判断是AJAX请求还是URL请求
		String header = request.getHeader("X-Requested-With");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		// 先固定用户查询
		int userId = 1;
		
		
//		int listId = Integer.parseInt(request.getParameter("id"));

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
			musicList = listMusicDao.getMusicListById(1);
			
			// JSON对象
			JSONObject json = new JSONObject();
			json.put("playlist", userMusicList);
			json.put("musiclist", musicList);
			
			// 无法跳转，需要ajax做跳转
			// response.sendRedirect("/MusicPlayer/user/playlist.html");
			// 如果是通过url直接访问的
	        if(header == null){
	        	response.sendRedirect("/MusicPlayer/user/playlist.html");
	        }
			
			out.print(json);
			
			// 把数据响应给AJAX
			out.flush();
	        out.close();
	        
	        
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
