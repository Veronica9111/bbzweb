package com.wisdom.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.User;

public class UserMapper implements RowMapper<User> {
	public User mapRow(ResultSet rs, int index) throws SQLException {
		User u = new User(rs.getLong("id"), rs.getString("user_id"), rs.getString("user_name"),
				rs.getString("msg_email"), rs.getInt("type_id"),
				rs.getString("user_level"), rs.getLong("company_id"), 
				rs.getString("user_encode"), rs.getInt("audit_status"), 
				rs.getString("bill_audit_user"), rs.getTimestamp("create_time"));
		return u;
	}
}