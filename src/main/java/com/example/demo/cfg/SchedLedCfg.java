package com.example.demo.cfg;

import com.example.demo.job.CloseConnectJob;
import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @Author: LZJ
 * @Date: 2019/7/4 16:00
 * @Version 1.0
 */
@Configuration
public class SchedLedCfg {
    //定义关闭无效任务
    @Bean("closeConnectJobBean")
    public JobDetailFactoryBean closeConnectJobBean(){
        //先创建一个任务描述的工厂bean
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        //设置spring容器的key，任务中可以根据key获取spring容器
        jobDetailFactoryBean.setApplicationContextJobDataKey("context");
        //设置任务
        jobDetailFactoryBean.setJobClass(CloseConnectJob.class);
        //设置当没有触发器和任务绑定，不会删除任务
        jobDetailFactoryBean.setDurability(true);
        return jobDetailFactoryBean;
    }

    //定义关闭无效链接触发器
    //@Qualifier注解通过名字注入bean
    @Bean("closeConnectJobTrigger")
    public CronTriggerFactoryBean closeConnectJobTrigger(
            @Qualifier(value = "closeConnectJobBean")JobDetailFactoryBean itemJobBean){
        //创建一个表达式触发器工厂bean
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        //设置任务描述到触发器中
        tigger.setJobDetail(itemJobBean.getObject());
        //设置七子表达式
        tigger.setCronExpression("0/5 * * * * ? ");
        return tigger;
    }

    //定义调度器
    @Bean
    public SchedulerFactoryBean schedulerFactory(CronTrigger[] cronTriggerImpl){
        //创建任务调度器的工厂bean
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        //给任务调度器设置触发器
        bean.setTriggers(cronTriggerImpl);
        return bean;
    }
}
