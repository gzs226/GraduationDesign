package com.example.graduationdesign.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduationdesign.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeekbackMessageActivity extends BaseActivity {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.edittext_feekback_message)
    EditText edittextFeekbackMessage;
    @BindView(R.id.button_handin_userview)
    Button buttonHandinUserview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feekback_message);
        ButterKnife.bind(this);
        InitView();
    }

    private void InitView(){
        headerCenterText.setText("意见反馈");
    }
    @OnClick({R.id.linear_back, R.id.button_handin_userview})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.button_handin_userview:
                handinUserView();
                break;
        }
    }
    private void handinUserView(){
        Toast.makeText(FeekbackMessageActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
        finish();
    }

}
