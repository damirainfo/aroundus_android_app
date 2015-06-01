package com.hilinju.android.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by qiuxj on 2015/5/27.
 */
public class Topic extends Base {

    private ImageAttachment logo;
    private String creator;
    private String content;
    private boolean isAll = false;
    private int commentCount = 0;
    private int favoriteCount = 0;
    private int forwardCount = 0;
    private int likeCount = 0;
    private Date createdAt;
    private List<ImageAttachment> images = new ArrayList<ImageAttachment>();
    private List<Comment> comments = new ArrayList<Comment>();

    public ImageAttachment getLogo() {
        return logo;
    }

    public void setLogo(ImageAttachment logo) {
        this.logo = logo;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isAll() {
        return isAll;
    }

    public void setAll(boolean isAll) {
        this.isAll = isAll;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public int getForwardCount() {
        return forwardCount;
    }

    public void setForwardCount(int forwardCount) {
        this.forwardCount = forwardCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<ImageAttachment> getImages() {
        return images;
    }

    public void setImages(List<ImageAttachment> images) {
        this.images = images;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
