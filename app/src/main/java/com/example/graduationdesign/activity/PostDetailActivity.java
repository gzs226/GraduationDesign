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
import com.example.graduationdesign.adapter.PostListViewAdapter;
import com.example.graduationdesign.utils.Contents;
import com.example.graduationdesign.utils.model.PostTransmit;
import com.example.graduationdesign.view.PostDetailListView;
import com.example.weblibrary.model.Postresuldata;
import com.example.weblibrary.user.PostSelectBySort;

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

public class PostDetailActivity extends BaseActivity implements PostDetailListView.OnRefreshListener, PostDetailListView.OnLoadListener, ListView.OnItemClickListener {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.button_amended)
    TextView buttonAmended;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    @BindView(R.id.image_write_post)
    ImageView imageWritePost;
    @BindView(R.id.post_list)
    PostDetailListView postList;
    private PostListViewAdapter adapter;
    private List<Postresuldata> listPostresuldata = new ArrayList<>();
    private int LVmodel = -1;
    private String titleString = "";
    private int CIRCLE_MODEL = -1;
    private String POST_TYPE = "";
    private String POST_SORT = "";
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        ButterKnife.bind(this);

        adapter = new PostListViewAdapter(postList,this, listPostresuldata);
        postList.setAdapter(adapter);
        postList.setOnRefreshListener(this);
        postList.setOnLoadListener(this);
        postList.setOnItemClickListener(this);
        postList.setSortItemOnClickListener(new MySortItemOnClickListener());
        initData();
    }

    private void getPostData() {

        switch (LVmodel) {
            case PostDetailListView.REFRESH:
                page = 1;
                break;
            case PostDetailListView.LOAD:

                break;
        }
        new PostSelectBySort().SelectPostBySort(
                page + "", "10", POST_TYPE, new PostSelectBySort.ISelectPostBySort() {
                    @Override
                    public void Success(List<Postresuldata> listPostresuldata) {

                        switch (LVmodel) {
                            case PostDetailListView.REFRESH:
                                postList.onRefreshComplete();
                                PostDetailActivity.this.listPostresuldata.clear();
                                PostDetailActivity.this.listPostresuldata.addAll(listPostresuldata);
                                break;
                            case PostDetailListView.LOAD:
                                postList.onLoadComplete();
                                PostDetailActivity.this.listPostresuldata.addAll(listPostresuldata);
                                break;
                        }
                        postList.setResultSize(listPostresuldata.size());
                        adapter.notifyDataSetChanged();
                        page++;
                        Toast.makeText(PostDetailActivity.this, "successMessage", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void Faild(String failMessage) {
                        Toast.makeText(PostDetailActivity.this,
                                "failMessage" + failMessage, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void Error(VolleyError volleyError) {

                    }
                });
    }

    private void initData() {

        LVmodel = PostDetailListView.REFRESH;
        CIRCLE_MODEL = getIntent().getIntExtra(Contents.CIRCLE_MODEL, -1);
        switch (CIRCLE_MODEL) {
            case Contents.CIRCLE_EXAM_COMMUNICATION:
                headerCenterText.setText("考试交流");
                POST_TYPE = "考试交流";
                postList.setInitMainView(R.drawable.comment1, "考试交流", "考研政治复习交流区");
                break;
            case Contents.CIRCLE_SINGUP_EXAM:
                headerCenterText.setText("报考交流");
                POST_TYPE = "报考交流";
                postList.setInitMainView(R.drawable.comment2, "报考交流", "考研报考交流区");
                break;
            case Contents.CIRCLE_FREE_COMMUNICATION:
                headerCenterText.setText("灌水区");
                POST_TYPE = "灌水区";
                postList.setInitMainView(R.drawable.comment3, "灌水区", "闲聊灌水交流区");
                break;
            case Contents.CIRCLE_GESS_QUESTION:
                headerCenterText.setText("猜猜题");
                POST_TYPE = "大家一起来押题";
                postList.setInitMainView(R.drawable.comment4, "大家一起来押题", "大家一起来押题区");
                break;
        }
        getPostData();
    }

    @Override
    public void onRefresh() {
        //loadData(PostDetailListView.REFRESH);
        LVmodel = PostDetailListView.REFRESH;
        getPostData();
    }

    @Override
    public void onLoad() {
        //loadData(PostDetailListView.LOAD);
        LVmodel = PostDetailListView.LOAD;
        getPostData();
    }

    class MySortItemOnClickListener implements PostDetailListView.SortItemOnClickListener {

        @Override
        public void SortItemChanges(int modelId) {
            switch (modelId) {
                case R.id.text_sortitem_all:
                    break;
                case R.id.text_sortitem_signup:
                    break;
                case R.id.text_sortitem_exam:
                    break;
                case R.id.text_sortitem_review:
                    break;
                case R.id.text_sortitem_help:
                    break;
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int mPosition = position - 2;
        PostTransmit mPostTransmit = new PostTransmit();
        mPostTransmit.setPostid(listPostresuldata.get(mPosition).getPostid());
        mPostTransmit.setUserid(listPostresuldata.get(mPosition).getUserid());
        mPostTransmit.setUsername(listPostresuldata.get(mPosition).getUsername());
        mPostTransmit.setUserschool(listPostresuldata.get(mPosition).getUserschool());
        mPostTransmit.setPosttitle(listPostresuldata.get(mPosition).getPosttitle());
        mPostTransmit.setPostsort(listPostresuldata.get(mPosition).getPostsort());
        mPostTransmit.setPosttype(listPostresuldata.get(mPosition).getPosttype());
        mPostTransmit.setPostcontent(listPostresuldata.get(mPosition).getPostcontent());
        mPostTransmit.setCommentnumber(listPostresuldata.get(mPosition).getCommentnumber());
        mPostTransmit.setPraisenumber(listPostresuldata.get(mPosition).getPraisenumber());
        mPostTransmit.setPublictime(listPostresuldata.get(mPosition).getPublictime());

        Intent intent = new Intent(PostDetailActivity.this, CommentDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("questiondata", mPostTransmit);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @OnClick({R.id.linear_back, R.id.image_write_post})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.image_write_post:
                Intent intent = new Intent(PostDetailActivity.this, WritePostActivity.class);
                intent.putExtra("post_type", POST_TYPE);
                startActivity(intent);
                break;
        }
    }
}
