package com.lzj.jd.service;

import com.lzj.jd.pojo.Item;

import java.util.List;

/**
 * @Author: LZJ
 * @Date: 2019/7/6 18:14
 * @Version 1.0
 */
public interface ItemService {
    /**
     * 保存商品
     * @param item
     */
    public void save(Item item);

    /**
     * 根据条件查询商品
     * @param item
     * @return
     */
    public List<Item> findAll(Item item);
}
