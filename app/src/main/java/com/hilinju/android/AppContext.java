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
     * 清除登录信息
     */
    public void cleanLoginInfo() {
        this.token = null;
        this.login = false;
        /*removeProperty("user.uid", "user.name", "user.face", "user.location",
                "user.followers", "user.fans", "user.score",
                "user.isRememberMe", "user.gender", "user.favoritecount");*/
    }

    /**
     * 用户注销
     */
    public void logout() {
        cleanLoginInfo();
        //TODO
       /* ApiHttpClient.cleanCookie();
        this.cleanCookie();
        this.login = false;
        this.loginUid = 0;

        Intent intent = new Intent(Constants.INTENT_ACTION_LOGOUT);
        sendBroadcast(intent);
        */
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

