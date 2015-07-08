package com.ai.c.cinterface.permission.service;

import java.util.List;

import com.ai.c.cinterface.permission.entity.AiMenu;

public interface AiMenuService {
	
	/**
	 * 查询功能菜单列表
	 * 
	 * @param aiMenu AiMenu实体类，包含查询条件
	 * @return
	 */
	public List<AiMenu> queryAiMenuList(AiMenu aiMenu);
	
	/**
	 * 查询单个菜单
	 * 
	 * @param funcId
	 * @return
	 */
	public AiMenu queryAiMenuById(String funcId);
	
	/**
	 * 增加功能菜单
	 * 
	 * @param aiMenu 功能菜单实体类
	 * @return
	 */
	public int insertAiMenu(AiMenu aiMenu);
	
	/**
	 * 更新功能菜单
	 * 
	 * @param aiMenu 功能菜单实体类
	 * @return
	 */
	public int updateAiMenu(AiMenu aiMenu);
	
	/**
	 * 删除功能菜单
	 * <li>有子菜单的菜单不能被删除</li>
	 * <li>删除全部该菜单与角色的对应关系</li>
	 * 
	 * @param funcId
	 * @return	
	 * 0  删除成功；
	 * 1 删除失败，该菜单下有子菜单
	 */
	public int deleteAiMenu(String funcId);
	
	/**
	 * 禁用功能菜单
	 * 
	 * @param funcId
	 * @return
	 */
	public boolean diableAiMenu(String funcId);
	
	/**
	 * 解禁功能菜单
	 * 
	 * @param funcId
	 * @return
	 */
	public boolean enableAimenu(String funcId);
	
}
