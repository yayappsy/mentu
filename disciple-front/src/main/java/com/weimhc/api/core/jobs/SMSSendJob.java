/**
 *
 */
package com.weimhc.api.core.jobs;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.weimhc.framework.job.AbstractJob;
import com.weimhc.framework.sms.SMSSendService;

/**
 * 发送验证码任务 定时任务Job
 *
 * @author zsm
 * @version 2015-11-20
 */
@Component
@Lazy(false)
public class SMSSendJob extends AbstractJob {
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "zthySMSSendService")
    private SMSSendService sMSSendService;

    /***
     * 定时任务，定时(每分钟)，检测并生成发送系统通知
     */
    @Scheduled(cron = "${job.sendSMS.cron}")
    public void sendPushMessage() {
        if (checkJobIsOpen()) {
            sMSSendService.send();
        }

    }

}