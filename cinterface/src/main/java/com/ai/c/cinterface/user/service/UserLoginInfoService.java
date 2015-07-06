package com.ai.c.cinterface.user.service;

import com.ai.c.cinterface.user.entity.UserInfo;
/**
 * 
 * @author LIUQIANMING
 *
 */
public interface UserLoginInfoService {

	/**
	 * 认证成功
	 * 
	 * @param userInfo
	 *            用户信息
	 * @param ip
	 *            发起认证的ip
	 * @param fromSysId
	 *            认证的来源平台id
	 * @param authSysId
	 *            承担认证的平台id
	 * @param ToSysId
	 *            认证目标平台
	 * @param loginInterfaceName
	 *            承担认证的接口名
	 * @param loginResultCode
	 *            认证结果
	 * @param hostName
	 *            认证所在主机名称
	 * @param authstyle
	 *            认证类型:41用户密码,42一次性短信密码,45 md5hash
	 * @param loginSource
	 *            登录来源:01 pc,02手机,03 掌厅
	 * @return
	 */
	int loginSuccess(UserInfo userInfo, String ip, String fromSysId,
			String authSysId, String ToSysId, String loginInterfaceName,
			int loginResultCode, String hostName, String authstyle,String loginSource);

	/**
	 * 认证失败
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            用户密码
	 * @param ip
	 *            发起认证的ip
	 * @param fromSysId
	 *            认证的来源平台id
	 * @param authSysId
	 *            承担认证的平台id
	 * @param ToSysId
	 *            认证目标平台
	 * @param loginInterfaceName
	 *            承担认证的接口名
	 * @param loginResultCode
	 *            认证结果
	 * @param hostName
	 *            认证所在主机名称
	 * @param authstyle
	 *            认证类型:41用户密码,42一次性短信密码,45 md5hash
	 * @return
	 */
	int loginFail(String username, String password, String ip,
			String fromSysId, String authSysId, String ToSysId,
			String loginInterfaceName, int loginResultCode, String hostName,
			String authstyle);

	int loginFail(UserInfo userInfo, String password, String ip,
			String fromSysId, String authSysId, String ToSysId,
			String loginInterfaceName, int resultCode, String hostName,
			String authstyle);
}
