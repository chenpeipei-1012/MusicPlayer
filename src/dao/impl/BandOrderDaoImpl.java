package dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import util.DBUtils;
import dao.BandOrderDao;
import entity.Music;

public class BandOrderDaoImpl  implements BandOrderDao {

	@Override
	public List<Music> orderByUpdata() throws SQLException{
		List<Music> list = new ArrayList<Music>();
		Connection conn = DBUtils.getConnection();
		
		String sql="select * from " +
						"(select  * from  music order by music_created_time desc limit 0,10) t1 " +
						"left join " +
						"album on t1.music_album_id = album.album_id";
		
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		
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
			
			list.add(music);
		}

		DBUtils.closeConnection(conn);
		
		return list;
	}
	
	
}
