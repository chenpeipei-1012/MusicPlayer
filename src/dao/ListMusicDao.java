package dao;

import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import entity.Music;

public interface ListMusicDao {

	// ���ݸ赥ID�õ������б�
	public List<Music> getMusicListById(int listId) throws SQLException;
	
	// ��赥����Ӹ���
	public void addMusicToList(int listId,int musicId) throws SQLException;
	
}
