package com.hilinju.android.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qiuxj on 2015/5/27.
 */
public interface ListEntity <T extends Base> extends Serializable {
    public List<T> getList();
}
