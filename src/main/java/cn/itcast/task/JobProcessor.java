package cn.itcast.task;


import cn.itcast.pojo.JobInfo;
import cn.itcast.task.service.SpringDataJobPipeline;
import cn.itcast.util.MathSalary;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Date;
import java.util.List;

@Component
public class JobProcessor implements PageProcessor {


    String url = "https://search.51job.com/list/080200,080201,0000,00,9,99,java,2,1.html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&ord_field=0&dibiaoid=2372&line=&welfare=";


    Site site = new Site().me()
            .setTimeOut(10 * 10000)
//            .setCharset()
            .setRetryTimes(3)
            .setUserAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.3; .NET4.0C; .NET4.0E)")
            .setRetrySleepTime(3000);

    @Override
    public Site getSite() {
        return this.site;
    }

    @Override
    public void process(Page page) {

        List<Selectable> list = page.getHtml().css("div#resultList div.el").nodes();

        if (!list.isEmpty()) {
            //表示这个是 列表页 解析处详情页的url地址 放进任务对列中
            for (Selectable selectable : list) {
                final String jobInfoUrl = selectable.links().toString();
                //获取到的url地址放进 任务队列中
                page.addTargetRequest(jobInfoUrl);
            }
            //获取下一页的url
            final String bkUrl = page.getHtml().css("div.p_in li.bk").nodes().get(1).links().toString();
            //把url放进队列中
            page.addTargetRequest(bkUrl);
        }
        //判断获取到的集合 是否为空
        else {
            //如果为空 表示这个招聘的详情页面解析页面 获取招聘的详细信息 保存数据
            this.saveJobInfo(page);
        }

//        final String html = page.getHtml().toString();
    }

    //    获取招聘的详细信息 保存数据
    private void saveJobInfo(Page page) {

        //创建招聘详情对象


        final JobInfo jobInfo = new JobInfo();
        //解析页面
        final Html html = page.getHtml();

        String comAddr = html.css("div.cn p.msg", "text").toString();
        String comInfo = html.css("div.tmsg", "text").toString();
        String comName = html.css("p.cname a", "text").toString();
        //获取数据


        if (comAddr != null) {
            String[] split = comAddr.split("    ");
            comAddr = split[0].replace(" ", "");
        }


        jobInfo.setCompanyAddr(comAddr);
        jobInfo.setCompanyInfo(comInfo);
        jobInfo.setCompanyName(comName);

        final String jobInf = html.css("div.job_msg *", "text").all().toString();
        final String jobName = html.css("div.cn h1", "text").toString();
        final String jobAddr = html.css("div[class=\"bmsg inbox\"] p.fp", "text").toString();
        jobInfo.setJobName(jobName);
        jobInfo.setJobInfo(jobInf);
        jobInfo.setJobAddr(jobAddr);


        jobInfo.setUrl(page.getUrl().toString());
        jobInfo.setTime(new Date().toString());


        final String salary = html.css("div.cn strong", "text").toString();
        if (!Strings.isNullOrEmpty(salary)) {
            final Integer[] salary1 = MathSalary.getSalary(salary);
            jobInfo.setSalaryMax(salary1[1]);
            jobInfo.setSalaryMin(salary1[0]);
        }

        String time = html.css("p.ltype", "text").regex(".*发布").toString();

        if (!Strings.isNullOrEmpty(time)) {
            String[] split = time.split("    ");
            for (String s : split) {
                if (s.indexOf("发布") != -1) {
                    time = s.replaceAll(" ", "").replaceAll("发布", "");
                }
            }
        }
        jobInfo.setTime(time);
        //把结果 保存起来  使用springboot data 保存
        page.putField("jobInfo", jobInfo);
        //封装到对象总
    }


    @Autowired
    SpringDataJobPipeline springDataPipeline;

    //但任务启动后 一秒后  initialDelay
    //任务分隔多久行一次
//    @Scheduled(initialDelay = 1000, fixedDelay = 1000 * 5)
    public void processor() {
        Spider.create(new JobProcessor())
                .addUrl(url)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .thread(5)
                .addPipeline(this.springDataPipeline)
                .run();
    }


}
