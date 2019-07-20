package com.lzj.job.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import javax.lang.model.element.PackageElement;

/**
 * @Author: LZJ
 * @Date: 2019/7/10 10:24
 * @Version 1.0
 */
//@Component
public class ProxyTest implements PageProcessor {

    @Scheduled(fixedDelay = 1000)
    public void Process() {
        //创建一个下载器Downloader
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        //给下载器设置代理服务器
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("171.80.162.98", 5225)));
        Spider.create(new ProxyTest())
                .addUrl("http://ip.tool.chinaz.com/")
                .setDownloader(httpClientDownloader)//设置下载器
                .run();
    }

    @Override
    public void process(Page page) {
        System.out.println(page);
    }

    @Override
    public Site getSite() {
        return null;
    }
}
