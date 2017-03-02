package com.example.graduationdesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.graduationdesign.R;
import com.example.graduationdesign.utils.model.CollectionDatas;
import com.example.graduationdesign.utils.model.NoteDatas;

import java.util.List;

/**
 * @author SunnyCoffee
 * @version 1.0
 * @date 2014-2-2
 * @desc 适配器
 */
public class NoteBankAdapter extends BaseAdapter {

    private ViewHolder holder;
    private List<NoteDatas> ListString;
    private Context context;

    public NoteBankAdapter(Context context, List<NoteDatas> ListResultdata) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_note_bank, null);
            holder.note = (TextView) convertView.findViewById(R.id.item_mynote_bank);
            holder.questin = (TextView) convertView.findViewById(R.id.item_note_bank);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.questin.setText(ListString.get(position).getQuestion() + "");
        holder.note.setText(ListString.get(position).getNote_text() + "");
        return convertView;
    }

    private static class ViewHolder {
        TextView note;
        TextView questin;
    }

}
