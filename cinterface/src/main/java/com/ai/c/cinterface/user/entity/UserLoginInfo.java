package com.ai.c.cinterface.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录日志信息
 * 
 * @author LIUQIANMING
 * @time 2014-11-21 下午3:03:40
 * 
 */

public final class UserLoginInfo implements Serializable{
	private static final long serialVersionUID = -5694559013486707134L;
	private String userId;
	private String userName;
	private Date createTime;
	private String ipAddress;
	private String fromSysId;
	private String authSysId;
	private String toSysId;
	private String interfaceName;
	private String userType;
	private String userAttribute;
	private String dataSource;
	private String authStyle;
	private int resultCode;
	private String hostName;
	private String loginSource;
	
	public String getLoginSource() {
		return loginSource;
	}

	public void setLoginSource(String loginSource) {
		this.loginSource = loginSource;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getFromSysId() {
		return fromSysId;
	}

	public void setFromSysId(String fromSysId) {
		this.fromSysId = fromSysId;
	}

	public String getAuthSysId() {
		return authSysId;
	}

	public void setAuthSysId(String authSysId) {
		this.authSysId = authSysId;
	}

	public String getToSysId() {
		return toSysId;
	}

	public void setToSysId(String toSysId) {
		this.toSysId = toSysId;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserAttribute() {
		return userAttribute;
	}

	public void setUserAttribute(String userAttribute) {
		this.userAttribute = userAttribute;
	}

	

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	
	
	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getAuthStyle() {
		return authStyle;
	}

	public void setAuthStyle(String authStyle) {
		this.authStyle = authStyle;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public static UserLoginInfo fromUserInfo(UserInfo userInfo){
		UserLoginInfo userLoginInfo = new UserLoginInfo();
		userLoginInfo.setUserId(userInfo.getUserId());
		userLoginInfo.setUserName(userInfo.getUserName());
		userLoginInfo.setUserType(userInfo.getUserType());
		userLoginInfo.setUserAttribute(userInfo.getUserAttribute());
		userLoginInfo.setDataSource(userInfo.getDataSource());
		return userLoginInfo;
	}

}
