/**
 * 
 */
package com.weimhc.api.modules.web.track;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.UserAgentUtils;
import com.weimhc.admin.core.utils.VisitTrackUtils;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.modules.dto.req.VisitTrackRQ;
import com.weimhc.api.modules.dto.resp.StatsRealTimeDto;
import com.weimhc.api.modules.dto.resp.VisitTrackDto;
import com.weimhc.framework.dto.req.DefaultPageRQ;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.dto.resp.PageRS;
import com.weimhc.framework.taobao.resp.IpAddressInfo;
import com.weimhc.framework.utils.AccessUtils;
import com.weimhc.framework.utils.CustomBeanUtils;
import com.weimhc.modules.track.entity.VisitTrack;
import com.weimhc.modules.track.service.VisitTrackService;
import com.weimhc.modules.track.utils.VisitBrowserAndOsUtils;
import com.weimhc.modules.track.utils.VisitSearchSourceUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 浏览器Controller
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api
@Controller("apiAdminVisitTrackController")
@RequestMapping(value = "${apiPath}/visit/track")
public class VisitTrackController extends ApiBaseController {

	@Autowired
	private VisitTrackService visitTrackService;

	@ApiOperation(value = "获取网站浏览量记录", notes = "获取网站浏览量记录", tags = { "统计" })
	@RequestMapping(value = "findList", method = RequestMethod.GET)
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "pageNo", value = "第几页", required = true, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页多少条", required = true, paramType = "query", dataType = "int") })
	@ResponseBody
	public ApiResult<PageRS<VisitTrackDto>> findSiteBrowser(DefaultPageRQ body,
			HttpServletRequest request) {
		ApiResult<PageRS<VisitTrackDto>> apiResult = new ApiResult<>();
		VisitTrack visitTrack = new VisitTrack();
		Page<VisitTrack> page = visitTrackService.findPage(
				new Page<VisitTrack>(body.getPageNo(), body.getPageSize()),
				visitTrack);
		PageRS<VisitTrackDto> pageRS = new PageRS<>();
		List<VisitTrackDto> lists = Lists.newArrayList();
		if (page.getList().size() < 1
				|| page.getPageable().getLast() < body.getPageNo()) {
			pageRS.setCurrentCount(0);
		} else {
			lists = CustomBeanUtils.copyList(page.getList(),
					VisitTrackDto.class);
		}
		pageRS.setPageable(page.getPageable());
		pageRS.setDataList(lists);

		apiResult.setData(pageRS);

		return apiResult;
	}

	@ApiOperation(value = "获取网站浏览量记录", notes = "获取网站浏览量记录", tags = { "统计" })
	@RequestMapping(value = "findVisitorCount", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<StatsRealTimeDto> findVisitorCount(Date searchDate,
			HttpServletRequest request) {
		ApiResult<StatsRealTimeDto> apiResult = new ApiResult<>();
		long count = visitTrackService.findRealTimeVisitor(searchDate);
		VisitTrack visitTrack = new VisitTrack();
		visitTrack.setCreateDate(searchDate);
		long viewCount = visitTrackService.findRealTimeViewCount(visitTrack);
		long visitorCount = visitTrackService
				.findRealTimeVisitorCount(visitTrack);
		StatsRealTimeDto statsRealTimeDto = new StatsRealTimeDto();
		statsRealTimeDto.setRealTimeVisitor(count);
		statsRealTimeDto.setRealTimeViewCount(viewCount);
		statsRealTimeDto.setRealTimeVisitorCount(visitorCount);
		apiResult.setData(statsRealTimeDto);
		return apiResult;
	}

	/**
	 * 获取最近30分钟访客数据
	 * 
	 * @param searchDate
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "获取最近30分钟访客数据", notes = "获取最近30分钟访客数据", tags = {
			"统计" })
	@RequestMapping(value = "findLatelyVisitor", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<StatsRealTimeDto>> findLatelyVisitor(Date searchDate,
			HttpServletRequest request) {
		ApiResult<List<StatsRealTimeDto>> apiResult = new ApiResult<>();

		List<StatsRealTimeDto> statsRealTimeDtoList = Lists.newArrayList();
		for (int i = 30; i > 0; i--) {
			StatsRealTimeDto statsRealTimeDto = new StatsRealTimeDto();
			long time = i * 60 * 1000;// 30分钟
			Date beforeDate = new Date(searchDate.getTime() - time);// 30分钟前的时间
			long viewCount = visitTrackService.findMinuteViewCount(beforeDate);
			long visitorCount = visitTrackService
					.findMinuteVisitorCount(beforeDate);
			statsRealTimeDto.setRealTimeViewCount(viewCount);
			statsRealTimeDto.setRealTimeVisitorCount(visitorCount);
			statsRealTimeDto.setRealTime(beforeDate);
			statsRealTimeDtoList.add(statsRealTimeDto);
		}
		apiResult.setData(statsRealTimeDtoList);
		// apiResult.setData(statsRealTimeDto);
		return apiResult;
	}

	@RequestMapping(value = "insert")
	@ApiIgnore
	public void insert(VisitTrackRQ visitTrackRQ, HttpServletRequest request,
			HttpServletResponse response) {
		VisitTrack visitTrack = new VisitTrack();
		BeanUtils.copyProperties(visitTrackRQ, visitTrack);
		visitTrack.setIsNewRecord(true);
		visitTrack.setId(visitTrackRQ.getPageViewId());

		IpAddressInfo ipAddressInfo = AccessUtils.getIpInfo(request);
		BeanUtils.copyProperties(ipAddressInfo, visitTrack);

		VisitBrowserAndOsUtils.dealWithBrowserAndOs(visitTrack,
				UserAgentUtils.getUserAgentString(request));

		VisitSearchSourceUtils.dealWithSearchSource(visitTrack);

		VisitTrackUtils.saveLog(request, visitTrack);

		response.setStatus(200);
	}
}