package com.wu.dao;

import com.wu.domain.User;

public interface LoginDao {
	public User useLogin(String name,String password);
}
