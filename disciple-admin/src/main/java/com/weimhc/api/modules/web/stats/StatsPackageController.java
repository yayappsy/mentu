/**
 * 
 */
package com.weimhc.api.modules.web.stats;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.modules.dto.resp.StatsPackageDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.modules.product.entity.Product;
import com.weimhc.modules.product.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 来源分析Controller
 * 
 * @author lc
 * @version 2016-10-05
 */
@Api
@Controller("apiAdminStatsPackageController")
@RequestMapping(value = "${apiPath}/stats/package")
public class StatsPackageController extends ApiBaseController {

	@Autowired
	private ProductService productSourceService;

	@ApiOperation(value = "统计访问地区", notes = "根据时间统计访问地区", tags = { "统计" })
	@ApiIgnore
	@RequestMapping(value = "findSitePackage", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<StatsPackageDto>> findSiteSource(
			@RequestParam(required = false) Date beginDate,
			@RequestParam(required = false) Date endDate,
			@RequestParam(required = true) String categoryId,
			HttpServletRequest request) {
		ApiResult<List<StatsPackageDto>> apiResult = new ApiResult<>();
		Product product = new Product();
		product.setBeginDate(beginDate);
		product.setEndDate(endDate);
		List<Product> productList = Lists.newArrayList();
		if (categoryId.equals("1")) {
			productList = productSourceService.findStatsLesson(product);
		} else if (categoryId.equals("3")) {
			productList = productSourceService.findStatsPackage(product);
		}
		List<StatsPackageDto> lists = Lists.newArrayList();
		for (Product product2 : productList) {
			StatsPackageDto statsPackageDto = new StatsPackageDto();
			statsPackageDto.setProductName(product2.getName());
			statsPackageDto.setAmount(product2.getAmount());
			statsPackageDto.setSalesCount(product2.getCount());
			lists.add(statsPackageDto);
		}
		apiResult.setData(lists);
		return apiResult;
	}

}