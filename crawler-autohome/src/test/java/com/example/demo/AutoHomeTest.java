package com.example.demo;


import com.example.demo.pojo.CarTest;
import com.example.demo.service.ApiService;
import com.example.demo.service.CarTestService;
import com.example.demo.util.TitleFilter;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: LZJ
 * @Date: 2019/7/5 11:50
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class AutoHomeTest {

    @Autowired
    private ApiService apiService;
    @Autowired
    private CarTestService carTestService;
    @Autowired
    private TitleFilter titleFilter;

    @Test
    public void testCrawler()throws Exception{
        //声明爬取的页面
        String url="https://www.autohome.com.cn/bestauto/1";
        //使用ApiService爬取数据
        //使用jsoup解析页面
        Document dom = Jsoup.parse(new File("D:/images/test.html"), "UTF-8");
        //获取所有的评测div
        Elements divs = dom.select("#bestautocontent div.uibox");
        //遍历当前页面所有评测数据
        for (Element div : divs) {
            //去重过滤，重复的数据不需要处理了
            String title = div.select("div.uibox-title").first().text();
            if (titleFilter.contains(title)){
                //如果数据存在，表示重复，进行下一个遍历
                continue;
            }
            //解析页面，获取评测数据对象
            CarTest cart = this.getCarTest(div);
            //解析页面，下载车辆评测图片
            String image = this.getCarImage(div);
            System.out.println(image);
            //把汽车评测数据保存数据库中
            this.carTestService.saveCarTest(cart);
        }


    }

    /**
     * 解析传递进来的元素,下载评测图片
     * @param div
     * @return
     */
    private String getCarImage(Element div) {
        //声明存放图片名称的集合
        List<String> images = new ArrayList<>();
        //获取图片的url地址
        Elements Page = div.select("ul.piclist02 li");
        //遍历评测图片的元素
        for (Element element : Page) {
            //获取评测图片的地址
            String imagPage = "https:"+element.select("a").attr("href");
            //使用apiservice发起请求获取展示页面
            String html = this.apiService.getHtml(imagPage);
            //解析图片展示页面
            Document dom = Jsoup.parse(html);
            //获取图片url地址
            String imageUrl = "https:"+dom.getElementById("img").attr("src");
            //根据图片url地址下载图片，返回图片名称
            String image = this.apiService.getImage(imageUrl);
            //把图片放入集合中
            images.add(image);
        }
        //把图片名称返回，把集合转为字符串用逗号分隔
        return StringUtils.join(images,",");
    }

    /**
     * 解析传递进来的元素封装为评测对象
     * @param div
     * @return
     */
    private CarTest getCarTest(Element div) {
        //创建评测对象
        CarTest carTest = new CarTest();
        //设置评测数据
        String title = div.select("div.uibox-title").first().text();
        carTest.setTitle(title);
        //加速评测
        String speed = div.select(".tabbox1 dd:nth-child(2) div.dd-div2").first().text();
        carTest.setTestSpeed(this.changeStr2Num(speed));
        //刹车评测
        String brake = div.select(".tabbox1 dd:nth-child(3) div.dd-div2").first().text();
        carTest.setTestBrake(this.changeStr2Num(brake));
        //油耗评测
        String oil = div.select(".tabbox1 dd:nth-child(4) div.dd-div2").first().text();
        if (!oil.equals("--")){
            carTest.setTestOil(this.changeStr2Num(oil));
        }
        //编辑1
        String name1 = div.select(".tabbox2 dd:nth-child(2) > div.dd-div1").first().text();
        carTest.setEditorName1(name1);
        String remark1 = div.select(".tabbox2 dd:nth-child(2) > div.dd-div3").first().text();
        carTest.setEditorRemark1(remark1);
        //编辑2
        String name2 = div.select(".tabbox2 dd:nth-child(3) > div.dd-div1").first().text();
        carTest.setEditorName2(name2);
        String remark2 = div.select(".tabbox2 dd:nth-child(3) > div.dd-div3").first().text();
        carTest.setEditorRemark2(remark2);
        //编辑3
        String name3 = div.select(".tabbox2 dd:nth-child(4) > div.dd-div1").first().text();
        carTest.setEditorName3(name3);
        String remark3 = div.select(".tabbox2 dd:nth-child(4) > div.dd-div3").first().text();
        carTest.setEditorRemark3(remark3);

        carTest.setCreated(new Date());
        carTest.setUpdated(carTest.getCreated());
        //返回评测对象
        return carTest;
    }

    /**
     * 把字符串最后一个字符去掉，转为数字*1000
     * @param str
     */
    private int changeStr2Num(String str) {
        //把字符串最后一位去掉
        str = str.substring(0, str.length()-1);
        //把字符串转为小数，必须使用number接受，否则会有精度丢失
        Number num = Float.parseFloat(str)*1000;
        return num.intValue();
    }
}
