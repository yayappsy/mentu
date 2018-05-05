/**
 * 
 */
package com.weimhc.framework.im.easemob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easemob.server.example.api.ChatGroupAPI;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.mapper.JsonMapper;
import com.weimhc.framework.im.IMGroupService;
import com.weimhc.framework.im.dto.req.IMGroupRQ;
import com.weimhc.framework.im.easemob.dto.resp.EasemobApiResponse;
import com.weimhc.framework.im.easemob.dto.resp.IMGroupModifyDto;
import com.weimhc.framework.service.impl.BaseServiceImpl;

import io.swagger.client.model.Group;
import io.swagger.client.model.ModifyGroup;
import io.swagger.client.model.NewOwner;
import io.swagger.client.model.UserName;
import io.swagger.client.model.UserNames;

/**
 * 
 * 环信群操作
 * 
 * @author shaozuo
 *
 */
@Service
public class EasemobIMGroupService extends BaseServiceImpl implements IMGroupService {

	@Autowired
	private ChatGroupAPI chatGroupAPI;

	@Override
	public IMGroupModifyDto createIMGroup(IMGroupRQ iMGroupRQ) {
		Group group = new Group();
		group.groupname(iMGroupRQ.getName()).desc(iMGroupRQ.getDescription())._public(true)
				.maxusers(100).approval(false).owner(iMGroupRQ.getManagerId());
		Object result = chatGroupAPI.createChatGroup(group);
		EasemobApiResponse<String, IMGroupModifyDto> response = JsonMapper.fromJsonString(
				result.toString(),
				new TypeReference<EasemobApiResponse<String, IMGroupModifyDto>>() {
				});
		return response.getData();
	}

	/**
	 * 修改基本信息，名称和描述
	 */
	@Override
	public IMGroupModifyDto modifyIMGroup(IMGroupRQ iMGroupRQ) {
		ModifyGroup group = new ModifyGroup();
		group.description(iMGroupRQ.getName()).groupname(iMGroupRQ.getDescription());
		Object result = chatGroupAPI.modifyChatGroup(iMGroupRQ.getId(), group);
		EasemobApiResponse<String, IMGroupModifyDto> response = JsonMapper.fromJsonString(
				result.toString(),
				new TypeReference<EasemobApiResponse<String, IMGroupModifyDto>>() {
				});
		return response.getData();
	}

	/**
	 * 修改群人数上限
	 * 
	 * @param iMGroupRQ
	 * @return
	 */
	public IMGroupModifyDto modifyIMGroupMaxUsers(IMGroupRQ iMGroupRQ) {
		ModifyGroup group = new ModifyGroup();
		group.maxusers(iMGroupRQ.getMaxUsers());
		Object result = chatGroupAPI.modifyChatGroup(iMGroupRQ.getId(), group);
		EasemobApiResponse<String, IMGroupModifyDto> response = JsonMapper.fromJsonString(
				result.toString(),
				new TypeReference<EasemobApiResponse<String, IMGroupModifyDto>>() {
				});
		return response.getData();
	}

	@Override
	public IMGroupModifyDto deleteIMGroup(IMGroupRQ iMGroupRQ) {
		Object result = chatGroupAPI.deleteChatGroup(iMGroupRQ.getId());
		EasemobApiResponse<String, IMGroupModifyDto> response = JsonMapper.fromJsonString(
				result.toString(),
				new TypeReference<EasemobApiResponse<String, IMGroupModifyDto>>() {
				});
		return response.getData();
	}

	@Override
	public Object addBatchUsersToChatGroup(String groupId, String... userIds) {
		UserNames userNames = new UserNames();
		UserName userList = new UserName();
		userList.addAll(Lists.newArrayList(userIds));
		userNames.usernames(userList);
		Object result = chatGroupAPI.addBatchUsersToChatGroup(groupId, userNames);
		return null;
	}

	@Override
	public Object removeBatchUsersFromChatGroup(String groupId, String... userIds) {
		Object result = chatGroupAPI.removeBatchUsersFromChatGroup(groupId, userIds);
		return null;
	}

	@Override
	public Object transferChatGroupOwner(String groupId, String userId) {
		NewOwner newOwner = new NewOwner();
		newOwner.newowner(userId);
		Object result = chatGroupAPI.transferChatGroupOwner(groupId, newOwner);
		return null;
	}

}
