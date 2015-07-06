package com.ai.c.cinterface.user.dao;

import org.springframework.stereotype.Repository;

import com.ai.c.cinterface.user.entity.UserLoginInfo;

/**
 * 用户登录日志
 * @author wangpeng
 *
 */
@Repository
public interface UserLoginInfoMapper {

	/**
	 * 认证成功
	 * @param userLoginInfo
	 * @return
	 */
	int loginSuccess(UserLoginInfo userLoginInfo);

	/**
	 * 认证失败
	 * @param userLoginInfo
	 * @return
	 */
	int loginFail(UserLoginInfo userLoginInfo);
	
}
