package com.hilinju.android.adapter;

import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.bitmap.BitmapCallBack;
import org.kymjs.kjframe.bitmap.BitmapHelper;
import org.kymjs.kjframe.utils.DensityUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hilinju.android.R;
import com.hilinju.android.base.ListBaseAdapter;
import com.hilinju.android.entity.Topic;
import com.hilinju.android.util.ImageUtils;
import com.hilinju.android.util.StringUtils;
import com.hilinju.android.widget.AvatarView;
import com.hilinju.android.widget.TweetTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by qiuxj on 2015/5/27.
 */
public class TopicAdapter extends ListBaseAdapter {
    public TopicAdapter() {

    }
    private int rectSize;

    private void initImageSize(Context cxt) {
        if (cxt != null && rectSize == 0) {
            rectSize = (int) cxt.getResources().getDimension(R.dimen.space_100);
        } else {
            rectSize = 300;
        }
    }

    @Override
    protected View getRealView(int position, View convertView,
                               final ViewGroup parent) {
        ViewHolder vh = null;
        initImageSize(parent.getContext());
        if (convertView == null || convertView.getTag() == null) {
            convertView = getLayoutInflater(parent.getContext()).inflate(
                    R.layout.list_cell_topic, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        final Topic item = (Topic) mDatas.get(position);


        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_name)
        TextView name;
        @InjectView(R.id.tv_from)
        TextView from;
        @InjectView(R.id.tv_time)
        TextView time;
        @InjectView(R.id.tv_action)
        TextView action;
        @InjectView(R.id.tv_action_name)
        TextView actionName;
        @InjectView(R.id.tv_comment_count)
        TextView commentCount;
        @InjectView(R.id.tv_body)
        TweetTextView body;
        @InjectView(R.id.tv_reply)
        TweetTextView reply;
        @InjectView(R.id.iv_pic)
        ImageView pic;
        @InjectView(R.id.ly_reply)
        View lyReply;
        @InjectView(R.id.iv_avatar)
        AvatarView avatar;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}