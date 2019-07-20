package com.lzj.webmagic.test;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.HttpConstant;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: LZJ
 * @Date: 2019/7/6 21:32
 * @Version 1.0
 */
public class JobProcessor implements PageProcessor {
    //负责解析页面
    public void process(Page page) {
        Html html = page.getHtml();
        System.out.println(1);
    }


    private Site site = Site.me()
            .addCookie("Cookie", "JSESSIONID=657349543EB108A6D394151ED4B02A84")
            .setCharset("utf-8")//设置编码格式
            .setTimeOut(10000)//设置超时时间
            .setRetrySleepTime(3000)//设置重试时间
            .setSleepTime(3)//设重试次数
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");

    public Site getSite() {
        return site;
    }

    //执行爬虫
    public static void main(String[] args) {
        String url = "http://180.153.49.130:9000/business/resMge/pwMge/performanceMge/perfdata.xhtml";
        //设置post请求
        Request request = new Request(url);
        //只有post请求才可以添加附加参数
        request.setMethod(HttpConstant.Method.POST);
        NameValuePair[] nameValuePairs = new NameValuePair[]{
//            new BasicNameValuePair("AJAXREQUEST:","_viewRoot"),
//            new BasicNameValuePair("queryForm:","queryForm"),
                new BasicNameValuePair("queryForm:aid:", "10053697"),
                new BasicNameValuePair("queryForm:siteProvinceId:", "0096766"),
                new BasicNameValuePair("queryForm:queryFlag:", "3"),
                new BasicNameValuePair("queryForm:unitHidden1:", "0096766"),
                new BasicNameValuePair("queryForm:unitTypeHidden:", "PROVINCE"),
                new BasicNameValuePair("queryForm:siteNameId:", "青山湖区昌东三联村"),
                new BasicNameValuePair("queryForm:deviceName:", "青山湖区昌东三联村机房/开关电源01"),
                new BasicNameValuePair("queryForm:unitTypeHidden:", "36011140600118"),
                new BasicNameValuePair("queryForm:midType:", "遥测"),
//            new BasicNameValuePair("queryForm:starttimeInputDate:","2019-07-09 14:44"),
//            new BasicNameValuePair("queryForm:starttimeInputCurrentDate:","07/2019"),
//            new BasicNameValuePair("queryForm:endtimeInputDate:","2019-07-10 14:44"),
//            new BasicNameValuePair("queryForm:endtimeInputCurrentDate:","07/2019"),
                new BasicNameValuePair("queryForm:currPageObjId:", "0"),
                new BasicNameValuePair("queryForm:pageSizeText:", "35"),
                new BasicNameValuePair("javax.faces.ViewState:", "j_id31"),
                new BasicNameValuePair("queryForm:j_id43:", "queryForm:j_id43")
        };
        request.putExtra("nameValuePairs", nameValuePairs);
        Spider.create(new JobProcessor())
                .addRequest(request)
                .thread(5)
                .run(); //执行爬虫
    }

}
