package service.impl;

import java.sql.SQLException;
import java.util.Vector;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public User verifyUser(String userName, String password) {
		User user = null;
		UserDao userDao = new UserDaoImpl();
		
		try {
			user = userDao.queryUserInfoByName(userName);
			
			// 用户不存在
			if(user == null){
				return null;
			}
			
			// 如果用户存在，验证密码
			if(user.getUserPwd().equals(password)){
				
			}else{
				// 用户密码错误
				user = null;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		// 0: 用户名或密码错误    1: 普通用户    2: 管理员用户
		return user;
	}

	@Override
	public boolean saveUser(String userName, String password) {
		UserDao userDao = new UserDaoImpl();
		
		boolean result = false;
		
		try {
			result =  userDao.insertUser(userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
