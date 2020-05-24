package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.iPageble;

public interface iNewDAO extends iGenericDAO<NewModel> {
	NewModel findOne(long id);
	List<NewModel> findByCategoryId(Long categoryId);
	Long save(NewModel newModel);
	void update(NewModel updateNew);
	void delete(long id);
    List<NewModel> findAll(iPageble pageble);
    int getTotalItem();
}
