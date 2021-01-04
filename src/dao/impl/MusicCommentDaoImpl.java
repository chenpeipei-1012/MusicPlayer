package dao.impl;

import entity.Music;
import entity.MusicComment;
import entity.Pagination;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils;
import dao.MusicCommentDao;

public class MusicCommentDaoImpl implements MusicCommentDao{
  
	@Override
	public void addMusicComment(int musicId, int userId, String comment) throws SQLException{
		Connection conn = DBUtils.getConnection();
		String sql = "insert into music_comment(music_id,user_id,comment) values(?,?,?);";
		
		// Ԥ׼��Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// ��������ֵ
		stmt.setInt(1, musicId);
		stmt.setInt(2, userId);
		stmt.setString(3, comment);
		
		// ִ��SQL
		int result = stmt.executeUpdate();
		System.out.println("result:" + result);
		// �ر�����
		DBUtils.closeConnection(conn);
	}


	@Override
	public int getCommentCountByMusicId(int musicId) throws SQLException {
		int commentCount = -1;
		Connection conn = DBUtils.getConnection();
		
		String sql = "select count(*) from music_comment where music_id = ?";
		
		// Ԥ׼��Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// ��������ֵ
		stmt.setInt(1, musicId);
		
		// ִ��SQL
		ResultSet result = stmt.executeQuery();

		Music music = null;
		// ѭ�������
		if(result.next()){
			commentCount = result.getInt(1);
		}
		
		return commentCount;
	}


	@Override
	public List<Object[]> getMusicCommentsByMusicId(int musicId,
			Pagination page) throws SQLException {
		List<Object[]> list = new ArrayList<Object[]>();
		
		Connection conn = DBUtils.getConnection();
		
		String sql = "select mc_id,music_id,comment,comment_date,mc.user_id user_id,user_nick,user_pic from " + 
						"( " +
						"select * from music_comment " +
						"where music_id = ? " +
						"ORDER BY comment_date desc " +
						"limit  ?,? " +
						") mc " +
					"LEFT JOIN `user` " +
					"ON `user`.user_id = mc.user_id";
		
		// Ԥ׼��Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// ��������ֵ
		stmt.setInt(1, musicId);
		stmt.setInt(2, page.getCurStartRow());
		stmt.setInt(3, page.getPageSize());
		
		// ִ��SQL
		ResultSet result = stmt.executeQuery();

		MusicComment mc = null;
		User user = null;
		Object[] object = null; 
		// ѭ�������
		while(result.next()){
			mc = new MusicComment();
			user = new User();
			object = new Object[2];
			
			// ����ʵ��
			mc.setMcId(result.getInt("mc_id"));
			mc.setMusicId(result.getInt("music_id"));
			mc.setComment(result.getString("comment"));
			mc.setCommentDate(result.getTimestamp("comment_date"));
			
			// �û�ʵ��
			user.setUserId(result.getInt("user_id"));
			user.setUserNick(result.getString("user_nick"));
			user.setUserPic(result.getString("user_pic"));
			
			object[0] = mc;
			object[1] = user;
			
			list.add(object);
		}
		
		return list;
	}
}