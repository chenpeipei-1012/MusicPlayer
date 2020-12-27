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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MusicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String listId = request.getParameter("listId");
		String musicId = request.getParameter("musicId");
		
		PrintWriter out = response.getWriter();
		boolean isSuccess = false;
		String errMsg = "";
		
		// �������
		ListMusicDao listMusicDao = new ListMusicDaoImpl();
		try {
			listMusicDao.addMusicToList(Integer.parseInt(listId), Integer.parseInt(musicId));
			isSuccess = true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (MySQLIntegrityConstraintViolationException e) {
			errMsg = "�ø������ڸ赥��";
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			// JSON����
			JSONObject json = new JSONObject();
			json.put("isSuccess", isSuccess);
			json.put("errMsg", errMsg);
			
			out.print(json);
			
			// ��������Ӧ��AJAX
			out.flush();
	        out.close();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
