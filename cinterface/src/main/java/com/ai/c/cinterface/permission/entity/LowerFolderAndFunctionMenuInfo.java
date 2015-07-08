package com.ai.c.cinterface.permission.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 二级菜单以及相应的下一级功能菜单
 * <li>数据来于ai_menu表</li>
 * <li>用于表示二三级菜单的树形结构数据</li>
 * <li>为了前台的显示方便，二三级菜单是同时查询出来的</li>
 * 
 * @author zhuwenkai
 *
 */
public class LowerFolderAndFunctionMenuInfo implements Serializable {
	
	private static final long serialVersionUID = 8803757354177267431L;

	private String id;
	
	private String name;
	
	private String url;
	
	private String isDefault;	// 默认是否访问该菜单
	
	private List<FuncMenuInfo> childMenuInfos;

	public List<FuncMenuInfo> getChildMenuInfos() {
		return childMenuInfos;
	}

	public void setChildMenuInfos(List<FuncMenuInfo> childMenuInfos) {
		this.childMenuInfos = childMenuInfos;
	}

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
