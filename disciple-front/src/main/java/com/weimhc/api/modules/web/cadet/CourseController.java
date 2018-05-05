package com.weimhc.api.modules.web.cadet;

import com.weimhc.api.modules.dto.resp.cadet.CourseDto;
import com.weimhc.api.modules.dto.resp.cadet.CourseLabelDto;
import com.weimhc.api.modules.dto.resp.cadet.PageCourseDto;
import com.weimhc.api.modules.service.CourseFrontService;
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
import java.util.List;

/**
 * 课程培训
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/26 10:40
 */
@Api
@Controller("apiCourseController")
@RequestMapping(value = "${apiPath}/course")
public class CourseController {

    @Autowired
    private CourseFrontService courseFrontService;

    @ApiOperation(value = "滚动课程", notes = "滚动课程", tags = {
            "D课程培训"}, authorizations = {@Authorization(value = "token")})
    @RequestMapping(value = "/rollCourse", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<CourseDto>> rollCourse(HttpServletRequest request) {
        ApiResult<List<CourseDto>> apiResult = new ApiResult<>();
        List<CourseDto> list = courseFrontService.findListByIsRoll();
        apiResult.setData(list);
        return apiResult;
    }

    @ApiOperation(value = "课程列表", notes = "课程列表", tags = {
            "D课程培训"}, authorizations = {@Authorization(value = "token")})
    @RequestMapping(value = "/courseList", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<PageCourseDto> courseList(HttpServletRequest request,
                                                  @ApiParam(value = "课程标签Id", required = true)@RequestParam String labelId,
                                                  @ApiParam(value = "第几页", defaultValue = "1") @RequestParam Integer pageNo,
                                                  @ApiParam(value = "数据条数", defaultValue = "10") @RequestParam Integer pageSize) {
        ApiResult<PageCourseDto> apiResult = new ApiResult<>();
        PageCourseDto pageCourseDto = courseFrontService.listCourse(labelId, pageNo, pageSize);
        apiResult.setData(pageCourseDto);
        return apiResult;
    }

    @ApiOperation(value = "课程标签", notes = "课程标签", tags = {
            "D课程培训"}, authorizations = {@Authorization(value = "token")})
    @RequestMapping(value = "/getLabels", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<CourseLabelDto>> getLabels(HttpServletRequest request) {
        ApiResult<List<CourseLabelDto>> apiResult = new ApiResult<>();
        List<CourseLabelDto> courseLabelDtos = courseFrontService.getLabels();
        apiResult.setData(courseLabelDtos);
        return apiResult;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Boolean> add(HttpServletRequest request,@RequestParam String companyId) {
        ApiResult<Boolean> apiResult = new ApiResult<>();
        courseFrontService.updateCompanyId(companyId);
        apiResult.setData(true);
        return apiResult;
    }
}
