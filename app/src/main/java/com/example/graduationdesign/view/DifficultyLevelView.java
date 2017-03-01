package com.example.graduationdesign.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.graduationdesign.R;

public class DifficultyLevelView extends LinearLayout {

    private View mLayoutInflater = null;

    private ImageView[] imageviewDifficultyLevel;

    public DifficultyLevelView(Context context) {
        this(context, null);
    }

    public DifficultyLevelView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        //在构造函数中将Xml中定义的布局解析出来。
        mLayoutInflater = LayoutInflater.from(context).inflate(R.layout.view_difficulty_level, this, true);
        InitBindView();
    }

    private void InitBindView() {

        imageviewDifficultyLevel = new ImageView[]{
                (ImageView) mLayoutInflater.findViewById(R.id.imageview_difficulty_level_1),
                (ImageView) mLayoutInflater.findViewById(R.id.imageview_difficulty_level_2),
                (ImageView) mLayoutInflater.findViewById(R.id.imageview_difficulty_level_3),
                (ImageView) mLayoutInflater.findViewById(R.id.imageview_difficulty_level_4),
                (ImageView) mLayoutInflater.findViewById(R.id.imageview_difficulty_level_5)};
    }

    public void setDifficultyLevel(int DifficultyLevel) {
        recoveryView();
        difficultyLevel(DifficultyLevel);
    }

    private void recoveryView() {
        for (int i = 0; i < 5; i++) {
            imageviewDifficultyLevel[i].setImageResource(R.drawable.raterstaruntruetwo);
        }
    }

    private void difficultyLevel(int DifficultyLevel) {
        for (int i = 0; i < DifficultyLevel; i++) {
            imageviewDifficultyLevel[i].setImageResource(R.drawable.raterstartrue);
        }
    }
}