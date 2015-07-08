package com.ai.c.cinterface.user.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.c.cinterface.user.dao.UserLoginInfoMapper;
import com.ai.c.cinterface.user.entity.UserInfo;
import com.ai.c.cinterface.user.entity.UserLoginInfo;
import com.ai.c.cinterface.user.service.UserLoginInfoService;

@Service
public class UserLoginInfoServiceImpl implements UserLoginInfoService {

	@Autowired
	private UserLoginInfoMapper userLoginInfoMapper;
	@Override
	public int loginSuccess(UserInfo userInfo, String ip, String fromSysId,
			String authSysId, String ToSysId, String loginInterfaceName,
			int loginResultCode, String hostName, String authstyle,String loginSource) {
		UserLoginInfo userLoginInfo = UserLoginInfo.fromUserInfo(userInfo);
		userLoginInfo.setIpAddress(ip);
		userLoginInfo.setFromSysId(fromSysId);
		userLoginInfo.setToSysId(ToSysId);
		userLoginInfo.setAuthSysId(authSysId);
		userLoginInfo.setInterfaceName(loginInterfaceName);
		userLoginInfo.setResultCode(loginResultCode);
		userLoginInfo.setHostName(hostName);
		userLoginInfo.setAuthStyle(authstyle);
		userLoginInfo.setCreateTime(new Date());
		userLoginInfo.setLoginSource(loginSource);
		return userLoginInfoMapper.loginSuccess(userLoginInfo);
	}

	@Override
	public int loginFail(String username, String password, String ip,
			String fromSysId, String authSysId, String ToSysId,
			String loginInterfaceName, int loginResultCode, String hostName,
			String authstyle) {
		UserLoginInfo userLoginInfo = new UserLoginInfo();
		userLoginInfo.setUserId("");
		userLoginInfo.setUserName(username);
		userLoginInfo.setUserType("");
		userLoginInfo.setUserAttribute("");
		userLoginInfo.setDataSource("");

		userLoginInfo.setIpAddress(ip);
		userLoginInfo.setFromSysId(fromSysId);
		userLoginInfo.setToSysId(ToSysId);
		userLoginInfo.setAuthSysId(authSysId);
		userLoginInfo.setInterfaceName(loginInterfaceName);
		userLoginInfo.setResultCode(loginResultCode);
		userLoginInfo.setHostName(hostName);
		userLoginInfo.setAuthStyle(authstyle);
		userLoginInfo.setCreateTime(new Date());

		return userLoginInfoMapper.loginFail(userLoginInfo);
	}

	@Override
	public int loginFail(UserInfo userInfo, String password, String ip,
			String fromSysId, String authSysId, String ToSysId,
			String loginInterfaceName,int resultCode, String hostName, String authstyle) {
		UserLoginInfo userLoginInfo = UserLoginInfo.fromUserInfo(userInfo);
		userLoginInfo.setIpAddress(ip);
		userLoginInfo.setFromSysId(fromSysId);
		userLoginInfo.setToSysId(ToSysId);
		userLoginInfo.setAuthSysId(authSysId);
		userLoginInfo.setInterfaceName(loginInterfaceName);
		userLoginInfo.setResultCode(resultCode);//密码错误
		userLoginInfo.setHostName(hostName);
		userLoginInfo.setAuthStyle(authstyle);
		userLoginInfo.setCreateTime(new Date());

		return userLoginInfoMapper.loginFail(userLoginInfo);

	}

}
