package dao;

import java.sql.SQLException;
import java.util.Date;

public interface UserDao {

	// �޸��û���Ϣ
	public boolean modifyUserInfo(int userId,String nick,String gender,String desc,
			String pic,Date birthday,String iddress) throws SQLException;
	
	// �޸�����
	public boolean modifyPwd(int userId,String pwd) throws SQLException;
}
