package com.ai.c.cinterface.permission.service;

import java.util.List;
import java.util.Map;

import com.ai.c.base.dao.Page;
import com.ai.c.cinterface.admin.entity.AdminInfo;
import com.ai.c.cinterface.permission.entity.AiUserJobInfo;

public interface AiUserJobInfoService {
	
	/**
	 * 查询某个用户的角色
	 * 
	 * @param userId
	 * @return
	 */
	public AiUserJobInfo queryJobInfoByUser(String userId);
	
	/**
	 * 查询某个角色拥有的用户
	 * 
	 * @param jobId
	 * @return
	 */
	public List<AiUserJobInfo> queryJobInfoByJob(String jobId);
	
	/**
	 * 根据角色查询管理员的信息
	 * 
	 * @param jobId
	 * @return
	 */
	public List<AdminInfo> queryAdminInfoByJob(String jobId);
	
	/**
	 * 用户角色信息列表查询接口
	 * 
	 * @param userJobInfo
	 * @return
	 */
	Page<AiUserJobInfo> queryUserJobInfoListPage(Map<String, Object> params, 
			int pageNo, int pageSize);
	
	/**
	 * 保存用户角色的关联信息
	 * 
	 * @param userRole
	 * @return
	 */
	public boolean saveAiUserJobInfo(AiUserJobInfo userRole);
	
	/**
	 * 修改用户角色的关联信息
	 * 
	 * @param userRole
	 * @return
	 */
	public boolean updateAiUserJobInfo(AiUserJobInfo userRole);
	
}
