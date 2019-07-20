package com.lzj.es.dao;

import com.lzj.es.pojo.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @Author: LZJ
 * @Date: 2019/7/10 10:57
 * @Version 1.0
 */
public interface ItemRepository extends ElasticsearchRepository<Item, Integer> {

    List<Item> findAllByTitleAndContent(String title, String content);

    Page<Item> findByTitleOrContent(String title, String content, Pageable pageable);

    Page<Item> findByTitleAndContentAndIdBetween(String title, String content, int min, int max, Pageable pageable);
}
