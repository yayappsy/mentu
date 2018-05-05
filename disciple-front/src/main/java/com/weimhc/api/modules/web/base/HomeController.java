/**
 * 
 */
package com.weimhc.api.modules.web.base;

import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.modules.dto.resp.*;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.utils.CustomBeanUtils;
import com.weimhc.framework.web.utils.MessageSourceUtils;
import com.weimhc.modules.ad.entity.Ad;
import com.weimhc.modules.ad.service.AdService;
import com.weimhc.modules.base.entity.OnlineCustomerService;
import com.weimhc.modules.base.entity.OnlineCustomerService.CustomerType;
import com.weimhc.modules.base.service.OnlineCustomerServiceService;
import com.weimhc.modules.navigation.entity.Navigation;
import com.weimhc.modules.navigation.service.NavigationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 首页信息Controller
 * 
 * @author lc
 * @version 2017-06-16
 */
@Api
@ApiIgnore
@Controller("apiHomeController")
@RequestMapping(value = "${apiPath}/home")
public class HomeController extends ApiBaseController {

	@Autowired
	private NavigationService navigationService;
	@Autowired
	private AdService adService;

	@Autowired
	private OnlineCustomerServiceService onlineCustomerServiceService;

	@ApiOperation(value = "获取头部导航及底部导航", notes = "获取头部导航及底部导航", tags = "首页")
	@RequestMapping(value = "topAndButtom", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<NavigationListDto> getTopAndButtom(HttpServletRequest request) {
		ApiResult<NavigationListDto> result = new ApiResult<>();
		// 获取头部导航
		Navigation navigation = new Navigation();
		navigation.setParent(new Navigation("2"));
		List<Navigation> navigationTopList = navigationService.findAllList(navigation);
		List<NavigationDto> navigationTops = CustomBeanUtils.copyList(navigationTopList,
				NavigationDto.class);
		// 获取底部导航
		Navigation bnavigation = new Navigation();
		bnavigation.setParent(new Navigation("4"));
		List<Navigation> navigationButtomList = navigationService.findAllList(bnavigation);
		List<NavigationDto> navigationButtoms = CustomBeanUtils.copyList(navigationButtomList,
				NavigationDto.class);
		NavigationListDto navigationListDto = new NavigationListDto();
		navigationListDto.setNavigationlTopList(navigationTops);
		navigationListDto.setNavigationlButtomList(navigationButtoms);
		result.setData(navigationListDto);
		return result;
	}

	@ApiOperation(value = "获取首页轮播及图片", notes = "获取首页轮播及图片", tags = "首页")
	@RequestMapping(value = "index", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<HomeDto> getIndex(HttpServletRequest request) {
		ApiResult<HomeDto> result = new ApiResult<>();
		// 获取首页轮播图
		List<Ad> adCarouseList = adService.findCarouselList(new Ad());
		List<AdDto> carouseLists = CustomBeanUtils.copyList(adCarouseList, AdDto.class);
		// 获取首页图片
		List<Ad> adImageList = adService.findImageList(new Ad());
		List<AdDto> imageLists = CustomBeanUtils.copyList(adImageList, AdDto.class);
		HomeDto homeDto = new HomeDto();
		homeDto.setAdCarouselList(carouseLists);
		homeDto.setAdList(imageLists);
		result.setData(homeDto);
		return result;
	}

	@ApiOperation(value = "获取查看视频规范图片", notes = "获取查看视频规范图片", tags = "首页")
	@RequestMapping(value = "center/index", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<AdDto> getCenterIndex(HttpServletRequest request) {
		ApiResult<AdDto> result = new ApiResult<>();
		// 获取个人中心首页图片
		Ad ad = adService.get("4");
		if (ad == null) {
			return ApiResult.error(MessageSourceUtils.getMessage("error.common.entityNotExist"));
		}
		AdDto adDto = new AdDto();
		BeanUtils.copyProperties(ad, adDto);
		result.setData(adDto);
		return result;
	}

	@ApiOperation(value = "获取个人中心图片", notes = "获取个人中心图片", tags = "首页")
	@RequestMapping(value = "ad/get", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<AdDto> getAd(
			@ApiParam(value = "游戏id", required = true) @RequestParam String id,
			HttpServletRequest request) {
		ApiResult<AdDto> result = new ApiResult<>();
		// 广告图片
		String adId = "3";
		if (id.equals("1")) {
			adId = "3";
		} else {
			adId = "5";
		}
		Ad ad = adService.get(adId);
		if (ad == null) {
			return ApiResult.error(MessageSourceUtils.getMessage("error.common.entityNotExist"));
		}
		AdDto adDto = new AdDto();
		BeanUtils.copyProperties(ad, adDto);
		result.setData(adDto);
		return result;
	}

	@ApiOperation(value = "获取在线客服信息", notes = "获取在线客服信息", tags = "首页")
	@RequestMapping(value = "customer/get", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<OnlineCustomerServiceDto> getOnlineCustomer(HttpServletRequest request) {
		ApiResult<OnlineCustomerServiceDto> result = new ApiResult<>();
		// 获取在线客服信息
		OnlineCustomerService onlineCustomerService = new OnlineCustomerService();
		onlineCustomerService.setType(CustomerType.qq);
		onlineCustomerService = onlineCustomerServiceService.getEntity(onlineCustomerService);
		if (onlineCustomerService == null) {
			return ApiResult.error(MessageSourceUtils.getMessage("error.common.entityNotExist"));
		}
		OnlineCustomerServiceDto onlineCustomerServiceDto = new OnlineCustomerServiceDto();
		BeanUtils.copyProperties(onlineCustomerService, onlineCustomerServiceDto);
		result.setData(onlineCustomerServiceDto);
		return result;
	}
}