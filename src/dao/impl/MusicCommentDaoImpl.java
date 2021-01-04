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
		
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 给参数赋值
		stmt.setInt(1, musicId);
		stmt.setInt(2, userId);
		stmt.setString(3, comment);
		
		// 执行SQL
		int result = stmt.executeUpdate();
		System.out.println("result:" + result);
		// 关闭连接
		DBUtils.closeConnection(conn);
	}


	@Override
	public int getCommentCountByMusicId(int musicId) throws SQLException {
		int commentCount = -1;
		Connection conn = DBUtils.getConnection();
		
		String sql = "select count(*) from music_comment where music_id = ?";
		
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 给参数赋值
		stmt.setInt(1, musicId);
		
		// 执行SQL
		ResultSet result = stmt.executeQuery();

		Music music = null;
		// 循环结果集
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
		
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 给参数赋值
		stmt.setInt(1, musicId);
		stmt.setInt(2, page.getCurStartRow());
		stmt.setInt(3, page.getPageSize());
		
		// 执行SQL
		ResultSet result = stmt.executeQuery();

		MusicComment mc = null;
		User user = null;
		Object[] object = null; 
		// 循环结果集
		while(result.next()){
			mc = new MusicComment();
			user = new User();
			object = new Object[2];
			
			// 评论实体
			mc.setMcId(result.getInt("mc_id"));
			mc.setMusicId(result.getInt("music_id"));
			mc.setComment(result.getString("comment"));
			mc.setCommentDate(result.getTimestamp("comment_date"));
			
			// 用户实体
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