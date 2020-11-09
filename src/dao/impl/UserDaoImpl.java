package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import util.DBUtils;
import dao.UserDao;

public class UserDaoImpl implements UserDao{

	@Override
	public boolean modifyUserInfo(int userId,String nick,String gender,String desc,String pic,
			Date birthday,String iddress) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String sql = "update user set user_nick=?,user_gender=?,user_desc=?,user_pic=?,user_birthday=?,user_iddr=? " +
						"where user_id=?;";
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 给参数赋值
		stmt.setString(1, nick);
		stmt.setString(2, gender);
		stmt.setString(3, desc);
		stmt.setString(4, pic);
		stmt.setString(5, dateFormat.format(birthday));
		stmt.setString(6, iddress);
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

}
