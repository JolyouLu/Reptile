package com.lzj.jd.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @Author: LZJ
 * @Date: 2019/7/6 18:24
 * @Version 1.0
 */
@Component
public class HttpUtils {

    private PoolingHttpClientConnectionManager cm;

    public HttpUtils() {
        this.cm = new PoolingHttpClientConnectionManager();
        //设置最大连接数
        this.cm.setMaxTotal(100);
        //设置每个主机最大连接数
        this.cm.setDefaultMaxPerRoute(10);
    }

    /**
     * 根据请求地址下载页面
     *
     * @param url
     * @return 页面数据
     */
    public String doGetHtml(String url) {
        //获取httpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        //设置hpptGet请求对象，设置url地址
        HttpGet httpGet = new HttpGet(url);
        //设置请求信息
        httpGet.setConfig(this.getConfig());
        CloseableHttpResponse response = null;
        try {
            //使用httpClient发起请求获取响应
            response = httpClient.execute(httpGet);
            //解析响应，返回结果
            if (response.getStatusLine().getStatusCode() == 200) {
                //判断响应体Entity是否不为空，如果不为空可以使用EntityUtils
                if (response.getEntity() != null) {
                    String content = EntityUtils.toString(response.getEntity(), "utf-8");
                    return content;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭response
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }


    /**
     * 下载图片
     *
     * @param url
     * @return 图片名称
     */
    public String doGetImage(String url) {
        //获取httpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        //设置hpptGet请求对象，设置url地址
        HttpGet httpGet = new HttpGet(url);
        //设置请求信息
        httpGet.setConfig(this.getConfig());
        CloseableHttpResponse response = null;
        try {
            //使用httpClient发起请求获取响应
            response = httpClient.execute(httpGet);
            //解析响应，返回结果
            if (response.getStatusLine().getStatusCode() == 200) {
                //判断响应体Entity是否不为空，如果不为空可以使用EntityUtils
                if (response.getEntity() != null) {
                    //下载图片
                    //获取图片的后缀
                    String extName = url.substring(url.lastIndexOf("."));
                    //创建图片名称，重命名图片
                    String picName = UUID.randomUUID().toString() + extName;
                    //下载图片
                    //声明outputstram
                    OutputStream outputStream = new FileOutputStream(new File("D:/images/" + picName));
                    response.getEntity().writeTo(outputStream);
                    //返回图片名称
                    return picName;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭response
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    //设置请求信息
    private RequestConfig getConfig() {
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(1000)
                .setConnectionRequestTimeout(500)
                .setSocketTimeout(10 * 1000)
                .build();
        return config;
    }
}
