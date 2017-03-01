package com.example.graduationdesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.graduationdesign.R;
import com.example.graduationdesign.adapter.ListViewAdapter;
import com.example.graduationdesign.utils.ConfigUserMessagePrefs;
import com.example.graduationdesign.utils.Contents;
import com.example.graduationdesign.utils.model.PostCommentTransmit;
import com.example.graduationdesign.utils.model.PostTransmit;
import com.example.graduationdesign.utils.model.QuestionTransmit;
import com.example.graduationdesign.view.QuestionCommentListView;
import com.example.graduationdesign.view.PostDetailListView;
import com.example.graduationdesign.view.QuestionCommentListView;
import com.example.weblibrary.model.PostCommentData;
import com.example.weblibrary.model.PostCommentDataResult;
import com.example.weblibrary.user.CommentSelectByQuestionId;
import com.example.weblibrary.user.CommentSelectByTopicId;
import com.example.weblibrary.user.QuestionWriteNew;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author SunnyCoffee
 * @version 1.0
 * @date 2014-1-28
 * @desc listview下拉刷新，上拉自动加载更多。 http：//blog.csdn.com/limb99
 */

public class QuestionCommentActivity extends BaseActivity implements QuestionCommentListView.OnRefreshListener, QuestionCommentListView.OnLoadListener, QuestionCommentListView.ViewVisible {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.lstvcommentq)
    QuestionCommentListView lstvcommentq;
    @BindView(R.id.lin_hot_comment)
    LinearLayout linHotComment;
    @BindView(R.id.lin_new_comment)
    LinearLayout linNewComment;
    @BindView(R.id.iamge_comment_detail_collect)
    ImageView iamgeCommentDetailCollect;
    private ListViewAdapter adapter;
    private int LVmodel = -1;
    private int page = 1;
    private int question_id;
    private List<PostCommentData> listdata = new ArrayList<>();
    private QuestionTransmit mQuestionTransmit;
    private final int TO_QUESTION_COMMENT_CODE = 105;
    private final int QUESTION_COMMENT_BACK_QUESTION_CODE = 107;
    private String commentText;
    private int USER_ID;
    private String USER_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_detail);
        ButterKnife.bind(this);
        lstvcommentq.setVisibility(View.VISIBLE);
        Intent intent = this.getIntent();
        mQuestionTransmit = (QuestionTransmit) intent.getSerializableExtra("questiondata");
        adapter = new ListViewAdapter(lstvcommentq,this, listdata, mQuestionTransmit.getQuestion_id());
        lstvcommentq.setAdapter(adapter);
        lstvcommentq.setOnRefreshListener(this);
        lstvcommentq.setOnLoadListener(this);
        lstvcommentq.setViewVisible(this);
        initData();
    }

    private void initData() {
        USER_ID = Integer.valueOf(ConfigUserMessagePrefs.getValue(QuestionCommentActivity.this, Contents.USER_ID, ""));
        USER_NAME = ConfigUserMessagePrefs.getValue(QuestionCommentActivity.this, Contents.USER_NAME, "");
        lstvcommentq.setQuestionTextView(mQuestionTransmit);
        setItemOnClickListener();
        headerCenterText.setText("评论");
        //topic_id = getIntent().getIntExtra(Contents.TOPIC_ID, -1);
        question_id = mQuestionTransmit.getQuestion_id();
        LVmodel = QuestionCommentListView.REFRESH;
        loadData();
    }

    private void setItemOnClickListener() {
        lstvcommentq.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < 6) {
                    return ;
                }
                PostCommentData mPostCommentData = new PostCommentData();
                mPostCommentData = listdata.get((position - 6));
                PostCommentTransmit mPostCommentTransmit = new PostCommentTransmit();
                mPostCommentTransmit.setId(mPostCommentData.getId());
                mPostCommentTransmit.setTopic_id(mPostCommentData.getTopic_id());
                mPostCommentTransmit.setTopic_type(mPostCommentData.getTopic_type());
                mPostCommentTransmit.setContent(mPostCommentData.getContent());
                mPostCommentTransmit.setFrom_uid(mPostCommentData.getFrom_uid());
                mPostCommentTransmit.setFrom_uname(mPostCommentData.getFrom_uname());
                mPostCommentTransmit.setTo_uid(mPostCommentData.getTo_uid());
                mPostCommentTransmit.setTo_uname(mPostCommentData.getTo_uname());
                mPostCommentTransmit.setComment_time(mPostCommentData.getComment_time());
                mPostCommentTransmit.setComment_number(mPostCommentData.getComment_number());
                mPostCommentTransmit.setPraise_number(mPostCommentData.getPraise_number());

                Intent intent = new Intent(QuestionCommentActivity.this, CommentMainPostActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("commentdata", mPostCommentTransmit);
                bundle.putInt("model", 0);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        // 这里模拟从服务器获取数据
        switch (LVmodel) {
            case QuestionCommentListView.REFRESH:
                page = 1;
                break;
            case QuestionCommentListView.LOAD:

                break;
        }

        new CommentSelectByQuestionId().SelectCommentByTopicId(
                "" + page, "10",
                "" + question_id, new CommentSelectByQuestionId.ISelectCommentByTopicId() {
                    @Override
                    public void Success(PostCommentDataResult postCommentDataResult) {
                        switch (LVmodel) {
                            case PostDetailListView.REFRESH:
                                lstvcommentq.onRefreshComplete();
                                listdata.clear();
                                listdata.addAll(postCommentDataResult.getPostCommentData());
                                break;
                            case PostDetailListView.LOAD:
                                lstvcommentq.onLoadComplete();
                                listdata.addAll(postCommentDataResult.getPostCommentData());
                                break;
                        }
                        lstvcommentq.setResultSize(postCommentDataResult.getNumber());
                        adapter.notifyDataSetChanged();
                        page++;
                        Toast.makeText(QuestionCommentActivity.this, "successMessage", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void Faild(String failMessage) {

                    }

                    @Override
                    public void Error(VolleyError volleyError) {

                    }
                });
    }

    @Override
    public void onRefresh() {
        LVmodel = QuestionCommentListView.REFRESH;
        loadData();
    }

    @Override
    public void onLoad() {
        LVmodel = QuestionCommentListView.LOAD;
        loadData();
    }


    @Override
    public void hotComment(boolean visible) {
        if (visible) {
            linHotComment.setVisibility(View.VISIBLE);
        } else {
            linHotComment.setVisibility(View.GONE);
        }
    }

    @Override
    public void newComment(boolean visible) {
        if (visible) {
            linNewComment.setVisibility(View.VISIBLE);
        } else {
            linNewComment.setVisibility(View.GONE);
        }
    }


    @OnClick({R.id.text_comment_detail_comment, R.id.iamge_comment_detail_collect,
              R.id.linear_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_comment_detail_comment:
                toCommetnQuestionActivity();
                break;
            case R.id.iamge_comment_detail_collect:
                break;
            case R.id.linear_back:
                finish();
                break;
        }
    }

    private void toCommetnQuestionActivity() {
        Intent intent = new Intent(QuestionCommentActivity.this, QuestionMainPostActivity.class);
        intent.putExtra("title", mQuestionTransmit.getChapter() + "");
        intent.putExtra("context", mQuestionTransmit.getQuestion() + "");
        startActivityForResult(intent, TO_QUESTION_COMMENT_CODE);
    }

    //第一个参数为请求码，即调用startActivityForResult()传递过去的值
    //第二个参数为结果码，结果码用于标识返回数据来自哪个新Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TO_QUESTION_COMMENT_CODE &&
            resultCode == QUESTION_COMMENT_BACK_QUESTION_CODE) {
            commentText = data.getExtras().getString("commenttext");//得到新Activity关闭后返回的数据
            Log.e("onActivityResult", "onActivityResult: resultCode=" + resultCode);
            WriteQuestionComment();
        }
    }

    private void WriteQuestionComment() {
        PostCommentData mPostCommentData = new PostCommentData();
        mPostCommentData.setTopic_id(mQuestionTransmit.getQuestion_id());
        mPostCommentData.setTopic_type(mQuestionTransmit.getSubject());
        mPostCommentData.setContent(commentText);
        mPostCommentData.setFrom_uid(USER_ID);
        mPostCommentData.setFrom_uname(USER_NAME);
        mPostCommentData.setTo_uid(mQuestionTransmit.getQuestion_id());
        mPostCommentData.setTo_uname("0");
        mPostCommentData.setComment_time("2017-02-12");
        mPostCommentData.setComment_number(0);
        mPostCommentData.setPraise_number(0);

        new QuestionWriteNew().WriteComment(mPostCommentData, new QuestionWriteNew.IWritePost() {
            @Override
            public void Success(String successMessage) {
                Toast.makeText(QuestionCommentActivity.this,
                        "" + successMessage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void Faild(String failMessage) {

            }

            @Override
            public void Error(VolleyError volleyError) {

            }
        });
    }

}
