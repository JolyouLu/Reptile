package jsoup;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import javax.xml.transform.Source;
import java.io.File;
import java.net.URL;

/**
 * @Author: LZJ
 * @Date: 2019/7/6 16:37
 * @Version 1.0
 */
public class JsoupFirstTest {
    @Test
    public void testUrl() throws Exception{
        //解析url地址,第一给参数的访问的url，第二给参数是访问的超时时间
        Document doc = Jsoup.parse(new URL("http://www.itcast.cn"), 10000);
        //使用标签选择器,获取title标签中的内容
        String title = doc.getElementsByTag("title").first().text();
        //打印
        System.out.println(title);

    }

    @Test
    public void testString() throws Exception{
        //使用工具类读取文件，获取字符串
        String conetxt = FileUtils.readFileToString(new File("D:/images/test.html"), "utf-8");
        //解析字符串
        Document document = Jsoup.parse(conetxt);
        String title = document.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    @Test
    public void testFile() throws Exception{
        //解析文件
        Document document = Jsoup.parse(new File("D:/images/test.html"), "utf-8");
        String title = document.getElementsByTag("title").first().text();
        System.out.println(title);

    }

    @Test
    public void testDom() throws Exception{
        //解析文件
        Document document = Jsoup.parse(new File("D:/images/test.html"), "utf-8");
        //获取元素
        //根据id查询元素
        Element elementById = document.getElementById("auto-header-find-club");
        //根据标签获取元素
        Element first = document.getElementsByTag("a").first();
        //打印元素的内容
        System.out.println(elementById.text());
        System.out.println(first);
    }
    @Test
    public void testSelect() throws Exception{
        //解析文件
        Document document = Jsoup.parse(new File("D:/images/test.html"), "utf-8");
        //通过标签获取
        Elements title = document.select("title");
        System.out.println(title.text());
        //通过id获取加#
        Elements select = document.select("#auto-header-find-club");
        System.out.println(select.text());
        //通过class获取 前面+。
        Elements select1 = document.select(".greylink");
        System.out.println(select1.text());
        //通过属性获取加【】
        Element first = document.select("[data-val]").first();
        //通过属性与属性值
        Elements select2 = document.select("[class=dd-div3-pp]");
        for (Element element : select2) {
            System.out.println(element);
        }
        System.out.println(first);
    }
    @Test
    public void testSelector2() throws  Exception{
        //解析文件
        Document document = Jsoup.parse(new File("D:/images/test.html"), "utf-8");
        //元素+ID el#id
        Element select = document.select("h2#auto-header-club-tab0").first();
        System.out.println(select);
        //元素+class el.class
        //元素+属性名 el[attr]
        //任意组合 span[abc].s_name
        //找某一个元素下子元素 .city li
        //找某一个元素下的直接子元素 .city > ul >li
        //找某一个父元素下所有直接子元素 parent > *

    }

}
