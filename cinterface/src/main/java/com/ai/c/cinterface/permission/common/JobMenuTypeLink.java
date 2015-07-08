package com.ai.c.cinterface.permission.common;

/**
 * 角色与菜单类型的对应关系
 * <li>菜单类别包括，管理者，供货商，合伙人，游客</li>
 * <li>每一类菜单的根菜单的func_id与菜单类别相一致，为MANAGE,SUPPLIER,PARTNER,VISITOR</li>
 * 
 * @author zhuwenkai
 *
 */
public enum JobMenuTypeLink {
	
	// 管理员类型角色-管理员类型菜单
	ADMIN("MANAGE"),
	// 供货商类型角色-供货商类型菜单
	SUPPLIER("SUPPLIER"),
	// 合伙人类型角色-合伙人类型菜单
	PARTNER("PARTNER"),
	// 游客类型角色-游客类型菜单
	VISITOR("VISITOR");
	
	private String menuType;
	
	public String getMenuType() {
		return menuType;
	}

	private JobMenuTypeLink(String menuType){
		this.menuType = menuType;
	}
	
}
