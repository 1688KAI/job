package cn.itcast.study.jsoup;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Set;

public class JsoupFirstTest {


    @Test
    public void TestUrl() throws Exception {

        //解析url
        Document document = Jsoup.parse(new URL("http://news.baidu.com"), 10000);
        System.out.println(document.text().length());
        //使用标签选择器 获取title 中的内容
        final String title = document.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    @Test
    public void TestString() throws IOException {

        //使用工具类读取文件 获取字符串
        String content = FileUtils.readFileToString(new File("H:\\java框架阶段\\java爬虫\\code\\aa.txt"), "utf8");
        //解析字符串
        Document document = Jsoup.parse(content);
        //使用标签选择器 获取title 中的内容
        final String title = document.getElementsByTag("title").first().text();
        System.out.println("title = " + title);

    }

    @Test
    public void testFile() throws Exception {

        //解析文件
        Document document = Jsoup.parse(new File("H:\\java框架阶段\\java爬虫\\code\\aa.txt"), "utf8");
        //使用标签选择器 获取title 中的内容
        final String title = document.getElementsByTag("title").first().text();
        System.out.println("title = " + title);

    }

    @Test
    public void TestDom() throws Exception {


        //解析文件
        Document document = Jsoup.parse(new File("H:\\java框架阶段\\java爬虫\\code\\aa.txt"), "utf8");
        System.out.println(document.text().length());

        //1,使用Id查询元素getElementById
        String content = document.getElementById("menu").text();
        //2,使用标签获取元素getElementByTag
        content = document.getElementsByTag("title").first().text();
        //3,使用class获取元素getElementClass
        content = document.getElementsByClass("active").text();
        //4,根据属性获取元素 getElementByattribute
        content = document.getElementsByAttribute("href").text();
        content = document.getElementsByAttributeValue("href", "http://tieba.baidu.com/").text();

        System.out.println(content);
    }


    @Test
    public void TestData() throws Exception {
        //元素中获取数据
        //解析文件
        Document document = Jsoup.parse(new File("H:\\java框架阶段\\java爬虫\\code\\aa.txt"), "utf8");
        System.out.println(document.text().length());

        Element element = document.getElementById("menu");
        String str = "";
        //1. 从元素中获取id
        str = element.id();
        //2. 从元素中获取className
        str = element.className();
        //2. 从元素中获取classNames
        final Set<String> strings = element.classNames();
        for (String string : strings) {
            System.out.println(string);
        }
        //3. 从元素中获取属性的值attribute
        str = element.attr("alog-group");
        //4. 从元素中获取所有所属性attributes
        str = element.attributes().toString();
        //5. 从元素中获取文本text
        str = element.text();
        //打印获取到的内容
        System.out.println("获取到的数据是 = " + str);
    }

    @Test
    public void TestSelector() throws Exception {
        //元素中获取数据
        //解析文件
        Document document = Jsoup.parse(new File("H:\\java框架阶段\\java爬虫\\code\\aa.txt"), "utf8");
        System.out.println(document.text().length());

        //tagname: 通过标签查找元素, 比如: span
        final Elements elements = document.select("span");
        for (Element element : elements) {
            System.out.println(element.text());
        }
        //#id : 通过id查找元素,比如: #city_bj
        Element elements1 = document.select("#menu").first();
        System.out.println("通过id获取元素:" + elements1.text());
        //.class: 通过class名称查找元素,比如: .class_a
        final Element elements2 = document.select(".a3").first();
        System.out.println("通过class获取元素:" + elements2.text());
        //[attribute]:利用属性查找元素,比如:[abc]
        final Elements elements3 = document.select("[target]");
        for (Element element : elements3) {
            System.out.println("[attribute]:利用属性查找元素" + element.text());
        }
        //[attr-value]:利用属性值来查找元素,比如:[class-s_name]
        final Elements elements4 = document.select("[mon=\"r=1\"]");
        for (Element element : elements4) {
            System.out.println("[attr-value]:利用属性值来查找元素," + element.text());
        }
    }


    @Test
    public void TestSelector2() throws Exception {
        //元素中获取数据
        //解析文件
        Document document = Jsoup.parse(new File("H:\\java框架阶段\\java爬虫\\code\\aa.txt"), "utf8");
        System.out.println(document.text().length());



        //el#id: 元素+ID 比如h3#city_bj
        Element element = document.select("div#menu").first();
        //el.class: 元素_class 比如 li.class_a
        element=document.select("li.navitem-index").first();
        //ancestor child: 查找某个元素下子元素, 比如 .city_con li 查到city_con下所有的li
        element=document.select(".clearfix li").first();
        //el[attr]: 元素+属性名 比如 span[abc]
        element=document.select("a[data-control]").first();


        //任意组合: 比如span[abc].s_name
        element=document.select("label[for].checked").first();

        //parent>child  查找某个父元素下的直接 ul>li

        element=document.select("ul>li").first();

        //.city_con >ul >li 查到city_从第一级 (直接子元素)的ul, 在找所有ul下的第一级li

        element=document.select(".hotnews>ul>li").first();

        //parent>* 查找某个父元素下所有直接的子元素

        final String text = document.select("ul>*").text();

        System.out.println(text);
        System.out.println("获取到的内容是:" + element.text());
    }


}
