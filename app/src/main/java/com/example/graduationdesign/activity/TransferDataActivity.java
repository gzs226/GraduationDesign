package com.example.graduationdesign.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.graduationdesign.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransferDataActivity extends BaseActivity {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_data);
        ButterKnife.bind(this);
        InitView();
    }

    private void InitView() {
        headerCenterText.setText("数据转移");
    }

    @OnClick({R.id.linear_back, R.id.text_update_record, R.id.text_download_record})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.text_update_record:
                break;
            case R.id.text_download_record:
                break;
        }
    }
}
