package com.weimhc.api.modules.web.cadet;

import com.weimhc.api.modules.dto.req.cadet.JobRQ;
import com.weimhc.api.modules.dto.resp.cadet.CompanyDto;
import com.weimhc.api.modules.dto.resp.cadet.IndustryOneDto;
import com.weimhc.api.modules.service.PublishhJobService;
import com.weimhc.framework.dto.resp.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 职位发布
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/24 20:44
 */
@Api
@Controller("apiPublishJobController")
@RequestMapping(value = "${apiPath}/publishJob")
public class PublishJobController {

    @Autowired
    private PublishhJobService publishhJobService;

    @Autowired
    private HomePageController homePageController;

    @ApiOperation(value = "当前发布职位次数", notes = "当前发布职位次数", tags = {
            "D发布职位" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getPublishCount", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Integer> getPublishCount(HttpServletRequest request,
                                              @ApiParam(value = "企业id", required = true) @RequestParam String companyId) {
        ApiResult<Integer> apiResult = new ApiResult<>();
        Integer count = publishhJobService.getPublishCount(companyId);
        apiResult.setData(count);
        return apiResult;
    }

    @ApiOperation(value = "职位类别列表", notes = "职位类别列表", tags = {
            "D发布职位" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/industryTags", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<IndustryOneDto>> industryTags(HttpServletRequest request) {
        return homePageController.industryTags(request);
    }


    @ApiOperation(value = "发布", notes = "发布", tags = {
            "D发布职位" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> publish(HttpServletRequest request, @RequestBody JobRQ jobRQ) {
        ApiResult<String> apiResult = new ApiResult<>();
        String publish = publishhJobService.publishJob(jobRQ);
        apiResult.setData(publish);
        return apiResult;
    }

    @ApiOperation(value = "获取公司列表（模糊搜索）", notes = "获取公司列表（模糊搜索）", tags = {
            "D发布职位" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/searchCompanyByName", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<List<CompanyDto>> searchCompanyByName(HttpServletRequest request, @ApiParam(value = "公司名") @RequestParam String name) {
        ApiResult<List<CompanyDto>> apiResult = new ApiResult<>();
        List<CompanyDto> companyDtos = publishhJobService.searchCompanyByName(name);
        apiResult.setData(companyDtos);
        return apiResult;
    }
}
