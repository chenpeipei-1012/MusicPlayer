package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Music;

public interface ListMusicDao {

	// ���ݸ赥ID�õ������б�
	public List<Music> getMusicListById(int listId) throws SQLException;
}
