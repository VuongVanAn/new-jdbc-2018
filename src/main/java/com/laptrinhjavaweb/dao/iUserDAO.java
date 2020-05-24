package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.model.UserModel;

public interface iUserDAO extends iGenericDAO<UserModel> {
	UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);

}
