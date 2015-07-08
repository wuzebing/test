package com.ai.c.cinterface.permission.entity;

import java.io.Serializable;

/**
 * 用户角色实体类
 * <li>信息来自于ai_job表</li>
 * 
 * @author zhuwenkai
 *
 */
public class AiJob implements Serializable {
	
	private String jobId;
	
	private String jobName;
	
	private String remark;
	
	private String adminId;
	
	private String adminName;
	
	private String adminTime;
	
	private String jobType;	// 角色类型

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

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	

}
