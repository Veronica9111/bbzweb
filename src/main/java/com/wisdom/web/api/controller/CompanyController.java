package com.wisdom.web.api.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.wisdom.area.service.IAreaService;
import com.wisdom.company.service.ICompanyService;
import com.wisdom.user.service.IUserModifyService;
import com.wisdom.user.service.IUserService;
import com.wisdom.web.api.ICompanyApi;
import com.wisdom.web.utils.CompanyOrgStructure;
import com.wisdom.web.utils.ErrorCode;

@Controller
public class CompanyController {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyController.class);

	@Autowired
	private ICompanyService companyService;

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IAreaService areaService;
	
	@Autowired
	private ICompanyApi companyApi;
	
	@Autowired
	private IUserModifyService userModifyService;
	
	@RequestMapping("/company/selectAccounter")
	@ResponseBody
	public Map<Integer, String> selectAccounter(HttpServletRequest request) {
		logger.info("enter selectAccounter");
		boolean selectSuccess = false;
		Map<Integer, String> retMap = new HashMap<>();
		String accounterUserId = request.getParameter("accounterId");
		String userId = (String) request.getSession().getAttribute("userId");
		long companyId = userService.getCompanyIdByUserId(userId);
		int accounterAmount = companyService
				.accounterAmountBelongToCompany(companyId);
		if (companyId > -1 && accounterAmount < 2) {
			selectSuccess = companyService.companySelectAccounter(companyId,
					accounterUserId);
		}
		if (selectSuccess) {
			retMap.put(ErrorCode.NO_ERROR_CODE, ErrorCode.NO_ERROR_MESSAGE);
		} else {
			retMap.put(ErrorCode.COMPANY_SELECT_ACCOUNTER_ERROR_CODE,
					ErrorCode.COMPANY_SELECT_ACCOUNTER_ERROR_MESSAGE);
		}
		logger.info("leave selectAccounter");
		return retMap;
	}
	
	@RequestMapping("/company/selectOneAccounter")
	@ResponseBody
	public Map<Integer, String> selectOneAccounter(HttpServletRequest request) {
		logger.info("enter selectOneAccounter");
		Map<Integer, String> retMap = new HashMap<>();
		String accounterUserId = request.getParameter("accounterId");
		String userId = (String) request.getSession().getAttribute("userId");
		long companyId = userService.getCompanyIdByUserId(userId);
		companyService.updateCompanyAccounter(companyId, accounterUserId);
		logger.info("leave selectOneAccounter");
		return retMap;
	}
	
	@RequestMapping("/comDetailRegister")
	@ResponseBody
	public Map<String, String> companyFinishRegister(HttpServletRequest request) {
		logger.debug("enter companyFinishRegister");
		Map<String, String> params = new HashMap<>();
		params.put("userEmail", request.getParameter("userEmail"));
		params.put("userPwd", request.getParameter("userPwd"));
		params.put("userName", request.getParameter("userName"));
		params.put("userCompanyName", request.getParameter("userCompanyName"));
		params.put("userCompanyIncomes", request.getParameter("userCompanyIncomes"));
		params.put("userCalledTime", request.getParameter("userCalledTime"));
		params.put("userPhone", request.getParameter("userPhone"));
		Map<String, String> retMap = companyApi.companyDetailRegister(params);
		logger.debug("finish companyFinishRegister, retMap : {}", retMap);
		return retMap;
	}
	
	@RequestMapping("/company/orgInfoSettings")
	public String orgInfoSettings(@RequestParam("files") MultipartFile[] files , HttpServletRequest request) {
		Map<String, String> params = new HashMap<>();
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/files/company");
		params.put("realPath", realPath);
		params.put("userId", (String) request.getSession().getAttribute("userId"));
		params.put("companyName", request.getParameter("companyName"));
		params.put("corparation", request.getParameter("corparation"));
		params.put("orgCode", request.getParameter("orgCode"));
		params.put("taxCode", request.getParameter("taxCode"));
		params.put("province", areaService.getProvinceNameByProvinceId(request.getParameter("province")));
		params.put("city", areaService.getCityNameByCityId(request.getParameter("city")));
		params.put("area", areaService.getAreaNameByAreaId(request.getParameter("area")));
		params.put("addExtra", request.getParameter("addExtra"));
		params.put("bankName", request.getParameter("bankName"));
		companyApi.companyInfoSettings(files, params);
		return "redirect:/views/webviews/company/organization_structure_settings.html";
	}
	
	@RequestMapping("/company/setSalarySocialSecurityInfo")
	public String setSalarySocialSecurityInfo(HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String userId = (String) request.getSession().getAttribute("userId");
		retMap.put("userId", userId);
		retMap.put("currentCity", request.getParameter("currentCity"));
		retMap.put("currentType", request.getParameter("currentType"));
		retMap.put("pensionCratio", request.getParameter("pensionCratio"));
		retMap.put("pensionPratio", request.getParameter("pensionPratio"));
		retMap.put("pensionBase", request.getParameter("pensionBase"));
		retMap.put("medicalCratio", request.getParameter("medicalCratio"));
		retMap.put("medicalPratio", request.getParameter("medicalPratio"));
		retMap.put("medicalBase", request.getParameter("medicalBase"));
		retMap.put("unemployCratio", request.getParameter("unemployCratio"));
		retMap.put("unemployPratio", request.getParameter("unemployPratio"));
		retMap.put("unemployBase", request.getParameter("unemployBase"));
		retMap.put("injuryCratio", request.getParameter("injuryCratio"));
		retMap.put("injuryPratio", request.getParameter("injuryPratio"));
		retMap.put("injuryBase", request.getParameter("injuryBase"));
		retMap.put("birthCratio", request.getParameter("birthCratio"));
		retMap.put("birthPratio", request.getParameter("birthPratio"));
		retMap.put("birthBase", request.getParameter("birthBase"));
		retMap.put("accfundCratio", request.getParameter("accfundCratio"));
		retMap.put("accfundPratio", request.getParameter("accfundPratio"));
		retMap.put("accfundBase", request.getParameter("accfundBase"));
		retMap.put("salaryCratio", request.getParameter("salaryCratio"));
		retMap.put("salaryPratio", request.getParameter("salaryPratio"));
		retMap.put("salaryBase", request.getParameter("salaryBase"));
		retMap.put("bigmedicalCratio", request.getParameter("bigmedicalCratio"));
		retMap.put("bigmedicalPratio", request.getParameter("bigmedicalPratio"));
		retMap.put("bigmedicalBase", request.getParameter("bigmedicalBase"));
		retMap = companyApi.updateSSSInfo(retMap);
		logger.debug("finish setSalarySocialSecurityInfo, retMap : {}", retMap);
		return "redirect:/views/webviews/company/salary_welfare_args_settings.html";
	}
	
	@RequestMapping("/company/getSalarySocialSecurityInfo")
	@ResponseBody
	public Map<String, String> getSalarySocialSecurityInfo(HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String userId = (String) request.getSession().getAttribute("userId");
		String cityName = request.getParameter("currentCity");
		String type = request.getParameter("currentType");
		retMap = companyApi.getSSSInfo(userId, cityName, type);
		logger.debug("finish getSalarySocialSecurityInfo, retMap : {}", retMap);
		return retMap;
	}
	
	@RequestMapping("/company/checkTemplateExist")
	@ResponseBody
	public Map<String, String> checkTemplateExist(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		String cityName = request.getParameter("currentCity");
		String type = request.getParameter("currentType");
		return companyApi.checkTemplateExist(userId, cityName, type);
	}
	
	@RequestMapping("/company/uploadSalary")
	public String uploadSalary(@RequestParam("files") MultipartFile file, HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/files/company");
		companyApi.uploadCompanySalary(userId, realPath, file);
		return "redirect:/views/webviews/company/salary_welfare_upload.html";
	}
	
	/*@RequestMapping("/company/uploadBankSta")
	@ResponseBody
	public Map<String, String> uploadBankSta(@RequestParam("files") MultipartFile file, HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/files/company");
		String date = request.getParameter("date");
		Map<String, String> params = new HashMap<>();
		params.put("userId", userId);
		params.put("realPath", realPath);
		params.put("date", date);
		return companyApi.uploadCompanyBankSta(file, params);
	}*/
	
	@RequestMapping("/company/uploadBankSta")
	@ResponseBody
	public String uploadBankSta(DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request) {
		logger.debug("===>uploadBankSta");
		String userId = (String) request.getSession().getAttribute("userId");
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/files/company");
		String date = request.getParameter("date");
		Map<String, String> params = new HashMap<>();
		params.put("userId", userId);
		params.put("realPath", realPath);
		params.put("date", date);
		if (multipartRequest != null) {
			Iterator<String> iterator = multipartRequest.getFileNames();
			while (iterator.hasNext()) {
				MultipartFile multifile = multipartRequest
						.getFile((String) iterator.next());
				companyApi.uploadCompanyBankSta(multifile, params);
			}
		}
		return new HashMap<String, String>().put("url", "");
	}
	
	@RequestMapping("/company/uploadCompanySales")
	@ResponseBody
	public String uploadCompanySales(DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request) {
		logger.debug("===>uploadBankSta");
		String userId = (String) request.getSession().getAttribute("userId");
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/files/company");
		Map<String, String> params = new HashMap<>();
		params.put("userId", userId);
		params.put("realPath", realPath);
		if (multipartRequest != null) {
			Iterator<String> iterator = multipartRequest.getFileNames();
			while (iterator.hasNext()) {
				MultipartFile multifile = multipartRequest
						.getFile((String) iterator.next());
				companyApi.uploadCompanySales(multifile, params);
			}
		}
		return new HashMap<String, String>().put("url", "");
	}
	
	@RequestMapping("/company/deleteCompanyBankSta")
	@ResponseBody
	public Map<String, String> deleteCompanyBankSta(HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String idList = (String)request.getParameter("deleteIdList");
		String realPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/files/company");
		if(companyApi.deleteCompanyBill(idList, realPath)) {
			retMap.put("error_code", "0");
		} else {
			retMap.put("error_code", "1");
		}
		return retMap;
	}
	
	@RequestMapping("/company/getAllCompanyBankSta")
	@ResponseBody
	public List<Map<String, String>> getAllCompanyBankSta(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		Map<String, String> params = new HashMap<>();
		params.put("conditionValue", request.getParameter("conditionValue"));
		params.put("conditionType", request.getParameter("conditionType"));
		params.put("userId", userId);
		return companyApi.getCompanyBankStaByCondition(params);
	}
	
	@RequestMapping("/company/getAllCompanySales")
	@ResponseBody
	public List<Map<String, String>> getAllCompanySales(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		Map<String, String> params = new HashMap<>();
		params.put("conditionValue", request.getParameter("conditionValue"));
		params.put("conditionType", request.getParameter("conditionType"));
		params.put("userId", userId);
		return companyApi.getCompanySalesByCondition(params);
	}
	
	@RequestMapping("/company/downloadSalarySocialSecurityTemplate")
	public ResponseEntity<byte[]> downloadSalarySocialSecurityTemplate(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		String cityName = request.getParameter("currentCity");
		String type = request.getParameter("currentType");
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/files/company");
		ResponseEntity<byte[]> re = companyApi.downloadSalarySocialSecurityTemplate(userId, cityName, type, realPath);
		return re;
	}
	
	@RequestMapping("/company/getOrgStructureData")
	@ResponseBody
	public List<CompanyOrgStructure> getOrgStructureData(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		return companyApi.getOrgStructureData(userId);
	}
	
	@RequestMapping("/company/getCompanyDetailInfo")
	@ResponseBody
	public Map<String, String> getCompanyDetailInfo(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		return companyApi.getCompanyDetailInfo(userId);
	}
	
	@RequestMapping("/company/getAllCostCenterEncode")
	@ResponseBody
	public List<Map<String, String>> getAllCostCenterEncode() {
		return companyApi.getAllCostCenterCode();
	}
	
	@RequestMapping("/company/getSalaryTemplateHistory")
	@ResponseBody
	public List<Map<String, String>> getSalaryTemplateHistory(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		long companyId = userService.getCompanyIdByUserId(userId);
		return companyApi.getSalaryTemplateHistory(companyId);
	}
	
	@RequestMapping("/company/getSalaryHistory")
	@ResponseBody
	public List<Map<String, String>> getSalaryHistory(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		long companyId = userService.getCompanyIdByUserId(userId);
		return companyApi.getSalaryHistory(companyId);
	}
	
	@RequestMapping("/company/deleteCompanySales")
	@ResponseBody
	public Map<String, String> deleteCompanySales(
			HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String idList = (String)request.getParameter("deleteIdList");
		String realPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/files/company");
		if(companyApi.deleteCompanySales(idList, realPath)) {
			retMap.put("error_code", "0");
		} else {
			retMap.put("error_code", "1");
		}
		return retMap;
	}
	
	@RequestMapping("/company/deleteCompanySalary")
	@ResponseBody
	public Map<String, String> deleteCompanySalary(
			HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String idList = (String)request.getParameter("deleteIdList");
		String realPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/files/company");
		if(companyApi.deleteCompanySalary(idList, realPath)) {
			retMap.put("error_code", "0");
		} else {
			retMap.put("error_code", "1");
		}
		return retMap;
	}
	
	@RequestMapping("/company/setTakeType")
	@ResponseBody
	public Map<String, String> setTakeType(
			HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String userId = (String) request.getSession().getAttribute("userId");
		long companyId = userService.getCompanyIdByUserId(userId);
		String takeType = (String)request.getParameter("type");
		if(companyApi.setTakeType(companyId, takeType)) {
			retMap.put("error_code", "0");
		} else {
			retMap.put("error_code", "1");
		}
		return retMap;
	}
}
