package com.wisdom.web.api.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.accounter.service.IAccounterService;
import com.wisdom.common.model.Company;
import com.wisdom.common.model.CompanyDetail;
import com.wisdom.common.model.SalarySocialSecurity;
import com.wisdom.common.model.User;
import com.wisdom.common.model.UserPhone;
import com.wisdom.company.service.ICompanyDetailService;
import com.wisdom.company.service.ICompanyService;
import com.wisdom.user.dao.IUserQueryDao;
import com.wisdom.user.service.IUserService;

@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory
			.getLogger(AdminController.class);

	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private ICompanyDetailService companyDetailService;
	
	@Autowired
	private IAccounterService accounterService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserQueryDao userQueryDao;
	
	@RequestMapping("/admin/getAllRegisterCompanyInfo")
	@ResponseBody
	public List<Map<String, String>> getAllRegisterCompanyInfo(HttpServletRequest request) {
		List<Company> companyList = companyService.getAllCompany();
		List<Map<String, String>> retList = new ArrayList<>();
		if(companyList != null && companyList.size() != 0) {
			for(Company company : companyList) {
				if(company.getParentId() != -1) continue;
				Map<String, String> map = new HashMap<>();
				map.put("companyName", company.getName() == null ? "未设定" : company.getName());
				map.put("date", company.getCreateTime().toString().substring(0, 10));
				map.put("expense", String.valueOf(company.getMonthExpense()));
				map.put("callTime", company.getPerfectMoment());
				retList.add(map);
			}
		}
		return retList;
	}
	
	@RequestMapping("/admin/getAllRegisterCompanyInfoByName")
	@ResponseBody
	public List<Map<String, String>> getAllRegisterCompanyInfoByName(HttpServletRequest request) {
		List<Company> companyList = companyService.getAllCompany();
		String conditionValue = request.getParameter("conditionValue");
		List<Map<String, String>> retList = new ArrayList<>();
		if(companyList != null && companyList.size() != 0) {
			for(Company company : companyList) {
				if(company.getParentId() != -1) continue;
				if(company.getName() == null) continue;
				if(company.getName().indexOf(conditionValue) == -1) continue;
				Map<String, String> map = new HashMap<>();
				map.put("companyName", company.getName() == null ? "未设定" : company.getName());
				map.put("date", company.getCreateTime().toString().substring(0, 10));
				map.put("expense", String.valueOf(company.getMonthExpense()));
				map.put("callTime", company.getPerfectMoment());
				retList.add(map);
			}
		}
		return retList;
	}
	
	@RequestMapping("/admin/getAllCompanyDetailInfo")
	@ResponseBody
	public List<Map<String, String>> getAllCompanyDetailInfo(HttpServletRequest request) {
		List<CompanyDetail> companyDetailList = companyDetailService.getAllCompanyDetail();
		List<Map<String, String>> retList = new ArrayList<>();
		if(companyDetailList != null && companyDetailList.size() != 0) {
			for(CompanyDetail companyDetail : companyDetailList) {
				long companyId = companyDetail.getCompanyId();
				String companyName = companyService.getCompanyName(companyId);
				Map<String, String> map = new HashMap<>();
				map.put("companyId", String.valueOf(companyId));
				map.put("companyName", companyName);
				map.put("coporation", companyDetail.getCorporation());
				map.put("zhuzhiCode", companyDetail.getOrgCode());
				map.put("shuiwuCode", companyDetail.getTaxCode());
				map.put("bank", companyDetail.getBankName());
				map.put("address", companyDetail.getRegAddress());
				map.put("companyFile", companyDetail.getCompanyResFile());
				map.put("zhuZhiFile", companyDetail.getOrgCodeFile());
				map.put("shuiWuFile", companyDetail.getTaxCodeFile());
				retList.add(map);
			}
		}
		return retList;
	}
	
	@RequestMapping("/admin/getAllCompanyDetailInfoByName")
	@ResponseBody
	public List<Map<String, String>> getAllCompanyDetailInfoByName(HttpServletRequest request) {
		List<CompanyDetail> companyDetailList = companyDetailService.getAllCompanyDetail();
		List<Map<String, String>> retList = new ArrayList<>();
		String conditionValue = request.getParameter("conditionValue");
		if(companyDetailList != null && companyDetailList.size() != 0) {
			for(CompanyDetail companyDetail : companyDetailList) {
				long companyId = companyDetail.getCompanyId();
				String companyName = companyService.getCompanyName(companyId);
				if(companyName == null) continue;
				if(companyName.indexOf(conditionValue) == -1) continue;
				Map<String, String> map = new HashMap<>();
				map.put("companyName", companyName);
				map.put("coporation", companyDetail.getCorporation());
				map.put("zhuzhiCode", companyDetail.getOrgCode());
				map.put("shuiwuCode", companyDetail.getTaxCode());
				map.put("bank", companyDetail.getBankName());
				map.put("address", companyDetail.getRegAddress());
				map.put("companyFile", companyDetail.getCompanyResFile());
				map.put("zhuZhiFile", companyDetail.getOrgCodeFile());
				map.put("shuiWuFile", companyDetail.getTaxCodeFile());
				retList.add(map);
			}
		}
		return retList;
	}
	
	@RequestMapping("/admin/getAllAccounterDetailInfo")
	@ResponseBody
	public List<Map<String, String>> getAllAccounterDetailInfo(HttpServletRequest request) {
		List<User> userList = userService.getAccounterUserList();
		List<Map<String, String>> retList = new ArrayList<>();
		if(userList != null && userList.size() != 0) {
			for(User user : userList) {
				
				Map<String, String> map = new HashMap<>();
				
				String date = user.getCreateTime().toString().substring(0,10);
				String userId = user.getUserId();
				String userName = user.getUserName() == null || user.getUserName().isEmpty() ? "未设定" : user.getUserName();
				UserPhone userPhone = userQueryDao.getUserPhoneByUserId(userId);
				String phone = userPhone == null || userPhone.getPhone() == null || userPhone.getPhone().isEmpty() ? "未设定" : userPhone.getPhone();
				map.put("date", date);
				map.put("userId", userId);
				map.put("userName", userName);
				map.put("phone", phone);
				
				Map<String, String> accounterMap = accounterService.getAccounterByUserId(userId);
				if(accounterMap == null || accounterMap.size() == 0) {
					map.put("workTime", "未设定");
					map.put("workIndustry", "未设定");
					map.put("workCertificate", "未设定");
					map.put("workArea", "未设定");
					map.put("image", "none");
				} else {
					map.put("workTime", accounterMap.get("career"));
					map.put("workIndustry", accounterMap.get("industry"));
					map.put("workCertificate", accounterMap.get("certificate"));
					map.put("workArea", accounterMap.get("province")+accounterMap.get("city")+accounterMap.get("area"));
					map.put("image", accounterMap.get("image"));
				}
				retList.add(map);
			}
		}
		return retList;
	}
	
	@RequestMapping("/admin/downloadCompanyFile")
	public ResponseEntity<byte[]> downloadCompanyFile(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		String cId = request.getParameter("companyId");
		long companyId = Long.valueOf(cId);
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF");
		ResponseEntity<byte[]> re = null;
		CompanyDetail companyDetail = companyDetailService.getCompanyDetailByCompanyId(companyId);
		if (companyDetail == null)
			return null;
		File file = new File(realPath + "/" + companyDetail.getCompanyResFile());
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDispositionFormData("attachment", new String(
					companyDetail.getCompanyResFile().getBytes("UTF-8"), "iso-8859-1"));
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			re = new ResponseEntity<byte[]>(
					FileUtils.readFileToByteArray(file), headers,
					HttpStatus.CREATED);
		} catch (IOException e) {
			logger.debug("download exception : {}", e.toString());
		}
		return re;
	}
	
	@RequestMapping("/admin/downloadShuiWuFile")
	public ResponseEntity<byte[]> downloadShuiWuFile(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		String cId = request.getParameter("companyId");
		long companyId = Long.valueOf(cId);
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF");
		ResponseEntity<byte[]> re = null;
		CompanyDetail companyDetail = companyDetailService.getCompanyDetailByCompanyId(companyId);
		if (companyDetail == null)
			return null;
		File file = new File(realPath + "/" + companyDetail.getTaxCodeFile());
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDispositionFormData("attachment", new String(
					companyDetail.getTaxCodeFile().getBytes("UTF-8"), "iso-8859-1"));
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			re = new ResponseEntity<byte[]>(
					FileUtils.readFileToByteArray(file), headers,
					HttpStatus.CREATED);
		} catch (IOException e) {
			logger.debug("download exception : {}", e.toString());
		}
		return re;
	}
	
	@RequestMapping("/admin/downloadZhuZhiFile")
	public ResponseEntity<byte[]> downloadZhuZhiFile(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		String cId = request.getParameter("companyId");
		long companyId = Long.valueOf(cId);
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF");
		ResponseEntity<byte[]> re = null;
		CompanyDetail companyDetail = companyDetailService.getCompanyDetailByCompanyId(companyId);
		if (companyDetail == null)
			return null;
		File file = new File(realPath + "/" + companyDetail.getOrgCodeFile());
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDispositionFormData("attachment", new String(
					companyDetail.getOrgCodeFile().getBytes("UTF-8"), "iso-8859-1"));
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			re = new ResponseEntity<byte[]>(
					FileUtils.readFileToByteArray(file), headers,
					HttpStatus.CREATED);
		} catch (IOException e) {
			logger.debug("download exception : {}", e.toString());
		}
		return re;
	}
}