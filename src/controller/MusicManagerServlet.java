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
import entity.Pagination;

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
		String condition = request.getParameter("condition");
		
		System.out.println("curPage:" + curPage);
		System.out.println("condition:" + condition);
		
		PrintWriter out = response.getWriter();
		// JSON对象
		JSONObject json = new JSONObject();
		
		MusicDao musicDao = new MusicDaoImpl();
		
		List<Music> musicList = null;
		try {
			Pagination page = new Pagination();
			page.setCurPage(curPage);
			page.setPageSize(5);
			
			musicList = musicDao.queryMusicByPaging(page,condition);
			int musicCount = musicDao.getMusicCountByCondition(condition);
			page.setTotal(musicCount);
			
			json.put("musicList", musicList);
			json.put("page", page);
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
