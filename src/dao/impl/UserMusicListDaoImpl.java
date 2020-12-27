package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils;
import dao.UserMusicListDao;
import entity.UserMusicList;

public class UserMusicListDaoImpl implements UserMusicListDao{

	@Override
	public int addMusicList(int userId, String listName) throws SQLException {
		Connection conn = DBUtils.getConnection();
		
		String sql = "insert into user_musiclist(list_name,list_uid) values(?,?)";
		
		int id = -1;
		// Ԥ׼��Statement
		PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		
		// ��������ֵ
		stmt.setString(1, listName);
		stmt.setInt(2, userId);
		
		// ִ��SQL
		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();

		if (rs.next()) {
            id = rs.getInt(1); 
        }
		// �ر�����
		DBUtils.closeConnection(conn);
		
		return id;
	}

	@Override
	public boolean deleteMusicList(int listId) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String sql = "delete from user_musiclist where list_id = ?;";
		
		// Ԥ׼��Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// ��������ֵ
		stmt.setInt(1, listId);
		
		// ִ��SQL
		boolean result = stmt.execute();
		// �ر�����
		DBUtils.closeConnection(conn);
		
		return result;
	}

	@Override
	public boolean modifyMusicList(int listId, String listName) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String sql = "update user_musiclist set list_name=? where list_id = ?";
		
		// Ԥ׼��Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// ��������ֵ
		stmt.setString(1, listName);
		stmt.setInt(2, listId);
		
		// ִ��SQL
		boolean result = stmt.execute();
		// �ر�����
		DBUtils.closeConnection(conn);
		
		return result;
	}

	@Override
	public List<UserMusicList> queryMusicList(int userId) throws SQLException {
		List<UserMusicList> list = new ArrayList<UserMusicList>();
		
		Connection conn = DBUtils.getConnection();

		String sql = "select list_id,list_name,list_time,list_uid,list_love,num,music_pic from " +
						"(select *,count(lid) num, max(save_time) time from user_musiclist " + 
						"LEFT JOIN list_music on list_id = lid " +
						"where list_uid = ? " +
						"GROUP BY list_id " +
						") lm " +
					"left join music m on m.music_id = mid;";

		
		// Ԥ׼��Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// ��������ֵ
		stmt.setInt(1, userId);
		
		// ִ��SQL
		ResultSet result = stmt.executeQuery();

		UserMusicList musicList = null;
		// ѭ�������
		while(result.next()){
			musicList = new UserMusicList();
			musicList.setListId(result.getInt("list_id"));
			musicList.setListName(result.getString("list_name"));
			musicList.setListTime(result.getTimestamp("list_time"));
			musicList.setListUid(result.getInt("list_uid"));
			musicList.setListLove(result.getInt("list_love"));
			musicList.setMusicNum(result.getInt("num"));
			musicList.setListPic(result.getString("music_pic"));
			
			list.add(musicList);
		}
		
		DBUtils.closeConnection(conn);
		
		return list;
	}
	
	// ����
	public static void main(String args []){
		UserMusicListDaoImpl impl = new UserMusicListDaoImpl();
		try {
			impl.addMusicList(1, "�赥1");
			// impl.deleteMusicList(3);
			// impl.modifyMusicList(4, "�赥�޸�");
			//impl.queryMusicList(1);
			//System.out.println(impl.getLoveMusicListId(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getLoveMusicListId(int userId) throws SQLException {
		int loveListId = -1;
		Connection conn = DBUtils.getConnection();
		
		String sql = "select list_id from user_musiclist where list_uid = ? and list_love = 1;";
		// Ԥ׼��Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// ��������ֵ
		stmt.setInt(1, userId);
		
		ResultSet result = stmt.executeQuery();
		if(result.next()){
			loveListId = result.getInt("list_id");
		}
		
		return loveListId;
	}
}