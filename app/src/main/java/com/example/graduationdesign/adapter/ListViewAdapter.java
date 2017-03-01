package com.example.graduationdesign.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.graduationdesign.R;
import com.example.graduationdesign.activity.UserMessageActivity;
import com.example.graduationdesign.tool.CircleImageView;
import com.example.graduationdesign.tool.ImageLoaderByAsyncTask;
import com.example.graduationdesign.tool.ListViewImageLoaderByAsyncTask;
import com.example.weblibrary.URL;
import com.example.weblibrary.model.PostCommentData;
import com.example.weblibrary.model.PostCommentDataResult;

import java.util.List;

/**
 * @author SunnyCoffee
 * @version 1.0
 * @date 2014-2-2
 * @desc 适配器
 */
public class ListViewAdapter extends BaseAdapter {

    private ViewHolder holder;
    private List<PostCommentData> list;
    private Context context;
    private int QuestionId;

    private ListViewImageLoaderByAsyncTask imageLoaderByAsyncTask;
    public ListViewAdapter(ListView listView, Context context, List<PostCommentData> list, int QuestionId) {
        this.list = list;
        this.context = context;
        imageLoaderByAsyncTask = new ListViewImageLoaderByAsyncTask(listView);
        this.QuestionId = QuestionId;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_post_comment, null);
            holder.username = (TextView) convertView.findViewById(R.id.textview_post_name);
            holder.userschool = (TextView) convertView.findViewById(R.id.textview_post_userschool);
            holder.publictime = (TextView) convertView.findViewById(R.id.textview_post_time);
            holder.praiseNumber = (TextView) convertView.findViewById(R.id.textview_comment_praise_number);
            holder.toUserName = (TextView) convertView.findViewById(R.id.textview_comment_tousername);
            holder.Topic = (TextView) convertView.findViewById(R.id.textview_comment_content);
            holder.mContent = (TextView) convertView.findViewById(R.id.textview_post_content);
            holder.RelaComment = (RelativeLayout) convertView.findViewById(R.id.rela_reply_content);
            holder.UserIcon = (CircleImageView) convertView.findViewById(R.id.imageview_post_usericon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (list.get(position).getTo_uid() == list.get(position).getTopic_id()) {
            holder.RelaComment.setVisibility(View.GONE);
        } else {
            holder.RelaComment.setVisibility(View.VISIBLE);
        }
        holder.username.setText(list.get(position).getFrom_uname() + "");
        holder.userschool.setText(list.get(position).getFrom_uid() + "");
        holder.publictime.setText(list.get(position).getComment_time() + "");
        holder.toUserName.setText(list.get(position).getTo_uname() + "");
        holder.Topic.setText(list.get(position).getTopic_type() + "");
        holder.praiseNumber.setText(list.get(position).getPraise_number() + "");
        holder.mContent.setText(list.get(position).getContent() + "");

        holder.UserIcon.setImageResource(R.drawable.ic_launcher);
        String mTag = (list.get(position).getId()+"");
        holder.UserIcon.setTag(mTag);
        String IconUrl =
                URL.downloadimage + "userid=" + list.get(position).getFrom_uid();
        imageLoaderByAsyncTask.loadImages(IconUrl,holder.UserIcon,mTag);
        imageLoaderByAsyncTask.showImageByAsyncTask(holder.UserIcon, IconUrl);

        return convertView;
    }


    private static class ViewHolder {
        CircleImageView UserIcon;
        TextView username;
        TextView userschool;
        TextView publictime;
        TextView praiseNumber;
        TextView toUserName;
        TextView Topic;
        TextView mContent;
        RelativeLayout RelaComment;
    }

}
