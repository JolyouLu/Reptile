package com.example.demo.service;

import com.example.demo.pojo.CarTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: LZJ
 * @Date: 2019/7/4 15:51
 * @Version 1.0
 */
public interface CarTestService {

    /**
     * 分页查询评测数据的汽车标题
     *
     * @param page
     * @param rows
     * @return
     */
    List<String> queryTitleByPage(int page, int rows);

    /**
     * 保存
     *
     * @param cart
     */
    void saveCarTest(CarTest cart);
}
