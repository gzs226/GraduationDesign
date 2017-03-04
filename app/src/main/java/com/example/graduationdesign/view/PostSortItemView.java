package com.example.graduationdesign.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduationdesign.R;
import com.example.graduationdesign.tool.MyColor;


public class PostSortItemView extends LinearLayout implements View.OnClickListener {
    private View mLayoutInflater = null;
    private TextView[] ArrTextView;

    private int sortModel = -1;
    private SortItemOnClickListener mSortItemOnClickListener;
    private Context context;

    public PostSortItemView(Context context) {
        this(context, null);
        this.context = context;
    }

    public PostSortItemView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        //在构造函数中将Xml中定义的布局解析出来。
        mLayoutInflater = LayoutInflater.from(context).inflate(R.layout.view_post_sort, this, true);
        InitBindView();
    }

    private void InitBindView() {

        TextView textSortAll = (TextView) mLayoutInflater.findViewById(R.id.text_sortitem_all);
        TextView textSortSignUp = (TextView) mLayoutInflater.findViewById(R.id.text_sortitem_signup);
        TextView textSortExam = (TextView) mLayoutInflater.findViewById(R.id.text_sortitem_exam);
        TextView textSortReview = (TextView) mLayoutInflater.findViewById(R.id.text_sortitem_review);
        TextView textSortHelp = (TextView) mLayoutInflater.findViewById(R.id.text_sortitem_help);

        ArrTextView = new TextView[]{textSortAll, textSortSignUp, textSortExam, textSortReview, textSortHelp};


        for (int i = 0; i < 5; i++) {
            ArrTextView[i].setOnClickListener(this);
        }

        InitItemView(R.id.text_sortitem_all);
    }

    private void InitItemView(int model) {
        for (int i = 0; i < 5; i++) {
            if (ArrTextView[i].getId() == model) {//text_chapter_detail
                ArrTextView[i].setTextColor(ContextCompat.getColor(context,R.color.text_chapter_detail));
            } else {
                ArrTextView[i].setTextColor(ContextCompat.getColor(context,R.color.text_chapter));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_sortitem_all:
                setItemView(R.id.text_sortitem_all);
                break;
            case R.id.text_sortitem_signup:
                setItemView(R.id.text_sortitem_signup);
                break;
            case R.id.text_sortitem_exam:
                setItemView(R.id.text_sortitem_exam);
                break;
            case R.id.text_sortitem_review:
                setItemView(R.id.text_sortitem_review);
                break;
            case R.id.text_sortitem_help:
                setItemView(R.id.text_sortitem_help);
                break;
        }

    }

    public void setSortItemOnClickListener(SortItemOnClickListener mSortItemOnClickListener) {
        this.mSortItemOnClickListener = mSortItemOnClickListener;
    }

    private void setItemView(int model) {
        for (int i = 0; i < 5; i++) {
            if (ArrTextView[i].getId() == model) {
                ArrTextView[i].setTextColor(ContextCompat.getColor(context,R.color.text_chapter_detail));
            } else {
                ArrTextView[i].setTextColor(ContextCompat.getColor(context,R.color.text_chapter));
            }
        }
        mSortItemOnClickListener.SortItemChanges(model);
    }


    public interface SortItemOnClickListener {
        void SortItemChanges(int modelId);
    }
}