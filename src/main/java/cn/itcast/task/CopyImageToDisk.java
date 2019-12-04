package cn.itcast.task;

import cn.itcast.mapper.TuInfoMapper;
import cn.itcast.model.gen.TuInfo;
import cn.itcast.model.gen.TuInfoExample;
import cn.itcast.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CopyImageToDisk {


    @Autowired
    TuInfoMapper tuInfoMapper;

    @Autowired
    HttpClientUtil httpClientUtil;

    @Value("${path.image.download}")
    String path;

//    @Scheduled(initialDelay = 1000*10, fixedDelay = 10000*5)
    public void CopyImageToDiskFun() {
        List<TuInfo> all = tuInfoMapper.selectByExample(new TuInfoExample());
        for (TuInfo tuInfo : all) {
            httpClientUtil.doGetImage(tuInfo.getUrl(), tuInfo.getTitle(),path);
        }
    }
}
