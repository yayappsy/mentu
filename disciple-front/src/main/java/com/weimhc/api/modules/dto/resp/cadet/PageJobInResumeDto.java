package com.weimhc.api.modules.dto.resp.cadet;

import com.thinkgem.javamg.common.persistence.Pageable;

import java.util.List;

/**
 * 应聘分页简历
 * @Author cwl [942057398@qq.com]
 * @Date 2018/1/10 9:17
 */
public class PageJobInResumeDto {

    private List<JobInResumeDto> jobInResumeDtos;

    private Pageable pageable;

    public List<JobInResumeDto> getJobInResumeDtos() {
        return jobInResumeDtos;
    }

    public void setJobInResumeDtos(List<JobInResumeDto> jobInResumeDtos) {
        this.jobInResumeDtos = jobInResumeDtos;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
