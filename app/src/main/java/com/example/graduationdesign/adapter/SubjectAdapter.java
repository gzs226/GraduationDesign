package com.example.graduationdesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.graduationdesign.R;
import com.example.graduationdesign.tool.Model.SubjectSort;
import com.example.graduationdesign.tool.Model.Chapter;

import java.util.ArrayList;
import java.util.List;

/***
 * 数据源
 *
 * @author Administrator
 */
public class SubjectAdapter extends BaseExpandableListAdapter {
    private Context context;
    private LayoutInflater inflater;

    private ArrayList<SubjectSort> subjectSortList;
    private ArrayList<List<Chapter>> childList;

    public SubjectAdapter(Context context, ArrayList<SubjectSort> subjectSortList, ArrayList<List<Chapter>> childList) {
        this.subjectSortList = subjectSortList;
        this.childList = childList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    // 返回父列表个数
    @Override
    public int getGroupCount() {
        return subjectSortList.size();
    }

    // 返回子列表个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        return subjectSortList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {

        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (convertView == null) {
            groupHolder = new GroupHolder();
            convertView = inflater.inflate(R.layout.item_subject_group, null);
            groupHolder.textView = (TextView) convertView.findViewById(R.id.textview_subject_group_title);
            groupHolder.imageView = (ImageView) convertView.findViewById(R.id.imageview_subject_group_arrow);
            groupHolder.Icon = (ImageView) convertView.findViewById(R.id.imageview_subject_group_icon);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }

        switch (groupPosition % 5) {
            case 0:
                groupHolder.Icon.setImageResource(R.drawable.shizheng);
                break;
            case 1:
                groupHolder.Icon.setImageResource(R.drawable.mayuan);
                break;
            case 2:
                groupHolder.Icon.setImageResource(R.drawable.shigang);
                break;
            case 3:
                groupHolder.Icon.setImageResource(R.drawable.mayuan);
                break;
            case 4:
                groupHolder.Icon.setImageResource(R.drawable.shizheng);
                break;
        }

        groupHolder.textView.setText(((SubjectSort) getGroup(groupPosition)).getTitle());
        if (isExpanded)// ture is Expanded or false is not isExpanded
        {
            groupHolder.imageView.setImageResource(R.drawable.expanded);
        } else {
            groupHolder.imageView.setImageResource(R.drawable.collapse);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (convertView == null) {
            childHolder = new ChildHolder();
            convertView = inflater.inflate(R.layout.item_subject_child, null);

            childHolder.textName = (TextView) convertView.findViewById(R.id.textview_subject_child_title);
            childHolder.imageView = (ImageView) convertView.findViewById(R.id.imageview_subject_child_arrow);

            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        childHolder.textName.setText(((Chapter) getChild(groupPosition, childPosition)).getSort());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    static class GroupHolder {
        ImageView Icon;
        TextView textView;
        ImageView imageView;
    }

    static class ChildHolder {
        TextView textName;
        ImageView imageView;
    }


}