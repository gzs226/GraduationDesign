package com.example.graduationdesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.graduationdesign.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    @BindView(R.id.lin_function_introduction)
    LinearLayout linFunctionIntroduction;
    @BindView(R.id.lin_app_evaluate)
    LinearLayout linAppEvaluate;
    @BindView(R.id.lin_feedback_message)
    LinearLayout linFeedbackMessage;
    @BindView(R.id.lin_app_update)
    LinearLayout linAppUpdate;
    @BindView(R.id.text_app_version)
    TextView textAppVersion;
    @BindView(R.id.lin_update_answer)
    LinearLayout linUpdateAnswer;
    @BindView(R.id.text_logout)
    TextView textLogout;

    private String AppVersion = "1.0.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        InitView();
    }

    private void InitView() {
        headerCenterText.setText("设置");
        getVersion();
        textAppVersion.setText("版本号 " + AppVersion + "");
    }

    private void getVersion() {
        AppVersion = "1.0.0";
    }

    @OnClick({R.id.linear_back, R.id.lin_function_introduction, R.id.lin_app_evaluate,
              R.id.lin_feedback_message, R.id.lin_app_update, R.id.lin_update_answer,
              R.id.text_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.lin_function_introduction:
                toAppIntroduction();
                break;
            case R.id.lin_app_evaluate:
                toAppEvaluate();
                break;
            case R.id.lin_feedback_message:
                toFeekbackMessage();
                break;
            case R.id.lin_app_update:
                checkAppVersion();
                break;
            case R.id.lin_update_answer:
                UpdateAnswer();
                break;
            case R.id.text_logout:
                LogOut();
                break;
        }
    }

    private void toAppIntroduction() {
        Intent intent = new Intent(SettingActivity.this, AppIntroductionActivity.class);
        startActivity(intent);
    }

    private void toAppEvaluate() {
        Intent intent = new Intent(SettingActivity.this, AppEvaluateActivity.class);
        startActivity(intent);
    }

    private void toFeekbackMessage() {
        Intent intent = new Intent(SettingActivity.this, FeekbackMessageActivity.class);
        startActivity(intent);
    }

    private void checkAppVersion() {

    }

    private void UpdateAnswer() {
        Intent intent = new Intent(SettingActivity.this, UpdateQuestionActivity.class);
        startActivity(intent);
    }

    private void LogOut() {

    }
}
