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
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import dao.BandOrderDao;
import dao.MusicDao;
import dao.impl.BandOrderDaoImpl;
import dao.impl.MusicDaoImpl;
import entity.Music;
/**
 * Servlet implementation class BandOrderServlet
 */
@WebServlet("/bandOrder")
public class BandOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// JSON对象
		JSONObject json = new JSONObject();
				
		List<Music> newMusicList = new ArrayList<>();
		List<Music> saveMusicList = new ArrayList<>();
		List<Music> downloadMusicList = new ArrayList<>();
		
		BandOrderDao bandOrderDao = new BandOrderDaoImpl();
		MusicDao musicDao = new MusicDaoImpl();
		
		try {
			
			newMusicList = bandOrderDao.orderByUpdata();
			saveMusicList = musicDao.queryTopSaveSongs();
			downloadMusicList = musicDao.queryTopDownloadSongs();
			
			json.put("newMusicList", newMusicList);
			json.put("saveMusicList", saveMusicList);
			json.put("downloadMusicList", downloadMusicList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			// 把数据响应给AJAX
	        out.print(json);
			out.flush();
	        out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
