package com.ai.c.cinterface.permission.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.c.cinterface.permission.dao.AiJobRelationMapper;
import com.ai.c.cinterface.permission.entity.AiJobRelation;
import com.ai.c.cinterface.permission.service.AiJobRelationService;

@Service
public class AiJobRelationServiceImpl implements AiJobRelationService {

	@Autowired
	private AiJobRelationMapper aiJobRelationMapper;
	
	@Override
	public AiJobRelation getJobRelation(Map<String, String> params) {
		return aiJobRelationMapper.getJobRelation(params);
	}

}
