package com.ai.c.cinterface.permission.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ai.c.cinterface.permission.entity.AiJobRelation;

@Repository
public interface AiJobRelationMapper {

	/**
	 * 查询job_relation
	 * @param params
	 * @return
	 */
	public AiJobRelation getJobRelation(Map<String,String> params);
}
