package com.example.graduationdesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.graduationdesign.R;
import com.example.graduationdesign.utils.ConfigUserMessagePrefs;
import com.example.graduationdesign.utils.Contents;
import com.example.graduationdesign.utils.model.PostCommentTransmit;
import com.example.weblibrary.model.PostCommentData;
import com.example.weblibrary.user.CommentWriteNew;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionMainPostActivity extends BaseActivity {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.edit_reply_postcomment)
    EditText editReplyPostcomment;
    @BindView(R.id.text_reply_postcomment)
    TextView textReplyPostcomment;
    @BindView(R.id.button_reply_postcomment)
    Button buttonReplyPostcomment;
    @BindView(R.id.text_write_comment_question)
    TextView textWriteCommentQuestion;
    private String QTitle;
    private String QContext;
    private final int QUESTION_COMMENT_BACK_QUESTION_CODE = 107;
    private String mCommentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_main_post);
        ButterKnife.bind(this);
        getIntentData();
        InitView();
    }

    private void InitView() {
        headerCenterText.setText("评论");
        textReplyPostcomment.setText("" + QTitle);
        textWriteCommentQuestion.setText("" + QContext);
    }

    private void getIntentData() {
        Intent intent = this.getIntent();
        QTitle = intent.getStringExtra("title");
        QContext = intent.getStringExtra("context");
    }

    private boolean ifNull() {
        mCommentText = editReplyPostcomment.getText() + "";
        if (mCommentText.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private void ResultUserMessage() {
        Intent intent = new Intent(QuestionMainPostActivity.this, QuestionDetailedActivity.class);
        intent.putExtra("commenttext", mCommentText);
        QuestionMainPostActivity.this.setResult(QUESTION_COMMENT_BACK_QUESTION_CODE, intent);
        QuestionMainPostActivity.this.finish();
    }


    @OnClick({R.id.linear_back, R.id.text_reply_postcomment, R.id.button_reply_postcomment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.text_reply_postcomment:
                break;
            case R.id.button_reply_postcomment:
                if (!ifNull()) {
                    ResultUserMessage();
                }
                break;
        }
    }

}
