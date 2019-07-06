package com.example.demo.service.impl;

import com.example.demo.service.ApiService;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @Author: LZJ
 * @Date: 2019/7/5 9:38
 * @Version 1.0
 */
@Service
public class ApiServiceImpl implements ApiService {
    //注入httpClient连接管理器
    @Autowired
    private PoolingHttpClientConnectionManager cm;

    @Override
    public String getHtml(String url) {
        //获取httpcleient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        //声明httpget请求
        HttpGet httpGet = new HttpGet(url);
        //设置用户代理信息
        httpGet.setHeader("User-Agent","");
        //设置请求参数RequestConfig
        httpGet.setConfig(this.getConfig());
        CloseableHttpResponse response = null;
        try {
            //使用httpclient发起请求，返回response
             response = httpClient.execute(httpGet);
            //解析response返回的数据
            if (response.getStatusLine().getStatusCode() == 200){
                String html = "";
                //如果response.getEntity获取结果是空，在执行EntityUtils.toString会报错
                //需要对Entity进行非空判断
                if (response.getEntity()!=null){
                    html = EntityUtils.toString(response.getEntity(), "UTF-8");
                }
                return  html;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (response!=null) {
                    //关闭连接
                    response.close();
                }
                //不能关闭，现在使用的连接管理器
//                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String getImage(String url) {
        //获取httpcleient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        //声明httpget请求
        HttpGet httpGet = new HttpGet(url);
        //设置用户代理信息
        httpGet.setHeader("User-Agent","");
        //设置请求参数RequestConfig
        httpGet.setConfig(this.getConfig());
        CloseableHttpResponse response = null;
        try {
            //使用httpclient发起请求，返回response
            response = httpClient.execute(httpGet);
            //解析response下载图片
            if (response.getStatusLine().getStatusCode() == 200){
                //image/gif
                String contentType = response.getEntity().getContentType().getValue();
                //获取文件类型image[0]/[1]gif[2]从第1个字符开始切
                String extName = "."+contentType.split("/")[1];
                //使用uuid生成图片名
                String  imageName= UUID.randomUUID().toString()+extName;
                //声明输出的文件
                OutputStream outStream = new FileOutputStream(new File("D:/images/"+imageName));
                //使用响应体输出文件
                response.getEntity().writeTo(outStream);
                //返回生成的图片名称
                return imageName;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (response!=null) {
                    //关闭连接
                    response.close();
                }
                //不能关闭，现在使用的连接管理器
//                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取请求参数对象
     * @return
     */
    private RequestConfig getConfig(){
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000)//设置创建连接超时时间
                .setConnectionRequestTimeout(500)//设置获取连接超时时间
                .setSocketTimeout(10000)//设置连接超时时间
                .build();
        return  config;
    }
}
