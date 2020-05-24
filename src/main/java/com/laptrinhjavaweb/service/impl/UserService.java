package com.laptrinhjavaweb.service.impl;

import javax.inject.Inject;
import com.laptrinhjavaweb.dao.iUserDAO;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.iUserService;

public class UserService implements iUserService{
	
	@Inject
	private iUserDAO userDao;

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {		
		return userDao.findByUserNameAndPasswordAndStatus(userName, password, status);
	}

}
