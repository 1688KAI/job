package cn.itcast.task.service;

import cn.itcast.model.gen.TuInfo;
import cn.itcast.service.TuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class SpringDataTuInfoPipeline implements Pipeline {

    @Autowired
    TuInfoService tuInfoService;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    @Override
    public void process(ResultItems resultItems, Task task) {

        ArrayList<TuInfo> tuInfos = resultItems.get("tuInfos");
        CopyOnWriteArrayList<TuInfo> tuInfos1 = new CopyOnWriteArrayList<>(tuInfos);
        for (TuInfo tuInfo : tuInfos1) {
            if (tuInfo != null) {
                System.out.println(tuInfo.getTitle());
                System.out.println(Thread.currentThread().getName());
                tuInfoService.save(tuInfo);
            }
        }

    }
}
