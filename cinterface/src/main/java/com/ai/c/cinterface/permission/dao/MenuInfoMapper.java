package com.ai.c.cinterface.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ai.c.cinterface.permission.entity.FolderMenuInfo;
import com.ai.c.cinterface.permission.entity.FuncMenuInfo;
import com.ai.c.cinterface.permission.entity.LowerFolderAndFunctionMenuInfo;


@Repository
public interface MenuInfoMapper {
	
	/**
	 * 查询头部一级菜单
	 * 
	 * @param level
	 * @param role
	 * @return
	 */
	public List<FolderMenuInfo> queryHeaderMenuInfo(@Param("parentId")String parentId, @Param("role")String role);
	
	/**
	 * 查询某一菜单下的所有页面功能菜单
	 * 
	 * @param folderId 上级菜单ID	
	 * @return	页面菜单列表
	 */
	public List<FuncMenuInfo> queryFuncMenuInfo(String folderId);
	
	/**
	 * 查询用户有权限访问的所有的左侧二级菜单和相应的下级菜单
	 * 
	 * @param upperFolderId 上级菜单ID
	 * @param role	用户所属角色
	 * @return	菜单列表对象
	 */
	public List<LowerFolderAndFunctionMenuInfo> queryLowerFolderAndFuncMenuInfo(
			@Param("parentId")String upperFolderId, @Param("role")String role);
	
	/**
	 * 查询用户有权限访问的所有的菜单url
	 * 
	 * @param role 用户所属的角色
	 * @return	菜单url列表
	 */
	public List<String> queryMenusbyRole(String role);

}
