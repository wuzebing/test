package com.ai.c.cinterface.permission.service;


import java.util.Map;

import com.ai.c.base.dao.Page;
import com.ai.c.cinterface.permission.entity.AiJob;

public interface AiJobService {

	/**
	 * 查询所有的用户角色
	 * <li>支持分页查询</li>
	 * 
	 * @param pageSize 分页大小
	 * @param pageNo 当前页数
	 * 
	 * @return
	 */
	public Page<AiJob> queryAiJobs(Map<String, Object> params, 
			int pageNo, int pageSize);
	
	/**
	 * 查询某个角色记录
	 * 
	 * @param jobId
	 * @return
	 */
	public AiJob queryAiJobByJobId(String jobId);
	
	/**
	 * 新增用户角色
	 * 
	 * @param aiJob 新增的角色实体对象
	 * @return
	 */
	public int insertAiJob(AiJob aiJob);
	
	
	/**
	 * 修改某一用户角色
	 * 
	 * @param aiJob 角色信息实体
	 * @return
	 */
	public int updateAiJob(AiJob aiJob);
}
