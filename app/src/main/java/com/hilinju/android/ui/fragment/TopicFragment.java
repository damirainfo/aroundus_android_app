package com.hilinju.android.ui.fragment;

import java.util.HashMap;
import java.util.Map;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.common.internal.Maps;
import com.hilinju.android.AppContext;
import com.hilinju.android.R;
import com.hilinju.android.adapter.TopicAdapter;
import com.hilinju.android.base.BaseListFragment;
import com.hilinju.android.base.Constants;
import com.hilinju.android.base.EmptyLayout;
import com.hilinju.android.entity.Result;
import com.hilinju.android.entity.Topic;
import com.hilinju.android.util.volley.GsonRequest;
import com.hilinju.android.util.volley.VolleyErrorHelper;
import com.hilinju.android.util.volley.VolleyHelper;

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
        //if (AppContext.getInstance().isLogin()) {
            mIsWatingLogin = false;
            super.requestData(refresh);
//        } else {
//            mIsWatingLogin = true;
//            mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
//            mErrorLayout.setErrorMessage(getString(R.string.unlogin_tip));
//        }
    }

    @Override
    protected void sendRequestData() {
        //TODO HTTPS example https://github.com/ogrebgr/android_volley_examples/tree/master/src/com/github/volley_examples/toolbox
        StringRequest request = new StringRequest(Request.Method.POST, "https://api.hilinju.com/api/v1/auth/login", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (TextUtils.isEmpty(response)) {
                    //TODO
                    return;
                }
                Log.e("RESP>>>>", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String errorMessage = VolleyErrorHelper.getMessage(error, getApplication());
                Log.e("ERROR RESP>>>>", errorMessage);
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user[login_name]", "user_0@aroundus.com");
                params.put("user[password]", "00000000");
                return params;
            }
        };
        VolleyHelper.addToRequestQueue(request);

//        Map<String, String> params = Maps.newHashMap();
//        params.put("user[login_name]", "user_0@aroundus.com");
//        params.put("user[password]", "00000000");
//        GsonRequest<Result> r = new GsonRequest<Result>(Request.Method.POST, "https://api.hilinju.com/api/v1/auth/login", Result.class, null, params, new Response.Listener<String>() {
//            @Override
//            public void onResponse(T response) {
//                Log.e("GSON RESP>>>>", "");
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                String errorMessage = VolleyErrorHelper.getMessage(error, getApplication());
//                Log.e("GSON ERROR RESP>>>>", errorMessage);
//            }
//        });
//        VolleyHelper.addToRequestQueue(r);
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
