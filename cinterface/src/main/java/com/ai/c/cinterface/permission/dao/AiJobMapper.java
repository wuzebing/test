package com.ai.c.cinterface.permission.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ai.c.cinterface.permission.entity.AiJob;

/**
 * 用户角色映射类
 * 
 * @author zhuwenkai
 *
 */
@Repository
public interface AiJobMapper {
	
	/**
	 * 查询用户角色
	 * 
	 * @param params 包含（查询条件，分页参数，排序方式）
	 * @return
	 */
	public List<AiJob> queryAiJobsListPage(Map<String,Object> params);
	
	/**
	 * 查询某条角色记录
	 * 
	 * @param jobId
	 * @return
	 */
	public AiJob queryAiJobByJobId(String jobId);
	
	/**
	 * 查询数据库中的最大job_id
	 * 
	 * @return
	 */
	@Select("select max(job_id) from ai_job")
	public int queryMaxJobId();
	
	/**
	 * 添加用户角色
	 * 
	 * @param aiJob 新增的角色实体对象
	 * @return
	 */
	public int insertAiJob(AiJob aiJob);
	
	/**
	 * 更新某一用户角色
	 * <li>只更新aiJob实体中属性值不为null的的属性</li>
	 * 
	 * @param aiJob
	 * @return
	 */
	public int updateAiJob(AiJob aiJob);
}
