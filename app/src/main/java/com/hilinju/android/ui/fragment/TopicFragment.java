package com.hilinju.android.ui.fragment;

import java.io.InputStream;
import java.io.Serializable;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hilinju.android.AppContext;
import com.hilinju.android.R;
import com.hilinju.android.adapter.TopicAdapter;
import com.hilinju.android.base.BaseListFragment;
import com.hilinju.android.base.Constants;
import com.hilinju.android.base.EmptyLayout;
import com.hilinju.android.entity.Topic;
import com.hilinju.android.helper.VolleyHelper;
import com.hilinju.android.ui.activity.MainActivity;
import com.hilinju.android.ui.dialog.CommonDialog;
import com.hilinju.android.ui.dialog.DialogHelper;
import com.hilinju.android.util.TDevice;

/**
 * Created by qiuxj on 2015/5/27.
 */
public class TopicFragment extends BaseListFragment<Topic> {

    protected static final String TAG = TopicFragment.class.getSimpleName();
    private static final String CACHE_KEY_PREFIX = "topic_list";
    private boolean mIsWatingLogin; // 还没登陆

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (mErrorLayout != null) {
                mIsWatingLogin = true;
                mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
                mErrorLayout.setErrorMessage(getString(R.string.unlogin_tip));
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter filter = new IntentFilter(Constants.INTENT_ACTION_LOGOUT);
        getActivity().registerReceiver(mReceiver, filter);
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    @Override
    public void onResume() {
        if (mIsWatingLogin) {
            mCurrentPage = 0;
            mState = STATE_REFRESH;
            requestData(false);
        }
        refreshNotice();
        super.onResume();
    }

    /**
     * 开始刷新请求
     */
    private void refreshNotice() {
    }

    @Override
    protected TopicAdapter getAdapter() {
        return new TopicAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return new StringBuffer(CACHE_KEY_PREFIX + mCatalog).append(
                AppContext.getInstance().getToken()).toString();
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mListView.setOnItemClickListener(this);
        mErrorLayout.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (AppContext.getInstance().isLogin()) {
//                    mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
//                    requestData(false);
//                } else {
//                    UIHelper.showLoginActivity(getActivity());
//                }
            }
        });
//        if (AppContext.getInstance().isLogin()) {
//            UIHelper.sendBroadcastForNotice(getActivity());
//        }
    }

    @Override
    protected void requestData(boolean refresh) {
        if (AppContext.getInstance().isLogin()) {
            mIsWatingLogin = false;
            super.requestData(refresh);
        } else {
            mIsWatingLogin = true;
            mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
            mErrorLayout.setErrorMessage(getString(R.string.unlogin_tip));
        }
    }

    @Override
    protected void sendRequestData() {
        //TODO HTTPS example https://github.com/ogrebgr/android_volley_examples/tree/master/src/com/github/volley_examples/toolbox
        String url = "";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (TextUtils.isEmpty(response)) {
                    //listView.onGetDataFailure(page);
                    return;
                }
               // DBHelper.newInstance().save(tab, response);
                //handleData(page, response, System.currentTimeMillis() / 1000);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyErrorHelper.getMessage(error, sActivity);
                //listView.onGetDataFailure(page);
            }
        });
        //VolleyHelper.addToRequestQueue(request, tab);
    }

    /**
     * 点击跳转
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Topic topic = mAdapter.getItem(position);
    }

    @Override
    protected long getAutoRefreshTime() {
        return super.getAutoRefreshTime();
    }

}
