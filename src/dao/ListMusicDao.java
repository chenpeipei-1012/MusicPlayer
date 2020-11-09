package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Music;

public interface ListMusicDao {

	// 根据歌单ID得到歌曲列表
	public List<Music> getMusicListById(int listId) throws SQLException;
}
