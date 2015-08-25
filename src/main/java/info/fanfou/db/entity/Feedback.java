package info.fanfou.db.entity;

import java.io.Serializable;
import java.util.Date;

public class Feedback implements Serializable {
    private Integer id;

    private Long userId;

    private String content;

    private Date createdDatetime;

    private Date updatedDatetime;

    private static final long serialVersionUID = 1L;

    public Feedback(Integer id, Long userId, String content, Date createdDatetime, Date updatedDatetime) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.createdDatetime = createdDatetime;
        this.updatedDatetime = updatedDatetime;
    }

    public Feedback() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public Date getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(Date updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }
}