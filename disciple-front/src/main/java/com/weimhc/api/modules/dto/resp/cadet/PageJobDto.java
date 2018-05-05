package com.weimhc.api.modules.dto.resp.cadet;

import com.thinkgem.javamg.common.persistence.Pageable;

import java.util.List;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2017/12/22 14:35
 */
public class PageJobDto {
    private Pageable pageable;

    List<JobDto> jobDtos;

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public List<JobDto> getJobDtos() {
        return jobDtos;
    }

    public void setJobDtos(List<JobDto> jobDtos) {
        this.jobDtos = jobDtos;
    }
}
