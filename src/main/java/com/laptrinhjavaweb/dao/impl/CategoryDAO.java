package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.iCategoryDAO;
import com.laptrinhjavaweb.mapper.CategoryMapper;
import com.laptrinhjavaweb.model.CategoryModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements iCategoryDAO {
    
	
	@Override
	public List<CategoryModel> findAll() {			
		String sql = "select * from jspservletjdbc.category";
		return query(sql, new CategoryMapper());		
	}

	@Override
	public CategoryModel findOne(long id) {
		String sql = "select * from jspservletjdbc.category where id = ?";
		List<CategoryModel> category =  query(sql, new CategoryMapper(), id);
		return category.isEmpty()? null: category.get(0);
	}

	@Override
	public CategoryModel findOneByCode(String code) {
		String sql = "select * from jspservletjdbc.category where code = ?";
		List<CategoryModel> category =  query(sql, new CategoryMapper(), code);
		return category.isEmpty()? null: category.get(0);
	}	
    
}
