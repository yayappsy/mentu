/**
 * 
 */
package com.weimhc.modules.member.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.entity.SnType;
import com.weimhc.modules.base.service.SnService;
import com.weimhc.modules.base.utils.setting.MemberSettingUtils;
import com.weimhc.modules.member.dao.MemberDao;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.user.dao.UserInfoDao;
import com.weimhc.modules.user.entity.IdentityType;
import com.weimhc.modules.user.utils.UserAuthUtils;

/**
 * 会员Service
 * 
 * @author zsm
 * @version 2016-01-12
 */
@Service
@Transactional(readOnly = true)
public class MemberService extends CrudServiceImpl<MemberDao, Member> {

	@Autowired
	private SnService snService;

	@Autowired
	private UserInfoDao userInfoDao;

	@Override
	public Member get(String id) {
		return super.get(id);
	}

	@Override
	public long count(Member member) {
		return super.count(member);
	}

	@Override
	public boolean exists(String id) {
		return super.exists(id);
	}

	@Override
	public List<Member> findList(Member member) {
		return super.findList(member);
	}

	@Override
	public Page<Member> findPage(Page<Member> page, Member member) {
		return super.findPage(page, member);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Member member) {
		super.save(member);
	}

	/**
	 * userInfo 中的数据也应该一起删除
	 */
	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(Member member) {
		super.deleteEntity(member);
		userInfoDao.deleteEntity(member.getLatestUserInfo());
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Member entity) {
		super.delete(entity);
		userInfoDao.deleteEntity(entity.getLatestUserInfo());
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Member member = null;
			for (String id : ids) {
				member = new Member(id);
				deleteEntity(member);
			}
		}

	}

	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	public boolean usernameExists(String username) {
		return dao.usernameExists(username);

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
			for (String disabledUsername : MemberSettingUtils
					.getDisabledUsernames()) {
				if (StringUtils.containsIgnoreCase(username,
						disabledUsername)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断E-mail是否存在
	 * 
	 * @param email
	 *            E-mail(忽略大小写)
	 * @return E-mail是否存在
	 */
	public boolean emailExists(String email) {
		return dao.emailExists(email);
	}

	/**
	 * 判断手机号是否存在
	 * 
	 * @param mobile
	 *            E-mail(忽略大小写)
	 * @return E-mail是否存在
	 */
	public boolean mobileExists(String mobile) {
		return dao.mobileExists(mobile);
	}

	/**
	 * 判断E-mail是否唯一
	 * 
	 * @param previousEmail
	 *            修改前E-mail(忽略大小写)
	 * @param currentEmail
	 *            当前E-mail(忽略大小写)
	 * @return E-mail是否唯一
	 */
	public boolean emailUnique(String previousEmail, String currentEmail) {
		if (StringUtils.equalsIgnoreCase(previousEmail, currentEmail)) {
			return true;
		} else {
			if (dao.emailExists(currentEmail)) {
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * 更新登录信息，如：登录IP、登录时间
	 * 
	 * @param user
	 * @return
	 */
	public int updateLoginInfo(Member member) {
		return dao.updateLoginInfo(member);
	}

	/**
	 * 更新个人信息，如：头像、生日、真实姓名等
	 * 
	 * @param user
	 * @return
	 */
	public int updateProfile(Member member) {
		return dao.updateProfile(member);
	}

	/**
	 * 更新用户密码
	 * 
	 * @param user
	 * @return
	 */
	public int updatePasswordById(Member member) {
		return dao.updatePasswordById(member);
	}

	/**
	 * 更新余额
	 * 
	 * @param member
	 * @param effectiveAmount
	 */
	public void updateBalance(Member member, BigDecimal effectiveAmount) {

	}

	public int updateAvatarById(Member member) {
		return dao.updateAvatarById(member);
	}

	/**
	 * 保存会员
	 */
	@Transactional(readOnly = false)
	public void saveMember(Member member) {
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
		this.saveMember(member, true, IdentityType.username,
				IdentityType.mobile);
	}

	/**
	 * 保存会员
	 * 
	 * @param member
	 * @param ifCreateUserAuth
	 *            是否创建用户登录凭证
	 */
	@Transactional(readOnly = false)
	public void saveMember(Member member, boolean ifCreateUserAuth,
			IdentityType... identityTypes) {
		if (member.getIsNewRecord()) {
			member.preInsert();
			if (StringUtils.isBlank(member.getSn())) {
				member.setSn(snService.generate(SnType.user, ""));
			}
			userInfoDao.insert(member.getLatestUserInfo());
			dao.insert(member);
			if (ifCreateUserAuth) {
				UserAuthUtils.insertPassword(member.getLatestUserInfo(),
						identityTypes);
			}

		} else {
			member.preUpdate();
			userInfoDao.update(member.getLatestUserInfo());
			dao.update(member);
			if (ifCreateUserAuth) {
				UserAuthUtils.savePassword(member.getLatestUserInfo(),
						identityTypes);
			}
		}
	}

	/**
	 * 编号是否存在
	 * 
	 * @return
	 */
	public boolean snExists(Member member) {
		if (member == null || StringUtils.isBlank(member.getSn())) {
			return true;
		}
		return dao.snExists(member);
	}

}