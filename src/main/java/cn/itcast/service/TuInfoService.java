package cn.itcast.service;

import cn.itcast.pojo.JobInfo;
import cn.itcast.pojo.TuInfo;

import java.util.List;

public interface TuInfoService {
    void save(TuInfo tuInfo);

    List findAll(TuInfo tuInfo);
}
