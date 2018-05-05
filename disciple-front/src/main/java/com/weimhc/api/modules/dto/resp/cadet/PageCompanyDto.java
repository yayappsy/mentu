package com.weimhc.api.modules.dto.resp.cadet;

import com.thinkgem.javamg.common.persistence.Pageable;

import java.util.List;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2018/01/08 11Z:35
 */
public class PageCompanyDto {
    private Pageable pageable;

    List<CompanyDto> companyDtos;

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public List<CompanyDto> getCompanyDtos() {
        return companyDtos;
    }

    public void setCompanyDtos(List<CompanyDto> companyDtos) {
        this.companyDtos = companyDtos;
    }
}
