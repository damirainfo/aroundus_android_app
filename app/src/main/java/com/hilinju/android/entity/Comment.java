package com.hilinju.android.entity;

import java.util.Date;

/**
 * Created by qiuxj on 2015/6/1.
 */
public class Comment extends Base {

    private Creator creator;
    private String content;
    private Date createdAt;

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
