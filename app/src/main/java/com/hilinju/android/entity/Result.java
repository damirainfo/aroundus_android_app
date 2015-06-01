package com.hilinju.android.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by qiuxj on 2015/6/1.
 */
public class Result<T> {

    private boolean success = false;
    private String msg;
    private String errCode;
    private T record;
    private List<T> records;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public String getErrCode() {
        return errCode;
    }

    public T getRecord() {
        return record;
    }

    public List<T> getRecords() {
        return records;
    }

}
