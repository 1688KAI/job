package cn.itcast.dao;

import cn.itcast.pojo.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobInfoDao extends JpaRepository<JobInfo,Long> {
}
