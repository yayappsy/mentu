/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

import com.google.common.collect.Lists;
import com.weimhc.framework.dto.AbstractDto;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 导航DTO
 * 
 * @author lc
 * @version 2016-12-15
 */
public class NavigationListDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095501047336970672L;

	/**
	 * 头部导航
	 */
	private List<NavigationDto> navigationlTopList = Lists.newArrayList();
	/**
	 * 底部导航
	 */
	private List<NavigationDto> navigationlButtomList = Lists.newArrayList();

	/**
	 * 获取头部导航
	 * 
	 * @return the navigationlTopList
	 */
	@ApiModelProperty(value = "头部导航")
	public List<NavigationDto> getNavigationlTopList() {
		return navigationlTopList;
	}

	/**
	 * 设置头部导航
	 * 
	 * @param navigationlTopList
	 *            the navigationlTopList to set
	 */
	public void setNavigationlTopList(List<NavigationDto> navigationlTopList) {
		this.navigationlTopList = navigationlTopList;
	}

	/**
	 * 获取底部导航
	 * 
	 * @return the navigationlButtomList
	 */
	@ApiModelProperty(value = "底部导航")
	public List<NavigationDto> getNavigationlButtomList() {
		return navigationlButtomList;
	}

	/**
	 * 设置底部导航
	 * 
	 * @param navigationlButtomList
	 *            the navigationlButtomList to set
	 */
	public void setNavigationlButtomList(
			List<NavigationDto> navigationlButtomList) {
		this.navigationlButtomList = navigationlButtomList;
	}

}