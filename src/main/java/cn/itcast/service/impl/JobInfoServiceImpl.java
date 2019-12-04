package cn.itcast.service.impl;

import cn.itcast.mapper.JobInfoMapper;
import cn.itcast.model.gen.JobInfo;
import cn.itcast.model.gen.JobInfoExample;
import cn.itcast.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobInfoServiceImpl implements JobInfoService {


    @Autowired
    JobInfoMapper jobInfoMapper;

    @Override
    @Transactional
    public void save(JobInfo jobinfo) {
        //根据url和 时间 查询数据
        final JobInfo parame = new JobInfo();
        parame.setUrl(jobinfo.getUrl());
        parame.setTime(jobinfo.getTime());

        JobInfoExample jobInfoExample = new JobInfoExample();
        jobInfoExample.createCriteria().andUrlEqualTo(jobinfo.getUrl());
        List<JobInfo> list = jobInfoMapper.selectByExample(jobInfoExample);
        if (list.size()==0){
            if (jobinfo.getTime()!=null)
                jobInfoMapper.insertSelective(jobinfo);
        }
    }

}
