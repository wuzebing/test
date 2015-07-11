/*
 * 文 件 名:  UserInfo.java
 * 版    权:   Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:   LIUQIANMING
 * 修改时间:  2015-07-11 
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ai.c.cinterface.user.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户信息业务对象
 * 
 * @author  LIUQIANMING
 * @version  [版本号, 2015-07-11]
 * @see  [相关类/方法]
 * @since  [用户/模块版本]
 */
public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = -6647777357399379451L;
	/**用户ID*/
	private String userId;
	/**用户登录名*/
	private String userName;
	/**用户密码*/
	private String userPassword;
	/**用户类型*/
	private String userType;
	/**用户密码类型*/
	private String pwdType;
	/**用户属性*/
	private String userAttribute;
	/**用户状态*/
	private String statusId;
	/**用户归属系统ID*/
	private String sysId;
	/**用户来源*/
	private String dataSource;
	private String monthTime;
	private String yearTime;
	private String monthCount;
	/**用户创建时间*/
	private Date createTime;
	/**用户更新时间*/
	private Date updateTime;
	/**用户角色ID*/
	private String jobId;
	
	
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
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
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPwdType() {
		return pwdType;
	}
	public void setPwdType(String pwdType) {
		this.pwdType = pwdType;
	}
	public String getUserAttribute() {
		return userAttribute;
	}
	public void setUserAttribute(String userAttribute) {
		this.userAttribute = userAttribute;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getMonthTime() {
		return monthTime;
	}
	public void setMonthTime(String monthTime) {
		this.monthTime = monthTime;
	}
	public String getYearTime() {
		return yearTime;
	}
	public void setYearTime(String yearTime) {
		this.yearTime = yearTime;
	}
	public String getMonthCount() {
		return monthCount;
	}
	public void setMonthCount(String monthCount) {
		this.monthCount = monthCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
