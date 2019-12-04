package cn.itcast.task;

import cn.itcast.dao.TuInfoDao;
import cn.itcast.pojo.TuInfo;
import cn.itcast.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CopyImageToDisk {


    @Autowired
    TuInfoDao tuInfoDao;

    @Autowired
    HttpClientUtil httpClientUtil;

    @Value("${path.image.download}")
    String path;

//    @Scheduled(initialDelay = 1000*10, fixedDelay = 10000*5)
    public void CopyImageToDiskFun() {
        List<TuInfo> all = tuInfoDao.findAll();
        for (TuInfo tuInfo : all) {
            httpClientUtil.doGetImage(tuInfo.getUrl(), tuInfo.getTitle(),path);
        }
    }
}
