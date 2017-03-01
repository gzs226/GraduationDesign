package com.example.graduationdesign.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.graduationdesign.utils.model.PostCommentTransmit;
import com.example.graduationdesign.utils.model.PostTransmit;
import com.example.graduationdesign.view.CommentDetailListView;
import com.example.graduationdesign.view.PostDetailListView;
import com.example.weblibrary.model.PostCommentData;
import com.example.weblibrary.model.PostCommentDataResult;
import com.example.weblibrary.user.CommentSelectByTopicId;

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

public class CommentDetailActivity extends BaseActivity implements CommentDetailListView.OnRefreshListener, CommentDetailListView.OnLoadListener, CommentDetailListView.ViewVisible {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.lstvcommentc)
    CommentDetailListView lstvcommentc;
    @BindView(R.id.lin_hot_comment)
    LinearLayout linHotComment;
    @BindView(R.id.lin_new_comment)
    LinearLayout linNewComment;
    @BindView(R.id.iamge_comment_detail_collect)
    ImageView iamgeCommentDetailCollect;
    private ListViewAdapter adapter;
    private int LVmodel = -1;
    private int page = 1;
    private int topic_id;
    private List<PostCommentData> listdata = new ArrayList<>();
    private PostTransmit mPostTransmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_detail);
        ButterKnife.bind(this);
        lstvcommentc.setVisibility(View.VISIBLE);
        Intent intent = this.getIntent();
        mPostTransmit = (PostTransmit) intent.getSerializableExtra("questiondata");
        adapter = new ListViewAdapter(lstvcommentc,this, listdata, mPostTransmit.getUserid());
        lstvcommentc.setAdapter(adapter);
        lstvcommentc.setOnRefreshListener(this);
        lstvcommentc.setOnLoadListener(this);
        lstvcommentc.setViewVisible(this);
        initData();
    }

    private void initData() {

        lstvcommentc.setPostMainTextView(mPostTransmit);
        setItemOnClickListener();
        headerCenterText.setText("帖子详情");
        //topic_id = getIntent().getIntExtra(Contents.TOPIC_ID, -1);
        topic_id = mPostTransmit.getPostid();
        LVmodel = CommentDetailListView.REFRESH;
        loadData();
    }

    private void setItemOnClickListener() {
        lstvcommentc.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position < 6) {
                    return;
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

                Intent intent = new Intent(CommentDetailActivity.this, CommentMainPostActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("commentdata", mPostCommentTransmit);
                bundle.putInt("model", 1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        // 这里模拟从服务器获取数据
        switch (LVmodel) {
            case CommentDetailListView.REFRESH:
                page = 1;
                break;
            case CommentDetailListView.LOAD:

                break;
        }

        new CommentSelectByTopicId().SelectCommentByTopicId(
                "" + page, "10",
                "" + topic_id, new CommentSelectByTopicId.ISelectCommentByTopicId() {
                    @Override
                    public void Success(PostCommentDataResult postCommentDataResult) {
                        switch (LVmodel) {
                            case PostDetailListView.REFRESH:
                                lstvcommentc.onRefreshComplete();
                                listdata.clear();
                                listdata.addAll(postCommentDataResult.getPostCommentData());
                                break;
                            case PostDetailListView.LOAD:
                                lstvcommentc.onLoadComplete();
                                listdata.addAll(postCommentDataResult.getPostCommentData());
                                break;
                        }
                        lstvcommentc.setResultSize(postCommentDataResult.getNumber());
                        adapter.notifyDataSetChanged();
                        page++;
                        Toast.makeText(CommentDetailActivity.this, "successMessage", Toast.LENGTH_SHORT).show();
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
        LVmodel = CommentDetailListView.REFRESH;
        loadData();
    }

    @Override
    public void onLoad() {
        LVmodel = CommentDetailListView.LOAD;
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
                WritePostHome();
                break;
            case R.id.iamge_comment_detail_collect:
                break;
            case R.id.linear_back:
                finish();
                break;
        }
    }

    private void WritePostHome() {
        PostCommentTransmit mPostCommentTransmit = new PostCommentTransmit();
        mPostCommentTransmit.setTopic_id(mPostTransmit.getPostid());
        mPostCommentTransmit.setTopic_type(mPostTransmit.getPosttitle());
        mPostCommentTransmit.setContent(mPostTransmit.getPosttitle());
        mPostCommentTransmit.setFrom_uid(mPostTransmit.getPostid());
        mPostCommentTransmit.setFrom_uname(mPostTransmit.getUsername());
        //        mPostCommentTransmit.setTo_uid(mPostCommentData.getTo_uid());
        //        mPostCommentTransmit.setTo_uname(mPostCommentData.getTo_uname());
        mPostCommentTransmit.setComment_time(mPostTransmit.getPublictime());
        //   mPostCommentTransmit.setComment_number(mPostTransmit.);
        // mPostCommentTransmit.setPraise_number(mPostCommentData.getPraise_number());

        Intent intent = new Intent(CommentDetailActivity.this, CommentMainPostActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("commentdata", mPostCommentTransmit);
        bundle.putInt("model", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
