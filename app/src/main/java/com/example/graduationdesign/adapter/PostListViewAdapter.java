package com.example.graduationdesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.graduationdesign.R;
import com.example.graduationdesign.tool.CircleImageView;
import com.example.graduationdesign.tool.ListViewImageLoaderByAsyncTask;
import com.example.graduationdesign.tool.ImageLoaderByAsyncTask;
import com.example.weblibrary.URL;
import com.example.weblibrary.model.Postresuldata;

import java.util.List;

/**
 * @author SunnyCoffee
 * @version 1.0
 * @date 2014-2-2
 * @desc 适配器
 */
public class PostListViewAdapter extends BaseAdapter {

    private ViewHolder holder;
    private List<Postresuldata> listPostresuldata;
    private Context context;
    private ListViewImageLoaderByAsyncTask imageLoaderByAsyncTask;

    public PostListViewAdapter(ListView listView, Context context, List<Postresuldata> listPostresuldata) {
        this.listPostresuldata = listPostresuldata;
        this.context = context;
        imageLoaderByAsyncTask = new ListViewImageLoaderByAsyncTask(listView);
    }


    @Override
    public int getCount() {
        return listPostresuldata.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_post_detail, null);
            holder.commentNumber = (TextView) convertView.findViewById(R.id.textview_praise_munber);
            holder.PostTitle = (TextView) convertView.findViewById(R.id.text_item_post_title);
            holder.WritePostUser = (TextView) convertView.findViewById(R.id.textview_post_username);
            holder.WritePostTime = (TextView) convertView.findViewById(R.id.textview_post_writetime);
            holder.WritePostUserIcon = (CircleImageView) convertView.findViewById(R.id.imageview_postdetail_usericon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.commentNumber.setText(listPostresuldata.get(position).getCommentnumber() + "");
        holder.PostTitle.setText(listPostresuldata.get(position).getPosttitle() + "");
        holder.WritePostUser.setText(listPostresuldata.get(position).getUsername() + "");
        holder.WritePostTime.setText(listPostresuldata.get(position).getPublictime() + "");

        holder.WritePostUserIcon.setImageResource(R.drawable.ic_launcher);
        String mTag = (listPostresuldata.get(position).getPostid()+"");
        holder.WritePostUserIcon.setTag(mTag);
        String IconUrl =
                URL.downloadimage + "userid=" + listPostresuldata.get(position).getUserid();
        imageLoaderByAsyncTask.loadImages(IconUrl,holder.WritePostUserIcon,mTag);
        imageLoaderByAsyncTask.showImageByAsyncTask(holder.WritePostUserIcon, IconUrl);
        //setUserIcon(listPostresuldata.get(position).getUserid(), holder.WritePostUserIcon);
        //Log.e(" ", "getView: " + listPostresuldata.get(position).getUserid());
        return convertView;
    }


    private static class ViewHolder {
        TextView commentNumber;
        TextView PostTitle;
        TextView WritePostUser;
        TextView WritePostTime;
        CircleImageView WritePostUserIcon;
    }

}
