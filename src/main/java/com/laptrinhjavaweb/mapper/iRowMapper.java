package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;

public interface iRowMapper<T> {
   T mapRow(ResultSet rs);
}
