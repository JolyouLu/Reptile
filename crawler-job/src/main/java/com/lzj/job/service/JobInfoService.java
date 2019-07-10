package com.lzj.job.service;

import com.lzj.job.pojo.JobInfo;
import org.springframework.data.domain.Page;

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

    /**
     * 分页查询数据
     * @param page
     * @param rows
     * @return
     */
    Page<JobInfo> findJobInfoByPage(int page, int rows);
}
