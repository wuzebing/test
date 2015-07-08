package com.ai.c.cinterface.permission.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ai.c.base.dao.Page;
import com.ai.c.cinterface.permission.dao.AiJobMapper;
import com.ai.c.cinterface.permission.entity.AiJob;
import com.ai.c.cinterface.permission.service.AiJobService;

@Service
public class AiJobServiceImpl implements AiJobService{
	
	@Autowired
	private AiJobMapper mapper;

	@Override
	public Page<AiJob> queryAiJobs(Map<String, Object> params, 
			int pageNo, int pageSize) {
		
		Page<AiJob> result = new Page<AiJob>(pageNo, pageSize);
		params.put(Page.PAGE, result);	//设置分页
		params.put(Page.DESC, "DESC");	//设置降序排序
		
		result.setResult(mapper.queryAiJobsListPage(params));	//查询参数为空，查询全部
		return result;
	}
	
	@Override
	public AiJob queryAiJobByJobId(String jobId){
		
		return mapper.queryAiJobByJobId(jobId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int insertAiJob(AiJob aiJob) {
		
		// 生成job_id,取数据库中的最大job_id，然后加1
		int max_jobId = mapper.queryMaxJobId();
		aiJob.setJobId(""+(max_jobId+1));
		
		return mapper.insertAiJob(aiJob);
	}


	@Override
	public int updateAiJob(AiJob aiJob) {
		return mapper.updateAiJob(aiJob);
	}
	
	public void setMapper(AiJobMapper mapper) {
		this.mapper = mapper;
	}


}
