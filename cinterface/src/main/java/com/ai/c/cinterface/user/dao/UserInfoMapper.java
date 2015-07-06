package com.ai.c.cinterface.user.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.ai.c.cinterface.user.entity.UserInfo;
import com.ai.c.cinterface.user.entity.UserInfoStatusHistory;
@Repository
public interface UserInfoMapper {

	/**
	 * 查询用户
	 * 
	 * @param userName
	 * @return 
	 */
	UserInfo queryUserInfoByUsername(@Param("userName")String userName);
	
	int updateUserInfo(UserInfo userInfo);

	/**
	 * 插入userInfo
	 * @param userInfo
	 * @return
	 */
	int insertUserInfo(UserInfo userInfo);

	/**
	 * 根据用户名查询用户，返回个数
	 * @param userName
	 * @return
	 */
	int queryUserNameCount(String userName);
	
	/**
	 * 插入状态变更表
	 * @param userInfoPwdHistory
	 * @return
	 */
	int insertUserInfoStatusHistory(UserInfoStatusHistory userInfoStatusHistory);

	/**
	 * 根据用户id查询
	 * @param userId
	 * @return
	 */
	UserInfo queryUserInfoByUserId(String userId);

	@Update("update user_info set status_id=#{status} where user_id=#{userId}")
	int updateUserStatus(@Param("userId")String userId,@Param("status")String status);

	/**
	 * 更新密码
	 * 
	 * @param username
	 * @param newPassword
	 * @return
	 */
	@Update("update user_info set user_password = #{newPassword} where user_name = #{username}")
	int updatePassword(@Param("username") String username,
			@Param("newPassword") String newPassword);
}
