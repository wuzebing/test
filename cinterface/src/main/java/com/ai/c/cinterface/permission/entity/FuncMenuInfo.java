package com.ai.c.cinterface.permission.entity;

import java.io.Serializable;

/**
 * 功能菜单类（对应于当前菜单模型中最底层页面菜单）
 * <li>数据来源于ai_menu表</li>
 * <li>该类是按照以前的菜单模型规范建立的,为了不改变前后台的数据交互，
 * 	继续使用该类表示需要显示的功能菜单</li>
 *  
 * @author zhuwenkai
 *
 */
public class FuncMenuInfo implements Serializable {


	private static final long serialVersionUID = 7242449297997197755L;

	private String id;
	
	private String name;
	
	private String url;
	
	private String isDefault;	// 默认是否访问该菜单

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	
}
