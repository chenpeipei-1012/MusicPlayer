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
		// ��Session�Ự�����л�ȡ��ǰ��¼�û�
		
		
		// ����ͷ��Ϣ�ж���AJAX������URL����
		String header = request.getHeader("X-Requested-With");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		// �ȹ̶��û���ѯ
		int userId = 1;
		
		
//		int listId = Integer.parseInt(request.getParameter("id"));

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
			musicList = listMusicDao.getMusicListById(1);
			
			// JSON����
			JSONObject json = new JSONObject();
			json.put("playlist", userMusicList);
			json.put("musiclist", musicList);
			
			// �޷���ת����Ҫajax����ת
			// response.sendRedirect("/MusicPlayer/user/playlist.html");
			// �����ͨ��urlֱ�ӷ��ʵ�
	        if(header == null){
	        	response.sendRedirect("/MusicPlayer/user/playlist.html");
	        }
			
			out.print(json);
			
			// ��������Ӧ��AJAX
			out.flush();
	        out.close();
	        
	        
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
