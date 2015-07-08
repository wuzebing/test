package com.ai.c.cinterface.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ai.c.cinterface.permission.entity.AiMenu;


@Repository
public interface AiMenuMapper {
	
	/**
	 * 查询功能菜单列表
	 * <li>aiMemu实体类中非空的属性作为查询条件</li>
	 * 
	 * @param parentId 父及功能菜单ID
	 * @return
	 */
	public List<AiMenu> queryAiMenus(AiMenu aiMenu);
	
	/**
	 * 查询某个菜单下子菜单的个数
	 * 
	 * @param parentId
	 * @return
	 */
	@Select("select count(*) from ai_menu where parent_id=#{parentId}")
	public int queryChildMenuCount(String parentId);
	
	/**
	 * 查询子菜单中最大的menuOrder
	 * 
	 * @param parentId
	 * @return
	 */
	@Select("select IFNULL(max(menu_order),0) from ai_menu where parent_id=#{parentId}")
	public int queryMaxChildMenuOrder(String parentId);
	
	@Select("select max(func_id) from ai_menu where parent_id=#{parentId}")
	public String queryMaxChildMenuId(String parentId);
	
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
	 * 
	 * @param sequenceId 功能菜单ID
	 * @return
	 */
	public int deleteAiMenu(String sequenceId);
	
}
