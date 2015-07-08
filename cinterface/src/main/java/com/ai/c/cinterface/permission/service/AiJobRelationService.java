package com.ai.c.cinterface.permission.service;

import java.util.Map;

import com.ai.c.cinterface.permission.entity.AiJobRelation;

public interface AiJobRelationService {

	/**
	 * 查询job_relation
	 * @param params
	 * @return
	 */
	public AiJobRelation getJobRelation(Map<String,String> params);
}
