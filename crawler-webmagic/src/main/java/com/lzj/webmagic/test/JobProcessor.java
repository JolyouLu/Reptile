package com.lzj.webmagic.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.awt.image.ImageFilter;

/**
 * @Author: LZJ
 * @Date: 2019/7/6 21:32
 * @Version 1.0
 */
public class JobProcessor implements PageProcessor {
    //负责解析页面
    public void process(Page page) {
        //解析返回数据的page，并且把解析的结果放到ResultItems中
        page.putField("div",page.getHtml().css("div.cartype-r > div.tit > span.gray66.fn-fontsize14").all());
        //Xpath
        page.putField("div2",page.getHtml().xpath("//*[@id=\"bestautocontent\"]/div[5]/div/div/div[1]/a"));
    }


    private Site site = Site.me()
            .setCharset("utf-8")//设置编码格式
            .setTimeOut(10000)//设置超时时间
            .setRetrySleepTime(3000)//设置重试时间
            .setSleepTime(3)//设重试次数
            ;

    public Site getSite() {
        return site;
    }
    //猪函数，执行爬虫
    public static void main(String[] args) {
        Spider.create(new JobProcessor())
                .addUrl("http://180.153.49.130:9000/layout/index.xhtml") //设置爬取数据的页面
                .addRequest()
                .addPipeline(new FilePipeline("D:/images/"))
                .thread(5)
                .run(); //执行爬虫
    }

}
