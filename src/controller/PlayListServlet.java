package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ListMusicDao;
import dao.impl.ListMusicDaoImpl;
import entity.Music;

@WebServlet("/playlist")
public class PlayListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int listId = Integer.parseInt(request.getParameter("id"));

		ListMusicDao listMusic = new ListMusicDaoImpl();
		List<Music> musicList = null;
		
		try {
			musicList = listMusic.getMusicListById(listId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
