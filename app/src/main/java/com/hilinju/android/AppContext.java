package com.hilinju.android;

import com.hilinju.android.base.BaseApplication;


/**
 * Created by qiuxj on 2015/5/25.
 */
public class AppContext extends BaseApplication {
    public static final int PAGE_SIZE = 20;// 默认分页大小

    private static AppContext instance;
    private String token;
    private boolean login;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public String getToken() {
        return token;
    }

    public boolean isLogin() {
        return login;
    }


    /**
     * 获得当前app运行的AppContext
     *
     * @return
     */
    public static AppContext getInstance() {
        return instance;
    }

    public static boolean isFirstStart() {
        return getPreferences().getBoolean(AppConfig.KEY_FIRST_START, true);
    }

    public static void setFirstStart(boolean first) {
        set(AppConfig.KEY_FIRST_START, first);
    }
}

