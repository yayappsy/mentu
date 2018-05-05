/**
 * 
 */
package com.weimhc.framework.im;

import java.util.List;

import com.weimhc.modules.user.entity.UserInfo;

/**
 * 第三方IM 用户管理，包括创建、修改、删除、禁用、强制下线
 * 
 * @author shaozuo
 *
 */
public interface IMUserManageService {

	/**
	 * 创建单个用户
	 * 
	 * @param userInfo
	 */
	Object createNewIMUserSingle(UserInfo userInfo);

	/**
	 * 批量创建用户
	 * 
	 * @param userInfoList
	 */
	Object createNewIMUserBatch(List<UserInfo> userInfoList);

	/**
	 * 获取IM用户[单个]
	 * 
	 * @param userInfo
	 * @return
	 */
	Object getIMUserByUserName(UserInfo userInfo);

	/**
	 * 获取IM用户[批量]，参数为空时默认返回最早创建的10个用户 <br>
	 * GET
	 * 
	 * @param limit
	 *            单页获取数量
	 * @param cursor
	 *            游标，大于单页记录时会产生
	 * @return
	 */
	Object getIMUsersBatch(Long limit, String cursor);

	/**
	 * 修改用户密码
	 * 
	 * @param userInfo
	 */
	Object modifyIMUserPassword(UserInfo userInfo);

	/**
	 * 修改用户昵称
	 * 
	 * @param userInfo
	 */
	Object modifyIMUserNickname(UserInfo userInfo);

	/**
	 * IM用户账号禁用与解禁 <br>
	 * 
	 * @param userInfo
	 *            用户信息
	 * @param ifActivated
	 *            是否启用
	 * @return
	 */
	boolean handleIMUserActivate(UserInfo userInfo, boolean ifActivated);

	/**
	 * 环信用户强制下线
	 * 
	 * @param userInfo
	 * @return
	 */
	boolean forceDisconnect(UserInfo userInfo);

}
