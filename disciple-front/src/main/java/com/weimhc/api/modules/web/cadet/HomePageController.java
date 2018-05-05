package com.weimhc.api.modules.web.cadet;

import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.api.modules.dto.req.cadet.JobSearchRQ;
import com.weimhc.api.modules.dto.req.cadet.StudentCreateDateRQ;
import com.weimhc.api.modules.dto.resp.cadet.*;
import com.weimhc.api.modules.service.CompanyDetailService;
import com.weimhc.api.modules.service.HomePageService;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.modules.collect.entity.Collect;
import com.weimhc.modules.collect.service.CollectService;
import com.weimhc.modules.company.entity.Company;
import com.weimhc.modules.company.service.CompanyService;
import com.weimhc.modules.industry.dao.IndustryDao;
import com.weimhc.modules.industry.service.IndustryService;
import com.weimhc.modules.job.entity.CollectStore;
import com.weimhc.modules.job.entity.Communication;
import com.weimhc.modules.job.service.CollectStoreService;
import com.weimhc.modules.job.service.CommunicationService;
import com.weimhc.modules.sys.entity.Area;
import com.weimhc.modules.sys.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/20 11:55
 */
@Api
@Controller("apiIndustryController")
@RequestMapping(value = "${apiPath}/homepage")
public class HomePageController {

    @Autowired
    private HomePageService homePageService;

    @Autowired
    private IndustryService industryService;

    @Autowired
    private IndustryDao industryDao;

    @Autowired
    private CompanyDetailService companyDetailService;

    @Autowired
    private CommunicationService communicationService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private CollectStoreService collectStoreService;

    @Autowired
    private CompanyService companyService;

    @ApiOperation(value = "全部分类-行业标签列表", notes = "全部分类-行业标签列表", tags = {
            "D工作" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/industryTags", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<IndustryOneDto>> industryTags(HttpServletRequest request) {
        ApiResult<List<IndustryOneDto>> apiResult = new ApiResult<>();
        List<IndustryOneDto> industryOneDtos = homePageService.getIndustryTags();
        apiResult.setData(industryOneDtos);
        return apiResult;
    }

    @ApiOperation(value = "热招职业数", notes = "热招职业数", tags = {
            "D工作" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getHitNum", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Integer> getHitNum(HttpServletRequest request) {
        ApiResult<Integer> apiResult = new ApiResult<>();
        Integer hitNum = homePageService.getHitNum();
        apiResult.setData(hitNum);
        return apiResult;
    }

    @ApiOperation(value = "获取职位列表", notes = "获取职位列表", tags = {
            "D工作" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/jobSearch", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<PageJobDto> jobSearch(HttpServletRequest request, @RequestBody JobSearchRQ jobSearchRQ,
                                           @ApiParam(value = "第几页", defaultValue = "1") @RequestParam Integer pageNo,
                                           @ApiParam(value = "数据条数", defaultValue = "10") @RequestParam Integer pageSize){
        ApiResult<PageJobDto> apiResult = new ApiResult<>();
        PageJobDto pageJobDto = homePageService.getJobBySearch(jobSearchRQ, pageNo, pageSize);
        apiResult.setData(pageJobDto);
        return apiResult;
    }



    @ApiOperation(value = "获取聊天信息(notes里的json数据即可，其它参数不用考虑)", notes = "{\n" +
            "  \"companyId\": \"string\",\n" +
            "\n" +
            "  \"content\": \"string\",\n" +
            "\n" +
            "  \"isRead\": \"string\",\n" +
            "\n" +
            "  \"type\": \"string\",\n" +
            "\n" +
            "  \"userId\": \"string\"\n" +
            "\n" +
            "}" +
            "\n" +
            "isRead->是否已读（0未读，1已读\n" +
            "content->内容\n" +
            "userId->用户id\n" +
            "companyId->企业id\n" +
            "type->类型（1,用户发送给企业  2，企业发送给用户）", tags = {
            "D工作"}, authorizations = {@Authorization(value = "token")})
    @RequestMapping(value = "/getCommunicationList", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Page<Communication>> listByNotRead(HttpServletRequest request,
                                                           @RequestBody Communication communication,
                                                           @ApiParam(value = "第几页", defaultValue = "1") @RequestParam Integer pageNo,
                                                           @ApiParam(value = "数据条数", defaultValue = "10") @RequestParam Integer pageSize
    ) {
        ApiResult<Page<Communication>> apiResult = new ApiResult<>();
        Page<Communication> page = communicationService.findPage(new Page<>(pageNo, pageSize), communication);
        apiResult.setData(page);
        return apiResult;
    }

    @ApiOperation(value = "消息已读", notes = "消息已读", tags = {
            "D工作" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/CommunicationIsRead", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> isRead(HttpServletRequest request,
                                                              @ApiParam(value = "聊天消息id", required = true)@RequestParam String id
                                                              ) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        Communication communication = communicationService.get(id);
        communication.setIsRead("1");
        communicationService.save(communication);
        apiResult.setData(true);
        return apiResult;
    }

    @ApiOperation(value = "注册学生列表", notes = "注册学生列表", tags = {
            "D工作" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/listAllStudent", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<List<StudentDto>> listAllStudent(HttpServletRequest request,
                                     @RequestBody StudentCreateDateRQ studentCreateDateRQ
    ) {
        ApiResult<List<StudentDto>> apiResult = new ApiResult<>();
        List<StudentDto> studentDtos = homePageService.listAllStudent(studentCreateDateRQ.getStartDate(), studentCreateDateRQ.getEndDate());
        apiResult.setData(studentDtos);
        return apiResult;
    }

    @ApiOperation(value = "发送消息", notes = "发送消息", tags = {
            "D工作" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/addCommunication", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> addCommunication(HttpServletRequest request,
                                     @ApiParam(value = "用户id", required = true)@RequestParam String userId,
                                     @ApiParam(value = "企业id", required = true)@RequestParam String companyId,
                                     @ApiParam(value = "类型（1,用户发送给企业  2，企业发送给用户）", required = true)@RequestParam String type,
                                     @ApiParam(value = "发送内容", required = true)@RequestParam String content

    ) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(companyId)) {
            return ApiResult.error(-1, "错误，参数为空！");
        }
        Communication communication = new Communication();
        communication.setUserId(userId);
        communication.setCompanyId(companyId);
        communication.setType(type);
        communication.setContent(content);
        communication.setIsRead("0");
        communicationService.save(communication);
        apiResult.setData(true);
        return apiResult;
    }

    @ApiOperation(value = "首页展示的企业", notes = "首页展示的企业", tags = {
            "D工作" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getCompanyInfoByHomeShow", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<List<CompanyDto>> companyInfo(HttpServletRequest request
                                             ) {
        ApiResult<List<CompanyDto>> apiResult = new ApiResult<>();
        List<CompanyDto> companyDtos = companyDetailService.getCompanyInfoByHomeShow();
        apiResult.setData(companyDtos);
        return apiResult;
    }

    @ApiOperation(value = "获取热门城市", notes = "获取热门城市", tags = {
            "D工作" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getHitCity", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<List<Area>> getHitCity(HttpServletRequest request
    ) {
        ApiResult<List<Area>> apiResult = new ApiResult<>();
        List<Area> areas = areaService.getHitCity();
        apiResult.setData(areas);
        return apiResult;
    }

    @ApiOperation(value = "是否收藏", notes = "是否收藏(职位和企业通用)", tags = {
            "D工作" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/isCollect", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> isCollect(HttpServletRequest request,
                                            @ApiParam(value = "用户id", required = true)@RequestParam String userId,
                                            @ApiParam(value = "收藏id(职位id或者企业id)", required = true)@RequestParam String collectorId
    ) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        CollectStore collectStore = new CollectStore();
        collectStore.setCollectorId(collectorId);
        collectStore.setUserId(userId);
        List<CollectStore> list = collectStoreService.findList(collectStore);
        if (list.size() == 0) {
            apiResult.setData(false);
        } else {
            apiResult.setData(true);
        }
        return apiResult;
    }


    @ApiOperation(value = "是否有企业邮箱", notes = "是否有企业邮箱", tags = {
            "D工作" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/isHaveEmail", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean > isHaveEmail(HttpServletRequest request,
                                        @ApiParam(value = "企业id", required = true)@RequestParam String companyId
    ) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        Company company = companyService.get(companyId);
        if (company == null) {
            apiResult.setData(false);
            return apiResult;
        }
        if (StringUtils.isEmpty(company.getEmail())) {
            apiResult.setData(false);
        } else {
            apiResult.setData(true);
        }
        return apiResult;
    }
}
