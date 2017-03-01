package com.example.graduationdesign.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.graduationdesign.R;
import com.example.graduationdesign.adapter.SystemMessageAdapter;
import com.example.graduationdesign.view.PopWindowsSystemMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SystemMessageActivity extends BaseActivity {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.list_system_message)
    ListView listSystemMessage;
    private List<String> listdata;
    private SystemMessageAdapter mSystemMessageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_message);
        ButterKnife.bind(this);
        InitData();
        InitView();
    }

    private void InitData() {
        listdata = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            listdata.add(i + "");
        }
    }

    private void InitView() {
        headerCenterText.setText("我的消息");
        mSystemMessageAdapter = new SystemMessageAdapter(this, listdata);
        listSystemMessage.setAdapter(mSystemMessageAdapter);
        listSystemMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PopWindowsSystemMessage mPopWindowsSystemMessage = new PopWindowsSystemMessage(SystemMessageActivity.this,"","");
                mPopWindowsSystemMessage.showPopupWindow(headerCenterText);
            }
        });
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
