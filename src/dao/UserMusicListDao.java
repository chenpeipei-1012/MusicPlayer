package dao;

import java.sql.SQLException;
import java.util.List;

import entity.UserMusicList;

public interface UserMusicListDao {

	// ��Ӹ赥
	public boolean addMusicList(int userId,String listName) throws SQLException;
	
	// ɾ���赥
	public boolean deleteMusicList(int listId) throws SQLException;
	
	// �޸ĸ赥
	public boolean modifyMusicList(int listId,String listName) throws SQLException;
	
	// ��ѯĳ���û��ĸ赥
	public List<UserMusicList> queryMusicList(int userId) throws SQLException;
}