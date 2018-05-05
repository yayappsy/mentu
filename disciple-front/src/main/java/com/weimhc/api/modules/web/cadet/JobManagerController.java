package com.weimhc.api.modules.web.cadet;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.api.modules.dto.req.cadet.JobRQ;
import com.weimhc.api.modules.dto.req.cadet.JobSearchRQ;
import com.weimhc.api.modules.dto.resp.cadet.JobDto;
import com.weimhc.api.modules.dto.resp.cadet.PageJobDto;
import com.weimhc.api.modules.service.HomePageService;
import com.weimhc.api.modules.service.JobManagerService;
import com.weimhc.api.modules.service.PublishhJobService;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.modules.job.dao.ExpectPracticeDao;
import com.weimhc.modules.job.entity.Job;
import com.weimhc.modules.job.service.ExpectPracticeService;
import com.weimhc.modules.job.service.JobService;
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
 * 职位管理
 *
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/4 10:53
 */
@Api
@Controller("apiJobManagerController")
@RequestMapping(value = "${apiPath}/jobManager")
public class JobManagerController {

    @Autowired
    private HomePageService homePageService;

    @Autowired
    private JobManagerService jobManagerService;

    @Autowired
    private PublishhJobService publishhJobService;

    @Autowired
    private JobService jobService;

    @Autowired
    private ExpectPracticeDao expectPracticeDao;


    @ApiOperation(value = "获取职位列表", notes = "获取职位列表", tags = {
            "D职位管理"}, authorizations = {@Authorization(value = "token")})
    @RequestMapping(value = "/jobSearch", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<PageJobDto> jobSearch(HttpServletRequest request,
                                           @ApiParam(value = "公司id") @RequestParam String companyId,
                                           @ApiParam(value = "职位名称") @RequestParam(required = false) String jobName,
                                           @ApiParam(value = "职位状态（1，招聘中 2，已经下线 3，已过期 4审核中）") @RequestParam String state,
                                           @ApiParam(value = "是否内推（0是 1不是）") @RequestParam(defaultValue = "1",required = false) String isPush,
                                           @ApiParam(value = "第几页", defaultValue = "1") @RequestParam Integer pageNo,
                                           @ApiParam(value = "数据条数", defaultValue = "10") @RequestParam Integer pageSize) {
        ApiResult<PageJobDto> apiResult = new ApiResult<>();
        JobSearchRQ jobSearchRQ = new JobSearchRQ();
        jobSearchRQ.setCompanyId(companyId);
        jobSearchRQ.setSearchName(jobName);
        jobSearchRQ.setState(state);
        jobSearchRQ.setIsPush(isPush);
        PageJobDto pageJobDto = homePageService.getJobBySearch(jobSearchRQ, pageNo, pageSize);
        apiResult.setData(pageJobDto);
        return apiResult;
    }

    @ApiOperation(value = "推荐职位列表", notes = "推荐职位列表", tags = {
            "D职位管理"}, authorizations = {@Authorization(value = "token")})
    @RequestMapping(value = "/jobRecommend", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<PageJobDto> jobRecommend(HttpServletRequest request,
                                           @ApiParam(value = "学生id") @RequestParam String userId,
                                           @ApiParam(value = "第几页", defaultValue = "1") @RequestParam Integer pageNo,
                                           @ApiParam(value = "数据条数", defaultValue = "10") @RequestParam Integer pageSize) {
        ApiResult<PageJobDto> apiResult = new ApiResult<>();
        String industryIds = expectPracticeDao.getIndustryIds(userId);
        JobSearchRQ jobSearchRQ = new JobSearchRQ();
        jobSearchRQ.setIndustryId(industryIds);
        PageJobDto pageJobDto = homePageService.getJobBySearch(jobSearchRQ, pageNo, pageSize);
        apiResult.setData(pageJobDto);
        return apiResult;
    }

    @ApiOperation(value = "获取职位", notes = "获取职位", tags = {
            "D职位管理"}, authorizations = {@Authorization(value = "token")})
    @RequestMapping(value = "/getJobById", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<JobDto> getJobById(HttpServletRequest request, @ApiParam(value = "职位id") @RequestParam String jobId,
                                        @ApiParam(value = "是否内推（0是 1不是）") @RequestParam(required = false) String type) {
        ApiResult<JobDto> apiResult = new ApiResult<>();
        JobDto jobDto = jobManagerService.getJobById(jobId,type);
        apiResult.setData(jobDto);
        return apiResult;
    }

    @ApiOperation(value = "修改发布", notes = "修改发布", tags = {
            "D职位管理"}, authorizations = {@Authorization(value = "token")})
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> publish(HttpServletRequest request, @RequestBody JobRQ jobRQ) {
        ApiResult<String> apiResult = new ApiResult<>();
        if (StringUtils.isEmpty(jobRQ.getId())) {
            return ApiResult.error(-1, "更新时id不能为空！");
        }
        if (jobService.get(jobRQ.getId()) == null) {
            return ApiResult.error(-1, "不存在的职位！");
        }
        String publish = publishhJobService.publishJob(jobRQ);
        apiResult.setData(publish);
        return apiResult;
    }

    /**
     * 下线
     *
     * @param request
     * @param ids
     * @return
     */
    @ApiOperation(value = "下线", notes = "下线", tags = {
            "D职位管理"}, authorizations = {@Authorization(value = "token")})
    @RequestMapping(value = "/lineOff", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> lineOff(HttpServletRequest request, @ApiParam(value = "职位ids") @RequestParam List<String> ids) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        if (ids == null) {
            return ApiResult.error(-1, "后台未接收到ids的值！");
        }
        Boolean off = jobManagerService.lineOff(ids);
        apiResult.setData(off);
        return apiResult;
    }

    @ApiOperation(value = "上线", notes = "上线", tags = {
            "D职位管理"}, authorizations = {@Authorization(value = "token")})
    @RequestMapping(value = "/lineOn", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> lineOn(HttpServletRequest request, @ApiParam(value = "职位ids") @RequestParam List<String> ids,
                                     @ApiParam(value = "过期时间（天）") @RequestParam Integer days) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        if (ids == null) {
            return ApiResult.error(-1, "后台未接收到ids的值！");
        }
        Boolean off = jobManagerService.lineOn(ids,days);
        apiResult.setData(off);
        return apiResult;
    }


    @ApiOperation(value = "删除职位", notes = "删除职位", tags = {
            "D职位管理"}, authorizations = {@Authorization(value = "token")})
    @RequestMapping(value = "/delJob", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> delJob(HttpServletRequest request, @ApiParam(value = "职位id") @RequestParam String id) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        Job job = new Job();
        job.setId(id);
        jobService.deleteJob(job);
        apiResult.setData(true);
        return apiResult;
    }

    @ApiOperation(value = "下线职位一键删除", notes = "下线职位一键删除", tags = {
            "D职位管理"}, authorizations = {@Authorization(value = "token")})
    @RequestMapping(value = "/delJobs", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> delJobs(HttpServletRequest request, @ApiParam(value = "职位ids") @RequestParam List<String> ids) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        for (String id : ids) {
            Job job = new Job();
            job.setId(id);
            jobService.deleteJob(job);
        }
        apiResult.setData(true);
        return apiResult;
    }
    @ApiOperation(value = "刷新", notes = "刷新", tags = {
            "D职位管理"}, authorizations = {@Authorization(value = "token")})
    @RequestMapping(value = "/fresh", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> fresh(HttpServletRequest request, @ApiParam(value = "职位ids") @RequestParam List<String> ids) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        if (ids == null) {
            return ApiResult.error(-1, "后台未接收到ids的值！");
        }
        Boolean off = jobService.fresh(ids);
        apiResult.setData(off);
        return apiResult;
    }
}
