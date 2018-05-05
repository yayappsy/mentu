/**
 * 
 */
package com.weimhc.modules.remind.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 业务提醒Entity
 * 
 * @author zsm
 * @version 2017-03-23
 */
public class Remind extends BaseNameEntity<Remind> {

	private static final long serialVersionUID = 1L;

	/**
	 * 业务动作
	 * 
	 */
	private BusinessAction businessAction;

	/**
	 * 提醒目标
	 * 
	 */
	private TargetType targetType;

	/**
	 * 提醒模板列表
	 */
	private List<RemindTemplate> remindTemplateList;

	/**
	 * 可选参数列表
	 */
	private List<RemindParameter> remindParameterList;

	/**
	 * 可使用的提醒方式
	 */
	private List<RemindWay> selectedRemindWayList;

	public Remind() {
		super();
	}

	public Remind(String id) {
		super(id);
	}

	/**
	 * 获取业务动作
	 * 
	 * @return 业务动作
	 */
	public BusinessAction getBusinessAction() {
		return businessAction;
	}

	/**
	 * 设置业务动作
	 * 
	 * @param businessAction
	 *            业务动作
	 */
	public void setBusinessAction(BusinessAction businessAction) {
		this.businessAction = businessAction;
	}

	/**
	 * 获取提醒目标
	 * 
	 * @return 提醒目标
	 */
	@Length(min = 1, max = 20)
	public TargetType getTargetType() {
		return targetType;
	}

	/**
	 * 设置提醒目标
	 * 
	 * @param targetType
	 *            提醒目标
	 */
	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}

	/**
	 * 返回 可选参数列表
	 * 
	 * @return the remindParameterList
	 */
	public List<RemindParameter> getRemindParameterList() {
		return remindParameterList;
	}

	/**
	 * 设置 可选参数列表
	 * 
	 * @param remindParameterList
	 *            the remindParameterList to set
	 */
	public void setRemindParameterList(
			List<RemindParameter> remindParameterList) {
		this.remindParameterList = remindParameterList;
	}

	/**
	 * 返回 提醒模板列表
	 * 
	 * @return the remindTemplateList
	 */
	public List<RemindTemplate> getRemindTemplateList() {
		return remindTemplateList;
	}

	/**
	 * 设置 提醒模板列表
	 * 
	 * @param remindTemplateList
	 *            the remindTemplateList to set
	 */
	public void setRemindTemplateList(List<RemindTemplate> remindTemplateList) {
		this.remindTemplateList = remindTemplateList;
	}

	/**
	 * 返回 获取已选择的提醒方式
	 * 
	 * @return the selectedRemindWayList
	 */
	public List<RemindWay> getSelectedRemindWayList() {
		if (selectedRemindWayList == null) {
			selectedRemindWayList = Lists.newArrayList();
			if (remindTemplateList != null) {
				for (RemindTemplate remindTemplate : remindTemplateList) {
					selectedRemindWayList.add(remindTemplate.getRemindWay());
				}
			}
		}
		return selectedRemindWayList;
	}

	/**
	 * 设置 获取已选择的提醒方式
	 * 
	 * @param selectedRemindWayList
	 *            the selectedRemindWayList to set
	 */
	public void setSelectedRemindWayList(
			List<RemindWay> selectedRemindWayList) {
		this.selectedRemindWayList = selectedRemindWayList;
	}

	@JsonIgnore
	public List<String> getRemindParameterIdList() {
		List<String> idList = Lists.newArrayList();
		for (RemindParameter entity : remindParameterList) {
			idList.add(entity.getId());
		}
		return idList;
	}

	public void setRemindParameterIdLis(List<String> idList) {
		remindParameterList = Lists.newArrayList();
		for (String id : idList) {
			RemindParameter entity = new RemindParameter(id);
			remindParameterList.add(entity);
		}
	}
}