package dao;

import java.sql.SQLException;
import java.util.List;

import entity.MusicDownload;
import entity.User;

public interface UserDao {

	// 修改用户信息
	public boolean modifyUserInfo(int userId,String nick,String gender,String desc,
			String birthday,String iddress,String picPath) throws SQLException;
	
	// 修改密码
	public boolean modifyPwd(int userId,String pwd) throws SQLException;
	
	// 查询用户信息
	public User queryUserInfoById(int userId) throws SQLException;
	
	// 根据用户名查询用户
	public User queryUserInfoByName(String userName) throws SQLException;
	
	//插入用户
	public boolean insertUser(String userName,String password) throws SQLException;
}
