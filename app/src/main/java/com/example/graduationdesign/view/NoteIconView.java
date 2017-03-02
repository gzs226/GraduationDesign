package com.example.graduationdesign.view;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.graduationdesign.R;
import com.example.graduationdesign.utils.SelectCollectionDatas;
import com.example.graduationdesign.utils.SelectNoteDatas;


public class NoteIconView extends FrameLayout {
    private SelectNoteDatas mSelectCollectionDatas = null;
    private View mLayoutInflater = null;
    private ImageView mImageView;
    private int questionId;

    public NoteIconView(Context context) {
        this(context, null);
    }

    public NoteIconView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        //在构造函数中将Xml中定义的布局解析出来。
        mLayoutInflater = LayoutInflater.from(context).inflate(R.layout.view_collection_icon, this, true);
        mImageView = (ImageView) mLayoutInflater.findViewById(R.id.image_collect_view);
    }

    public void setQuestionId(int questionId, SQLiteDatabase ReaderDb) {
        this.questionId = questionId;
        mSelectCollectionDatas = new SelectNoteDatas(ReaderDb);
        if (mSelectCollectionDatas.allCaseNumByQuestionId(questionId)) {
            //存在
            mImageView.setImageResource(R.drawable.btn_edited);
            mImageView.setEnabled(false);
        } else {
            //不存在
            mImageView.setImageResource(R.drawable.btn_edit);
        }
    }

    public void setNote() {
        mImageView.setImageResource(R.drawable.btn_edited);
        mImageView.setEnabled(false);
    }
}