package com.example.graduationdesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.graduationdesign.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentQuestionActivity extends BaseActivity {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    @BindView(R.id.edittext_comment_question)
    EditText edittextCommentQuestion;
    @BindView(R.id.button_handin_comment_question)
    Button buttonHandinCommentQuestion;

    private String CommentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_question);
        ButterKnife.bind(this);
        InitView();
    }

    private void InitView() {
        headerCenterText.setText("评论");
    }

    private void backToQuestionDetail() {
        Intent intent = new Intent(CommentQuestionActivity.this, UserMessageActivity.class);
        startActivity(intent);
        finish();
    }

    private void handInComment() {
        CommentText = edittextCommentQuestion.getText().toString() + "";
//        if (success) {
//            backToQuestionDetail()
//        }
    }

    @OnClick({R.id.linear_back, R.id.button_handin_comment_question})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.button_handin_comment_question:
                handInComment();
                break;
        }
    }
}
