package com.weimhc.api.modules.dto.resp.cadet;

import com.thinkgem.javamg.common.persistence.Pageable;

import java.util.List;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/26 11:01
 */
public class PageCourseDto {

    private Pageable pageable;

    private List<CourseDto> courseDtos;

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public List<CourseDto> getCourseDtos() {
        return courseDtos;
    }

    public void setCourseDtos(List<CourseDto> courseDtos) {
        this.courseDtos = courseDtos;
    }
}
