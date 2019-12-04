package cn.itcast.service.impl;

import cn.itcast.mapper.TuInfoMapper;
import cn.itcast.model.gen.TuInfo;
import cn.itcast.model.gen.TuInfoExample;
import cn.itcast.service.TuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TuInfoServiceImpl implements TuInfoService {



    @Autowired
    TuInfoMapper tuInfoMapper;

    @Override
    public  void save(TuInfo tuInfo) {
        //根据url和 标题 查询数据
        TuInfoExample tuInfoExample = new TuInfoExample();
        tuInfoExample.createCriteria().andUrlEqualTo(tuInfo.getUrl()).andTitleEqualTo(tuInfo.getTitle());
        List<TuInfo> list = tuInfoMapper.selectByExample(tuInfoExample);
        if (list == null) {
            int row = tuInfoMapper.insertSelective(tuInfo);
        }
    }

}
