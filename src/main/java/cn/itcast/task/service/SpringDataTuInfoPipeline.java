package cn.itcast.task.service;

import cn.itcast.model.gen.TuInfo;
import cn.itcast.service.TuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class SpringDataTuInfoPipeline implements Pipeline {

    @Autowired
    TuInfoService tuInfoService;



    @Override
    public  void process(ResultItems resultItems, Task task) {


        ArrayList<TuInfo> tuInfos = resultItems.get("tuInfos");
        if (tuInfos != null && !tuInfos.isEmpty()) {
//                List<TuInfo> tuInfos1 = Collections.synchronizedList(tuInfos);
//            CopyOnWriteArrayList<TuInfo> tuInfos1 = new CopyOnWriteArrayList<>(tuInfos);
            for (TuInfo tuInfo : tuInfos) {
                if (tuInfo != null) {
                    tuInfoService.save(tuInfo);
                }
            }
        }


    }


}
