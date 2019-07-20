package com.example.demo.service;


import com.example.demo.DemoApplication;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.junit.Assert.*;

/**
 * @Author: LZJ
 * @Date: 2019/7/5 10:12
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ApiServiceTest {

    @Autowired
    private ApiService apiService;

    @Test
    public void testGetHtml() {
        String html = this.apiService.getHtml("https://www.autohome.com.cn/bestauto/1");
        Document dom = Jsoup.parse(html);
        System.out.println(dom.select("title").first().text());
    }

    @Test
    public void testImage() {
        String imageName = this.apiService.getImage("https://car3.autoimg.cn/cardfs/product/g2/M04/BD/78/autohomecar__ChcCRF0ZigCALm2MAAqgnowdDuA153.jpg#pvareaid=2042293");
        System.out.println(imageName);
    }

}