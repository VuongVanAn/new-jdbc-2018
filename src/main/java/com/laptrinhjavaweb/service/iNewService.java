package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.iPageble;

public interface iNewService {
   List<NewModel> findByCategoryId(long categoryId);
   NewModel save(NewModel newModel);
   NewModel update(NewModel updateNew);
   void delete(long[] ids);
   List<NewModel> findAll(iPageble pageble);
   int getTotalItem();
   NewModel findOne(long id);
}
