package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.dao.iNewDAO;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.iPageble;

public class NewDAO extends AbstractDAO<NewModel>implements iNewDAO {

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		String sql = "select * from jspservletjdbc.news where categoryid = ?";
		return query(sql, new NewMapper(), categoryId);
	}

	@Override
	public Long save(NewModel newModel) {
		StringBuilder sql = new StringBuilder("insert into jspservletjdbc.news(title,content,");
		sql.append("thumbnail,shortdescription,categoryid,createddate,createdby)");
		sql.append(" values(?,?,?,?,?,?,?)");
		return insert(sql.toString(), newModel.getTitle(), newModel.getContent(), 
				newModel.getThumbnail(), newModel.getShortDescription(), newModel.getCategoryId(),
				newModel.getCreatedDate(), newModel.getCreatedBy());
	}

	@Override
	public NewModel findOne(long id) {
		String sql = "select * from jspservletjdbc.news where id = ?";
		List<NewModel> news =  query(sql, new NewMapper(), id);
		return news.isEmpty()? null: news.get(0);
	}

	@Override
	public void update(NewModel updateNew) {
		StringBuilder sql = new StringBuilder("update jspservletjdbc.news set title = ?, thumbnail = ?,");
		sql.append(" shortdescription = ?, content  = ?, categoryid = ?,");
		sql.append(" createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ? where id = ?");
		update(sql.toString(), updateNew.getTitle(),updateNew.getThumbnail(),
				updateNew.getShortDescription(),updateNew.getContent(),updateNew.getCategoryId(),
				updateNew.getCreatedDate(),updateNew.getCreatedBy(),
				updateNew.getModifiedDate(),updateNew.getModifiedBy(),updateNew.getId());	
	}

	@Override
	public void delete(long id) {
		String sql = "delete from jspservletjdbc.news where id = ?";
		update(sql, id);
	}

	@Override
	public List<NewModel> findAll(iPageble pageble) {	
		StringBuilder sql = new StringBuilder("select * from jspservletjdbc.news ");
		if(pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortBy())&& StringUtils.isNotBlank(pageble.getSorter().getSortName())){
			sql.append("order by "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+" ");			
		}
		if(pageble.getOffset() != null && pageble.getLimit() != null){
			sql.append("limit "+pageble.getOffset()+", "+pageble.getLimit()+"");
		}	
		return query(sql.toString(), new NewMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "select count(*) from jspservletjdbc.news";
		return count(sql);
	}
	
}
