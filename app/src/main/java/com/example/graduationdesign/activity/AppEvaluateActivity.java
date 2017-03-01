package com.example.graduationdesign.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduationdesign.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppEvaluateActivity extends BaseActivity {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.imageview_app_level_1)
    ImageView imageviewAppLevel1;
    @BindView(R.id.imageview_app_level_2)
    ImageView imageviewAppLevel2;
    @BindView(R.id.imageview_app_level_3)
    ImageView imageviewAppLevel3;
    @BindView(R.id.imageview_app_level_4)
    ImageView imageviewAppLevel4;
    @BindView(R.id.imageview_app_level_5)
    ImageView imageviewAppLevel5;
    @BindView(R.id.edittext_app_evaluate)
    EditText edittextAppEvaluate;
    @BindView(R.id.button_app_evaluate)
    Button buttonAppEvaluate;

    private List<ImageView> imageViewStartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_evaluate);
        ButterKnife.bind(this);
        InitView();
    }

    private void InitView() {
        headerCenterText.setText("评价");
        imageViewStartList = new ArrayList<ImageView>();
        imageViewStartList.add(imageviewAppLevel1);
        imageViewStartList.add(imageviewAppLevel2);
        imageViewStartList.add(imageviewAppLevel3);
        imageViewStartList.add(imageviewAppLevel4);
        imageViewStartList.add(imageviewAppLevel5);
    }


    @OnClick({R.id.linear_back, R.id.imageview_app_level_1, R.id.imageview_app_level_2, R.id.imageview_app_level_3, R.id.imageview_app_level_4, R.id.imageview_app_level_5, R.id.button_app_evaluate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.imageview_app_level_1:
                setDifficultyLevel(1);
                break;
            case R.id.imageview_app_level_2:
                setDifficultyLevel(2);
                break;
            case R.id.imageview_app_level_3:
                setDifficultyLevel(3);
                break;
            case R.id.imageview_app_level_4:
                setDifficultyLevel(4);
                break;
            case R.id.imageview_app_level_5:
                setDifficultyLevel(5);
                break;
            case R.id.button_app_evaluate:
                appEvaluate();
                break;
        }
    }

    private void appEvaluate() {
        Toast.makeText(AppEvaluateActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void setDifficultyLevel(int DifficultyLevel) {
        recoveryView();
        difficultyLevel(DifficultyLevel);
    }

    private void recoveryView() {
        for (int i = 0; i < 5; i++) {
            imageViewStartList.get(i).setImageResource(R.drawable.icon_star_gary);
        }
    }

    private void difficultyLevel(int DifficultyLevel) {
        for (int i = 0; i < DifficultyLevel; i++) {
            imageViewStartList.get(i).setImageResource(R.drawable.icon_star_yellow);
        }
    }
}
