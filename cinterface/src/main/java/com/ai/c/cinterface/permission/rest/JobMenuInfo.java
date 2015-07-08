package com.ai.c.cinterface.permission.rest;

/**
 * 角色菜单信息类
 * <li>信息来自于，ai_func,ai_menu两个表</li>
 * 
 * @author zhuwenkai
 *
 */
public class JobMenuInfo {
	
	private String jobId;
	
	private String funcId;
	
	private String name;	// 菜单名称
	
	private String parentId;
	
	private Boolean isAssign;	// 是否已分配

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Boolean getIsAssign() {
		return isAssign;
	}

	public void setIsAssign(Boolean isAssign) {
		this.isAssign = isAssign;
	}

	
}
