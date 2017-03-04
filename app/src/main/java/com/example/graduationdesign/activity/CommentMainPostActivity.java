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

public class CommentMainPostActivity extends BaseActivity {

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
    private PostCommentTransmit mPostCommentTransmit;
    private PostCommentData mWritePostComment;
    private int UserId;
    private String UserName;
    private int model;
    private final int QUESTION = 0;
    private final int POST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_main_post);
        ButterKnife.bind(this);
        getIntentData();
        InitView();
    }


    private void InitView() {
        headerCenterText.setText("回复帖子");
        textReplyPostcomment.setText("回复" + mPostCommentTransmit.getFrom_uname());
        textWriteCommentQuestion.setVisibility(View.GONE);
        mWritePostComment = new PostCommentData();
        UserId = Integer.valueOf(ConfigUserMessagePrefs.getValue(CommentMainPostActivity.this, Contents.USER_ID, "1"));
        UserName = ConfigUserMessagePrefs.getValue(CommentMainPostActivity.this, Contents.USER_NAME, "");
    }

    private void getIntentData() {
        Intent intent = this.getIntent();
        mPostCommentTransmit = (PostCommentTransmit) intent.getSerializableExtra("commentdata");
        model = intent.getIntExtra("model", -1);
    }

    @OnClick({R.id.linear_back, R.id.text_reply_postcomment, R.id.button_reply_postcomment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                break;
            case R.id.text_reply_postcomment:
                break;
            case R.id.button_reply_postcomment:
                postCommet();
                break;
        }
    }

    private void postCommet() {
        buttonReplyPostcomment.setEnabled(false);
        buttonReplyPostcomment.setText("正在提交..");
        mWritePostComment.setTopic_id(mPostCommentTransmit.getTopic_id());
        mWritePostComment.setTopic_type(mPostCommentTransmit.getContent());
        mWritePostComment.setContent(editReplyPostcomment.getText() + "");
        mWritePostComment.setFrom_uid(UserId);
        mWritePostComment.setFrom_uname(UserName);
        mWritePostComment.setTo_uid(mPostCommentTransmit.getFrom_uid());
        mWritePostComment.setTo_uname(mPostCommentTransmit.getFrom_uname());
        mWritePostComment.setComment_time(mPostCommentTransmit.getComment_time());
        mWritePostComment.setComment_number(0);
        mWritePostComment.setPraise_number(0);

        if (model == QUESTION) {

            new CommentWriteNew().WriteQuestion(mWritePostComment, new CommentWriteNew.IWritePost() {
                @Override
                public void Success(String successMessage) {
                    Toast.makeText(CommentMainPostActivity.this, "success", Toast.LENGTH_SHORT).show();
                    //buttonReplyPostcomment.setText("完成");
                    finish();
                }

                @Override
                public void Faild(String failMessage) {
                    Toast.makeText(CommentMainPostActivity.this, "faild", Toast.LENGTH_SHORT).show();
                    buttonReplyPostcomment.setEnabled(true);
                    buttonReplyPostcomment.setText("回复");
                }

                @Override
                public void Error(VolleyError volleyError) {
                    Toast.makeText(CommentMainPostActivity.this, "faild", Toast.LENGTH_SHORT).show();
                    buttonReplyPostcomment.setEnabled(true);
                    buttonReplyPostcomment.setText("回复");
                }
            });
        } else if (model == POST) {

            new CommentWriteNew().WritePost(mWritePostComment, new CommentWriteNew.IWritePost() {
                @Override
                public void Success(String successMessage) {
                    Toast.makeText(CommentMainPostActivity.this, "success", Toast.LENGTH_SHORT).show();
                    //buttonReplyPostcomment.setText("完成");
                    finish();
                }

                @Override
                public void Faild(String failMessage) {
                    Toast.makeText(CommentMainPostActivity.this, "faild", Toast.LENGTH_SHORT).show();
                    buttonReplyPostcomment.setEnabled(true);
                    buttonReplyPostcomment.setText("回复");
                }

                @Override
                public void Error(VolleyError volleyError) {
                    Toast.makeText(CommentMainPostActivity.this, "faild", Toast.LENGTH_SHORT).show();
                    buttonReplyPostcomment.setEnabled(true);
                    buttonReplyPostcomment.setText("回复");
                }
            });
        }

    }
}
