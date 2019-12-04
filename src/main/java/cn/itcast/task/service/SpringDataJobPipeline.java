package cn.itcast.task.service;

import cn.itcast.model.gen.JobInfo;
import cn.itcast.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;


@Component
public class SpringDataJobPipeline implements Pipeline {

    @Autowired
    JobInfoService jobInfoService;
    @Override
    public void process(ResultItems resultItems, Task task) {
        if (null!=resultItems){
            JobInfo jobInfo=resultItems.get("jobInfo");
            if (jobInfo != null) {
                jobInfoService.save(jobInfo);
            }
        }
    }
}
