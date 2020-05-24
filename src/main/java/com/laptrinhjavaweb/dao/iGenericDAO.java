package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.mapper.iRowMapper;

public interface iGenericDAO<T> {
	@SuppressWarnings("hiding")
	<T> List<T> query(String sql, iRowMapper<T> rowMapper, Object... parameters);
	void update(String sql, Object... parameters);
	Long insert(String sql, Object... parameters);
	int count(String sql, Object... parameters);
}
