package com.wisdom.common.model;

import java.sql.Timestamp;

/**
 * CommunityTag entity. @author MyEclipse Persistence Tools
 */

public class CommunityTag implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public CommunityTag() {
	}

	/** minimal constructor */
	public CommunityTag(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public CommunityTag(Long id, String name, Timestamp createTime) {
		this.id = id;
		this.name = name;
		this.createTime = createTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}