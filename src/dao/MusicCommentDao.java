package dao;

import entity.Pagination;

import java.sql.SQLException;
import java.util.List;

public interface MusicCommentDao {
	
	// 通过歌曲ID获得歌曲评论总条数
	public int getCommentCountByMusicId(int musicId) throws SQLException;
	
	// 通过歌曲ID和分页实体获得某页的歌曲评论集合
    public List<Object[]> getMusicCommentsByMusicId (int musicId, Pagination page) throws SQLException;
    
    // 为歌曲添加评论
    public void addMusicComment(int musicId, int userId, String comment) throws SQLException;
}
