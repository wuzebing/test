package com.ai.c.cinterface.par.service;
/**
 * @author zouning
 * @time 2014-7-31 下午5:07:29
 * 
 */

public interface ParSystemService {
	/**
	 * 通过系统的id获取系统的密钥
	 * @param sysId
	 * @return
	 */
	String getSysKeyBySysId(String sysId);
}
