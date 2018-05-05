/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

import com.google.common.collect.Lists;
import com.weimhc.framework.dto.AbstractIdDto;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 商品信息 DTO
 * 
 * @author zsm
 * @version 2016-10-05
 */
public class HomeDto extends AbstractIdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6707586013228500755L;
	/**
	 * 首页轮播图片
	 */
	private List<AdDto> adCarouselList = Lists.newArrayList();
	/**
	 * 首页其他图片
	 */
	private List<AdDto> adList = Lists.newArrayList();

	/**
	 * @return the adCarouselList
	 */
	@ApiModelProperty(value = "首页轮播图片")
	public List<AdDto> getAdCarouselList() {
		return adCarouselList;
	}

	/**
	 * @param adCarouselList
	 *            the adCarouselList to set
	 */
	public void setAdCarouselList(List<AdDto> adCarouselList) {
		this.adCarouselList = adCarouselList;
	}

	/**
	 * @return the adList
	 */
	@ApiModelProperty(value = "其他图片")
	public List<AdDto> getAdList() {
		return adList;
	}

	/**
	 * @param adList
	 *            the adList to set
	 */
	public void setAdList(List<AdDto> adList) {
		this.adList = adList;
	}

}