package com.ai.c.cinterface.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ai.c.cinterface.permission.entity.AiJobFunc;
import com.ai.c.cinterface.permission.entity.JobMenuInfo;

/**
 * 角色菜单映射操作接口
 * 
 * @author zhuwenkai
 *
 */
@Repository
public interface AiJobFuncMapper {
	
	/**
	 * 查询某个角色可以分配的菜单以及菜单是否已分配
	 * 
	 * @param roleId 用户所属的角色
	 * @return
	 */
	public List<JobMenuInfo> queryAllAndAssignedFuncs(@Param("jobId")String jobId, 
			@Param("jobType")String jobType);
	
	/**
	 * 增加角色菜单关联关系
	 * 
	 * @param aiJobFunc 角色菜单关联实体
	 * @return
	 */
	public int insertAiJobFunc(AiJobFunc aiJobFunc);
	
	/**
	 * 批量删除该菜单对应的角色菜单关联记录
	 * 
	 * @param funcId
	 * @return
	 */
	public boolean deleteAiJobFuncByFunc(String funcId);
	
	/**
	 * 批量删除该角色对应的角色菜单关联记录
	 * 
	 * @param aiJobFunc
	 * @return
	 */
	public boolean deleteAiJobFuncByJob(String jobId);
	
}
