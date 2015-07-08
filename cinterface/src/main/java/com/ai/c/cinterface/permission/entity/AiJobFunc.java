package com.ai.c.cinterface.permission.entity;

/**
 * 角色菜单关联类
 * <li>对应于ai_job_func表</li>
 * 
 * @author zhuwenkai
 *
 */
public class AiJobFunc {

	private String funcId;
	
	private String jobId;
	
	private String menuOrder;
	
	private String adminId;
	
	private String adminName;
	
	private String adminTime;

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
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
	
	
}
