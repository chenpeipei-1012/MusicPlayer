package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import util.DBUtils;
import dao.UserDao;
import entity.User;

public class UserDaoImpl implements UserDao{

	@Override
	public boolean modifyUserInfo(int userId,String nick,String gender,String desc,
			String birthday,String iddress,String picPath) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String sql = "update user set user_nick=?,user_gender=?,user_desc=?,user_birthday=?,user_iddr=?,user_pic = ? " +
						"where user_id=?;";
		
		if("".equals(birthday))
			birthday = null;
		// Ԥ׼��Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// ��������ֵ
		stmt.setString(1, nick);
		stmt.setString(2, gender);
		stmt.setString(3, desc);
		stmt.setString(4, birthday);
		stmt.setString(5, iddress);
		stmt.setString(6, picPath);
		stmt.setInt(7, userId);
		
		// ִ��SQL
		boolean result = stmt.execute();
		// �ر�����
		DBUtils.closeConnection(conn);
		
		return result;
	}
	
	@Override
	public boolean modifyPwd(int userId,String pwd) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String sql = "update user set user_pwd=? where user_id = ?";
		
		// Ԥ׼��Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// ��������ֵ
		stmt.setString(1, pwd);
		stmt.setInt(2, userId);
		
		// ִ��SQL
		boolean result = stmt.execute();
		// �ر�����
		DBUtils.closeConnection(conn);
		
		return result;
	}

	// ����
	public static void main(String args []){
		UserDaoImpl impl = new UserDaoImpl();
		
		try {
			//impl.modifyUserInfo(1, "����", "Ů", "�����ҵ�����", "c://", new Date(), "����ʡ ������");
			impl.modifyPwd(1, "666666");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public User queryUserInfoById(int userId) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from user where user_id = ?";
		
		// Ԥ׼��Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// ��������ֵ
		stmt.setInt(1, userId);
		
		// ִ��SQL
		ResultSet result = stmt.executeQuery();
		User user = null;
		if(result.next()){
			user = new User(result.getInt("user_id"),
					result.getString("user_name"),
					result.getString("user_gender"),
					result.getString("user_desc"),
					result.getString("user_pic"),
					result.getString("user_nick"),
					result.getString("user_iddr"),
					null,
					result.getInt("user_type"));
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = result.getDate("user_birthday");
			if(date != null)
				user.setUserBirthday(dateFormat.format(date));
		}
		
		// �ر�����
		DBUtils.closeConnection(conn);
		
		return user;
	}

	@Override
	public User queryUserInfoByName(String userName) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from user where user_name = ?";
		
		// Ԥ׼��Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// ��������ֵ
		stmt.setString(1, userName);
		
		// ִ��SQL
		ResultSet result = stmt.executeQuery();
		User user = null;
		if(result.next()){
			user = new User(result.getInt("user_id"),
					result.getString("user_name"),
					result.getString("user_gender"),
					result.getString("user_desc"),
					result.getString("user_pic"),
					result.getString("user_nick"),
					result.getString("user_iddr"),
					null,
					result.getInt("user_type"));
			
			user.setUserPwd(result.getString("user_pwd"));
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = result.getDate("user_birthday");
			if(date != null)
				user.setUserBirthday(dateFormat.format(date));
		}
		
		// �ر�����
		DBUtils.closeConnection(conn);
		
		return user;
	}

}
