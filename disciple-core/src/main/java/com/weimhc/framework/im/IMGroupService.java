/**
 * 
 */
package com.weimhc.framework.im;

import com.weimhc.framework.im.dto.req.IMGroupRQ;

/**
 * 第三方IM 群管理
 * 
 * @author shaozuo
 *
 */
public interface IMGroupService {

	/**
	 * 创建IM 群组
	 * 
	 * @param iMGoupRQ
	 * @return
	 */
	Object createIMGroup(IMGroupRQ iMGoupRQ);

	/**
	 * 修改IM 群组
	 * 
	 * @param iMGoupRQ
	 * @return
	 */
	Object modifyIMGroup(IMGroupRQ iMGoupRQ);

	/**
	 * 删除IM 群组
	 * 
	 * @param iMGoupRQ
	 * @return
	 */
	Object deleteIMGroup(IMGroupRQ iMGoupRQ);

	/**
	 * 群组加人[批量] <br>
	 * POST
	 * 
	 * @param groupId
	 *            群组标识
	 * @param userIds
	 *            用户ID或用户名列表
	 * @return
	 * @see com.easemob.server.example.comm.body.UserNamesBody
	 */
	Object addBatchUsersToChatGroup(String groupId, String... userIds);

	/**
	 * 群组减人
	 * 
	 * @param groupId
	 *            群组标识
	 * @param userIds
	 *            用户ID或用户名列表
	 * @return
	 */
	Object removeBatchUsersFromChatGroup(String groupId, String... userIds);

	/**
	 * 群组转让 <br>
	 * PUT
	 * 
	 * @param groupId
	 *            群组标识
	 * @param payload
	 *            新群主ID或用户名
	 * @return
	 */
	Object transferChatGroupOwner(String groupId, String userId);
}
