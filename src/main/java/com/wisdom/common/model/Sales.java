package com.wisdom.common.model;


public class Sales implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String sallerAccount;
    private String userName;
    private String userCompany;
    private String userPhone;
    private String latestComment;
    private String status;
    private String updatedTime;
    private String accountant;
    private String accountantId;
    private Integer hasSendEmail;
    private String sallerId;


 
    public Sales() {
        super();
    }
 
    public Sales(Integer id, String sallerAccount, String userName, String userCompany, String userPhone, 
    		String latestComment, String status, String updatedTime, String accountant, String accountantId,
    		Integer hasSendEmail, String sallerId) {
        super();
        this.id = id;
        this.sallerAccount = sallerAccount;
        this.userName = userName;
        this.userCompany = userCompany;
        this.userPhone = userPhone;
        this.latestComment = latestComment;
        this.status = status;
        this.updatedTime = updatedTime;
        this.accountant = accountant;
        this.accountantId = accountantId;
        this.hasSendEmail = hasSendEmail;
        this.sallerId = sallerId;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	public String getSallerAccount() {
		return sallerAccount;
	}

	public void setSallerAccount(String sallerAccount) {
		this.sallerAccount = sallerAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(String userCompany) {
		this.userCompany = userCompany;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getLatestComment() {
		return latestComment;
	}

	public void setLatestComment(String latestComment) {
		this.latestComment = latestComment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getAccountant() {
		return accountant;
	}

	public void setAccountant(String accountant) {
		this.accountant = accountant;
	}

	public String getAccountantId() {
		return accountantId;
	}

	public void setAccountantId(String accountantId) {
		this.accountantId = accountantId;
	}

	public Integer getHasSendEmail() {
		return hasSendEmail;
	}

	public void setHasSendEmail(Integer hasSendEmail) {
		this.hasSendEmail = hasSendEmail;
	}

	public String getSallerId() {
		return sallerId;
	}

	public void setSallerId(String sallerId) {
		this.sallerId = sallerId;
	}
 

 
   
}
