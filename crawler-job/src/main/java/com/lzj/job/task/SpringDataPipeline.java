package com.lzj.job.task;

import com.lzj.job.pojo.JobInfo;
import com.lzj.job.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @Author: LZJ
 * @Date: 2019/7/10 8:52
 * @Version 1.0
 */
@Component
public class SpringDataPipeline implements Pipeline {

    @Autowired
    private JobInfoService jobInfoService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取封装好的招聘对象
        JobInfo jobInfo = resultItems.get("jobInfo");
        //判断数据是否不为空
        if (!jobInfo.equals(null)){
            //如果不为空保存
            this.jobInfoService.save(jobInfo);
        }
    }
}
