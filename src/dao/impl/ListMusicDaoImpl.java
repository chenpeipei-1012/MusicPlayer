package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import util.DBUtils;
import dao.ListMusicDao;
import entity.Music;

public class ListMusicDaoImpl implements ListMusicDao{

	@Override
	public List<Music> getMusicListById(int listId) throws SQLException {
		List<Music> list = new ArrayList<Music>();
		Connection conn = DBUtils.getConnection();
		
		String sql = "select * from list_music left join " +
				"(select * from music left join album on music.music_album_id = album.album_id) m " +
				"on list_music.mid = m.music_id where lid = ?;";
				
		
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 给参数赋值
		stmt.setInt(1, listId);
		
		// 执行SQL
		ResultSet result = stmt.executeQuery();

		Music music = null;
		// 循环结果集
		while(result.next()){
			music = new Music();
			music.setMusicId(result.getInt("music_id"));
			music.setMusicName(result.getString("music_name"));
			music.setMusicAuthor(result.getString("music_author"));
			music.setMusicAlbumId(result.getInt("music_album_id"));
			music.setMusicAlbum(result.getString("album_name"));
			music.setMusicPath(result.getString("music_path"));
			music.setMusicCreatedTime(result.getTimestamp("music_created_time"));
			music.setMusicLyricPath(result.getString("music_lyric_path"));
			music.setMusicPic(result.getString("music_pic"));
			
			music.setMusicDuration(result.getString("music_duration"));
			
			// 歌曲类型
            music.setMusicTypeId(result.getInt("music_type_id"));
			
			list.add(music);
		}

		DBUtils.closeConnection(conn);
		return list;
	}
	
	// 测试
	public static void main(String args []){
		ListMusicDaoImpl impl = new ListMusicDaoImpl();
		try {
			//impl.getMusicListById(1);
			impl.addMusicToList(1, 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addMusicToList(int listId, int musicId) throws MySQLIntegrityConstraintViolationException,SQLException{
		Connection conn = DBUtils.getConnection();
		String sql = "insert into list_music(lid,mid) values(?,?)";
		
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 给参数赋值
		stmt.setInt(1, listId);
		stmt.setInt(2, musicId);
		
		// 执行SQL
		int result = stmt.executeUpdate();
		System.out.println("result:" + result);
		// 关闭连接
		DBUtils.closeConnection(conn);
	}

}
