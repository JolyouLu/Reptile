package com.example.demo.cfg;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: LZJ
 * @Date: 2019/7/4 15:56
 * @Version 1.0
 */
@Configuration
public class HttpClientConnectManagerCfg {
    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager(){
        //创建链接管理器
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        //设置最大链接数
        cm.setMaxTotal(200);
        //设置每个主机最大连接数
        cm.setDefaultMaxPerRoute(20);
        return cm;
    }
}
