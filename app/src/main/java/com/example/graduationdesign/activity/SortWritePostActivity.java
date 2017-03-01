package com.example.graduationdesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.graduationdesign.R;
import com.example.graduationdesign.utils.ConfigUserMessagePrefs;
import com.example.graduationdesign.utils.Contents;
import com.example.graduationdesign.view.GenderItemView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SortWritePostActivity extends BaseActivity implements GenderItemView.SeleteStates {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.button_amended)
    TextView buttonAmended;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    @BindView(R.id.sort_item_signup)
    GenderItemView sortItemSignup;
    @BindView(R.id.sort_item_exam_experience)
    GenderItemView sortItemExamExperience;
    @BindView(R.id.sort_item_review_experience)
    GenderItemView sortItemReviewExperience;
    @BindView(R.id.sort_item_seekhelp)
    GenderItemView sortItemSeekhelp;

    private boolean IsFrist = true;
    private final int SORT_BACK_TO_WRITE_POST = 104;
    private final int SORT_SIGN_UP = 0;
    private final int SORT_EXAM = 1;
    private final int SORT_REVIEW = 2;
    private final int SORT_SEEK_HELP = 3;
    private String SortText = "";
    private int postSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_sort_write_post);
        ButterKnife.bind(this);
        InitView();
    }

    private void InitView() {
        headerCenterText.setText("分类");

        buttonAmended.setVisibility(View.INVISIBLE);
        sortItemExamExperience.setInterfaceSeleteStates(this);
        sortItemReviewExperience.setInterfaceSeleteStates(this);
        sortItemSeekhelp.setInterfaceSeleteStates(this);
        sortItemSignup.setInterfaceSeleteStates(this);
        //get intent data
        postSort = getIntent().getIntExtra(Contents.SORT_MODEL, Contents.SORT_SIGN_UP);

        if (postSort == Contents.SORT_SIGN_UP) {
            setItemView(SORT_SIGN_UP);
        } else if (postSort == Contents.SORT_EXAM) {
            setItemView(SORT_EXAM);
        } else if (postSort == Contents.SORT_REVIEW) {
            setItemView(SORT_REVIEW);
        } else if (postSort == Contents.SORT_SEEK_HELP) {
            setItemView(SORT_SEEK_HELP);
        }
    }


    @Override
    public void SeleteStateChange(String ItemId, boolean isSelete) {
        setGenderItemState(ItemId);
    }

    private void setGenderItemState(String ItemId) {
        if (ItemId.equals("singup")) {
            setItemView(SORT_SIGN_UP);
        } else if (ItemId.equals("exam")) {
            setItemView(SORT_EXAM);
        } else if (ItemId.equals("review")) {
            setItemView(SORT_REVIEW);
        } else if (ItemId.equals("help")) {
            setItemView(SORT_SEEK_HELP);
        }
    }

    private void setItemView(int model) {
        switch (model) {
            case SORT_SIGN_UP:
                postSort = 0;
                SortText = "" + sortItemSignup.getItemText();
                sortItemSignup.setIsSelete(true);
                sortItemExamExperience.setIsSelete(false);
                sortItemReviewExperience.setIsSelete(false);
                sortItemSeekhelp.setIsSelete(false);
                break;
            case SORT_EXAM:
                postSort = 1;
                SortText = "" + sortItemExamExperience.getItemText();
                sortItemSignup.setIsSelete(false);
                sortItemExamExperience.setIsSelete(true);
                sortItemReviewExperience.setIsSelete(false);
                sortItemSeekhelp.setIsSelete(false);
                break;
            case SORT_REVIEW:
                postSort = 2;
                SortText = "" + sortItemReviewExperience.getItemText();
                sortItemSignup.setIsSelete(false);
                sortItemExamExperience.setIsSelete(false);
                sortItemReviewExperience.setIsSelete(true);
                sortItemSeekhelp.setIsSelete(false);
                break;
            case SORT_SEEK_HELP:
                postSort = 3;
                SortText = "" + sortItemSeekhelp.getItemText();
                sortItemSignup.setIsSelete(false);
                sortItemExamExperience.setIsSelete(false);
                sortItemReviewExperience.setIsSelete(false);
                sortItemSeekhelp.setIsSelete(true);
                break;
        }
        if (IsFrist) {
            IsFrist = false;
        } else {
            ResultUserMessage();
        }
    }

    private void ResultUserMessage() {
        Intent intent = new Intent(SortWritePostActivity.this, UserMessageActivity.class);
        intent.putExtra(Contents.SORT_MODEL, postSort);
        intent.putExtra("sorttext", SortText);
        SortWritePostActivity.this.setResult(SORT_BACK_TO_WRITE_POST, intent);
        SortWritePostActivity.this.finish();
    }

    @OnClick({R.id.linear_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                ResultUserMessage();
                break;
        }
    }
}
