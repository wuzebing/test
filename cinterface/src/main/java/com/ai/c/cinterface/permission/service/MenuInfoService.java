package com.ai.c.cinterface.permission.service;

import java.util.List;

import com.ai.c.cinterface.permission.entity.FolderMenuInfo;
import com.ai.c.cinterface.permission.entity.FuncMenuInfo;
import com.ai.c.cinterface.permission.entity.LowerFolderAndFunctionMenuInfo;

public interface MenuInfoService {
	
	/**
	 * 查询用户可以访问的相应主菜单（一级菜单）
	 * @param role	用户所属的角色
	 * @return	菜单信息列表
	 */
	public List<FolderMenuInfo> queryHeaderMenuInfo(String role);
	
	/**
	 * 查询用户可以访问的二级菜单及功能菜单信息
	 * @param upperFolderID	父及菜单的ID
	 * @param role	用户所属的角色
	 * @return	菜单信息列表
	 */
	public List<LowerFolderAndFunctionMenuInfo> queryLowerFolderAndFuncMenuInfo(String upperFolderId, 
			String role);
	
	/**
	 * 查询用户可以访问的所有功能菜单url
	 * 
	 * @param role	用户角色
	 * @return	菜单url列表
	 */
	public List<String> queryMenusbyRole(String role);
	
}
