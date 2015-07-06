package com.ai.c.cinterface.user.entity;

import java.io.Serializable;
import java.util.Date;

public class UserInfoStatusHistory implements Serializable{

	private static final long serialVersionUID = -8211332992672540983L;
	private String userId;
	private String userName;
	private String userStatusBefore;
	private String userStatusNow;
	private String ipAddress;
	private String sysId;
	private String operatorId;
	private String operatorName;
	private String dataSource;
	private String remark;
	private Date createTime;
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
	public String getUserStatusBefore() {
		return userStatusBefore;
	}
	public void setUserStatusBefore(String userStatusBefore) {
		this.userStatusBefore = userStatusBefore;
	}
	public String getUserStatusNow() {
		return userStatusNow;
	}
	public void setUserStatusNow(String userStatusNow) {
		this.userStatusNow = userStatusNow;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
