package dao;

import java.sql.SQLException;
import java.util.List;

import entity.UserMusicList;

public interface UserMusicListDao {

	// 添加歌单
	public boolean addMusicList(int userId,String listName) throws SQLException;
	
	// 删除歌单
	public boolean deleteMusicList(int listId) throws SQLException;
	
	// 修改歌单
	public boolean modifyMusicList(int listId,String listName) throws SQLException;
	
	// 查询某个用户的歌单
	public List<UserMusicList> queryMusicList(int userId) throws SQLException;
}