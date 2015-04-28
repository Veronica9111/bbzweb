package com.wisdom.company.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.Company;
import com.wisdom.company.dao.ICompanyDao;
import com.wisdom.company.mapper.CompanyMapper;

@Repository("companyDao")
public class CompanyDaoImpl implements ICompanyDao {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Company getCompanyByCompanyId(long companyId) {
		String sql = "select * from company where id = ?";
		Company company = jdbcTemplate.queryForObject(sql,
				new Object[] { companyId }, new CompanyMapper());
		logger.debug("getCompanyByCompanyId");
		return company;
	}

	@Override
	public long addCompany(Company company) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		long id = jdbcTemplate.update(new PreparedStatementCreator() {  
	        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
	        	String sql = "insert into company (name, parent_id, month_expense, perfect_moment, create_time)"
	    				+ " values (?, ?, ?, ?, ?)";
	               PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
	               ps.setString(1, company.getName());  
	               ps.setLong(2, company.getParentId());  
	               ps.setInt(3, company.getMonthExpense());
	               ps.setTimestamp(4, company.getPerfectMoment());
	               ps.setTimestamp(5, company.getCreateTime());
	               return ps;  
	        }  
	    }, keyHolder);
		logger.debug("addCompany result : {}", id);
		return id;
	}

	@Override
	public boolean deleteCompany(Company company) {
		String sql = "delete from company where id = ?";
		int affectedRows = jdbcTemplate.update(sql, company.getId());
		logger.debug("deleteCompany result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateCompany(Company company) {
		String sql = "update company set name=?, parent_id=?, month_expense=?, perfect_moment=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, company.getName(),
				company.getParentId(), company.getMonthExpense(),
				company.getPerfectMoment(), company.getId());
		logger.debug("updateCompany result : {}", affectedRows);
		return affectedRows != 0;
	}

}
