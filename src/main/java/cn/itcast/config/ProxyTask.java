package cn.itcast.config;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;


@Component
public class ProxyTask implements PageProcessor {





//    @Scheduled(initialDelay = 1000,fixedDelay = 1000)
    public void processor(){

        //创建下载器 Downloader
        HttpClientDownloader httpClientDownloader =new HttpClientDownloader();


        //给下载器设置dialing服务器信息
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("59.37.33.62",50686)));
        Spider.create(new ProxyTask())
                .addUrl("http://www.ip138.com/ips138.asp?ip=59.37.33.62")
//                .addUrl("https://p.3.cn/prices/mgets?skuIds=J_32951962614")
//                .setDownloader(httpClientDownloader)
                .run();
    }


    @Override
    public void process(Page page) {
        page.getHtml();
    }

    Site site=Site.me()
            .setTimeOut(5000)
            .setSleepTime(3000)
            .setRetryTimes(3);

    @Override
    public Site getSite() {
        return site;
    }
}
