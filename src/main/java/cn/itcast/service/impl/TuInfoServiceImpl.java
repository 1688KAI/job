package cn.itcast.service.impl;

import cn.itcast.dao.TuInfoDao;
import cn.itcast.pojo.TuInfo;
import cn.itcast.service.TuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class TuInfoServiceImpl implements TuInfoService {

    @Autowired
    TuInfoDao tuInfoDao;

    @Override
    public synchronized void  save(TuInfo tuInfo) {
        //根据url和 时间 查询数据
        final TuInfo parame = new TuInfo();
        parame.setUrl(tuInfo.getUrl());
        parame.setUrl(tuInfo.getTitle());
        final List<TuInfo> list = findAll(parame);
        if (list==null){
            tuInfoDao.save(tuInfo);
        }
    }

    @Override
    public List findAll(TuInfo tuInfo) {
        return null;
    }
}
