package com.ai.c.cinterface.permission.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ai.c.cinterface.permission.common.JobMenuTypeLink;
import com.ai.c.cinterface.permission.dao.AiJobFuncMapper;
import com.ai.c.cinterface.permission.entity.AiJobFunc;
import com.ai.c.cinterface.permission.entity.AiJob;
import com.ai.c.cinterface.permission.entity.JobMenuInfo;
import com.ai.c.cinterface.permission.service.AiJobFuncService;
import com.ai.c.cinterface.permission.service.AiJobService;
import com.enterprisedt.util.debug.Logger;

@Service
public class AiJobFuncServiceImpl implements AiJobFuncService {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private AiJobFuncMapper aiFuncMapper;
	
	@Autowired
	private AiJobService aiJobService;

	@Override
	public List<JobMenuInfo> queryAllAndAssignedFuncs(String jobId) {
		
		// 查询角色所属类别
		AiJob aiJob = aiJobService.queryAiJobByJobId(jobId);
		if(aiJob == null){
			return null;
		}
		String jobType = JobMenuTypeLink.valueOf(aiJob.getJobType()).getMenuType();
		
		return aiFuncMapper.queryAllAndAssignedFuncs(jobId, jobType);
	}

	@Override
	public boolean deleteAiJobFuncByFunc(String funcId) {
		
		return aiFuncMapper.deleteAiJobFuncByFunc(funcId);
	}

	@Override
	public boolean deleteAiJobFuncByJob(String jobId) {
		
		return aiFuncMapper.deleteAiJobFuncByJob(jobId);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean saveAiFuncs(List<AiJobFunc> list, String jobId) {
		
		// 全部删除与jobId角色相关的角色菜单关联关系
		aiFuncMapper.deleteAiJobFuncByJob(jobId);
		
		// 保存角色菜单关联关系
		for(AiJobFunc func:list){
			aiFuncMapper.insertAiJobFunc(func);
		}
		return true;
	}

}
