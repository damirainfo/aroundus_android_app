package com.hilinju.android.helper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hilinju.android.AppContext;
import com.hilinju.android.base.SimpleBackPage;
import com.hilinju.android.ui.activity.SimpleBackActivity;

/**
 * Created by qiuxj on 2015/5/27.
 */
public class UIHelper {

    /**
     * 进入用户中心
     * @param context
     * @param uid
     * @param name
     */
    public static void showUserCenter(Context context, int uid,
                                      String name) {
        if (uid == 0 && name.equalsIgnoreCase("匿名")) {
            AppContext.showToast("提醒你，该用户为非会员");
            return;
        }
        Bundle args = new Bundle();
        args.putInt("uid", uid);
        args.putString("name", name);
        showSimpleBack(context, SimpleBackPage.USER_CENTER, args);
    }

    public static void showSimpleBack(Context context, SimpleBackPage page) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        context.startActivity(intent);
    }

    public static void showSimpleBack(Context context, SimpleBackPage page,
                                      Bundle args) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_ARGS, args);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        context.startActivity(intent);
    }

}
