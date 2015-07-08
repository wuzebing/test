package com.ai.c.cinterface.permission.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ai.c.cinterface.permission.common.AiMenuStatus;
import com.ai.c.cinterface.permission.dao.AiMenuMapper;
import com.ai.c.cinterface.permission.entity.AiMenu;
import com.ai.c.cinterface.permission.service.AiJobFuncService;
import com.ai.c.cinterface.permission.service.AiMenuService;

@Service
public class AiMenuServiceImpl implements AiMenuService {
	
	@Autowired
	private AiMenuMapper aiMenuMapper;
	
	@Autowired
	private AiJobFuncService aiFuncService;

	@Override
	public List<AiMenu> queryAiMenuList(AiMenu aiMenu) {

		return aiMenuMapper.queryAiMenus(aiMenu);
	}
	
	@Override
	public AiMenu queryAiMenuById(String funcId) {
		
		AiMenu aiMenu = new AiMenu();
		aiMenu.setFuncId(funcId);
		List<AiMenu> results = aiMenuMapper.queryAiMenus(aiMenu);
		
		if(results.size() == 1){
			return results.get(0);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int insertAiMenu(AiMenu aiMenu) {
		
		String parentId = aiMenu.getParentId();
		// 生成菜唯一单标示符funcId,funcId格式为(parentId+"_"+唯一的编号)
		String maxChildMenuId = aiMenuMapper.queryMaxChildMenuId(parentId);
		String funcId = "";
		if(maxChildMenuId == null){
			funcId = aiMenu.getParentId() + "_1";
		}else{
			funcId = aiMenu.getParentId() + "_" + 
				(Integer.parseInt(maxChildMenuId.substring(parentId.length()+1)) + 1);
		}
		
		// 生成menuOrder，默认放到上一级菜单下的最后一位
		int menuOrder = aiMenuMapper.queryMaxChildMenuOrder(aiMenu.getParentId()) + 1;
		
		aiMenu.setFuncId(funcId);
		aiMenu.setMenuOrder(menuOrder);
		return aiMenuMapper.insertAiMenu(aiMenu);
		
	}

	@Override
	public int updateAiMenu(AiMenu aiMenu) {
		
		return aiMenuMapper.updateAiMenu(aiMenu);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteAiMenu(String funcId) {
		
		// 查询该菜单是否有子菜单，如果有不允许删除该菜单
		int childMenuCount = aiMenuMapper.queryChildMenuCount(funcId);
		if(childMenuCount > 0){
			return 1;
		}
		
		// 删除全部该菜单与角色对应关系
		aiFuncService.deleteAiJobFuncByFunc(funcId);
		
		// 删除功能菜单
		aiMenuMapper.deleteAiMenu(funcId);
		
		return 0;
	}

	@Override
	public boolean enableAimenu(String funcId) {
		
		AiMenu aiMenu = new AiMenu();
		aiMenu.setFuncId(funcId);
		aiMenu.setStatus(AiMenuStatus.ABLE_STAUS);
		
		aiMenuMapper.updateAiMenu(aiMenu);
		return true;
	}
	
	@Override
	public boolean diableAiMenu(String funcId) {
		
		AiMenu aiMenu = new AiMenu();
		aiMenu.setFuncId(funcId);
		aiMenu.setStatus(AiMenuStatus.DISABLE_STATUS);
		return true;
	}
	
	public void setAiMenuMapper(AiMenuMapper aiMenuMapper) {
		this.aiMenuMapper = aiMenuMapper;
	}

	public void setAiFuncService(AiJobFuncService aiFuncService) {
		this.aiFuncService = aiFuncService;
	}

}
