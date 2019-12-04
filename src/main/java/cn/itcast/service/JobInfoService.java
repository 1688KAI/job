package cn.itcast.service;

import cn.itcast.pojo.JobInfo;

import java.util.List;

public interface JobInfoService {

    void save(JobInfo jobinfo);

    List findAll(JobInfo jobInfo);
}
