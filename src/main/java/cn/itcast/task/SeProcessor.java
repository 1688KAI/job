package cn.itcast.task;

import cn.itcast.model.gen.TuInfo;
import cn.itcast.task.service.SpringDataTuInfoPipeline;
import cn.itcast.util.HttpClientUtil;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeProcessor implements PageProcessor {


    static String url = "https://www.leshe.us/";


    @Autowired
    HttpClientUtil httpClientUtil;
    Site site = Site.me()
            .setTimeOut(10 * 10000)
//            .setCharset()
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50")
            .setRetryTimes(3)
            .setSleepTime(1000*20)
            .setRetrySleepTime(3000);

    @Override
    public void process(Page page) {
        List<Selectable> list = page.getHtml().css(".excerpt-c5").nodes();
        if (!list.isEmpty()) {
            //表示这个是 列表页 解析处详情页的url地址 放进任务对列中
            for (Selectable selectable : list) {
                final String jobInfoUrl = selectable.links().toString();
                //获取到的url地址放进 任务队列中
                page.addTargetRequest(jobInfoUrl);
            }
            //获取下一页的url
            final String bkUrl = page.getHtml().css(".next-page").links().get();
            //把url放进队列中
            page.addTargetRequest(bkUrl);
        }
        //判断获取到的集合 是否为空
        else {
            //如果为空 表示这个招聘的详情页面解析页面 获取招聘的详细信息 保存数据
            this.saveJobInfo(page);
        }
    }

    //    获取招聘的详细信息 保存数据
    private void saveJobInfo(Page page) {
        String detailUrl = page.getRequest().getUrl();
        String title = Jsoup.parse(page.getHtml().css(".focusbox-title").get()).text();
        ArrayList < TuInfo > tuInfos = new ArrayList<>();

        List<String> urlList = page.getHtml().css("#dgwt-jg-0").links().all();
        for (String url : urlList) {
            tuInfos.add(new TuInfo(detailUrl,title,url));
        }
        //把结果 保存起来  使用springboot data 保存
        page.putField("tuInfos", tuInfos);
        //封装到对象总
    }

    @Override
    public Site getSite() {
        return site;
    }

    @Autowired
    SpringDataTuInfoPipeline springDataTuInfoPipeline;
//    @Scheduled(initialDelay = 1000, fixedDelay = 10000)
    public  void processor() {
        Spider.create(new SeProcessor())
                .addUrl(url)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .addPipeline(this.springDataTuInfoPipeline)
                .thread(5)
                .run();
    }


}
