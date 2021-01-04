package dao;

import entity.Pagination;

import java.sql.SQLException;
import java.util.List;

public interface MusicCommentDao {
	
	// ͨ������ID��ø�������������
	public int getCommentCountByMusicId(int musicId) throws SQLException;
	
	// ͨ������ID�ͷ�ҳʵ����ĳҳ�ĸ������ۼ���
    public List<Object[]> getMusicCommentsByMusicId (int musicId, Pagination page) throws SQLException;
    
    // Ϊ�����������
    public void addMusicComment(int musicId, int userId, String comment) throws SQLException;
}
