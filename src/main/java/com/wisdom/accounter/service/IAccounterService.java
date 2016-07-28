package com.wisdom.accounter.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.wisdom.common.model.Accounter;
import com.wisdom.common.model.CustomerManagement;

public interface IAccounterService {

	public List<Accounter> getAllAccounterList();
	
	public Map<Long, String> getAllAccounterCareer();
	
	public Map<Long, String> getAllAccounterCertificate();
	
	public Map<Long, String> getAllAccounterIndustry();
	
	public Map<String, String> getAccounterByUserId(String userId);
	
	public List<Map<String, String>> getAllAccounter();
	
	public List<Map<String, String>> getAllAccounterByCondition(String province, String city, String area,
			String industry, String career);
	
	public boolean updateAccounter(Accounter accounter);
	
	public boolean addAccounter(Accounter accounter);
	
	public Map<String, List<Map<String, String>>> getAllCompanyExpense(String userId);
	
	public List<Map<String, String>> getAllAccounterCompany(String userId);
	
	public List<Map<String, String>> getAllAccounterCompanyWithoutCondition(String userId);
		
	public Map<String, List<Map<String, String>>> getAllCompanyExpenseByCondition(String userId, Map conditions);
	
	public Map<String, String> accounterBelongToCompany(String userId);
	
	public Map<String, String> accounterHasFinishRegister(String userId);
	
	public Map<String, String> uploadCompanySalaryTemplate(String companyId, String realPath, MultipartFile file, String userId);
	
	public Map<String, String> getTakeBillWay(String companyId);
	
	public List<Map<String, String>>  getAllCustomer();
	
}
