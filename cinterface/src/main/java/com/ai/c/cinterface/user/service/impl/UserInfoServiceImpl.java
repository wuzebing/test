package com.ai.c.cinterface.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.transaction.TransactionException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ai.c.base.util.ConfigUtils;
import com.ai.c.base.util.HostInfo;
import com.ai.c.base.util.TokenUtil;
import com.ai.c.cinterface.par.service.ParSystemService;
import com.ai.c.cinterface.user.dao.UserInfoMapper;
import com.ai.c.cinterface.user.entity.UserInfo;
import com.ai.c.cinterface.user.entity.UserInfoStatusHistory;
import com.ai.c.cinterface.user.service.UserInfoService;
import com.ai.c.cinterface.user.service.UserLoginInfoService;
import com.ai.c.cinterface.user.status.UserStatusConst;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	private static Logger logger = Logger.getLogger(UserInfoServiceImpl.class);
	private final String validCodeFormatter = "%06d";
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private ParSystemService parSystemService;
	@Autowired
	private UserLoginInfoService userLoginInfoService;





	@Override
	public String login(String username, String password, String passwordType,
			String fromSysId, String loginInterfaceName, String ip,String loginSource) {
		// TODO:如果是一次性下发密码的处理

		// 固定密码的处理
		Map<String, String> queryInfo = new HashMap<String, String>();
		queryInfo.put("username", username);
		queryInfo.put("password", password);
		queryInfo.put("passwordType", passwordType);
		UserInfo userInfo = userInfoMapper.queryUserInfoByUsername(username);
	    //登录失败,用户不存在
		if(userInfo==null){
			userLoginInfoService.loginFail(username, password, ip, fromSysId,
				ConfigUtils.getStringValue("sys.serverSysId"),
				ConfigUtils.getStringValue("sys.serverSysId"),
				loginInterfaceName, 901, HostInfo.getHostName(),
				passwordType);
				return null;
		}else if (!userInfo.getUserPassword().equals(password)) {
			// 登录失败,密码错误
			userLoginInfoService.loginFail(userInfo, password, ip, fromSysId,
					ConfigUtils.getStringValue("sys.serverSysId"),
					ConfigUtils.getStringValue("sys.serverSysId"),
					loginInterfaceName, 902, HostInfo.getHostName(),
					passwordType);

			return null;
		}else{
			// 用户存在且密码正确
			// 检查用户状态
			if (UserStatusConst.FREEZE.getStatus().equals(userInfo.getStatusId())
					|| UserStatusConst.FORBIDDEN.getStatus().equals(
						userInfo.getStatusId())) {
					// 冻结状态和禁用状态不能登录
					userLoginInfoService.loginFail(userInfo, password, ip,
							fromSysId,ConfigUtils.getStringValue("sys.serverSysId"),
						    ConfigUtils.getStringValue("sys.serverSysId"),
						    loginInterfaceName, 903, HostInfo.getHostName(),
						    passwordType);
					throw new TransactionException("903");
			}
		}
		// 用户存在且密码正确
		// 检查用户状态
		String sysKey = parSystemService.getSysKeyBySysId(userInfo.getSysId());
		String token = null;
		String provinceId="";
		String areaId = "";
		String jobId = userInfo.getJobId();
		token = TokenUtil.generateToken(userInfo.getSysId(), sysKey,
					userInfo.getUserId(), username, userInfo.getUserType(),
					passwordType, userInfo.getUserAttribute(),
					userInfo.getStatusId(), provinceId, areaId, jobId);
		// 记录认证结果
		userLoginInfoService.loginSuccess(userInfo, ip, fromSysId,
					ConfigUtils.getStringValue("sys.serverSysId"),
					ConfigUtils.getStringValue("sys.serverSysId"),
					loginInterfaceName, 200, HostInfo.getHostName(),
					passwordType,loginSource);
		return token;
	}

	@Override
	public int updateUserInfo(UserInfo userInfo) {
		return userInfoMapper.updateUserInfo(userInfo);
	}


	@Override
	public int countUsername(String userName) {
		return userInfoMapper.queryUserNameCount(userName);
	}

	@Override
	public int insertUserInfoStatusHistory(
			UserInfoStatusHistory userInfoStatusHistory) {
		return userInfoMapper.insertUserInfoStatusHistory(userInfoStatusHistory);
	}

	@Override
	public UserInfo queryUserInfoByUserId(String userId) {
		return userInfoMapper.queryUserInfoByUserId(userId);
	}

	@Override
	public int updateUserStatus(String userId, String status) {
		return userInfoMapper.updateUserStatus(userId,status);
	}

	@Override
	public UserInfo queryUserInfoByUsernameAndPassword(String userName,
			String password) {
		// TODO:根据密码类型进行区分
		UserInfo userInfo = userInfoMapper.queryUserInfoByUsername(userName);
		if (userInfo == null) {
			return null;
		} else if (!userInfo.getUserPassword().equals(password)) {
			return null;
		} else {
			return userInfo;
		}
	}
	
	

	@Override
	public int updatePassword(String userName, String password) {
		return userInfoMapper.updatePassword(userName,password);
	}



	@Override
	public UserInfo queryUserByUserName(String userName) {
		return userInfoMapper.queryUserInfoByUsername(userName);
	}

	

	@Override
	public int updateUserPassword(String username, String password,
			String newPassword) {
		UserInfo userInfo = userInfoMapper.queryUserInfoByUsername(username);
		if (userInfo == null) {
			return 901;// 用户名不存在
		}
		if (!userInfo.getUserPassword().equals(password)) {
			return 902;// 用户密码不正确
		}
		int result = userInfoMapper.updatePassword(username, newPassword);
		if(result >0)
			return 200;
		else
			return 999;
	}

	@Override
	public int insertUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSMSValidCode(String phoneNumber, String ip, String sysId,
			String operationType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean validVCode(String phoneNumber, String vCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int sendEmail(String userName, String ip, String sysId,
			String operationType) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String mobileLogin(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
