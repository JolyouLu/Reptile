package cn.itcast.crawler.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @Author: LZJ
 * @Date: 2019/7/6 14:50
 * @Version 1.0
 */
public class CrawlerFist {
    public static void main(String[] args) throws Exception {
        //打开浏览器，创建httpclitent对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //输入网站
        HttpGet httpGet = new HttpGet("http://www.itcast.cn/");

        //回车发起请求，返回响应
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //解析响应获取数据
        //判断状态码是否是200
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = response.getEntity();
            String string = EntityUtils.toString(httpEntity, "utf-8");
            System.out.println(string);
        }
    }
}
