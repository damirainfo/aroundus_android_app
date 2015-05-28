package com.hilinju.android.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.hilinju.android.R;

/**
 * Created by qiuxj on 2015/5/28.
 */
public class QuickOptionDialog extends Dialog implements
        android.view.View.OnClickListener {

    private ImageView mClose;

    public interface OnQuickOptionFormClick {
        void onQuickOptionClick(int id);
    }

    private OnQuickOptionFormClick mListener;

    private QuickOptionDialog(Context context, boolean flag,
                              OnCancelListener listener) {
        super(context, flag, listener);
    }

    private QuickOptionDialog(Context context, int defStyle) {
        super(context, defStyle);
        View contentView = getLayoutInflater().inflate(
                R.layout.dialog_quick_option, null);
        contentView.findViewById(R.id.ly_quick_option_text).setOnClickListener(
                this);
        contentView.findViewById(R.id.ly_quick_option_album)
                .setOnClickListener(this);
        contentView.findViewById(R.id.ly_quick_option_photo)
                .setOnClickListener(this);
        contentView.findViewById(R.id.ly_quick_option_voice)
                .setOnClickListener(this);
        mClose = (ImageView) contentView.findViewById(R.id.iv_close);

        Animation operatingAnim = AnimationUtils.loadAnimation(getContext(),
                R.anim.quick_option_close);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);

        mClose.startAnimation(operatingAnim);

        mClose.setOnClickListener(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                QuickOptionDialog.this.dismiss();
                return true;
            }
        });
        super.setContentView(contentView);

    }

    public QuickOptionDialog(Context context) {
        this(context, R.style.quick_option_dialog);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setGravity(Gravity.BOTTOM);

        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth();
        getWindow().setAttributes(p);
    }

    public void setOnQuickOptionFormClickListener(OnQuickOptionFormClick lis) {
        mListener = lis;
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.ly_quick_option_text:
                onClickTweetPub(R.id.ly_quick_option_text);
                break;
            case R.id.ly_quick_option_album:
                onClickTweetPub(R.id.ly_quick_option_album);
                break;
            case R.id.ly_quick_option_photo:
                onClickTweetPub(R.id.ly_quick_option_photo);
                break;
            case R.id.ly_quick_option_voice:
                //UIHelper.showSimpleBack(getContext(), SimpleBackPage.RECORD);
                break;
            default:
                break;
        }
        if (mListener != null) {
            mListener.onQuickOptionClick(id);
        }
        dismiss();
    }

    private void onClickTweetPub(int id) {
        Bundle bundle = new Bundle();
        int type = -1;
        switch (id) {
            case R.id.ly_quick_option_album:
                //type = TweetPubFragment.ACTION_TYPE_ALBUM;
                break;
            case R.id.ly_quick_option_photo:
                //type = TweetPubFragment.ACTION_TYPE_PHOTO;
                break;
            default:
                break;
        }
        //bundle.putInt(TweetPubFragment.ACTION_TYPE, type);
        //UIHelper.showTweetActivity(getContext(), SimpleBackPage.TWEET_PUB,
        //        bundle);
    }
}