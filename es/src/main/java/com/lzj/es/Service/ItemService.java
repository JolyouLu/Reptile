package com.lzj.es.Service;

import com.lzj.es.pojo.Item;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: LZJ
 * @Date: 2019/7/10 10:58
 * @Version 1.0
 */
public interface ItemService {
    void save(Item item);

    void delete(Item item);

    void saveAll(List<Item> list);

    //查询所有数据
    Iterable<Item> findAll();

    //分页查询
    Page<Item> findByPage(int page, int rows);

    //根据标题内容查询，交集
    List<Item> findAllByTitleAndContent(String title, String content);

    //根据标题或内容查询，并集
    Page<Item> findByTitleOrContent(String title, String content, Integer page, Integer rows);

    //根据title或者content和id的范围，进行分页查询
    Page<Item> findByTitleAndContentAndIdBetween(String title, String content, int min, int max, int page, int row);
}
