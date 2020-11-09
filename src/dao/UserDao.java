package dao;

import java.sql.SQLException;
import java.util.Date;

public interface UserDao {

	// 修改用户信息
	public boolean modifyUserInfo(int userId,String nick,String gender,String desc,
			String pic,Date birthday,String iddress) throws SQLException;
	
	// 修改密码
	public boolean modifyPwd(int userId,String pwd) throws SQLException;
}
