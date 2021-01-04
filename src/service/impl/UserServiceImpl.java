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
			
			// �û�������
			if(user == null){
				return null;
			}
			
			// ����û����ڣ���֤����
			if(user.getUserPwd().equals(password)){
				
			}else{
				// �û��������
				user = null;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		// 0: �û������������    1: ��ͨ�û�    2: ����Ա�û�
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
