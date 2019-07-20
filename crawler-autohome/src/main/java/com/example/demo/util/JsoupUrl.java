package com.example.demo.util;


import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.File;
import java.net.URL;


/**
 * 获取html并且把页面下载下来
 *
 * @Author: LZJ
 * @Date: 2019/7/5 11:56
 * @Version 1.0
 */
public class JsoupUrl {
    public static void main(String[] args) throws Exception {
        //解析url地址，第一个参数是需要解析url，第二个参数是连接的时间，单位是毫秒
        Document dom = Jsoup.parse(new URL("https://www.autohome.com.cn/bestauto/1"), 10000);
        //获取页面信息，输出未html文件
        FileUtils.writeStringToFile(new File("D:/images/test.html"), dom.html(), "UTF-8");
        //使用dom对对象解析页面，获取title的内容
        String title = dom.getElementsByTag("title").first().text();
        System.out.println(title);
    }
}
