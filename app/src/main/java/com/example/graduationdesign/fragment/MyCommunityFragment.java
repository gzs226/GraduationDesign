package com.example.graduationdesign.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.graduationdesign.R;
import com.example.graduationdesign.activity.PostDetailActivity;
import com.example.graduationdesign.utils.Contents;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCommunityFragment extends Fragment {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.button_amended)
    TextView buttonAmended;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    @BindView(R.id.imageview_exam_icon)
    ImageView imageviewExamIcon;
    @BindView(R.id.exam_communication_new_number)
    TextView examCommunicationNewNumber;
    @BindView(R.id.rel_exam_communication)
    RelativeLayout relExamCommunication;
    @BindView(R.id.rel_signup_exam)
    RelativeLayout relSignupExam;
    @BindView(R.id.rel_free_communication)
    RelativeLayout relFreeCommunication;
    @BindView(R.id.rel_guess_question)
    RelativeLayout relGuessQuestion;

    private View communityView;

    public MyCommunityFragment() {

    }

    public MyCommunityFragment newFragment() {
        return new MyCommunityFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the body_fragment_mymain for this fragment
        if (communityView == null) {
            communityView = inflater.inflate(R.layout.fragment_my_community, container, false);
        }
        ButterKnife.bind(this, communityView);
        InitView();
        return communityView;
    }

    private void InitView() {
        headerCenterText.setText("圈子");
        linearBack.setVisibility(View.INVISIBLE);
        buttonAmended.setVisibility(View.INVISIBLE);
    }

    @OnClick({R.id.rel_exam_communication, R.id.rel_signup_exam, R.id.rel_free_communication, R.id.rel_guess_question})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.rel_exam_communication:
                intent.setClass(getContext(), PostDetailActivity.class);
                intent.putExtra(Contents.CIRCLE_MODEL,Contents.CIRCLE_EXAM_COMMUNICATION);
                break;
            case R.id.rel_signup_exam:
                intent.setClass(getContext(), PostDetailActivity.class);
                intent.putExtra(Contents.CIRCLE_MODEL,Contents.CIRCLE_SINGUP_EXAM);
                break;
            case R.id.rel_free_communication:
                intent.setClass(getContext(), PostDetailActivity.class);
                intent.putExtra(Contents.CIRCLE_MODEL,Contents.CIRCLE_FREE_COMMUNICATION);
                break;
            case R.id.rel_guess_question:
                intent.setClass(getContext(), PostDetailActivity.class);
                intent.putExtra(Contents.CIRCLE_MODEL,Contents.CIRCLE_GESS_QUESTION);
                break;
        }
        getActivity().startActivity(intent);
    }
}
