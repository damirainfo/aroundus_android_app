package com.hilinju.android.entity;

/**
 * Created by qiuxj on 2015/6/1.
 */
public class Creator extends Base{

    private ImageAttachment avatar;
    private String name;
    private String mobile;
    private String email;
    private String address;

    public ImageAttachment getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageAttachment avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
