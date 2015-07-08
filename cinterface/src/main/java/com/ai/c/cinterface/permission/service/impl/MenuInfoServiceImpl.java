package com.ai.c.cinterface.permission.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.c.cinterface.permission.common.JobMenuTypeLink;
import com.ai.c.cinterface.permission.dao.MenuInfoMapper;
import com.ai.c.cinterface.permission.entity.AiJob;
import com.ai.c.cinterface.permission.entity.FolderMenuInfo;
import com.ai.c.cinterface.permission.entity.LowerFolderAndFunctionMenuInfo;
import com.ai.c.cinterface.permission.service.AiJobService;
import com.ai.c.cinterface.permission.service.AiUserJobInfoService;
import com.ai.c.cinterface.permission.service.MenuInfoService;

/**
 * 提供前台菜单显示相关的功能
 * <li>该类的方法命名和相关实体类是建立在以前的菜单模型建立的；</li>
 * <li>这块牵涉到前台的太多地方，为了减少工作量，只进行功能实现的重构，并未改变功能和返回的数据形式；</li>
 * 
 * @author zhuwenkai
 *
 */
@Service
public class MenuInfoServiceImpl implements MenuInfoService {
	
	@Autowired
	private MenuInfoMapper menuInfoMapper;
	
	@Autowired
	private AiJobService aiJobService;

	
	@Override
	public List<FolderMenuInfo> queryHeaderMenuInfo(String role) {
		
		// 查询用户的角色类别,获得相对应的根菜单
		AiJob aiJob = aiJobService.queryAiJobByJobId(role);
		String func_id = JobMenuTypeLink.valueOf(aiJob.getJobType()).getMenuType();
		
		return menuInfoMapper.queryHeaderMenuInfo(func_id, role);
	}

	@Override
	public List<LowerFolderAndFunctionMenuInfo> queryLowerFolderAndFuncMenuInfo(String upperFolderID,
			String role) {
		
		return menuInfoMapper.queryLowerFolderAndFuncMenuInfo(upperFolderID, role);
	}
	
	@Override
	public List<String> queryMenusbyRole(String role) {
		
		return menuInfoMapper.queryMenusbyRole(role);
	}

	public void setMenuInfoMapper(MenuInfoMapper menuInfoMapper) {
		this.menuInfoMapper = menuInfoMapper;
	}
}
