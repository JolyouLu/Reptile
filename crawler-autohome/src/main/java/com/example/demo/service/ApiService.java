package com.example.demo.service;

/**
 * @Author: LZJ
 * @Date: 2019/7/5 9:35
 * @Version 1.0
 */
public interface ApiService {
    /**
     * get请求获取页面数据
     *
     * @param url
     * @return
     */
    public String getHtml(String url);

    /**
     * get请求下载图片
     *
     * @param url
     * @return 返回图片名称
     */
    public String getImage(String url);
}
