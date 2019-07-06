package com.example.demo.job;


import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Author: LZJ
 * @Date: 2019/7/4 16:21
 * @Version 1.0
 */
//当定时任务没有执行完的情况下，不会再次启动新任务
@DisallowConcurrentExecution
public class CloseConnectJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        //获取spring容器
        ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap().get("context");
        //从spring容器中获取httpClient链接管理器，关闭无效链接
        applicationContext.getBean(PoolingHttpClientConnectionManager.class).closeExpiredConnections();
    }
}
