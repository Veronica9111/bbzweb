package com.wisdom.user.dao;

import java.util.List;

import com.wisdom.common.model.UserDept;

public interface IUserDeptDao {

	public UserDept getUserDeptByUserId(String userId);
	
	public List<UserDept> getUserDeptListByDeptId(long deptId);
	
	public List<UserDept> getUserDeptListByDeptIdAndUserId(long deptId, String userId);
	
	public List<UserDept> getUserDeptListByStatusAndDeptId(int status, long deptId);
	
	public long getUserNumByDeptId(long deptId);
	
	public boolean addUserDeptRecord(UserDept userDept);
	
	public int delUserByDeptId(long deptId);
	
	public boolean delUserDept(String userId,long deptId);
	
	public boolean updateUserDeptStatus(String userId,int status);
		
}