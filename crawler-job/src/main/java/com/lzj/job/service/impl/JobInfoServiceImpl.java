package com.lzj.job.service.impl;

import com.lzj.job.dao.JobInfoDao;
import com.lzj.job.pojo.JobInfo;
import com.lzj.job.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class JobInfoServiceImpl implements JobInfoService {

    @Autowired
    private JobInfoDao jobInfoDao;

    @Override
    @Transactional
    public void save(JobInfo jobInfo) {
        //先从数据库查询数据,根据发布日期查询和url查询
        JobInfo param = new JobInfo();
        param.setUrl(jobInfo.getUrl());
        param.setTime(jobInfo.getTime());
        List<JobInfo> list = this.findJobInfo(param);

        if (list.size() == 0) {
            //没有查询到数据则新增或者修改数据
            this.jobInfoDao.saveAndFlush(jobInfo); 
        }
    }

    @Override
    public List<JobInfo> findJobInfo(JobInfo jobInfo) {

        //设置查询条件
        Example example = Example.of(jobInfo);

        List<JobInfo> list = this.jobInfoDao.findAll(example);

        return list;
    }

    @Override
    public Page<JobInfo> findJobInfoByPage(int page, int rows) {
        Page<JobInfo> jobInfos = this.jobInfoDao.findAll(PageRequest.of(page - 1, rows));
        return jobInfos;
    }
}
