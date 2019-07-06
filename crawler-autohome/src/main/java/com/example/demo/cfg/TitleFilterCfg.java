package com.example.demo.cfg;

import com.example.demo.service.CarTestService;
import com.example.demo.util.TitleFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Author: LZJ
 * @Date: 2019/7/5 11:27
 * @Version 1.0
 */
@Configuration
public class TitleFilterCfg {

    @Autowired
    private CarTestService carTestService;

    @Bean
    public TitleFilter titleFilter(){
        //创建汽车标题的去重过滤器
        TitleFilter titleFilter = new TitleFilter();
        //声明页面数
        int page = 1,pageSize =0;
        do {
            //查询数据库中title数据，因为数据量大，最好分页查询
            List<String> titles = this.carTestService.queryTitleByPage(page,500);
            for (String title : titles) {
                //初始化数据，把数据库中已有的数据汽车标题放到去重过滤器中
                titleFilter.add(title);
            }
            //执行完成后页码+1
            page ++;
            pageSize = titles.size();
        }while (pageSize == 500);
        return titleFilter;
    }
}
