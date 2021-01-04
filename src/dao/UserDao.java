package dao;

import java.sql.SQLException;
import java.util.List;

import entity.MusicDownload;
import entity.User;

public interface UserDao {

	// �޸��û���Ϣ
	public boolean modifyUserInfo(int userId,String nick,String gender,String desc,
			String birthday,String iddress,String picPath) throws SQLException;
	
	// �޸�����
	public boolean modifyPwd(int userId,String pwd) throws SQLException;
	
	// ��ѯ�û���Ϣ
	public User queryUserInfoById(int userId) throws SQLException;
	
	// �����û�����ѯ�û�
	public User queryUserInfoByName(String userName) throws SQLException;
	
	//�����û�
	public boolean insertUser(String userName,String password) throws SQLException;
}
