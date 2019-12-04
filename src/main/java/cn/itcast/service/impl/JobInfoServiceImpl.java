package cn.itcast.service.impl;

import cn.itcast.dao.JobInfoDao;
import cn.itcast.pojo.JobInfo;
import cn.itcast.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class JobInfoServiceImpl implements JobInfoService {


    @Autowired
    JobInfoDao jobInfoDao;

    @Override
    @Transactional
    public void save(JobInfo jobinfo) {
        //根据url和 时间 查询数据
        final JobInfo parame = new JobInfo();
        parame.setUrl(jobinfo.getUrl());
        parame.setTime(jobinfo.getTime());

        final List<JobInfo> list = findAll(parame);
        if (list.size()==0){
            if (jobinfo.getTime()!=null)
            jobInfoDao.save(jobinfo);
        }
    }
    @Override
    public List<JobInfo> findAll(JobInfo jobInfo) {
        Example example = Example.of(jobInfo);
        return jobInfoDao.findAll(example);
    }
}
