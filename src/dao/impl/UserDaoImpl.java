package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import util.DBUtils;
import dao.UserDao;
import entity.MusicDownload;
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
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 给参数赋值
		stmt.setString(1, nick);
		stmt.setString(2, gender);
		stmt.setString(3, desc);
		stmt.setString(4, birthday);
		stmt.setString(5, iddress);
		stmt.setString(6, picPath);
		stmt.setInt(7, userId);
		
		// 执行SQL
		boolean result = stmt.execute();
		// 关闭连接
		DBUtils.closeConnection(conn);
		
		return result;
	}
	
	@Override
	public boolean modifyPwd(int userId,String pwd) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String sql = "update user set user_pwd=? where user_id = ?";
		
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 给参数赋值
		stmt.setString(1, pwd);
		stmt.setInt(2, userId);
		
		// 执行SQL
		boolean result = stmt.execute();
		// 关闭连接
		DBUtils.closeConnection(conn);
		
		return result;
	}

	// 测试
	public static void main(String args []){
		UserDaoImpl impl = new UserDaoImpl();
		
		try {
			//impl.modifyUserInfo(1, "佩佩", "女", "这是我的描述", "c://", new Date(), "湖南省 株洲市");
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
		
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 给参数赋值
		stmt.setInt(1, userId);
		
		// 执行SQL
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
		
		// 关闭连接
		DBUtils.closeConnection(conn);
		
		return user;
	}

	@Override
	public User queryUserInfoByName(String userName) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from user where user_name = ?";
		
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 给参数赋值
		stmt.setString(1, userName);
		
		// 执行SQL
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
		
		// 关闭连接
		DBUtils.closeConnection(conn);
		
		return user;
	}

	@Override
	public boolean insertUser(String userName, String password)
			throws SQLException {
		Connection conn = DBUtils.getConnection();
		String sql = "insert into user (user_name, user_pwd,user_nick,user_pic) values(?,?,?,?)";
		boolean result = false;
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		// 给参数赋值
		stmt.setString(1, userName);
		stmt.setString(2, password);
		stmt.setString(3, userName);
		stmt.setString(4, "musicCloud/userPic/default_user_pic.jpg");
		
		int userId = -1;
		// 执行SQL
		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();

		if (rs.next()) {
			userId = rs.getInt(1); 
			result = true;
        }
		
		DBUtils.closeConnection(conn);
		
		addDefault(userId,1);
		addDefault(userId,2);
		return result;
	}
	
	public void addDefault(int userId,int type) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String sql = "insert into user_musiclist(list_name,list_uid,list_love) values(?,?,?)";

		
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		String listName = "";
		if(type == 1){
			listName = "我喜欢的音乐";
		}else if(type == 2){
			listName = "默认收藏";
		}
		// 给参数赋值
		stmt.setString(1, listName);
		stmt.setInt(2, userId);
		stmt.setInt(3, type);
		
		// 执行SQL
		boolean result = stmt.execute();
		// 关闭连接
		DBUtils.closeConnection(conn);
	}

}
