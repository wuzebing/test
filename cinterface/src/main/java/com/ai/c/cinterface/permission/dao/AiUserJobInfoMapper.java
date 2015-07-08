package com.ai.c.cinterface.permission.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ai.c.cinterface.admin.entity.AdminInfo;
import com.ai.c.cinterface.permission.entity.AiUserJobInfo;

@Repository
public interface AiUserJobInfoMapper {
	
	/**
	 * 查询某个用户的角色
	 * 
	 * @param userId
	 * @return
	 */
	public AiUserJobInfo queryJobInfoByUser(String userId);
	
	/**
	 * 查询某个角色所拥有的用户
	 * 
	 * @param jobId
	 * @return
	 */
	public List<AiUserJobInfo> queryJobInfoByJob(String jobId);
	
	/**
	 * 查询某一角色相关联的管理员的信息
	 * 
	 * @param jobId
	 * @return
	 */
	public List<AdminInfo> queryAdminInfoByJob(String jobId);
	
	/**
	 * 查询用户角色相关信息列表
	 * 
	 * @param userJobInfo
	 * @return
	 */
	List<AiUserJobInfo> queryUserJobInfoListPage(Map<String,Object> params);
	
	/**
	 * 保存用户角色关联信息
	 * @param userRole
	 * @return true 保存成功；false 保存失败
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
