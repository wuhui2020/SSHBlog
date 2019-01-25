package com.wu.service;

import com.wu.dao.LoginDao;
import com.wu.domain.User;

public class LoginServiceImpl implements LoginService {
	private LoginDao loginDao;
	public void setLoginDao(LoginDao loginDao){
		this.loginDao = loginDao;
	}
	@Override
	public User login(User user) {
		return loginDao.useLogin(user.getName(), user.getPassword());
		 
	}

}
