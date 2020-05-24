package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;

public class UserMapper implements iRowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet rs) {
		try {
			UserModel users = new UserModel();
			users.setId(rs.getLong("id"));
			users.setUserName(rs.getString("username"));
			users.setPassword(rs.getString("password"));
			users.setFullName(rs.getString("fullname"));
			users.setStatus(rs.getInt("status"));
			try{
				RoleModel roles = new RoleModel();
				roles.setCode(rs.getString("code"));
				roles.setName(rs.getString("name"));
				users.setRole(roles);				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			return users;
		} catch (SQLException e) {			
			return null;
		}		
	}

}
