package com.wisdom.invoice.dao.impl;

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

import com.wisdom.common.model.Invoice;
import com.wisdom.invoice.dao.IInvoiceDao;
import com.wisdom.invoice.mapper.InvoiceMapper;

@Repository("invoiceDao")
public class InvoiceDaoImpl implements IInvoiceDao {

	private static final Logger logger = LoggerFactory
			.getLogger(InvoiceDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Invoice getInvoiceById(long id) {
		String sql = "select * from invoice where id = ?";
		Invoice invoice = jdbcTemplate.queryForObject(sql,
				new Object[] { id }, new InvoiceMapper());
		logger.debug("getInvoiceById");
		return invoice;
	}

	@Override
	public long addInvoiceAndGetKey(final Invoice invoice) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int id = jdbcTemplate.update(new PreparedStatementCreator() {  
	        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
	        	String sql = "insert into invoice (title, amount, date, create_time)"
	    				+ " values (?, ?, ?, ?)";
	               PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
	               ps.setString(1, invoice.getTitle());  
	               ps.setDouble(2, invoice.getAmount());  
	               ps.setTimestamp(3, invoice.getDate());  
	               ps.setTimestamp(4, invoice.getCreateTime());
	               return ps;  
	        }  
	    }, keyHolder);
		logger.debug("addInvoiceAndGetKey result : {}", id);
		return id;
	}

	@Override
	public boolean deleteInvoice(Invoice invoice) {
		String sql = "delete from invoice where id = ?";
		int affectedRows = jdbcTemplate.update(sql, invoice.getId());
		logger.debug("deleteInvoice result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateInvoice(Invoice invoice) {
		String sql = "update invoice set title=?, amount=?, date=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, invoice.getTitle(),
				invoice.getAmount(), invoice.getDate(), invoice.getId());
		logger.debug("updateInvoice result : {}", affectedRows);
		return affectedRows != 0;
	}

}