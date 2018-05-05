package com.weimhc.api.modules.web.cadet;

import com.thinkgem.javamg.common.utils.CacheUtils;
import com.weimhc.api.modules.dto.req.cadet.SendEmailQR;
import com.weimhc.api.modules.service.JobInManagerService;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.modules.company.entity.Company;
import com.weimhc.modules.company.service.CompanyService;
import com.weimhc.modules.job.service.RelatedOptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 账号管理
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/24 20:05
 */
@Api
@Controller("apiAccountManageController")
@RequestMapping(value = "${apiPath}/AccountManage")
public class AccountManageController {

    @Autowired
    private JobInManagerService jobInManagerService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private RelatedOptionService relatedOptionService;


    @ApiOperation(value = "发送到邮箱", notes = "富文本，请求前需要请求（获取邮箱验证码）", tags = {
            "D账号管理"}, authorizations = {@Authorization(value = "token")})
    @RequestMapping(value = "/sendCaptcha", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> sendCaptcha(HttpServletRequest request,
                                          @RequestBody SendEmailQR sendEmailQR) throws Exception{
        ApiResult<Boolean> apiResult = new ApiResult<>();
        jobInManagerService.sendEmail(sendEmailQR);
        return apiResult;
    }

    @ApiOperation(value = "获取邮箱验证码", notes = "获取邮箱验证码", tags = {
            "D账号管理"}, authorizations = {@Authorization(value = "token")})
    @RequestMapping(value = "/getEmailCaptcha", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> getEmailCaptcha(HttpServletRequest request,
                                          @ApiParam(value = "企业id", required = true) @RequestParam String companyId) {
        ApiResult<String> apiResult = new ApiResult<>();
        String random = createRandom();
        CacheUtils.put(companyId,random);
        apiResult.setData(random);
        return apiResult;
    }

    @ApiOperation(value = "资料审核中", notes = "提交审核资料后调用", tags = {
            "D账号管理"}, authorizations = {@Authorization(value = "token")})
    @RequestMapping(value = "/setAuditing", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> setAuditing(HttpServletRequest request,
                                             @ApiParam(value = "企业id", required = true) @RequestParam String companyId) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        Integer result = companyService.setAuditing(companyId);
        if (result != 1) {
            return ApiResult.error(-1, "已经在审核中了，不要再提交了！");
        }
        apiResult.setData(true);
        return apiResult;
    }

    @ApiOperation(value = "企业资料提交", notes = "企业资料提交", tags = {
            "D账号管理"}, authorizations = {@Authorization(value = "token")})
    @RequestMapping(value = "/proofData", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> setAuditing(HttpServletRequest request,
                                          @ApiParam(value = "企业id", required = true) @RequestParam String companyId,
                                          @ApiParam(value = "企业证明资料文件位置", required = true) @RequestParam String proofData) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        if (null == companyService.get(companyId)) {
            return ApiResult.error(-1, "不存在的企业id");
        }
        Company company = new Company();
        company.setId(companyId);
        company.setProofData(proofData);
        companyService.updateProofData(company);
        apiResult.setData(true);
        return apiResult;
    }

    @ApiOperation(value = "绑定手机号", notes = "绑定手机号", tags = {
            "D账号管理"}, authorizations = {@Authorization(value = "token")})
    @RequestMapping(value = "/bindPhone", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> bindPhone(HttpServletRequest request,
                                             @ApiParam(value = "企业用户id", required = true) @RequestParam String id,
                                       @ApiParam(value = "手机号") @RequestParam String phone) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        companyService.bindPhone(id, phone);
        apiResult.setData(true);
        return apiResult;
    }

    @ApiOperation(value = "提交验证码", notes = "提交验证码", tags = {
            "D账号管理"}, authorizations = {@Authorization(value = "token")})
    @RequestMapping(value = "/commitCaptcha", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> commitCaptcha(HttpServletRequest request,
                                            @ApiParam(value = "企业id", required = true) @RequestParam String companyId,
                                            @ApiParam(value = "公司法律名称", required = true) @RequestParam String legalName,
                                            @ApiParam(value = "验证码", required = true) @RequestParam String captcha
    ) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        apiResult.setData(true);
        captcha = captcha.toUpperCase();
        String result = CacheUtils.get(companyId).toString();
        if (!result.equals(captcha)) {
            apiResult.setMessage("验证码错误！");
        }
        Company company = companyService.get(companyId);
        if (company == null) {
            apiResult.setMessage("企业信息未查询到！");
        }
        company.setLegalName(legalName);
        companyService.save(company);
        apiResult.setData(true);
        return apiResult;
    }

    private String createRandom() {
        String[] beforeShuffle = new String[]{"2", "3", "4", "5", "6", "7",
                "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z"};
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        return afterShuffle.substring(5, 9);
    }

}
