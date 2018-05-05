/**
 * 
 */
package com.weimhc.api.modules.dto.resp.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.dto.AbstractIdDto;
import com.weimhc.modules.coupon.entity.Coupon;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

/**
 * 用户信息 DTO
 * 
 * @author zsm
 * @version 2016-10-05
 */
public class MemberCouponDto extends AbstractIdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6707586013228500755L;
	/**
	 * 优惠券
	 * 
	 */
	private Coupon coupon;
	/**
	 * 代码
	 * 
	 */
	private String code;
	/**
	 * 是否使用
	 * 
	 */
	private Boolean isUsed;
	/**
	 * 使用时间
	 * 
	 */
	private Date usedDate;

	/**
	 * @return the coupon
	 */
	@ApiIgnore
	@JsonIgnore
	public Coupon getCoupon() {
		return coupon;
	}

	/**
	 * @param coupon
	 *            the coupon to set
	 */
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	/**
	 * 优惠券名称
	 * 
	 * @return the code
	 */
	@ApiModelProperty(value = "优惠券名称")
	public String getCouponName() {
		if (getCoupon() != null
				&& StringUtils.isNotBlank(getCoupon().getName())) {
			return getCoupon().getName();
		}
		return "";
	}

	/**
	 * 优惠券介绍
	 * 
	 * @return the code
	 */
	@ApiModelProperty(value = "优惠券介绍")
	public String getCouponDescription() {
		if (getCoupon() != null
				&& StringUtils.isNotBlank(getCoupon().getDescription())) {
			return getCoupon().getDescription();
		}
		return "";
	}

	/**
	 * @return the code
	 */
	@ApiModelProperty(value = "代码")
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the isUsed
	 */
	@ApiModelProperty(value = "是否使用")
	public Boolean getIsUsed() {
		return isUsed;
	}

	/**
	 * @param isUsed
	 *            the isUsed to set
	 */
	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	/**
	 * @return the usedDate
	 */
	@ApiModelProperty(value = "使用时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getUsedDate() {
		return usedDate;
	}

	/**
	 * @param usedDate
	 *            the usedDate to set
	 */
	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}

}