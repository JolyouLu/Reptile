package com.lzj.job.dao;

import com.lzj.job.pojo.JobInfoField;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @Author: LZJ
 * @Date: 2019/7/10 13:33
 * @Version 1.0
 */
@Component
public interface JobRepository extends ElasticsearchRepository<JobInfoField, Long> {

}
