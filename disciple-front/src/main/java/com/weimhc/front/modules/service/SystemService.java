/**
 * 
 */
package com.weimhc.front.modules.service;

import com.weimhc.modules.user.dao.UserInfoDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.thinkgem.javamg.common.security.shiro.session.SessionDAO;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.api.modules.dto.req.BaseLoginRQ;
import com.weimhc.api.modules.dto.resp.LoginDto;
import com.weimhc.api.modules.dto.resp.user.MemberDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.security.MemberPrincipal;
import com.weimhc.framework.utils.AppTokenHelper;
import com.weimhc.framework.web.utils.MessageSourceUtils;
import com.weimhc.front.core.security.UsernamePasswordToken;
import com.weimhc.front.modules.utils.MemberUtils;
import com.weimhc.modules.base.utils.setting.MemberSettingUtils;
import com.weimhc.modules.member.dao.MemberDao;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.user.entity.BusinessSystem;
import com.weimhc.modules.user.entity.IdentityType;
import com.weimhc.modules.user.entity.UserInfo;
import com.weimhc.modules.user.service.UserInfoCommonService;

/**
 * 系统管理， 处理会员信息修改，会员密码修改等
 * 
 * @version 2016-02-23
 */
@Service
@Transactional(readOnly = true)
public class SystemService extends UserInfoCommonService implements InitializingBean {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private SessionDAO sessionDao;

	@Autowired
	private UserInfoDao userInfoDao;

	public SessionDAO getSessionDao() {
		return sessionDao;
	}

	// -- User Service --//

	/**
	 * 获取会员
	 * 
	 * @param id
	 * @return
	 */
	public Member getMember(String id) {
		return MemberUtils.get(id);
	}

	/**
	 * 根据用户登录标志查询用户信息
	 * 
	 * @param identifier
	 *            用户登录标志 eg 手机号 用户名 邮箱等
	 * @param identityType
	 *            TODO
	 * @return
	 */
	public Member getByIdentifier(String identifier, IdentityType identityType) {
		Member member = null;
		switch (identityType) {
		case username:
			member = MemberUtils.getByUsername(identifier);
			break;
		case email:
			member = MemberUtils.getByEmail(identifier);
			break;
		case mobile:
			member = MemberUtils.getByMobile(identifier);
			break;
		case qq:
		case wechat:
		default:
			break;
		}

		return member;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 更新登录失败次数
	 * 
	 * @param member
	 */
	@Transactional(readOnly = false)
	public void updateMemberLoginFailure(Member member) {
		userLoginAuthorizationDao.update(member.getUserLoginAuthorization());
	}

	/**
	 * 更新个人信息，如：头像、生日、真实姓名等
	 * 
	 * @param user
	 * @return
	 */
	@Transactional(readOnly = false)
	public void updateProfile(Member member) {
		super.updateProfile(member.getLatestUserInfo());
		MemberUtils.clearCache(member);
	}

	/**
	 * 更新用户密码
	 * 
	 * @param user
	 * @return
	 */
	@Override
	@Transactional(readOnly = false)
	public void updatePassword(UserInfo userInfo, IdentityType... identityTypes) {
		super.updatePassword(userInfo, identityTypes);
		MemberUtils.clearCache(userInfo);
	}

	@Transactional(readOnly = false)
	public int updateAvatarById(Member member) {
		return userInfoDao.updateAvatarById(member.getLatestUserInfo());
	}

	/**
	 * 保存会员
	 * 
	 * @param member
	 */
	@Transactional(readOnly = false)
	public void insertMember(Member member) {
		this.saveMember(member, true);
	}

	/**
	 * 保存会员
	 * 
	 * @param member
	 * @param ifCreateUserAuth
	 *            是否创建用户登录凭证
	 */
	@Transactional(readOnly = false)
	public void saveMember(Member member, boolean ifCreateUserAuth) {
		this.saveMember(member, ifCreateUserAuth, IdentityType.mobile);
	}

	/**
	 * 保存会员
	 * 
	 * @param member
	 * @param ifCreateUserAuth
	 *            是否创建用户登录凭证
	 */
	@Transactional(readOnly = false)
	public void saveMember(Member member, boolean ifCreateUserAuth, IdentityType... identityTypes) {
		member.getUserLoginAuthorization().setBusinessSystem(BusinessSystem.front);
		UserInfo userInfo = member.getLatestUserInfo();
		saveUserInfo(userInfo, ifCreateUserAuth, identityTypes);
		BeanUtils.copyProperties(userInfo, member, "userInfo");
	}

	/**
	 * 判断用户名是否禁用
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否禁用
	 */
	public boolean usernameDisabled(String username) {
		Assert.hasText(username);
		if (MemberSettingUtils.getDisabledUsernames() != null) {
			for (String disabledUsername : MemberSettingUtils.getDisabledUsernames()) {
				if (StringUtils.containsIgnoreCase(username, disabledUsername)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 检查登录标志是否可用（该账户是否可以用来注册）
	 * 
	 * @param identifier
	 *            登录注册标识
	 * @param identityType
	 *            登录标识类型
	 */
	public boolean checkIdentifierCanUse(String identifier, IdentityType identityType) {
		if (StringUtils.isEmpty(identifier)) {
			return false;
		}
		boolean canUse = true;
		switch (identityType) {
		case username:
			if (usernameDisabled(identifier) || memberDao.usernameExists(identifier)) {
				canUse = false;
			}
			break;
		case mobile:
			if (memberDao.mobileExists(identifier)) {
				canUse = false;
			}
			break;
		case email:
			if (memberDao.emailExists(identifier)) {
				canUse = false;
			}
			break;

		default:
			break;
		}
		return canUse;
	}

	/**
	 * 登录
	 * 
	 * @param body
	 */
	@Transactional(readOnly = false)
	public ApiResult<? extends MemberDto> login(BaseLoginRQ body, boolean ifMobleLogin) {
		UsernamePasswordToken uPasswordToken = new UsernamePasswordToken();
		uPasswordToken.setUsername(body.getIdentifier());
		uPasswordToken.setPassword(body.getPassword().toCharArray());
		uPasswordToken.setMobileLogin(ifMobleLogin);
		uPasswordToken.setIsNeedCaptcha(false);
		uPasswordToken.setIdentityType(body.getIdentityType());
		if (logger.isDebugEnabled()) {
			logger.debug("是否手机或平板登录： " + ifMobleLogin);
		}
		String message = MemberUtils.subjectLogin(uPasswordToken);

		if (StringUtils.isNotBlank(message)) {
			return ApiResult.error(MessageSourceUtils.getMessage(message));
		}
		Member member = getByIdentifier(body.getIdentifier(), body.getIdentityType());
		String userId = member.getId();
		userInfoDao.updateType(userId, body.getType());
		MemberPrincipal memberPrincipal = new MemberPrincipal(member, true);
		String token = AppTokenHelper.generateToken(memberPrincipal);
		ApiResult<LoginDto> apiResult = new ApiResult<>();
		LoginDto loginDto = new LoginDto();
		loginDto.setType(body.getType());
		BeanUtils.copyProperties(member, loginDto);
		loginDto.setToken(token);
		apiResult.setData(loginDto);
		return apiResult;
	}
}
