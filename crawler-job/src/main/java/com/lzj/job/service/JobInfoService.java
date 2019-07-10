package com.lzj.job.service;

import com.lzj.job.pojo.JobInfo;

import java.util.List;

public interface JobInfoService {

    /**
     * 保存数据
     *
     * @param jobInfo
     */
    public void save(JobInfo jobInfo);

    /**
     * 根据条件查询数据
     *
     * @param jobInfo
     * @return
     */
    public List<JobInfo> findJobInfo(JobInfo jobInfo);
}
