package com.lzj.es.pojo;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Author: LZJ
 * @Date: 2019/7/10 10:48
 * @Version 1.0
 */
@Document(indexName = "item",type = "item")
public class Item {
    @Id
    @Field(index = true,store = true,type = FieldType.Integer)
    private Integer id;
    @Field(index = true,store = true,analyzer ="ik_smart",searchAnalyzer = "ik_smart",type = FieldType.text)
    private String title;
    @Field(index = true,store = true,analyzer ="ik_smart",searchAnalyzer = "ik_smart",type = FieldType.text)
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "Item{" +
                "id=" + id +
                ",title='" + title + '\''+
                ",content" + content + '\'' +
                '}';
    }

}
