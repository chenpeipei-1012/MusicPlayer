package dao;

import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import entity.Music;

public interface ListMusicDao {

	// 根据歌单ID得到歌曲列表
	public List<Music> getMusicListById(int listId) throws SQLException;
	
	// 向歌单中添加歌曲
	public void addMusicToList(int listId,int musicId) throws SQLException;
	
}
