package com.example.graduationdesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.graduationdesign.R;
import com.example.graduationdesign.utils.ConfigUserMessagePrefs;
import com.example.graduationdesign.utils.Contents;
import com.example.weblibrary.model.Postresuldata;
import com.example.weblibrary.user.PostWriteNew;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WritePostActivity extends BaseActivity {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.button_amended)
    TextView buttonAmended;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    @BindView(R.id.edittext_sort)
    TextView edittextSort;
    @BindView(R.id.image_sort)
    ImageView imageSort;
    @BindView(R.id.edittext_write_post_title)
    EditText edittextWritePostTitle;
    @BindView(R.id.lin_write_post_title)
    LinearLayout linWritePostTitle;
    @BindView(R.id.edittext_write_post_context)
    EditText edittextWritePostContext;
    @BindView(R.id.button_release)
    Button buttonRelease;
    @BindView(R.id.text_show_sort)
    TextView textShowSort;

    private final int SORT_SIGN_UP = 0;
    private final int SORT_EXAM = 1;
    private final int SORT_REVIEW = 2;
    private final int SORT_SEEK_HELP = 3;

    private int postSort = 0;
    private final int TO_SORT_REQUEST_CODE = 1002;
    private final int SORT_BACK_TO_WRITE_POST = 104;

    private String mTitle;
    private String mContent;
    private String post_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_write_post);
        ButterKnife.bind(this);
        InitView();
        getIntentData();
    }

    private void InitView() {
        headerCenterText.setText("发布帖子");
        buttonAmended.setVisibility(View.INVISIBLE);
    }

    private void getIntentData() {
        post_type = getIntent().getStringExtra("post_type");
    }

    private void Back() {
        finish();
    }


    @OnClick({R.id.linear_back, R.id.lin_write_post_title, R.id.button_release})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                Back();
                break;
            case R.id.lin_write_post_title:
                Sort();
                break;
            case R.id.button_release:
                if (!TitleContenISNull()) {
                    Release();
                }
                break;
        }
    }

    private void Sort() {
        Intent intent = new Intent(WritePostActivity.this, SortWritePostActivity.class);
        intent.putExtra(Contents.SORT_MODEL, postSort);
        startActivityForResult(intent, TO_SORT_REQUEST_CODE);
    }

    //第一个参数为请求码，即调用startActivityForResult()传递过去的值
    //第二个参数为结果码，结果码用于标识返回数据来自哪个新Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TO_SORT_REQUEST_CODE && resultCode == SORT_BACK_TO_WRITE_POST) {
            String result = data.getExtras().getString("sorttext");//得到新Activity关闭后返回的数据
            postSort = data.getIntExtra(Contents.SORT_MODEL, 0);
            textShowSort.setText(result);
        }
    }


    private void Release() {
        Postresuldata mPostresuldata = new Postresuldata();
        mPostresuldata.setCommentnumber(0);
        mPostresuldata.setPosttitle(mTitle);
        mPostresuldata.setPostcontent(mContent);
        mPostresuldata.setUserid(Integer.parseInt(ConfigUserMessagePrefs.getValue(WritePostActivity.this, Contents.USER_ID, "")));
        mPostresuldata.setUserschool(ConfigUserMessagePrefs.getValue(WritePostActivity.this, Contents.USER_SCHOOL, ""));
        mPostresuldata.setPostsort(getSortPost(postSort));
        mPostresuldata.setPosttype(post_type);
        mPostresuldata.setPublictime("2015-02-12-22:44:22");
        mPostresuldata.setPraisenumber(0);
        mPostresuldata.setUsername(ConfigUserMessagePrefs.getValue(WritePostActivity.this, Contents.USER_NAME, ""));

        new PostWriteNew().WritePost(mPostresuldata, new PostWriteNew.IWritePost() {
            @Override
            public void Success(String successMessage) {
                Toast.makeText(WritePostActivity.this, successMessage, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void Faild(String failMessage) {

            }

            @Override
            public void Error(VolleyError volleyError) {

            }
        });
    }

    private String getSortPost(int mPostSort) {
        String postSort = "";
        switch (mPostSort) {
            case SORT_SIGN_UP:
                postSort = "报名";
                break;
            case SORT_EXAM:
                postSort = "考试经验";
                break;
            case SORT_REVIEW:
                postSort = "复习交流";
                break;
            case SORT_SEEK_HELP:
                postSort = "求职答疑";
                break;
        }
        return postSort;
    }

    private boolean TitleContenISNull() {
        mTitle = edittextWritePostTitle.getText() + "";
        mContent = edittextWritePostContext.getText() + "";

        if (mTitle.equals("")) {
            Toast.makeText(WritePostActivity.this, "请输出标题", Toast.LENGTH_SHORT).show();
            return true;
        } else if (mContent.equals("")) {
            Toast.makeText(WritePostActivity.this, "请输出内容", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }
}
