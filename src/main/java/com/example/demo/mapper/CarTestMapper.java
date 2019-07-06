package com.example.demo.mapper;


import com.example.demo.pojo.CarTest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author: LZJ
 * @Date: 2019/7/4 15:45
 * @Version 1.0
 */
@Mapper
public interface CarTestMapper {
    /***
     *分页查询汽车标题
     * @param map 有2个key一个是start从那一条开始，另一个是rows每页显示的条数
     * @return
     */
    @Select("SELECT title FROM car_test LIMIT #{start},#{rows}")
    List<String> queryTitleByPage(Map<String, Object> map);

    /**
     * 保存
     * @param cart
     */
    @Insert(
        "INSERT INTO `car_test` (" +
        "	`title`," +
        "	`test_speed`," +
        "	`test_brake`," +
        "	`test_oil`," +
        "	`editor_name1`," +
        "	`editor_remark1`," +
        "	`editor_name2`," +
        "	`editor_remark2`," +
        "	`editor_name3`," +
        "	`editor_remark3`," +
        "	`image`," +
        "	`created`," +
        "	`updated` " +
        ")" +
        "VALUES" +
        "	( " +
        "		#{title}," +
        "		#{testSpeed}," +
        "		#{testBrake}," +
        "		#{testOil}," +
        "		#{editorName1}," +
        "		#{editorRemark1}," +
        "		#{editorName2}," +
        "		#{editorRemark2}," +
        "		#{editorName3}," +
        "		#{editorRemark3}," +
        "		#{image}," +
        "		#{created}," +
        "		#{updated}" +
        "	)"
    )
    void saveCarTest(CarTest cart);
}
