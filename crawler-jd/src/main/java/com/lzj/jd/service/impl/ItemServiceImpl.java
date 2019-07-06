package com.lzj.jd.service.impl;

import com.lzj.jd.dao.ItemDao;
import com.lzj.jd.pojo.Item;
import com.lzj.jd.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: LZJ
 * @Date: 2019/7/6 18:16
 * @Version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    @Transactional
    public void save(Item item) {
        this.itemDao.save(item);
    }

    @Override
    public List<Item> findAll(Item item) {
        //声明查询条件
        Example<Item> example = Example.of(item);
        //根据查询条件查询数据
        List<Item> list = this.itemDao.findAll(example);
        return list;
    }
}
