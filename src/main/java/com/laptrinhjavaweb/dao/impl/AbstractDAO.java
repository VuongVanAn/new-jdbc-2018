package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.laptrinhjavaweb.dao.iGenericDAO;
import com.laptrinhjavaweb.mapper.iRowMapper;

public class AbstractDAO<T> implements iGenericDAO<T> {

	ResourceBundle resourceBundle = ResourceBundle.getBundle("db");

	public Connection getConnection() {
		try {			
			Class.forName(resourceBundle.getString("driverName"));
			String url = resourceBundle.getString("url");
			String user = resourceBundle.getString("user");
			String password = resourceBundle.getString("password");

			return DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> List<T> query(String sql, iRowMapper<T> rowMapper, Object... parameters) {
		List<T> results = new ArrayList<>();

		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			ptmt = conn.prepareStatement(sql);
			setParameter(ptmt, parameters);
			rs = ptmt.executeQuery();
			while (rs.next()) {
				results.add(rowMapper.mapRow(rs));
			}
			return results;
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ptmt != null) {
					ptmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}

	}

	// Truyen tham so vao CSDL phan them insert
	private void setParameter(PreparedStatement ptmt, Object... parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				Object parameter = parameters[i];
				int index = i + 1;
				if (parameter instanceof Long) {
					ptmt.setLong(index, (Long) parameter);
				} else if (parameter instanceof String) {
					ptmt.setString(index, (String) parameter);
				} else if (parameter instanceof Integer) {
					ptmt.setInt(index, (Integer) parameter);
				} else if (parameter instanceof Timestamp) {
					ptmt.setTimestamp(index, (Timestamp) parameter);
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void update(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement ptmt = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ptmt = conn.prepareStatement(sql);
			setParameter(ptmt, parameters);
			ptmt.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ptmt != null) {
					ptmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		Long id = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			ptmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameter(ptmt, parameters);
			ptmt.executeUpdate();

			rs = ptmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getLong(1);
			}
			conn.commit();
			return id;

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ptmt != null) {
					ptmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	@Override
	public int count(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		try {
			int count = 0;
			conn = getConnection();
			ptmt = conn.prepareStatement(sql);
			setParameter(ptmt, parameters);
			rs = ptmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			return 0;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ptmt != null) {
					ptmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				return 0;
			}
		}
	}

}
