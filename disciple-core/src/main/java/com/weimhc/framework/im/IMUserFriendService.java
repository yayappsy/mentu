/**
 * 
 */
package com.weimhc.framework.im;

/**
 * 第三方IM 用户好友管理
 * 
 * @author shaozuo
 *
 */
public interface IMUserFriendService {

	/**
	 * 给IM用户的添加好友 <br>
	 * POST
	 * 
	 * @param imUsername
	 *            用戶名或用戶ID
	 * @param friendImUsername
	 *            好友用戶名或用戶ID
	 * @return
	 */
	Object addFriendSingle(String imUsername, String friendImUsername);

	/**
	 * 解除IM用户的好友关系 <br>
	 * DELETE
	 * 
	 * @param imUsername
	 *            用戶名或用戶ID
	 * @param friendImUsername
	 *            好友用戶名或用戶ID
	 * @return
	 */
	Object deleteFriendSingle(String imUsername, String friendImUsername);

	/**
	 * 查看某个IM用户的好友信息 <br>
	 * GET
	 * 
	 * @param imUsername
	 *            用戶名或用戶ID
	 * @return
	 */
	Object getFriends(String imUsername);

	/**
	 * 获取IM用户的黑名单 <br>
	 * GET
	 * 
	 * @param imUsername
	 *            用戶名或用戶ID
	 * @return
	 */
	Object getBlackList(String imUsername);

	/**
	 * 往IM用户的黑名单中加人 <br>
	 * POST
	 * 
	 * @param imUsername
	 *            用戶名或用戶ID
	 * @param payload
	 *            <code>{"usernames":["5cxhactgdj", "mh2kbjyop1"]}</code>
	 * @return
	 */
	Object addToBlackList(String imUsername, Object payload);

	/**
	 * 从IM用户的黑名单中减人 <br>
	 * DELETE
	 * 
	 * @param imUsername
	 *            用戶名或用戶ID
	 * @param blackListName
	 *            黑名单用戶名或用戶ID
	 * @return
	 */
	Object removeFromBlackList(String imUsername, String blackListName);
}
