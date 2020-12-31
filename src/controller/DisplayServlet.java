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

import dao.AlbumDao;
import dao.MusicCommentDao;
import dao.MusicDao;
import dao.UserDao;
import dao.impl.AlbumDaoImpl;
import dao.impl.MusicCommentDaoImpl;
import dao.impl.MusicDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Album;
import entity.Music;
import entity.MusicComment;
import net.sf.json.JSONObject;

@WebServlet(name = "/display",
			urlPatterns = {"/user/display"}
)
public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String musicId = request.getParameter("musicId");
		//
		Integer musicid = Integer.parseInt(musicId);
		MusicDao musicDao = new MusicDaoImpl();
		AlbumDao albumDao = new AlbumDaoImpl();
		MusicCommentDao musicCommentDao = new MusicCommentDaoImpl();
		UserDao userDao = new UserDaoImpl();

		//获取歌曲名 歌词 歌曲封面 作者 专辑
		Music music=null;

		try {
			music = musicDao.getMusicById(musicid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("{ Music:");
		System.out.println(music);
		System.out.println("}");
//		String music_name= music.getMusicName();
//		String musicLyricPath = music.getMusicLyricPath();
//		String musicPic = music.getMusicPic();
//		String musicAuthor = music.getMusicAuthor();
		String albumName = albumDao.getAlbumById(music.getMusicAlbumId()).getAlbumName();



		//获取评论
		List<MusicComment> mclist = new ArrayList<MusicComment>();

		mclist=musicCommentDao.getMusicCommentsByMusicId(musicid);
		//获得评论信息
		for(MusicComment musicComment : mclist){
			try {
				System.out.println(musicComment);
				String music_user_pic =userDao.queryUserInfoById(musicComment.getUser_id()).getUserPic();
				String user_name =userDao.queryUserInfoById(musicComment.getUser_id()).getUserName();
				System.out.println("user_name:"+user_name);
				System.out.println("music_user_pic:"+music_user_pic+"}");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		//获取推荐的11首歌
		List<Music> rmlist=new ArrayList<Music>();

			for(Music rmmusic:rmlist){

			System.out.println("{ RCMusic:"+"[")
			System.out.println(rmmusic);
			System.out.println("]"+"}")
		}

//		System.out.println("musicId:" + musicId);
		PrintWriter out = response.getWriter();
		// JSON对象
		JSONObject json = new JSONObject();
		
		out.print(json);
		
		// 把数据响应给AJAX
		out.flush();
        out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
