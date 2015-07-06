package com.ai.c.cinterface.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录日志信息
 * 
 * @author wangpeng
 * @time 2014-11-21 下午3:03:40
 * 
 */

public final class UserLoginInfo implements Serializable{
	private static final long serialVersionUID = -5694559013486707134L;
	private String userId;
	private String userName;
	private Date createtime;
	private String ipaddress;
	private String fromSysId;
	private String authSysId;
	private String toSysId;
	private String interfaceName;
	private String userType;
	private String userAttribute;
	private String datasource;
	private String authstyle;
	private int resultCode;
	private String hostname;
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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
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

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	public String getAuthstyle() {
		return authstyle;
	}

	public void setAuthstyle(String authstyle) {
		this.authstyle = authstyle;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	public static UserLoginInfo fromUserInfo(UserInfo userInfo){
		UserLoginInfo userLoginInfo = new UserLoginInfo();
		userLoginInfo.setUserId(userInfo.getUserId());
		userLoginInfo.setUserName(userInfo.getUserName());
		userLoginInfo.setUserType(userInfo.getUserType());
		userLoginInfo.setUserAttribute(userInfo.getUserAttribute());
		userLoginInfo.setDatasource(userInfo.getDataSource());
		return userLoginInfo;
	}

}
