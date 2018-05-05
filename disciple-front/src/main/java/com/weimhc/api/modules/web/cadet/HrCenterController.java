package com.weimhc.api.modules.web.cadet;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.api.modules.dto.resp.cadet.ResumeDetailDto;
import com.weimhc.api.modules.service.HrCenterService;
import com.weimhc.api.modules.service.JobInManagerService;
import com.weimhc.framework.dto.resp.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Hr中心
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/19 10:35
 */
@Api
@Controller("apiHrCenterController")
@RequestMapping(value = "${apiPath}/hrCenterController")
public class HrCenterController {

    @Autowired
    private JobInManagerService jobInManagerService;

    @Autowired
    private HrCenterService hrCenterService;


    @ApiOperation(value = "职位状态数量", notes = "1，在招 2，即将过期 3，已过期", tags = {
            "DHr中心" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getJobNumByStatus", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Integer> getJobNumByStatus(HttpServletRequest request, @ApiParam(value = "公司id",required = true) @RequestParam String companyId,
                                @ApiParam(value = "职位状态类型（1，在招 2，即将过期 3，已过期）",required = true) @RequestParam String status) {
        ApiResult<Integer> apiResult = new ApiResult<>();
        if (StringUtils.isEmpty(companyId) || StringUtils.isEmpty(status)) {
            apiResult.setMessage("参数不能为空！");
            return apiResult;
        }
        Integer num = hrCenterService.getJobNumByStatus(companyId,status);
        apiResult.setData(num);
        return apiResult;
    }

    @ApiOperation(value = "简历状态数量", notes = "简历状态数量", tags = {
            "DHr中心" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getJobInNum", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Integer> getJobInNum(HttpServletRequest request, @ApiParam(value = "公司id") @RequestParam String companyId,
    @ApiParam(value = "0未处理，1通知面试，2不合适，3已录用，4待定 传空或者空字符串代表所有简历状态数量,8未读，9今日面试") @RequestParam(required = false) String status) {
        ApiResult<Integer> apiResult = new ApiResult<>();
        Integer num = jobInManagerService.getJobInNum(companyId,status);
        apiResult.setData(num);
        return apiResult;
    }

    @ApiOperation(value = "累计收到简历", notes = "累计收到简历", tags = {
            "DHr中心" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getJobInNumTotal", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Integer> getJobInNumTotal(HttpServletRequest request, @ApiParam(value = "公司id") @RequestParam String companyId) {
        ApiResult<Integer> apiResult = new ApiResult<>();
        Integer num = jobInManagerService.getJobInNumTotal(companyId);
        apiResult.setData(num);
        return apiResult;
    }


    @ApiOperation(value = "累计发布职位", notes = "累计发布职位", tags = {
            "DHr中心" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getJobNumTotal", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Integer> getJobNumTotal(HttpServletRequest request, @ApiParam(value = "公司id") @RequestParam String companyId) {
        ApiResult<Integer> apiResult = new ApiResult<>();
        Integer num = jobInManagerService.getJobNumTotal(companyId);
        apiResult.setData(num);
        return apiResult;
    }

}
