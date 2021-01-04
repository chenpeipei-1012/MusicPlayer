package service;

import entity.User;

public interface UserService {

	// 登录验证
	public User verifyUser(String userName, String password);
	
	// 保存新用户
	public boolean saveUser(String userName, String password);
}
