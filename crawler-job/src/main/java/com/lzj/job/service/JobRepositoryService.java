package com.lzj.job.service;

import com.lzj.job.pojo.JobInfoField;

import java.util.List;

/**
 * @Author: LZJ
 * @Date: 2019/7/10 13:34
 * @Version 1.0
 */
public interface JobRepositoryService {
    /**
     * 保存一条数据
     *
     * @param jobInfoField
     */
    public void save(JobInfoField jobInfoField);

    /**
     * 批量保存数据
     *
     * @param list
     */
    public void saveAll(List<JobInfoField> list);
}
