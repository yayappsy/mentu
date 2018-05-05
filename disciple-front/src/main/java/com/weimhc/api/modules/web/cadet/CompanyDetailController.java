package com.weimhc.api.modules.web.cadet;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.api.modules.dto.req.cadet.CompanyRQ;
import com.weimhc.api.modules.dto.resp.cadet.CompanyDto;
import com.weimhc.api.modules.dto.resp.cadet.JobDto;
import com.weimhc.api.modules.dto.resp.cadet.PageCompanyDto;
import com.weimhc.api.modules.service.CompanyDetailService;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.modules.company.service.CompanyLabelService;
import com.weimhc.modules.job.service.JobService;
import com.weimhc.modules.user.entity.UserInfo;
import com.weimhc.modules.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/25 16:07
 */
@Api
@Controller("apiCompanyDetailController")
@RequestMapping(value = "${apiPath}/companyDetail")
public class CompanyDetailController {

    @Autowired
    private CompanyDetailService companyDetailService;

    @Autowired
    private CompanyLabelService companyLabelService;

    @Autowired
    private JobService jobService;

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "企业信息", notes = "企业信息", tags = {
            "D企业详情" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/companyInfo", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<CompanyDto> companyInfo(HttpServletRequest request,
                                               @ApiParam(value = "企业id", required = true)@RequestParam String companyId) {
        ApiResult<CompanyDto> apiResult = new ApiResult<>();
        CompanyDto companyDto = companyDetailService.getCompanyInfo(companyId);
        apiResult.setData(companyDto);
        return apiResult;
    }

    @ApiOperation(value = "内推企业列表", notes = "内推企业列表", tags = {
            "D企业详情" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getPushCompanyList", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<PageCompanyDto> getPushCompanyList(HttpServletRequest request,
                                                        @ApiParam(value = "第几页", defaultValue = "1") @RequestParam Integer pageNo,
                                                        @ApiParam(value = "数据条数", defaultValue = "10") @RequestParam Integer pageSize) {
        ApiResult<PageCompanyDto> apiResult = new ApiResult<>();
        PageCompanyDto companyDtos = companyDetailService.getPushCompanyList(pageNo,pageSize);
        apiResult.setData(companyDtos);
        return apiResult;
    }

    @ApiOperation(value = "企业信息更新", notes = "企业信息更新", tags = {
            "D企业详情" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/updateCompany", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> updateCompany(HttpServletRequest request,
                                          @RequestBody CompanyRQ companyRQ) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        if (StringUtils.isEmpty(companyRQ.getId())) {
            return ApiResult.error(-1, "id不能为空");
        }
        Boolean save = companyDetailService.updateCompany(companyRQ);
        if (!save) {
            apiResult.setCode(-1);
            apiResult.setMessage("id错误！");
        }
        apiResult.setData(save);
        return apiResult;
    }



    @ApiOperation(value = "企业职位标签", notes = "企业职位标签", tags = {
            "D企业详情" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getLabels", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Map<String,String>> getLabels(HttpServletRequest request,
                                    @ApiParam(value = "企业id", required = true)@RequestParam String companyId) {
        ApiResult<Map<String,String>> apiResult = new ApiResult<>();
        Map<String,String> map = companyLabelService.getCompanyLabel(companyId);
        apiResult.setData(map);
        return apiResult;
    }

    @ApiOperation(value = "公司福利", notes = "公司福利", tags = {
            "D企业详情" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getCompanyWeal", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<List<String>> getCompanyWeal(HttpServletRequest request,
                                                   @ApiParam(value = "企业id", required = true)@RequestParam String companyId) {
        ApiResult<List<String>> apiResult = new ApiResult<>();
        List<String> weals = companyLabelService.getCompanyWeal(companyId);
        apiResult.setData(weals);
        return apiResult;
    }

    @ApiOperation(value = "企业职位", notes = "企业职位", tags = {
            "D企业详情" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getJobs", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<List<JobDto>> getJobs(HttpServletRequest request,
                                           @ApiParam(value = "企业id", required = true)@RequestParam String companyId,
                                           @ApiParam(value = "公司标签id")@RequestParam(required = false) String labelId,
                                           @ApiParam(value = "是否热招（0不是，1是）")@RequestParam String isHitJob) {
        ApiResult<List<JobDto>> apiResult = new ApiResult<>();
        List<JobDto> jobDtos = companyDetailService.getCompanyJobs(companyId, labelId, isHitJob);
        apiResult.setData(jobDtos);
        return apiResult;
    }
    @ApiOperation(value = "企业信息提交", notes = "企业信息提交", tags = {
            "D企业详情" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/saveCompany", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> saveCompany(HttpServletRequest request,
                                               @RequestBody CompanyRQ companyRQ) {
        ApiResult<String> apiResult = new ApiResult<>();
        if (StringUtils.isNotEmpty(companyDetailService.isPass(companyRQ.getUserInfoId()))) {
            return ApiResult.error(-1, "企业登录id已使用");
        }
        UserInfo userInfo = userInfoService.get(companyRQ.getUserInfoId());
        if (userInfo == null) {
            return ApiResult.error(-1, "不存在的企业用户，请注册！");
        }
        String save = companyDetailService.saveCompany(companyRQ);
        apiResult.setData(save);
        return apiResult;
    }


    @ApiOperation(value = "企业审核是否通过", notes = "是否通过审核(-1等待审核，0不通过，1通过)", tags = {
            "D企业详情" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/companyIsPass", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> isPass(HttpServletRequest request,
                                     @ApiParam(value = "登录Id") @RequestParam String id) {
        ApiResult<String> apiResult = new ApiResult<>();
        String save = companyDetailService.isPass(id);
        apiResult.setData(save);
        apiResult.setMessage("-1等待审核，0不通过，1通过)");
        return apiResult;
    }

    @ApiOperation(value = "企业id和职位id绑定（内推用）", notes = "企业id和职位id绑定（内推用）", tags = {
            "D企业详情" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/bindCompanyJob", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> bindCompanyJob(HttpServletRequest request,
                                    @ApiParam(value = "企业id") @RequestParam String companyId,
                                    @ApiParam(value = "职位id") @RequestParam String jobId
                                            ) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        Boolean save = jobService.bindCompanyJob(companyId, jobId);
        apiResult.setData(save);
        return apiResult;
    }


    @ApiOperation(value = "获取企业id", notes = "通过当前登录id获取企业id", tags = {
            "D企业详情" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getCompanyId", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> getCompanyId(HttpServletRequest request,
                                    @ApiParam(value = "登录Id") @RequestParam String id) {
        ApiResult<String> apiResult = new ApiResult<>();
        String save = companyDetailService.getCompanyId(id);
        apiResult.setData(save);
        return apiResult;
    }

    @ApiOperation(value = "投递简历", notes = "投递简历", tags = {
            "D企业详情" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/deliver", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> deliver(HttpServletRequest request,
                                          @ApiParam(value = "企业Id") @RequestParam String companyId,
                                          @ApiParam(value = "职位Id(用于投递次数)") @RequestParam String jobId,
                                          @ApiParam(value = "简历Id") @RequestParam String resumeId,
                                          @ApiParam(value = "用户id") @RequestParam String userId
                                     ) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        Boolean result = companyDetailService.deliver(companyId, jobId, resumeId,userId);
        apiResult.setData(result);
        return apiResult;
    }
}
