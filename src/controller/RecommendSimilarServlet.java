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
import dao.MusicDao;
import dao.impl.MusicDaoImpl;
import entity.Music;

@WebServlet("/recommend")
public class RecommendSimilarServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int musicType = Integer.parseInt(request.getParameter("musicTypeId"));
		System.out.println("musicType: " + musicType);
		
		PrintWriter out = response.getWriter();
		// JSON对象
		JSONObject json = new JSONObject();
				
		List<Music> recommendMusics = null;
		boolean isSuccess = false;
		try {
			MusicDao musicDao = new MusicDaoImpl();
			recommendMusics = musicDao.querySimilarSongs(musicType);
			
			isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			json.put("recommendMusics", recommendMusics);
			json.put("isSuccess", isSuccess);
			
			// 把数据响应给AJAX
	        out.print(json);
			out.flush();
	        out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
