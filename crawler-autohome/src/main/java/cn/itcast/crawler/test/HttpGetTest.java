package cn.itcast.crawler.test;

import org.apache.http.HttpEntity;
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
public class HttpGetTest {
    public static void main(String[] args) throws Exception {
        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //设置请求地址http://yun.itheima.com/course?key=java
        //创建uriBuilder对象
        URIBuilder uriBuilder = new URIBuilder("http://yun.itheima.com/course");
        //设置参数
        uriBuilder.setParameter("keys", "java");
        //创建httpget对象,设置url访问地址
        HttpGet httpGet = new HttpGet(uriBuilder.build());
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
