package com.example.demo.service.impl;

import com.example.demo.mapper.CarTestMapper;
import com.example.demo.pojo.CarTest;
import com.example.demo.service.CarTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: LZJ
 * @Date: 2019/7/4 15:52
 * @Version 1.0
 */
@Service
public class CarTestServiceImpl implements CarTestService {

    @Autowired
    private CarTestMapper carTestMapper;

    @Override
    public List<String> queryTitleByPage(int page, int rows) {
        //计算从那一条数据开始查
        int start = (page-1)*rows;
        //封装参数map
        Map<String,Object> map = new HashMap<>();
        map.put("start",start);
        map.put("rows",rows);
        //使用mapper从数据库查询数据
        List<String> list = this.carTestMapper.queryTitleByPage(map);
        return list;
    }

    @Override
    public void saveCarTest(CarTest cart) {
        this.carTestMapper.saveCarTest(cart);
    }
}
