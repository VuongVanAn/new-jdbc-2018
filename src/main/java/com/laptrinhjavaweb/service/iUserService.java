package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.model.UserModel;

public interface iUserService {
	UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);

}
