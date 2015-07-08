package com.ai.c.cinterface.permission.service;

import java.util.List;

import com.ai.c.cinterface.permission.entity.AiJobFunc;
import com.ai.c.cinterface.permission.entity.JobMenuInfo;

public interface AiJobFuncService {
	
	/**
	 * 查询某一角色与菜单的所有关联记录
	 * <li>该功能用于查询该角色可分配的菜单以及可分配菜单哪些已经分配</li>
	 * 
	 * @param jobId
	 * @return
	 */
	public List<JobMenuInfo> queryAllAndAssignedFuncs(String jobId);
	
	/**
	 * 批量删除该菜单对应的角色菜单关联记录
	 * 
	 * @param funcId 菜单功能ID
	 * @return
	 */
	public boolean deleteAiJobFuncByFunc(String funcId);
	
	/**
	 * 批量删除该角色对应的角色菜单关联记录
	 * 
	 * @param jobId
	 * @return
	 */
	public boolean deleteAiJobFuncByJob(String jobId);
	
	/**
	 * 保存角色菜单关联关系
	 * <li>目前采取的策略是先全部删除该角色的角色菜单关联关系，
	 * 然后把list对象中所有的AiFunc角色菜单关联实体进行保存</li>
	 * @TODO 保存策略后续可能需要进行优化
	 * 
	 * @param aifunc
	 * @param jobId
	 * @return
	 */
	public boolean saveAiFuncs(List<AiJobFunc> list, String jobId);
	

}
