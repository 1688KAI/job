package cn.itcast.service.impl;

import cn.itcast.mapper.TuInfoMapper;
import cn.itcast.model.gen.TuInfo;
import cn.itcast.model.gen.TuInfoExample;
import cn.itcast.service.TuInfoService;
import cn.itcast.util.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TuInfoServiceImpl implements TuInfoService {


    @Autowired
    TuInfoMapper tuInfoMapper;

    @Autowired
    private RedisLock redisLock;


    @Override
    @Transactional
    public void save(TuInfo tuInfo) {
        //根据url和 标题 查询数据
        long time = System.currentTimeMillis() + 1000 * 10;  //超时时间：10秒，最好设为常量
        //加锁
        boolean isLock = redisLock.lock(String.valueOf(tuInfo.getUrl()), String.valueOf(time));
        if (!isLock) {
            throw new RuntimeException("人太多了，换个姿势再试试~");
        }
        TuInfoExample tuInfoExample = new TuInfoExample();
        tuInfoExample.createCriteria().andUrlEqualTo(tuInfo.getUrl()).andTitleEqualTo(tuInfo.getTitle());
        List<TuInfo> list = tuInfoMapper.selectByExample(tuInfoExample);
        if (list.size() == 0) {
//            System.out.println(tuInfo.getTitle());
//            System.out.println(Thread.currentThread().getName()+"持久化数据库");
            int row = tuInfoMapper.insertSelective(tuInfo);
        }
        redisLock.unlock(String.valueOf(tuInfo.getUrl()), String.valueOf(time));

        //解锁
    }

}
