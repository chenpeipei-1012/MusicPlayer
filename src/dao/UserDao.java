package dao;

import java.sql.SQLException;
import java.util.Date;

import entity.User;

public interface UserDao {

	// �޸��û���Ϣ
	public boolean modifyUserInfo(int userId,String nick,String gender,String desc,
			String pic,String birthday,String iddress) throws SQLException;
	
	// �޸�����
	public boolean modifyPwd(int userId,String pwd) throws SQLException;
	
	// ��ѯ�û���Ϣ
	public User queryUserInfoById(int userId) throws SQLException;
}
