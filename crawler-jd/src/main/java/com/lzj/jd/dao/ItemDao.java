package com.lzj.jd.dao;

import com.lzj.jd.pojo.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: LZJ
 * @Date: 2019/7/6 18:13
 * @Version 1.0
 */
public interface ItemDao extends JpaRepository<Item, Long> {
}
