package com.ai.c.cinterface.permission.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ai.c.base.dao.Page;
import com.ai.c.cinterface.admin.entity.AdminInfo;
import com.ai.c.cinterface.permission.dao.AiUserJobInfoMapper;
import com.ai.c.cinterface.permission.entity.AiUserJobInfo;
import com.ai.c.cinterface.permission.service.AiUserJobInfoService;
import com.enterprisedt.util.debug.Logger;

import freemarker.template.utility.StringUtil;

@Service
public class AiUserJobInfoServiceImpl implements
		AiUserJobInfoService {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private AiUserJobInfoMapper mapper;
	

	@Override
	public boolean saveAiUserJobInfo(AiUserJobInfo userRole) {

		return mapper.saveAiUserJobInfo(userRole);
	}

	@Override
	public AiUserJobInfo queryJobInfoByUser(String userId) {
		
		return mapper.queryJobInfoByUser(userId);
	}

	@Override
	public boolean updateAiUserJobInfo(AiUserJobInfo userRole) {
		
		return mapper.updateAiUserJobInfo(userRole);
	}
	
	@Override
	public Page<AiUserJobInfo> queryUserJobInfoListPage(Map<String, Object> params, 
			int pageNo, int pageSize) {
		
		// 设置分页
		Page<AiUserJobInfo> result = new Page<AiUserJobInfo>(pageNo, pageSize);
		params.put(Page.PAGE, result); 
		
		result.setResult(mapper.queryUserJobInfoListPage(params));
		
		return result;
	}

	@Override
	public List<AiUserJobInfo> queryJobInfoByJob(String jobId) {
		return this.mapper.queryJobInfoByJob(jobId);
	}

	@Override
	public List<AdminInfo> queryAdminInfoByJob(String jobId) {

		return this.mapper.queryAdminInfoByJob(jobId);
	}

}
