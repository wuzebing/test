package com.ai.c.cinterface.par.dao;

import org.springframework.stereotype.Repository;


/**
 * @author LIUQIANMING
 * @time 2015-06-22 下午5:11:07
 * 
 */
@Repository
public interface ParSystemMapper {

	String getSysKeyBySysId(String sysId);

}
