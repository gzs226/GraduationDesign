package com.example.graduationdesign.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.graduationdesign.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyexamCommunicationActivity extends BaseActivity {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.button_amended)
    TextView buttonAmended;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    @BindView(R.id.lin_my_communication)
    LinearLayout linMyCommunication;
    @BindView(R.id.lin_communication_me)
    LinearLayout linCommunicationMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myexam_communication);
        ButterKnife.bind(this);
        InitView();
    }

    private void InitView() {
        headerCenterText.setText("考题交流");
    }

    @OnClick({R.id.linear_back, R.id.lin_my_communication, R.id.lin_communication_me})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.lin_my_communication:
                break;
            case R.id.lin_communication_me:
                break;
        }
    }
}
