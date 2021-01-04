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
import dao.MusicCommentDao;
import dao.impl.MusicCommentDaoImpl;
import entity.Pagination;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    // 分页展示评论
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 当前页
		int curPage = Integer.parseInt(request.getParameter("curPage"));
		// 音乐Id
		int musicId = Integer.parseInt(request.getParameter("musicId"));

		PrintWriter out = response.getWriter();
		// JSON对象
		JSONObject json = new JSONObject();
		
		List<Object[]> mcList = null;
		// 评论展示
		MusicCommentDao musicCommentDao = new MusicCommentDaoImpl();
		Pagination page = new Pagination();
		// 该歌曲的评论总数
		int commentCount = -1;
		try {
			commentCount = musicCommentDao.getCommentCountByMusicId(musicId);
			page.setTotal(commentCount);
			page.setCurPage(curPage);
			page.setPageSize(10);
			
			mcList = musicCommentDao.getMusicCommentsByMusicId(musicId, page);
			
			json.put("mcList", mcList);
			json.put("page", page);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			out.print(json);
			out.flush();
			
	        out.close();
		}
			
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
