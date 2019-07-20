package com.lzj.es.Service.impl;

import com.lzj.es.Service.ItemService;
import com.lzj.es.dao.ItemRepository;
import com.lzj.es.pojo.Item;
import org.elasticsearch.search.aggregations.ParsedAggregation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LZJ
 * @Date: 2019/7/10 10:59
 * @Version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void save(Item item) {
        this.itemRepository.save(item);
    }

    public void delete(Item item) {
        this.itemRepository.delete(item);
    }

    public void saveAll(List<Item> list) {
        this.itemRepository.saveAll(list);
    }

    public Iterable<Item> findAll() {
        Iterable<Item> items = this.itemRepository.findAll();
        return items;
    }

    public Page<Item> findByPage(int page, int rows) {
        Page<Item> items = this.itemRepository.findAll(PageRequest.of(page, rows));
        return items;
    }

    public List<Item> findAllByTitleAndContent(String title, String content) {
        List<Item> list = this.itemRepository.findAllByTitleAndContent(title, content);
        return list;
    }

    public Page<Item> findByTitleOrContent(String title, String content, Integer page, Integer rows) {
        Page<Item> items = this.itemRepository.findByTitleOrContent(title, content, PageRequest.of(page, rows));
        return items;
    }

    public Page<Item> findByTitleAndContentAndIdBetween(String title, String content, int min, int max, int page, int row) {
        Page<Item> items = this.itemRepository.findByTitleAndContentAndIdBetween(title, content, min, max, PageRequest.of(page - 1, row));
        return items;
    }


}
