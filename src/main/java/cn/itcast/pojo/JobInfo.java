package cn.itcast.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "job_info", schema = "test", catalog = "")
public class JobInfo {
    private Long id;
    private String companyName;
    private String companyAddr;
    private String companyInfo;
    private String jobName;
    private String jobAddr;
    private String jobInfo;
    private Integer salaryMin;
    private Integer salaryMax;
    private String url;
    private String time;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "company_addr")
    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    @Basic
    @Column(name = "company_info",columnDefinition="TEXT")
    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    @Basic
    @Column(name = "job_name")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Basic
    @Column(name = "job_addr")
    public String getJobAddr() {
        return jobAddr;
    }

    public void setJobAddr(String jobAddr) {
        this.jobAddr = jobAddr;
    }

    @Basic
    @Column(name = "job_info",columnDefinition="TEXT")
    public String getJobInfo() {
        return jobInfo;
    }

    public void setJobInfo(String jobInfo) {
        this.jobInfo = jobInfo;
    }

    @Basic
    @Column(name = "salary_min")
    public Integer getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(Integer salaryMin) {
        this.salaryMin = salaryMin;
    }

    @Basic
    @Column(name = "salary_max")
    public Integer getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(Integer salaryMax) {
        this.salaryMax = salaryMax;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobInfo jobInfo1 = (JobInfo) o;
        return Objects.equals(id, jobInfo1.id) &&
                Objects.equals(companyName, jobInfo1.companyName) &&
                Objects.equals(companyAddr, jobInfo1.companyAddr) &&
                Objects.equals(companyInfo, jobInfo1.companyInfo) &&
                Objects.equals(jobName, jobInfo1.jobName) &&
                Objects.equals(jobAddr, jobInfo1.jobAddr) &&
                Objects.equals(jobInfo, jobInfo1.jobInfo) &&
                Objects.equals(salaryMin, jobInfo1.salaryMin) &&
                Objects.equals(salaryMax, jobInfo1.salaryMax) &&
                Objects.equals(url, jobInfo1.url) &&
                Objects.equals(time, jobInfo1.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, companyAddr, companyInfo, jobName, jobAddr, jobInfo, salaryMin, salaryMax, url, time);
    }
}
