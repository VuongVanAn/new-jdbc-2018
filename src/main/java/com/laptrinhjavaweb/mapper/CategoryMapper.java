package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.CategoryModel;

public class CategoryMapper implements iRowMapper<CategoryModel> {

	@Override
	public CategoryModel mapRow(ResultSet rs) {		
		try {
			CategoryModel category = new CategoryModel();
			
			category.setId(rs.getLong("id"));
			category.setName(rs.getString("name"));
			category.setCode(rs.getString("code"));	
			
			return category;
		} catch (SQLException e) {
			return null;
		}		
		
	}

}
