package com.hilinju.android.ui.activity;

import com.hilinju.android.R;
import com.hilinju.android.ui.fragment.TopicFragment;

/**
 * Created by qiuxj on 2015/5/27.
 */
public enum MainTab {

    TOPIC(0, R.string.main_tab_name_topic, R.drawable.tab_icon_topic,
            TopicFragment.class),
    CHAT(1, R.string.main_tab_name_chat, R.drawable.tab_icon_topic,
            TopicFragment.class),
    QUICK(2, R.string.main_tab_name_quick, R.drawable.tab_icon_topic,
            null),
    NEARBY(3, R.string.main_tab_name_nearby, R.drawable.tab_icon_topic,
            TopicFragment.class),
    NETWORK(4, R.string.main_tab_name_network, R.drawable.tab_icon_topic,
            TopicFragment.class);

    private int idx;
    private int resName;
    private int resIcon;
    private Class<?> clz;

    private MainTab(int idx, int resName, int resIcon, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }

}
