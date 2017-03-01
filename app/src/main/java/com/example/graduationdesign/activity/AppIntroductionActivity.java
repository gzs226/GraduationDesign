package com.example.graduationdesign.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.graduationdesign.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppIntroductionActivity extends BaseActivity {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    @BindView(R.id.text_app_introduction)
    TextView textAppIntroduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_introduction);
        ButterKnife.bind(this);
        InitView();
    }

    private void InitView() {
        headerCenterText.setText("功能介绍");
    }

    @OnClick(R.id.linear_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                finish();
                break;
        }
    }
}
