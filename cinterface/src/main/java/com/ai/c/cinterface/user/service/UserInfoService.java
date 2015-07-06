package com.ai.c.cinterface.user.service;

import java.util.Map;

import com.ai.c.cinterface.user.entity.UserInfo;
import com.ai.c.cinterface.user.entity.UserInfoStatusHistory;

public interface UserInfoService {

	/**
	 * 用户登录
	 * <pre>
	 * 登录后写入登录成功表或者登录失败表
	 * </pre>
	 * @param username
	 * @param password
	 * @param passwordType
	 * @param fromSysId 发起认证的来源平台
	 * @param loginInterfaceName 承担认证的接口名字
	 * @param loginInterfaceName 用户的ip
	 * @param loginSource 登录来源
	 * @return null,如果用户名密码不正确
	 */
	String login(String username, String password, String passwordType,
			String fromSysId, String loginInterfaceName,String ip,String loginSource);

	int updateUserInfo(UserInfo userInfo);
	
	/**
	 * 插入
	 * @param userInfo
	 * @return
	 */
	int insertUserInfo(UserInfo userInfo);

	/**
	 * 查询用户数量 
	 * @param username
	 * @return
	 */
	int countUsername(String userName);
	
	/**
	 * 插入状态变更表
	 * @param userInfoStatusHistory
	 * @return
	 */
	int insertUserInfoStatusHistory(UserInfoStatusHistory userInfoStatusHistory);

	UserInfo queryUserInfoByUserId(String userId);

	int updateUserStatus(String userId, String status);

	UserInfo queryUserInfoByUsernameAndPassword(String userName, String password);
	
	/**
	 * 获取短信验证码
	 * @param phoneNumber 接受短信的手机号
	 * @param ip 发起的ip
	 * @param sysId 系统id
	 * @param operationType 短信验证码的下发类型
	 * @return
	 */
	int getSMSValidCode(String phoneNumber, String ip, String sysId,String operationType);
	
	/**
	 * 找回密码
	 * @param userName:用户名
	 * @param password：密码
	 * @return
	 */
	int updatePassword(String userName,String password);
	/**
	 * 校验短信验证码,并且更改验证码的状态
	 * @param phoneNumber 手机号码
	 * @param vCode	验证码
	 * @return
	 */
	boolean validVCode(String phoneNumber, String vCode);
	/**
	 * 根据用户名查询用户信息
	 * @param userName
	 * @return
	 */
	UserInfo queryUserByUserName(String userName);

	/**
	 * 发送邮件
	 * @param userName
	 * @param ip
	 * @param sysId
	 * @param operationType
	 * @return
	 */
	int sendEmail(String userName, String ip, String sysId, String operationType) throws Exception;
	
	/**
	 * 更改密码
	 * @param username
	 * @param password
	 * @param newPassword
	 * @return
	 */
	int updateUserPassword(String username, String password, String newPassword);

	/**
	 * 手机端登录
	 * @param params
	 * @return
	 */
	String mobileLogin(Map<String, String> params);
}
