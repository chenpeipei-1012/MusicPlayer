package service;

import entity.User;

public interface UserService {

	// ��¼��֤
	public User verifyUser(String userName, String password);
	
	// �������û�
	public boolean saveUser(String userName, String password);
}
