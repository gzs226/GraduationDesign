package com.example.graduationdesign.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.graduationdesign.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MycircleActivity extends BaseActivity {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.button_amended)
    TextView buttonAmended;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    @BindView(R.id.lin_mywrite_posts)
    LinearLayout linMywritePosts;
    @BindView(R.id.lin_myreply_posts)
    LinearLayout linMyreplyPosts;
    @BindView(R.id.lin_reply_myposts)
    LinearLayout linReplyMyposts;
    @BindView(R.id.lin_mycollect_posts)
    LinearLayout linMycollectPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycircle);
        ButterKnife.bind(this);
    }

    private void InitView() {
        headerCenterText.setText("我的圈子");
    }

    @OnClick({R.id.linear_back, R.id.lin_mywrite_posts, R.id.lin_myreply_posts, R.id.lin_reply_myposts, R.id.lin_mycollect_posts})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.lin_mywrite_posts:
                break;
            case R.id.lin_myreply_posts:
                break;
            case R.id.lin_reply_myposts:
                break;
            case R.id.lin_mycollect_posts:
                break;
        }
    }
}
