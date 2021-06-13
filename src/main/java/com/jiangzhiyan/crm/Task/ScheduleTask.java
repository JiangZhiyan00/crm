package com.jiangzhiyan.crm.Task;

import com.jiangzhiyan.crm.service.CustomerLossService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author JiangZhiyan
 */
@Component
public class ScheduleTask {

    @Resource
    private CustomerLossService customerLossService;

    /**
     * 定时任务:每3个月执行一次lossCustomersTask()方法
     * 注解@Scheduled:周期注解
     * (cron = "0/5 * * * * ?"):corn表达式:每5秒
     * (cron = "0 0 0 0 1/3 ?"):corn表达式:每3个月
     * 定义方法执行周期
     */
    //@Scheduled(cron = "0/5 * * * * ?")
    @Scheduled(cron = "0 0 0 0 1/3 ?")
    public void lossCustomersTask(){
        customerLossService.updateCustomerLossTable();
    }
}
