package com.weimhc.api.modules.dto.req.cadet;

import java.util.Date;

/**
 * @Author cwl [942057398@qq.com]
 * @Date 2018/3/19 21:21
 */
public class StudentCreateDateRQ {

    private Date startDate;

    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
