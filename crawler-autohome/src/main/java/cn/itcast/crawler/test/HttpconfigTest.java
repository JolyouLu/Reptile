package cn.itcast.crawler.test;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Author: LZJ
 * @Date: 2019/7/6 14:50
 * @Version 1.0
 */
public class HttpconfigTest {
    public static void main(String[] args) throws Exception {
        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httget对象，设置url访问地址
        HttpGet httpGet = new HttpGet("yun.itheima.com/course?key=java");
        //配置请求信息
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000)//创建连接的最长时间
                .setConnectionRequestTimeout(500) //设置获取连接的最长时间
                .setSocketTimeout(10 * 1000) //设置数据传送的最长时间
                .build();
        //给请求设置信息
        httpGet.setConfig(config);
        CloseableHttpResponse response = null;
        try {
            //使用httpclient发起请求，获取response
            response = httpClient.execute(httpGet);
            //解析响应
            if (response.getStatusLine().getStatusCode() == 200) {
                String string = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
