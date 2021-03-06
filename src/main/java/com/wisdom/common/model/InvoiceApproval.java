package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * InvoiceApproval entity. @author MyEclipse Persistence Tools
 */

public class InvoiceApproval implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long invoiceId;
	private String userId;
	private Integer status;
	private Integer approvalStatus;
	private Timestamp updateTime;
	private Timestamp createTime;
	private long oldInvoiceId;

	// Constructors

	/** default constructor */
	public InvoiceApproval() {
	}

	/** minimal constructor */
	public InvoiceApproval(Long id, Long invoiceId, String userId) {
		this.id = id;
		this.invoiceId = invoiceId;
		this.userId = userId;
	}

	/** full constructor */
	public InvoiceApproval(Long id, Long invoiceId, String userId,
			Integer status, Integer approvalStatus,Timestamp updateTime, Timestamp createTime) {
		this.id = id;
		this.invoiceId = invoiceId;
		this.userId = userId;
		this.status = status;
		this.approvalStatus = approvalStatus;
		this.updateTime = updateTime;
		this.createTime = createTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

}