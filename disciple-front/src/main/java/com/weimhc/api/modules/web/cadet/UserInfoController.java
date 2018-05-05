package com.weimhc.api.modules.web.cadet;

import com.weimhc.api.modules.dto.req.cadet.JobSearchRQ;
import com.weimhc.api.modules.dto.req.cadet.UserInfoRQ;
import com.weimhc.api.modules.dto.resp.cadet.*;
import com.weimhc.api.modules.service.UserInfo2Service;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.modules.job.entity.Resume;
import com.weimhc.modules.job.service.ResumeService;
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
 * 用户
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/26 11:35
 */
@Api
@Controller("apiUserInfoController")
@RequestMapping(value = "${apiPath}/userInfo")
public class UserInfoController {

    @Autowired
    private HomePageController homePageController;

    @Autowired
    private CreateResumeController createResumeController;

    @Autowired
    private UserInfo2Service userInfo2Service;

    @Autowired
    private ResumeService resumeService;

    @ApiOperation(value = "编辑个人信息", notes = "编辑个人信息", tags = {
            "D个人中心" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> editUser(HttpServletRequest request,
                                        @RequestBody UserInfoRQ userInfoDto) {
        return createResumeController.editUser(request,userInfoDto);
    }

    @ApiOperation(value = "获取职位列表", notes = "获取职位列表", tags = {
            "D个人中心" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/jobSearch", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<PageJobDto> jobSearch(HttpServletRequest request,
                                           @ApiParam(value = "第几页", defaultValue = "1") @RequestParam Integer pageNo,
                                           @ApiParam(value = "数据条数", defaultValue = "10") @RequestParam Integer pageSize){
        JobSearchRQ jobSearchRQ = new JobSearchRQ();
        // Dx TODO
        return homePageController.jobSearch(request, jobSearchRQ, pageNo, pageSize);
    }


    @ApiOperation(value = "职位收藏职业", notes = "职位收藏职业", tags = {
            "D个人中心" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/jobCollect", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> jobCollect(HttpServletRequest request,
                                         @ApiParam(value = "用户id", required = true) @RequestParam String numberId,
                                         @ApiParam(value = "职位id", required = true) @RequestParam String jobId){
        ApiResult<Boolean> apiResult = new ApiResult<>();
        Boolean aBoolean = userInfo2Service.collect(numberId, jobId, "1");
        apiResult.setData(aBoolean);
        return apiResult;
    }

    @ApiOperation(value = "取消收藏职业", notes = "取消收藏职业", tags = {
            "D个人中心" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/cancelJobCollect", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> cancelJobCollect(HttpServletRequest request,
                                         @ApiParam(value = "用户id", required = true) @RequestParam String numberId,
                                         @ApiParam(value = "职位id", required = true) @RequestParam String jobId){
        ApiResult<Boolean> apiResult = new ApiResult<>();
        Boolean aBoolean = userInfo2Service.cancelCollect(numberId,jobId,"1");
        apiResult.setData(aBoolean);
        return apiResult;
    }

    @ApiOperation(value = "职位收藏公司", notes = "职位收藏公司", tags = {
            "D个人中心" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/companyCollect", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> companyCollect(HttpServletRequest request,
                                         @ApiParam(value = "用户id", required = true) @RequestParam String numberId,
                                         @ApiParam(value = "公司id", required = true) @RequestParam String companyId){
        ApiResult<Boolean> apiResult = new ApiResult<>();
        Boolean aBoolean = userInfo2Service.collect(numberId, companyId, "2");
        apiResult.setData(aBoolean);
        return apiResult;
    }

    @ApiOperation(value = "取消收藏公司", notes = "取消收藏公司", tags = {
            "D个人中心" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/cancelCompanyCollect", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> cancelCompanyCollect(HttpServletRequest request,
                                               @ApiParam(value = "用户id", required = true) @RequestParam String numberId,
                                               @ApiParam(value = "公司id", required = true) @RequestParam String companyId){
        ApiResult<Boolean> apiResult = new ApiResult<>();
        Boolean aBoolean = userInfo2Service.cancelCollect(numberId,companyId,"2");
        apiResult.setData(aBoolean);
        return apiResult;
    }


    @ApiOperation(value = "我的收藏-职位", notes = "我的收藏-职位", tags = {
            "D个人中心" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/myCollectJob", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<PageJobDto> myCollectJob(HttpServletRequest request,
                                             @ApiParam(value = "用户id", required = true) @RequestParam String mumberId,
                                              @ApiParam(value = "第几页", defaultValue = "1") @RequestParam Integer pageNo,
                                              @ApiParam(value = "数据条数", defaultValue = "10") @RequestParam Integer pageSize){
        ApiResult<PageJobDto> apiResult = new ApiResult<>();
        PageJobDto pageJobDto = userInfo2Service.myCollectJob(mumberId,pageNo,pageSize);
        apiResult.setData(pageJobDto);
        return apiResult;
    }
    @ApiOperation(value = "我的收藏-公司", notes = "我的收藏-公司", tags = {
            "D个人中心" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/myCollectCompany", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<PageCompanyDto> myCollectCompany(HttpServletRequest request,
                                                        @ApiParam(value = "用户id", required = true) @RequestParam String memberId,
                                                      @ApiParam(value = "第几页", defaultValue = "1") @RequestParam Integer pageNo,
                                                      @ApiParam(value = "数据条数", defaultValue = "10") @RequestParam Integer pageSize){
        ApiResult<PageCompanyDto> apiResult = new ApiResult<>();
        PageCompanyDto pageCompanyDto = userInfo2Service.myCoolectComany(memberId,pageNo,pageSize);
        apiResult.setData(pageCompanyDto);
        return apiResult;
    }

    @ApiOperation(value = "我的简历List", notes = "我的简历List", tags = {
            "D个人中心" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/myResumeList", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<List<ResumeDto>> myResumeList(HttpServletRequest request,
                                                   @ApiParam(value = "用户id", required = true) @RequestParam String memberId
                                                      ){
        ApiResult<List<ResumeDto>> apiResult = new ApiResult<>();
        List<ResumeDto> resumeList= userInfo2Service.myResumeList(memberId);
        apiResult.setData(resumeList);
        return apiResult;
    }

    @ApiOperation(value = "简历反馈", notes = "简历反馈", tags = {
            "D个人中心" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/reJobIn", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<List<ReDeliverDto>> reJobIn(HttpServletRequest request,
                                                   @ApiParam(value = "用户id", required = true) @RequestParam String memberId,
                                                   @ApiParam(value = "状态（0投递成功，1通知面试，2不合适，9被查看）传空字符为全部") @RequestParam String type
    ){
        ApiResult<List<ReDeliverDto>> apiResult = new ApiResult<>();
        List<ReDeliverDto> resumeList= userInfo2Service.reJobInList(memberId,type);
        apiResult.setData(resumeList);
        return apiResult;
    }

    @ApiOperation(value = "删除简历", notes = "删除简历", tags = {
            "D个人中心" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> delete(HttpServletRequest request,
                                                 @ApiParam(value = "简历id", required = true) @RequestParam String resumeId
    ){
        ApiResult<Boolean> apiResult = new ApiResult<>();
        resumeService.delete(new Resume(resumeId));
        apiResult.setData(true);
        return apiResult;
    }

}
