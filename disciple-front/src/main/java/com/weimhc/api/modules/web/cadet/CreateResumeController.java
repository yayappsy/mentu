package com.weimhc.api.modules.web.cadet;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.api.modules.dto.req.cadet.UserInfoRQ;
import com.weimhc.api.modules.dto.req.cadet.resume.*;
import com.weimhc.api.modules.dto.resp.cadet.ResumeDto;
import com.weimhc.api.modules.service.CreateResumeService;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.modules.job.entity.Resume;
import com.weimhc.modules.job.service.RelatedOptionService;
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
 * 创建简历
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/26 14:55
 */
@Api
@Controller("apiResumeController")
@RequestMapping(value = "${apiPath}/resume")
public class CreateResumeController {

    @Autowired
    private RelatedOptionService relatedOptionService;

    @Autowired
    private CreateResumeService createResumeService;

    @Autowired
    private ResumeService resumeService;


    @ApiOperation(value = "创建简历", notes = "创建简历", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> create(HttpServletRequest request,
                                       @ApiParam(value = "用户id") @RequestParam String userId,
                                       @ApiParam(value = "语言（english:英文,chinese:中文）") @RequestParam String language,
                                       @ApiParam(value = "简历名称") @RequestParam String name,
                                       @ApiParam(value = "简历类型（0:默认简历,1:附件简历）") @RequestParam String type
                                     ) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        Boolean aBoolean = createResumeService.create(userId, language, type, name);
        if (!aBoolean) {
            apiResult.setMessage("存在简历，不可再次创建！");
        }
        apiResult.setData(aBoolean);
        return apiResult;
    }

    @ApiOperation(value = "获取简历", notes = "获取简历", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<List<ResumeDto>> get(HttpServletRequest request,
                                       @ApiParam(value = "用户id") @RequestParam String userId,
                                       @ApiParam(value = "简历类型（0:默认简历,1:附件简历）") @RequestParam String type
    ) {
        ApiResult<List<ResumeDto>> apiResult = new ApiResult<>();
        List<ResumeDto> resumeDtos = createResumeService.getResume(userId, type);
        apiResult.setData(resumeDtos);
        return apiResult;
    }


    @ApiOperation(value = "编辑个人信息", notes = "编辑个人信息", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> editUser(HttpServletRequest request,
                                       @RequestBody UserInfoRQ userInfoRQ) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        if (StringUtils.isBlank(userInfoRQ.getId())) {
            apiResult.setMessage("id不能为空");
            return apiResult;
        }
        Boolean aBoolean = createResumeService.editUser(userInfoRQ);
        if (!aBoolean) {
            apiResult.setMessage("当前用户id查询不到信息！");
        }
        apiResult.setData(aBoolean);
        return apiResult;
    }

    @ApiOperation(value = "获取个人信息", notes = "获取个人信息", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<UserInfoRQ> getUser(HttpServletRequest request,
                                       @ApiParam(value = "用户id") @RequestParam String userId) {
        ApiResult<UserInfoRQ> apiResult = new ApiResult<>();
        UserInfoRQ userInfoDto = createResumeService.getUserInfo(userId);
        apiResult.setData(userInfoDto);
        return apiResult;
    }


    
    @ApiOperation(value = "保存期望实习", notes = "保存期望实习", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/commitHopePractice", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> commitHopePractice(HttpServletRequest request, @RequestBody ResumeHopePracticeRQ resumeHopePracticeRQ) {
        ApiResult<String> apiResult = new ApiResult<>();
        if (!createResumeService.exitResumeId(resumeHopePracticeRQ.getResumeId())) {
            apiResult.setMessage("当前resumeId不存在！");
            return apiResult;
        }
        resumeService.updateUpdateDate(resumeHopePracticeRQ.getResumeId());
        createResumeService.saveHopePractice(resumeHopePracticeRQ);
        return apiResult;
    }


    @ApiOperation(value = "获取期望实习", notes = "获取期望实习", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getHopePractice", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<ResumeHopePracticeRQ> getHopePractice(HttpServletRequest request, @ApiParam(value = "简历Id")@RequestParam String id) {
        ApiResult<ResumeHopePracticeRQ> apiResult = new ApiResult<>();
        ResumeHopePracticeRQ hopePracticeDto = createResumeService.getHopePractice(id);
        apiResult.setData(hopePracticeDto);
        return apiResult;
    }

    @ApiOperation(value = "保存教育背景", notes = "保存教育背景", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/commitEducation", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> commitEducation(HttpServletRequest request, @RequestBody ResumeEducationRQ resumeEducationRQ) {
        ApiResult<String> apiResult = new ApiResult<>();
        if (!createResumeService.exitResumeId(resumeEducationRQ.getResumeId())) {
            apiResult.setMessage("当前resumeId不存在！");
            return apiResult;
        }
        resumeService.updateUpdateDate(resumeEducationRQ.getResumeId());
        createResumeService.saveEducation(resumeEducationRQ);
        return apiResult;
    }

    @ApiOperation(value = "获取教育背景", notes = "获取教育背景", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getEducations", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<ResumeEducationRQ>> getEducations(HttpServletRequest request,@ApiParam(value = "简历Id")@RequestParam String id) {
        ApiResult<List<ResumeEducationRQ>> apiResult = new ApiResult<>();
        List<ResumeEducationRQ> resumeEducationRQS = createResumeService.getEducations(id);
        apiResult.setData(resumeEducationRQS);
        return apiResult;
    }

    @ApiOperation(value = "删除教育背景", notes = "删除教育背景", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/delEducation", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> delEducation(HttpServletRequest request,@ApiParam(value = "教育背景Id")@RequestParam String id) {
        ApiResult<String> apiResult = new ApiResult<>();
        createResumeService.delEducation(id);
        return apiResult;
    }

    @ApiOperation(value = "保存实习经历", notes = "保存实习经历", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/commitPracticeExperience", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> commitPracticeExperience(HttpServletRequest request, @RequestBody ResumePracticeExperienceRQ resumePracticeExperienceRQ) {
        ApiResult<String> apiResult = new ApiResult<>();
        if (!createResumeService.exitResumeId(resumePracticeExperienceRQ.getResumeId())) {
            apiResult.setMessage("当前resumeId不存在！");
            return apiResult;
        }
        resumeService.updateUpdateDate(resumePracticeExperienceRQ.getResumeId());
        createResumeService.savePracticeExperience(resumePracticeExperienceRQ);
        return apiResult;
    }

    @ApiOperation(value = "获取实习经历", notes = "获取实习经历", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getPracticeExperiences", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<ResumePracticeExperienceRQ>> getPracticeExperiences(HttpServletRequest request,@ApiParam(value = "简历Id")@RequestParam String id) {
        ApiResult<List<ResumePracticeExperienceRQ>> apiResult = new ApiResult<>();
        List<ResumePracticeExperienceRQ> ResumePracticeExperienceRQs = createResumeService.getPracticeExperiences(id);
        apiResult.setData(ResumePracticeExperienceRQs);
        return apiResult;
    }

    @ApiOperation(value = "删除实习经历", notes = "删除实习经历", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/delPracticeExperience", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> delPracticeExperience(HttpServletRequest request,@ApiParam(value = "实习经历Id")@RequestParam String id) {
        ApiResult<String> apiResult = new ApiResult<>();
        createResumeService.delPracticeExperience(id);
        return apiResult;
    }

    @ApiOperation(value = "保存学术经历", notes = "保存学术经历", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/commitScienceExperience", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> commitScienceExperience(HttpServletRequest request, @RequestBody ResumeScienceExperienceRQ resumeScienceExperienceRQ) {
        ApiResult<String> apiResult = new ApiResult<>();
        if (!createResumeService.exitResumeId(resumeScienceExperienceRQ.getResumeId())) {
            apiResult.setMessage("当前resumeId不存在！");
            return apiResult;
        }
        resumeService.updateUpdateDate(resumeScienceExperienceRQ.getResumeId());
        createResumeService.saveScienceExperience(resumeScienceExperienceRQ);
        return apiResult;
    }

    @ApiOperation(value = "获取学术经历", notes = "获取学术经历", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getScienceExperiences", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<ResumeScienceExperienceRQ>> getScienceExperiences(HttpServletRequest request,@ApiParam(value = "简历Id")@RequestParam String id) {
        ApiResult<List<ResumeScienceExperienceRQ>> apiResult = new ApiResult<>();
        List<ResumeScienceExperienceRQ> ResumeScienceExperienceRQs = createResumeService.getScienceExperiences(id);
        apiResult.setData(ResumeScienceExperienceRQs);
        return apiResult;
    }

    @ApiOperation(value = "删除学术经历", notes = "删除学术经历", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/delScienceExperience", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> delScienceExperience(HttpServletRequest request,@ApiParam(value = "学术经历Id")@RequestParam String id) {
        ApiResult<String> apiResult = new ApiResult<>();
        createResumeService.delScienceExperience(id);
        return apiResult;
    }


    @ApiOperation(value = "保存校园经历", notes = "保存校园经历", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/commitSchoolExperience", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> commitSchoolExperience(HttpServletRequest request, @RequestBody ResumeSchoolExperienceRQ resumeSchoolExperienceRQ) {
        ApiResult<String> apiResult = new ApiResult<>();
        if (!createResumeService.exitResumeId(resumeSchoolExperienceRQ.getResumeId())) {
            apiResult.setMessage("当前resumeId不存在！");
            return apiResult;
        }
        resumeService.updateUpdateDate(resumeSchoolExperienceRQ.getResumeId());
        createResumeService.saveSchoolExperience(resumeSchoolExperienceRQ);
        return apiResult;
    }

    @ApiOperation(value = "获取校园经历", notes = "获取校园经历", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getSchoolExperiences", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<ResumeSchoolExperienceRQ>> getSchoolExperiences(HttpServletRequest request,@ApiParam(value = "简历Id")@RequestParam String id) {
        ApiResult<List<ResumeSchoolExperienceRQ>> apiResult = new ApiResult<>();
        List<ResumeSchoolExperienceRQ> ResumeSchoolExperienceRQs = createResumeService.getSchoolExperiences(id);
        apiResult.setData(ResumeSchoolExperienceRQs);
        return apiResult;
    }

    @ApiOperation(value = "删除校园经历", notes = "删除校园经历", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/delSchoolExperience", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> delSchoolExperience(HttpServletRequest request,@ApiParam(value = "校园经历Id")@RequestParam String id) {
        ApiResult<String> apiResult = new ApiResult<>();
        createResumeService.delSchoolExperience(id);
        return apiResult;
    }


    @ApiOperation(value = "保存技能爱好", notes = "保存技能爱好", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/commitSkillHobby", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> commitSkillHobby(HttpServletRequest request, @RequestBody ResumeSkillHobbyRQ resumeSkillHobbyRQ) {
        ApiResult<String> apiResult = new ApiResult<>();
        if (!createResumeService.exitResumeId(resumeSkillHobbyRQ.getResumeId())) {
            apiResult.setMessage("当前resumeId不存在！");
            return apiResult;
        }
        resumeService.updateUpdateDate(resumeSkillHobbyRQ.getResumeId());
        createResumeService.saveSkillHobby(resumeSkillHobbyRQ);
        return apiResult;
    }


    @ApiOperation(value = "获取技能爱好", notes = "获取技能爱好", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getSkillHobbys", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<ResumeSkillHobbyRQ>> getSkillHobbys(HttpServletRequest request,@ApiParam(value = "简历Id")@RequestParam String id) {
        ApiResult<List<ResumeSkillHobbyRQ>> apiResult = new ApiResult<>();
        List<ResumeSkillHobbyRQ> ResumeSkillHobbyRQs = createResumeService.getSkillHobbys(id);
        apiResult.setData(ResumeSkillHobbyRQs);
        return apiResult;
    }

    @ApiOperation(value = "删除技能爱好", notes = "删除技能爱好", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/delSkillHobby", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> delSkillHobby(HttpServletRequest request,@ApiParam(value = "技能爱好Id")@RequestParam String id) {
        ApiResult<String> apiResult = new ApiResult<>();
        createResumeService.delSkillHobby(id);
        return apiResult;
    }

    @ApiOperation(value = "保存作品展示", notes = "保存作品展示", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/commitOpusShow", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> commitOpusShow(HttpServletRequest request, @RequestBody ResumeOpusShowRQ resumeOpusShowRQ) {
        ApiResult<String> apiResult = new ApiResult<>();
        if (!createResumeService.exitResumeId(resumeOpusShowRQ.getResumeId())) {
            apiResult.setMessage("当前resumeId不存在！");
            return apiResult;
        }
        resumeService.updateUpdateDate(resumeOpusShowRQ.getResumeId());
        createResumeService.saveOpusShow(resumeOpusShowRQ);
        return apiResult;
    }

    @ApiOperation(value = "获取作品展示", notes = "获取作品展示", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getOpusShows", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<ResumeOpusShowRQ>> getOpusShows(HttpServletRequest request,@ApiParam(value = "简历Id")@RequestParam String id) {
        ApiResult<List<ResumeOpusShowRQ>> apiResult = new ApiResult<>();
        List<ResumeOpusShowRQ> ResumeOpusShowRQs = createResumeService.getOpusShows(id);
        apiResult.setData(ResumeOpusShowRQs);
        return apiResult;
    }

    @ApiOperation(value = "删除作品展示", notes = "删除作品展示", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/delOpusShow", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> delOpusShow(HttpServletRequest request,@ApiParam(value = "作品展示Id")@RequestParam String id) {
        ApiResult<String> apiResult = new ApiResult<>();
        createResumeService.delOpusShow(id);
        return apiResult;
    }

    @ApiOperation(value = "保存个人评价", notes = "保存个人评价", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/commitOwnAssess", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> commitOwnAssess(HttpServletRequest request, @RequestBody ResumeAssessRQ resumeAssessRQ) {
        ApiResult<String> apiResult = new ApiResult<>();
        if (!createResumeService.exitResumeId(resumeAssessRQ.getResumeId())) {
            apiResult.setMessage("当前resumeId不存在！");
            return apiResult;
        }
        resumeService.updateUpdateDate(resumeAssessRQ.getResumeId());
        createResumeService.saveOwnAssess(resumeAssessRQ);
        return apiResult;
    }

    @ApiOperation(value = "获取个人评价", notes = "获取个人评价", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getOwnAssess", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<ResumeAssessRQ> getOwnAssess(HttpServletRequest request,@ApiParam(value = "简历Id")@RequestParam String id) {
        ApiResult<ResumeAssessRQ> apiResult = new ApiResult<>();
        ResumeAssessRQ ResumeOwnAssessRQs = createResumeService.getOwnAssess(id);
        apiResult.setData(ResumeOwnAssessRQs);
        return apiResult;
    }


    @ApiOperation(value = "保存附件", notes = "保存附件", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/commitRelatedOption", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> commitRelatedOption(HttpServletRequest request,
                                    @RequestBody ResumeRelatedOptionRQ resumeRelatedOptionRQ) {
        ApiResult<String> apiResult = new ApiResult<>();
        if (!createResumeService.exitResumeId(resumeRelatedOptionRQ.getResumeId())) {
            apiResult.setMessage("当前resumeId不存在！");
            return apiResult;
        }
        Integer count = relatedOptionService.getCountByResume(resumeRelatedOptionRQ.getResumeId());
        if (count >= 1 && resumeRelatedOptionRQ.getType().equals("1") && StringUtils.isEmpty(resumeRelatedOptionRQ.getId())) {
            return ApiResult.error(-1, "附件简历已经存在，不可添加！");
        }
        resumeService.updateUpdateDate(resumeRelatedOptionRQ.getResumeId());
        createResumeService.saveRelatedOption(resumeRelatedOptionRQ);
        return apiResult;
    }
    @ApiOperation(value = "删除附件", notes = "删除附件", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/deleteRelatedOption", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> deleteRelatedOption(HttpServletRequest request,
                                                 @ApiParam(value = "附件Id")@RequestParam String id) {
        ApiResult<String> apiResult = new ApiResult<>();
        createResumeService.delRelatedOption(id);
        return apiResult;
    }

    @ApiOperation(value = "获取附件", notes = "获取附件", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getRelatedOption", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<ResumeRelatedOptionRQ> getRelatedOption(HttpServletRequest request, @ApiParam(value = "简历Id")@RequestParam String id,
                                                             @ApiParam(value = "类型(0相关附件，1附件简历)",defaultValue = "0") @RequestParam String type) {
        ApiResult<ResumeRelatedOptionRQ> apiResult = new ApiResult<>();
        ResumeRelatedOptionRQ relatedOption = createResumeService.getRelatedOption(id,type);
        apiResult.setData(relatedOption);
        return apiResult;
    }

    @ApiOperation(value = "获取简历类型", notes = "获取简历类型(0:在线简历,1:附件简历)\n语言（english:英文,chinese:中文）", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/getResumeType", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Resume> getResumeType(HttpServletRequest request, @ApiParam(value = "简历Id")@RequestParam String id) {
        ApiResult<Resume> apiResult = new ApiResult<>();
        Resume resume = resumeService.get(id);
        apiResult.setData(resume);
        return apiResult;
    }

    @ApiOperation(value = "修改简历名称", notes = "修改简历名称", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/updateResumeName", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> updateResumeName(HttpServletRequest request,
                                                 @ApiParam(value = "简历id")@RequestParam String id,
                                                 @ApiParam(value = "简历名称")@RequestParam String name
                                              ) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        Resume resume = resumeService.get(id);
        if (resume == null) {
            return ApiResult.error(-1, "不存在的简历");
        }
        resume.setName(name);
        resumeService.save(resume);
        return apiResult;
    }

    @ApiOperation(value = "删除简历", notes = "删除简历", tags = {
            "D创建简历" }, authorizations = { @Authorization(value = "token") })
    @RequestMapping(value = "/deleteResume", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Boolean> deleteResume(HttpServletRequest request,
                                               @ApiParam(value = "简历id")@RequestParam String id
    ) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        Resume resume = new Resume();
        resume.setId(id);
        resumeService.delete(resume);
        apiResult.setData(true);
        return apiResult;
    }

}
