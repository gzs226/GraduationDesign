package com.example.graduationdesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.graduationdesign.R;
import com.example.graduationdesign.utils.model.CollectionDatas;

import java.util.List;

/**
 * @author SunnyCoffee
 * @version 1.0
 * @date 2014-2-2
 * @desc 适配器
 */
public class CollectionBankAdapter extends BaseAdapter {

    private ViewHolder holder;
    private List<CollectionDatas> ListString;
    private Context context;

    public CollectionBankAdapter(Context context,List<CollectionDatas> ListResultdata) {
        this.ListString = ListResultdata;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ListString.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_collection_bank, null);
            holder.text = (TextView) convertView.findViewById(R.id.item_question_bank);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(ListString.get(position).getQuestion() + "");
        return convertView;
    }

    private static class ViewHolder {
        TextView text;
    }

}
