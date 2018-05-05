/**
 * 
 */
package com.weimhc.framework.im.easemob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easemob.server.example.api.IMUserAPI;
import com.weimhc.framework.im.IMUserFriendService;
import com.weimhc.framework.service.impl.BaseServiceImpl;

/**
 * 
 * 环信群操作
 * 
 * @author shaozuo
 *
 */
@Service
public class EasemobIMUserFriendService extends BaseServiceImpl implements IMUserFriendService {

	@Autowired
	private IMUserAPI iMUserAPI;

	@Override
	public Object addFriendSingle(String imUsername, String friendImUsername) {
		iMUserAPI.addFriendSingle(imUsername, friendImUsername);
		return null;
	}

	@Override
	public Object deleteFriendSingle(String imUsername, String friendImUsername) {
		iMUserAPI.deleteFriendSingle(imUsername, friendImUsername);
		return null;
	}

	@Override
	public Object getFriends(String imUsername) {
		iMUserAPI.getFriends(imUsername);
		return null;
	}

	@Override
	public Object getBlackList(String imUsername) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object addToBlackList(String imUsername, Object payload) {
		return null;
	}

	@Override
	public Object removeFromBlackList(String imUsername, String blackListName) {
		return null;
	}

}
