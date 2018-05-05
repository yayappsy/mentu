package com.weimhc.api.modules.web.cadet;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.api.modules.dto.req.cadet.SendEmailQR;
import com.weimhc.api.modules.dto.req.cadet.resume.CompanyContactRQ;
import com.weimhc.api.modules.dto.req.cadet.resume.ResumeSearchRQ;
import com.weimhc.api.modules.dto.resp.cadet.JobInResumeDto;
import com.weimhc.api.modules.dto.resp.cadet.PageJobInResumeDto;
import com.weimhc.api.modules.dto.resp.cadet.ResumeDetailDto;
import com.weimhc.api.modules.service.JobInManagerService;
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
 * 应聘管理
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/8 22:08
 */
@Api
@Controller("apiJobInManagerController")
@RequestMapping(value = "${apiPath}/jobInManager")
public class JobInManagerController {

    @Autowired
    private JobInManagerService jobInManagerService;

    @ApiOperation(value = "待处理简历数", notes = "待处理简历数", tags = {
            "D应聘管理" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/handleNum", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Long> handleNum(HttpServletRequest request, @ApiParam(value = "公司id") @RequestParam String companyId) {
        ApiResult<Long> apiResult = new ApiResult<>();
        Long num = jobInManagerService.handleNum(companyId);
        apiResult.setData(num);
        return apiResult;
    }

    @ApiOperation(value = "查看简历（被查看）", notes = "查看简历前调用", tags = {
            "D应聘管理" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/lookResume", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> lookResume(HttpServletRequest request,
                                         @ApiParam(value = "企业id", required = true)@RequestParam String companyId,
                                         @ApiParam(value = "简历id", required = true)@RequestParam String resumeId) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        Boolean aBoolean = jobInManagerService.lookResume(companyId, resumeId);
        apiResult.setData(aBoolean);
        return apiResult;
    }

    @ApiOperation(value = "保存邀请模板", notes = "保存邀请模板", tags = {
            "D应聘管理" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/commitCompanyContact", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> commitCompanyContact(HttpServletRequest request, @RequestBody CompanyContactRQ companyContactRQ) {
        ApiResult<String> apiResult = new ApiResult<>();
        jobInManagerService.saveCompanyContact(companyContactRQ);
        return apiResult;
    }

    @ApiOperation(value = "获取邀请模板", notes = "获取邀请模板", tags = {
            "D应聘管理" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getCompanyContacts", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<CompanyContactRQ>> getCompanyContacts(HttpServletRequest request, @ApiParam(value = "企业Id")@RequestParam String id) {
        ApiResult<List<CompanyContactRQ>> apiResult = new ApiResult<>();
        List<CompanyContactRQ> resumeCompanyContactRQS = jobInManagerService.getCompanyContacts(id);
        apiResult.setData(resumeCompanyContactRQS);
        return apiResult;
    }

    @ApiOperation(value = "删除邀请模板", notes = "删除邀请模板", tags = {
            "D应聘管理" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/delCompanyContact", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> delCompanyContact(HttpServletRequest request,@ApiParam(value = "邀请模板Id")@RequestParam String id) {
        ApiResult<String> apiResult = new ApiResult<>();
        jobInManagerService.delCompanyContact(id);
        return apiResult;
    }

    @ApiOperation(value = "获取简历详情", notes = "获取简历详情", tags = {
            "D应聘管理" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getResumeDetail", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<ResumeDetailDto> getResumeDetail(HttpServletRequest request, @ApiParam(value = "简历id")@RequestParam String id) {
        ApiResult<ResumeDetailDto> apiResult = new ApiResult<>();
        ResumeDetailDto resumeDetail = jobInManagerService.getResumeDetail(id);
        apiResult.setData(resumeDetail);
        return apiResult;
    }

    @ApiOperation(value = "处理简历", notes = "处理简历", tags = {
            "D应聘管理" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/handResume", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> handResume(HttpServletRequest request,@ApiParam(value = "应聘管理id")@RequestParam String id,
                                        @ApiParam(value = "处理结果（1通知面试，2不合适，3已录用，4待定）") @RequestParam String result) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        Boolean aBoolean = jobInManagerService.handResume(id, result);
        apiResult.setData(aBoolean);
        return apiResult;
    }

    @ApiOperation(value = "发送邮件通知", notes = "发送邮件通知", tags = {
            "D应聘管理" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> sendEmail(HttpServletRequest request, @RequestBody SendEmailQR sendEmailQR) throws Exception {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        Boolean aBoolean = jobInManagerService.sendEmail(sendEmailQR);
        apiResult.setData(aBoolean);
        return apiResult;
    }

    @ApiOperation(value = "简历搜索", notes = "简历搜索", tags = {
            "D应聘管理" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/searchResume", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<PageJobInResumeDto> searchResume(HttpServletRequest request, @RequestBody ResumeSearchRQ resumeSearchRQ,
                                                      @ApiParam(value = "第几页", defaultValue = "1") @RequestParam Integer pageNo,
                                                      @ApiParam(value = "数据条数", defaultValue = "10") @RequestParam Integer pageSize) {
        ApiResult<PageJobInResumeDto> apiResult = new ApiResult<>();
        if (StringUtils.isEmpty(resumeSearchRQ.getCompanyId())) {
            return ApiResult.error(-1, "企业id不可以为空！");
        }
        PageJobInResumeDto pageJobInResumeDto = jobInManagerService.searchResume(resumeSearchRQ,pageNo,pageSize);
        apiResult.setData(pageJobInResumeDto);
        return apiResult;
    }


    @ApiOperation(value = "全部职位分类", notes = "全部职位分类", tags = {
            "D应聘管理" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getJobNames", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<String>> getJobNames(HttpServletRequest request, @ApiParam(value = "公司id")@RequestParam String id) {
        ApiResult<List<String>> apiResult = new ApiResult<>();
        List<String> data = jobInManagerService.getJobNames(id);
        apiResult.setData(data);
        return apiResult;
    }


}
