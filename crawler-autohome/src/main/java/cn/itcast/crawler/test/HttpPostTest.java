package cn.itcast.crawler.test;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LZJ
 * @Date: 2019/7/6 14:50
 * @Version 1.0
 */
public class HttpPostTest {
    public static void main(String[] args) throws Exception {
        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //设置请求地址http://yun.itheima.com/course?key=java
        //创建httpPost对象，设置url访问地址
        HttpPost httpPost = new HttpPost("http://yun.itheima.com/course");
        //声明List集合，封装表单中的参数
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("keys", "java"));
        //创建表单的Entity对象
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "utf-8");
        //设置表单的Entity对象到post请求中
        httpPost.setEntity(formEntity);
        CloseableHttpResponse response = null;
        try {
            //使用httpclient发起请求，获取response
            response = httpClient.execute(httpPost);
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
