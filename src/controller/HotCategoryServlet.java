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
import dao.CategoryDao;
import dao.MusicDao;
import dao.impl.CategoryDaoImpl;
import dao.impl.MusicDaoImpl;
import entity.Category;
import entity.Music;

@WebServlet("/hotCategory")
public class HotCategoryServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String musicTypeStr = request.getParameter("categoryId");
		int musicType = -1;
		System.out.println("musicType: " + musicTypeStr);
		
		PrintWriter out = response.getWriter();
		// JSON对象
		JSONObject json = new JSONObject();
		
		CategoryDao categoryDao = new CategoryDaoImpl();
		MusicDao musicDao = new MusicDaoImpl();
				
		List<Object[]> hotMusics = null;
		List<Category> categoryList = null;
		boolean isSuccess = false;
		
		try {
			//
			if(musicTypeStr == null || "".equals(musicTypeStr)){
				// 查询类别
				categoryList = categoryDao.queryAllCategory();
				json.put("categoryList", categoryList);
				// 第一个类别
				musicType = categoryList.get(0).getCategory_id();
			}else{
				musicType = Integer.parseInt(musicTypeStr);
			}
			
			hotMusics = musicDao.queryTopSaveSongsBymusicType(musicType);
			
			isSuccess = true;
			json.put("hotMusics", hotMusics);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			json.put("isSuccess", isSuccess);
			
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
	}

}
