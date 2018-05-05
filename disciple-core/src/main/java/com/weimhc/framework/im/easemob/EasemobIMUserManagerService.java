/**
 * 
 */
package com.weimhc.framework.im.easemob;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easemob.server.example.api.IMUserAPI;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.mapper.JsonMapper;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.im.IMUserManageService;
import com.weimhc.framework.im.easemob.dto.req.IMUserRQ;
import com.weimhc.framework.im.easemob.dto.resp.EasemobApiResponse;
import com.weimhc.framework.im.easemob.dto.resp.IMUserDto;
import com.weimhc.framework.service.impl.BaseServiceImpl;
import com.weimhc.modules.user.entity.UserInfo;

import io.swagger.client.model.NewPassword;
import io.swagger.client.model.Nickname;
import io.swagger.client.model.RegisterUsers;

/**
 * 环信用户管理
 * 
 * @author shaozuo
 *
 */
@Service
public class EasemobIMUserManagerService extends BaseServiceImpl implements IMUserManageService {

	@Autowired
	private IMUserAPI iMUserAPI;

	/**
	 * 创建环信账号(默认为诚信号加M_前缀)，如果已经存在，则不继续创建
	 */
	@Override
	public IMUserDto createNewIMUserSingle(UserInfo userInfo) {
		if (userInfo == null) {
			return null;
		}
		if (StringUtils.isBlank(userInfo.getImUsername())) {
			userInfo.setImUsername("M_" + userInfo.getSn());
		}
		IMUserDto imUserDto = getIMUserByUserName(userInfo);
		if (imUserDto != null) {
			return imUserDto;
		}
		IMUserRQ user = new IMUserRQ(userInfo.getImUsername(), "123456", userInfo.getNickname());
		return getIMUserDtoFromResponseBody(
				iMUserAPI.createNewIMUserSingle(getRegisterUsers(user)));
	}

	/**
	 * 创建用户操作使用的request body
	 * 
	 * @param iMUserRQs
	 * @return
	 */
	private RegisterUsers getRegisterUsers(IMUserRQ... iMUserRQs) {
		RegisterUsers registerUsers = new RegisterUsers();
		registerUsers.addAll(Lists.newArrayList(iMUserRQs));
		return registerUsers;
	}

	/**
	 * 获取单个用户信息
	 * 
	 * @param resultStr
	 * @return
	 */
	private IMUserDto getIMUserDtoFromResponseBody(Object resultStr) {
		List<IMUserDto> resultUserList = getIMUserDtoListFromResponseBody(resultStr);
		IMUserDto resultUser = null;
		if (resultUserList != null) {
			resultUser = resultUserList.get(0);
		}
		return resultUser;
	}

	/**
	 * 获取多个环信账号信息 从 返回数据中获取环信账号信息
	 * 
	 * @return
	 */
	private List<IMUserDto> getIMUserDtoListFromResponseBody(Object result) {
		if (result == null) {
			return null;
		}
		EasemobApiResponse<IMUserDto, String> response = JsonMapper.fromJsonString(
				result.toString(), new TypeReference<EasemobApiResponse<IMUserDto, String>>() {
				});
		List<IMUserDto> users = response.getEntities();
		logger.debug("获取数据{}", users.size());
		return users;

	}

	/**
	 * 批量创建用户，最多一次性20条
	 */
	@Override
	public List<IMUserDto> createNewIMUserBatch(List<UserInfo> userInfoList) {
		RegisterUsers registerUsers = new RegisterUsers();
		for (int i = 0; i < userInfoList.size(); i++) {
			IMUserRQ user = new IMUserRQ(userInfoList.get(i).getImUsername(), "123456",
					userInfoList.get(i).getNickname());
			registerUsers.add(user);
		}
		List<IMUserDto> result = getIMUserDtoListFromResponseBody(
				iMUserAPI.createNewIMUserSingle(registerUsers));
		return result;
	}

	@Override
	public IMUserDto getIMUserByUserName(UserInfo userInfo) {
		IMUserDto result = null;
		if (userInfo != null && StringUtils.isNotBlank(userInfo.getImUsername())) {
			result = getIMUserDtoFromResponseBody(
					iMUserAPI.getIMUserByUserName(userInfo.getImUsername()));
		}
		return result;
	}

	@Override
	public Object getIMUsersBatch(Long limit, String cursor) {
		List<IMUserDto> users = getIMUserDtoListFromResponseBody(
				iMUserAPI.getIMUsersBatch(limit, cursor));
		return users;
	}

	@Override
	public Object modifyIMUserPassword(UserInfo userInfo) {
		NewPassword newPasswordRQ = new NewPassword().newpassword(userInfo.getNickname());
		iMUserAPI.modifyIMUserPasswordWithAdminToken(userInfo.getImUsername(), newPasswordRQ);
		return false;
	}

	@Override
	public Object modifyIMUserNickname(UserInfo userInfo) {
		Nickname nicknameRQ = new Nickname().nickname(userInfo.getNickname());
		iMUserAPI.modifyIMUserNickNameWithAdminToken(userInfo.getImUsername(), nicknameRQ);
		return false;
	}

	@Override
	public boolean handleIMUserActivate(UserInfo userInfo, boolean ifActivated) {
		if (ifActivated) {
			iMUserAPI.activateIMUser(userInfo.getImUsername());
		} else {
			iMUserAPI.deactivateIMUser(userInfo.getImUsername());
		}
		return false;
	}

	@Override
	public boolean forceDisconnect(UserInfo userInfo) {
		iMUserAPI.disconnectIMUser(userInfo.getImUsername());
		return false;
	}

}
