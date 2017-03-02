package com.example.graduationdesign.view;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.graduationdesign.R;
import com.example.graduationdesign.tool.MyColor;
import com.example.graduationdesign.utils.SelectCollectionDatas;


public class CollectionIconView extends FrameLayout {
    private SelectCollectionDatas mSelectCollectionDatas = null;
    private View mLayoutInflater = null;
    private ImageView mImageView;
    private int questionId;

    public CollectionIconView(Context context) {
        this(context, null);
    }

    public CollectionIconView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        //在构造函数中将Xml中定义的布局解析出来。
        mLayoutInflater = LayoutInflater.from(context).inflate(R.layout.view_collection_icon, this, true);
        mImageView = (ImageView) mLayoutInflater.findViewById(R.id.image_collect_view);
    }

    public void setQuestionId(int questionId, SQLiteDatabase ReaderDb) {
        this.questionId = questionId;
        mSelectCollectionDatas = new SelectCollectionDatas(ReaderDb);
        if (mSelectCollectionDatas.allCaseNumByQuestionId(questionId)) {
            //存在
            mImageView.setImageResource(R.drawable.ic_topic_detail_collect);
            mImageView.setEnabled(false);
        } else {
            //不存在
            mImageView.setImageResource(R.drawable.ic_topic_detail_no_collect);
        }
    }

    public void setCollection() {
        mImageView.setImageResource(R.drawable.ic_topic_detail_collect);
        mImageView.setEnabled(false);
    }
}