package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import dao.MusicDao;
import dao.impl.MusicDaoImpl;
import entity.MusicDownload;

@WebServlet(name = "/dashboard",
			urlPatterns = {"/admin/dashboard"}
)
public class DashboardServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// JSON对象
		JSONObject json = new JSONObject();
					
		MusicDao musicDao = new MusicDaoImpl();
		
		Map<String,Integer> numMap = null;
		List<MusicDownload> musicDownloadNum = null;
		try {
			numMap = musicDao.queryCurMusicAandUserNum();
			
			int activeSessionsNum = SessionCounter.getActiveSessions();
			System.out.println("activeSessionsNum:" + activeSessionsNum);
			
			numMap.put("activeSessionsNum", activeSessionsNum);
			
			// 近一周歌曲下载量
			musicDownloadNum = musicDao.queryDownloadNum();
			
			json.put("numMap", numMap);
			json.put("musicDownloadNum", musicDownloadNum);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
			out.print(json);
			
			// 把数据响应给AJAX
			out.flush();
	        out.close();
		}
	}

}