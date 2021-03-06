package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import net.sf.json.JSONObject;


import dao.ListMusicDao;
import dao.impl.ListMusicDaoImpl;

/**
 * Servlet implementation class MusicServlet
 */
@WebServlet(name ="/music",
			urlPatterns = {"/user/saveMusic"}
)
public class MusicServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public MusicServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String listId = request.getParameter("listId");
		String musicId = request.getParameter("musicId");
		
		PrintWriter out = response.getWriter();
		boolean isSuccess = false;
		String errMsg = "";
		
		// 添加音乐
		ListMusicDao listMusicDao = new ListMusicDaoImpl();
		try {
			listMusicDao.addMusicToList(Integer.parseInt(listId), Integer.parseInt(musicId));
			isSuccess = true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (MySQLIntegrityConstraintViolationException e) {
			errMsg = "该歌曲已在歌单中";
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			// JSON对象
			JSONObject json = new JSONObject();
			json.put("isSuccess", isSuccess);
			json.put("errMsg", errMsg);
			
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
