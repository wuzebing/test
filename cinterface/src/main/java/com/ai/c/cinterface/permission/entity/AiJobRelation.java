package com.ai.c.cinterface.permission.entity;

import java.io.Serializable;

public class AiJobRelation implements Serializable {
	
	private static final long serialVersionUID = 6076354937459299912L;

	private String jobId;
	private String jobName;
	private String childJobId;
	private String childJobName;
	private String jobType;
	private String remark;
	private String adminId;
	private String adminName;
	private String adminTime;
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getChildJobId() {
		return childJobId;
	}
	public void setChildJobId(String childJobId) {
		this.childJobId = childJobId;
	}
	public String getChildJobName() {
		return childJobName;
	}
	public void setChildJobName(String childJobName) {
		this.childJobName = childJobName;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminTime() {
		return adminTime;
	}
	public void setAdminTime(String adminTime) {
		this.adminTime = adminTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
