package com.lzj.job.task;

import com.lzj.job.pojo.JobInfo;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @Author: LZJ
 * @Date: 2019/7/9 15:06
 * @Version 1.0
 */
//@Component
public class JpbProcessor implements PageProcessor {

    private String url = "https://search.51job.com/list/000000,000000,0000,32,9,99,java,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";

    @Override
    public void process(Page page) {
        //解析页面，获取招聘信息详情的url地址
        List<Selectable> list = page.getHtml().css("div#resultList div.el").nodes();
        //判断获取的集合是否为空
        if (list.size()==0){
            //如果为空，表示这是招聘信息详情页,解析页面，获取招聘详情信息，保存数据
            this.saveJobInfo(page);
        }else {
            //如果不为空，表示这是列表页,解析出url地址，放到任务队列中
            for (Selectable selectable : list) {
                //获取url地址
                String jobInfoUrl = selectable.links().toString();
                //把获取到的url地址放到任务队列中
                page.addTargetRequest(jobInfoUrl);
            }
            //获取下一页url
            String bkUrl = page.getHtml().css("div.p_in li.bk").nodes().get(1).links().toString();
            //把url放入到任务队列中
            page.addTargetRequest(bkUrl);
        }
        String html = page.getHtml().toString();
        System.out.println(html);
    }
    //解析页面，获取招聘信息，保存数据
    private void saveJobInfo(Page page) {
        //创建招聘详情对象
        JobInfo jobInfo = new JobInfo();
        //解析页面
        Html html = page.getHtml();
        //获取数据，封装到对象中
        jobInfo.setCompanyName(html.css("div.cn p.cname a","text").toString());
        jobInfo.setCompanyAddr(Jsoup.parse(html.css("div.bmsg").nodes().get(1).toString()).text());
        jobInfo.setCompanyInfo(Jsoup.parse(html.css("div.tmsg").toString()).text());
        jobInfo.setJobName(html.css("div.cn h1","text").toString());
        jobInfo.setJobAddr(Jsoup.parse(html.css("div.cn > p.msg ").regex("\\D\\D-\\D{2,3}").toString()).text());
        jobInfo.setJobInfo(Jsoup.parse(html.css("div.bmsg").nodes().get(0).toString()).text());
        jobInfo.setUrl(Jsoup.parse(html.css("div.tHeader > div.in > div.cn p.cname a").links().toString()).text());
        //获取薪资
        Integer[] salary = MathSalary.getSalary(html.css("div.cn strong", "text").toString());
        jobInfo.setSalaryMin(salary[0]);
        jobInfo.setSalaryMax(salary[1]);
        String time = Jsoup.parse(html.css("div.tHeader > div.in > div.cn p.msg").regex("0\\d-\\d\\d\\D{2}").toString()).text();
        jobInfo.setTime(time.substring(0,time.length()-2));
        //把结果保存起来
        page.putField("jobInfo",jobInfo);
    }

    private Site site = Site.me()
            .setCharset("gbk") //设置编码格式
            .setTimeOut(10*1000) //设置超时时间
            .setRetrySleepTime(3000) //设置重试的间隔时间
            .setRetryTimes(3); //设置重试的次数

    @Override
    public Site getSite() {
        return site;
    }

    @Autowired
    private SpringDataPipeline springDataPipeline;

    //initialDelay当任务启动后，等待多久执行方法
    //fixedDelay每隔多久执行这个方法
    @Scheduled(initialDelay = 1000, fixedDelay = 100 * 1000)
    public void process() {
        Spider.create(new JpbProcessor())
                .addUrl(url)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .addPipeline(this.springDataPipeline)
                .thread(10)
                .run();
    }
}
