package com.ai.c.cinterface.par.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.c.cinterface.par.dao.ParSystemMapper;
import com.ai.c.cinterface.par.service.ParSystemService;


/**
 * @author LIUQIANMING
 * @time 2015-06-22 下午5:08:03
 * 
 */
@Service
public class ParSystemServiceImpl implements ParSystemService{

	@Autowired
	private ParSystemMapper parSystemMapper;
	
	@Override
	public String getSysKeyBySysId(String sysId) {
		
		return parSystemMapper.getSysKeyBySysId(sysId);
	}

}
