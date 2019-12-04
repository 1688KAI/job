package cn.itcast;

import cn.itcast.dao.TuInfoDao;
import cn.itcast.pojo.TuInfo;
import cn.itcast.util.HttpClientUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JobApplicationTests {


    @Autowired
    TuInfoDao tuInfoDao;

    @Autowired
    HttpClientUtil httpClientUtil;
    @Test
    void contextLoads() {
        List<TuInfo> tuInfos = tuInfoDao.findAll();


        for (TuInfo tuInfo : tuInfos) {
//            httpClientUtil.doGetImage(tuInfo.getUrl(), tuInfo.getTitle(),"");
        }



    }

}
