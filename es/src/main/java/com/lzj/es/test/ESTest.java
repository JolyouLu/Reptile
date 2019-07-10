package com.lzj.es.test;

import com.lzj.es.Service.ItemService;
import com.lzj.es.pojo.Item;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LZJ
 * @Date: 2019/7/10 11:26
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ESTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    //创建索引和映射
    @Test
    public void creatIndex(){
        this.elasticsearchTemplate.createIndex(Item.class);
        this.elasticsearchTemplate.putMapping(Item.class);
    }

    //新增
    @Test
    public void testSave(){
        Item item = new Item();
        item.setId(100);
        item.setTitle("spring es");
        item.setContent("使用springData 完成搜索功能");
        this.itemService.save(item);
    }
    //修改
    @Test
    public void testUpdate(){
        Item item = new Item();
        item.setId(100);
        item.setTitle("spring es");
        item.setContent("使用springData 完成job搜索功能");
        this.itemService.save(item);
    }
    //删除
    @Test
    public void testDelete(){
        Item item = new Item();
        item.setId(100);
        this.itemService.delete(item);
    }
    //批量保存
    @Test
    public void testSaveAll(){
        //创建集合
        List<Item> list = new ArrayList<Item>();
        //封装数据
        for (int i=1;i<100;i++){
            Item item = new Item();
            item.setId(i);
            item.setTitle("spring es"+i);
            item.setContent("使用springData 完成job搜索功能"+i);
            list.add(item);
        }
        //批量保存
        this.itemService.saveAll(list);
    }
    //查询所有数据
    @Test
    public void testFindAll(){
        Iterable<Item> items = this.itemService.findAll();
        for (Item item : items) {
            System.out.println(item);
        }
    }
    //分页查询数据
    @Test
    public void testFindByPage(){
        Page<Item> page = this.itemService.findByPage(1,30);
        for (Item item : page.getContent()) {
            System.out.println(item);
        }
    }
    //复杂查询
    //根据title和content进行查询，交集
    @Test
    public void testFindTitleAndContent(){
        List<Item> list = this.itemService.findAllByTitleAndContent("es","job");
        for (Item item : list) {
            System.out.println(item);
        }
    }
    //根据title或者content进行分页擦好像，并集
    @Test
    public void testFindByTitleOrContent(){
       Page<Item> page = this.itemService.findByTitleOrContent("es","job",0,5);
        for (Item item : page.getContent()) {
            System.out.println(item);
        }
    }
    //根据title或者content和id的范围，进行分页查询
    @Test
    public void testFindByTitleAndContentAndIdBetween(){
        Page<Item> page = this.itemService.findByTitleAndContentAndIdBetween("es","job",10,16,1,6);
        for (Item item : page.getContent()) {
            System.out.println(item);
        }
    }
}
