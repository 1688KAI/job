package cn.itcast.pojo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tu_info3", schema = "test", catalog = "")
public class TuInfo {
    private String title;
    private String detailUrl;
    private String url;

    private Long id;

    public TuInfo() {
    }

    public TuInfo(String title, String detailUrl, String url) {
        this.title = title;
        this.detailUrl = detailUrl;
        this.url = url;
    }


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
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "detailUrl")
    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
