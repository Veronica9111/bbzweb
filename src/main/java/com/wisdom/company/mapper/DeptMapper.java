package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.Dept;

public class DeptMapper implements RowMapper<Dept> {
	public Dept mapRow(ResultSet rs, int index) throws SQLException {
		Dept u = new Dept(rs.getLong("id"), rs.getString("name"),
				rs.getLong("cost_center_encode"), rs.getLong("company_id"),
				rs.getLong("parent_id"), rs.getString("dept_code"),rs.getInt("level"), rs.getTimestamp("create_time"));
		return u;
	}
}
