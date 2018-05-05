package com.weimhc.api.modules.service;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.api.modules.dto.resp.cadet.CourseDto;
import com.weimhc.api.modules.dto.resp.cadet.CourseLabelDto;
import com.weimhc.api.modules.dto.resp.cadet.PageCourseDto;
import com.weimhc.modules.job.entity.Course;
import com.weimhc.modules.job.entity.CourseLabel;
import com.weimhc.modules.job.service.CourseLabelService;
import com.weimhc.modules.job.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/31 19:45
 */
@Service
public class CourseFrontService {
    @Autowired
    private CourseService courseService;


    @Autowired
    private CourseLabelService courseLabelService;

    /**
     * 滚动课程
     *
     * @return
     */
    public List<CourseDto> findListByIsRoll() {
        List<CourseDto> courseDtos = new ArrayList<>();

        Course course = new Course();
        course.setIsRoll("1");
        List<Course> list = courseService.findList(course);
        for (Course cc : list) {
            CourseDto courseDto = new CourseDto();
            BeanUtils.copyProperties(cc, courseDto);
            courseDtos.add(courseDto);
        }
        return courseDtos;
    }

    /**
     * 获取标签列表
     *
     * @return
     */
    public List<CourseLabelDto> getLabels() {
        List<CourseLabelDto> courseLabelDtos = new ArrayList<>();
        CourseLabel courseLabel = new CourseLabel();
        List<CourseLabel> allList = courseLabelService.findAllList(courseLabel);
        for (CourseLabel label : allList) {
            CourseLabelDto courseLabelDto = new CourseLabelDto();
            BeanUtils.copyProperties(label, courseLabelDto);
            courseLabelDtos.add(courseLabelDto);
        }
        return courseLabelDtos;
    }

    /**
     * 课程列表
     *
     * @param labelId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageCourseDto listCourse(String labelId, Integer pageNo, Integer pageSize) {
        PageCourseDto pageCourseDto = new PageCourseDto();
        Course course = new Course();
        course.setCourseLabel(new CourseLabel(labelId));
        Page<Course> page = courseService
                .findPage(new Page<>(pageNo, pageSize), course);

        List<CourseDto> courseDtos = new ArrayList<>();
        for (Course cc : page.getList()) {
            CourseDto courseDto = new CourseDto();
            BeanUtils.copyProperties(cc, courseDto);
            courseDto.setSize(cc.getSize() + "");
            courseDtos.add(courseDto);
        }
        pageCourseDto.setCourseDtos(courseDtos);
        pageCourseDto.setPageable(page.getPageable());
        return pageCourseDto;
    }

    public void updateCompanyId(String companyId) {
        courseService.updateCompanyId(companyId);
    }
}
