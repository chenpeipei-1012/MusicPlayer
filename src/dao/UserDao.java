package dao;

import java.sql.SQLException;

import entity.User;

public interface UserDao {

	// �޸��û���Ϣ
	public boolean modifyUserInfo(int userId,String nick,String gender,String desc,
			String birthday,String iddress,String picPath) throws SQLException;
	
	// �޸�����
	public boolean modifyPwd(int userId,String pwd) throws SQLException;
	
	// ��ѯ�û���Ϣ
	public User queryUserInfoById(int userId) throws SQLException;
}
