package com.laptrinhjavaweb.service.impl;

import java.util.List;

import javax.inject.Inject;
import com.laptrinhjavaweb.dao.iCategoryDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.service.iCategoryService;

public class CategoryService implements iCategoryService {
	
	@Inject
	private iCategoryDAO categoryDao;

	@Override
	public List<CategoryModel> findAll() {
		
		return categoryDao.findAll();
	}
	 

}
