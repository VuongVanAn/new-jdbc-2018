package com.laptrinhjavaweb.dao;

import java.util.List;
import com.laptrinhjavaweb.model.CategoryModel;

public interface iCategoryDAO extends iGenericDAO<CategoryModel>{
	 List<CategoryModel> findAll();
	 CategoryModel findOne(long id);
	 CategoryModel findOneByCode(String code);
}
