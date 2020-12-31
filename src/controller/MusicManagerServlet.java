package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import dao.MusicDao;
import dao.impl.MusicDaoImpl;
import entity.Music;

/**
 * Servlet implementation class MusicManagerServlet
 */
@WebServlet(name = "/music-manage",
urlPatterns = {"/admin/music-manage"}
)
public class MusicManagerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		System.out.println("curPage:" + curPage);
		
		PrintWriter out = response.getWriter();
		// JSON对象
		JSONObject json = new JSONObject();
		
		int pageSize = 10;
		int offset = (curPage - 1) * pageSize;
		MusicDao musicDao = new MusicDaoImpl();
		
		List<Music> musicList = null;
		try {
			musicList = musicDao.queryMusicByPaging(offset, pageSize);
			json.put("musicList", musicList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			out.print(json);
			
			// 把数据响应给AJAX
			out.flush();
	        out.close();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
