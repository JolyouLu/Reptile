package com.lzj.job.service.impl;

import com.lzj.job.dao.JobRepository;
import com.lzj.job.pojo.JobInfoField;
import com.lzj.job.service.JobRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.Kernel;
import java.util.List;

/**
 * @Author: LZJ
 * @Date: 2019/7/10 13:36
 * @Version 1.0
 */
@Service
public class JobRepositoryServiceImpl implements JobRepositoryService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public void save(JobInfoField jobInfoField) {
        this.jobRepository.save(jobInfoField);
    }

    @Override
    public void saveAll(List<JobInfoField> list) {
        this.jobRepository.saveAll(list);
    }
}
