package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils;
import dao.UserMusicListDao;
import entity.UserMusicList;

public class UserMusicListDaoImpl implements UserMusicListDao{

	@Override
	public boolean addMusicList(int userId, String listName) throws SQLException {
		Connection conn = DBUtils.getConnection();
		
		String sql = "insert into user_musiclist(list_name,list_uid) values(?,?)";
		
//		// 得到系统当前时间
//		long time = System.currentTimeMillis();
//		Timestamp timestamp = new Timestamp(time);
		
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		// 给参数赋值
		stmt.setString(1, listName);
		//stmt.setTimestamp(2, timestamp);
		stmt.setInt(2, userId);
		
		// 执行SQL
		boolean result = stmt.execute();
		
		// 关闭连接
		DBUtils.closeConnection(conn);
		
		return result;
	}

	@Override
	public boolean deleteMusicList(int listId) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String sql = "delete from user_musiclist where list_id = ?;";
		
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 给参数赋值
		stmt.setInt(1, listId);
		
		// 执行SQL
		boolean result = stmt.execute();
		// 关闭连接
		DBUtils.closeConnection(conn);
		
		return result;
	}

	@Override
	public boolean modifyMusicList(int listId, String listName) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String sql = "update user_musiclist set list_name=? where list_id = ?";
		
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 给参数赋值
		stmt.setString(1, listName);
		stmt.setInt(2, listId);
		
		// 执行SQL
		boolean result = stmt.execute();
		// 关闭连接
		DBUtils.closeConnection(conn);
		
		return result;
	}

	@Override
	public List<UserMusicList> queryMusicList(int userId) throws SQLException {
		List<UserMusicList> list = new ArrayList<UserMusicList>();
		
		Connection conn = DBUtils.getConnection();
		String sql = "select list_name,list_time from user_musiclist where list_uid = ?" ;
		
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 给参数赋值
		stmt.setInt(1, userId);
		
		// 执行SQL
		ResultSet result = stmt.executeQuery();

		UserMusicList musicList = null;
		// 循环结果集
		while(result.next()){
			musicList = new UserMusicList();
			musicList.setListName(result.getString("list_name"));
			musicList.setListTime(result.getTimestamp("list_time"));
			
			list.add(musicList);
		}
		DBUtils.closeConnection(conn);
		
		return list;
	}
	
	// 测试
	public static void main(String args []){
		UserMusicListDaoImpl impl = new UserMusicListDaoImpl();
		try {
			// impl.addMusicList(1, "歌单1");
			// impl.deleteMusicList(3);
			// impl.modifyMusicList(4, "歌单修改");
			impl.queryMusicList(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}