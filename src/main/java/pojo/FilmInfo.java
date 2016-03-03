package pojo;

import java.math.BigDecimal;
import java.util.Date;

public class FilmInfo {
    private Integer id;
    private Date datetime;
    private String title;
    private String content;
    private String label;
    private String origin;
    private double douban_rating;
    private Object douban;


    public Object getDouban() {
        return douban;
    }

    public void setDouban(Object douban) {
        this.douban = douban;
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


    public double getDouban_rating() {
        return douban_rating;
    }

    public void setDouban_rating(double douban_rating) {
        this.douban_rating = douban_rating;
    }
}