package com.example.graduationdesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.graduationdesign.R;

import java.util.List;

/**
 * @author SunnyCoffee
 * @version 1.0
 * @date 2014-2-2
 * @desc 适配器
 */
public class SearchPostAdapter extends BaseAdapter {

    private ViewHolder holder;
    private List<String> list;
    private Context context;

    public SearchPostAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_search_post, null);
            holder.text = (TextView) convertView.findViewById(R.id.text_search_post_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //holder.text.setText(list.get(position));
        return convertView;
    }

    private static class ViewHolder {
        TextView text;
    }

}
