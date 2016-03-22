package pojo;

import java.math.BigDecimal;
import java.util.Date;

public class FilmInfo {
    private Integer id;
    private Integer douban_id;
    private Date datetime;
    private String title;
    private String content;
    private String label;
    private String origin;
    private DouBanInfo douBanInfo;




    public Integer getDouban_id() {
        return douban_id;
    }

    public void setDouban_id(Integer douban_id) {
        this.douban_id = douban_id;
    }
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }


    public DouBanInfo getDouBanInfo() {
        return douBanInfo;
    }

    public void setDouBanInfo(DouBanInfo douBanInfo) {
        this.douBanInfo = douBanInfo;
    }
}