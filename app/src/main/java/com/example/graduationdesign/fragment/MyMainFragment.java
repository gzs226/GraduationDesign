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
import com.example.graduationdesign.activity.MycircleActivity;
import com.example.graduationdesign.activity.MyexamCommunicationActivity;
import com.example.graduationdesign.activity.MymallActivity;
import com.example.graduationdesign.activity.SettingActivity;
import com.example.graduationdesign.activity.SystemMessageActivity;
import com.example.graduationdesign.activity.TransferDataActivity;
import com.example.graduationdesign.activity.UserMessageActivity;
import com.example.graduationdesign.tool.CircleImageView;
import com.example.graduationdesign.tool.ImageLoaderByAsyncTask;
import com.example.graduationdesign.utils.ConfigUserMessagePrefs;
import com.example.graduationdesign.utils.Contents;
import com.example.weblibrary.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyMainFragment extends Fragment {
    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.button_amended)
    TextView buttonAmended;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    @BindView(R.id.imageview_post_usericon)
    CircleImageView imageviewPostUsericon;
    @BindView(R.id.textview_post_userid)
    TextView textviewPostUserid;
    @BindView(R.id.textview_post_userschool)
    TextView textviewPostUserschool;
    @BindView(R.id.imageview_praise_number)
    ImageView imageviewPraiseNumber;
    @BindView(R.id.lin_myquestion_bank)
    LinearLayout linMyquestionBank;
    @BindView(R.id.lin_mysocial_Communication)
    LinearLayout linMysocialCommunication;
    @BindView(R.id.lin_my_mall)
    LinearLayout linMyMall;
    @BindView(R.id.lin_my_message)
    LinearLayout linMyMessage;
    @BindView(R.id.lin_transfer_record)
    LinearLayout linTransferRecord;
    @BindView(R.id.rel_my_information)
    RelativeLayout relMyInformation;
    private View myMainView;
    private ImageLoaderByAsyncTask mImageLoaderByAsyncTask;
    private int NEW_MODEL_STATE;

    public MyMainFragment() {

    }

    public static MyMainFragment newFragment() {
        return new MyMainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the body_fragment_mymain for this fragment
        if (NEW_MODEL_STATE !=
            ConfigUserMessagePrefs.getValue(getActivity(), Contents.DAY_NIGHT_STATE, -1)) {
            NEW_MODEL_STATE = ConfigUserMessagePrefs.getValue(getActivity(), Contents.DAY_NIGHT_STATE, -1);
            myMainView = inflater.inflate(R.layout.fragment_my_main, container, false);
        }
        if (myMainView == null) {
            NEW_MODEL_STATE = ConfigUserMessagePrefs.getValue(getActivity(), Contents.DAY_NIGHT_STATE, -1);
            myMainView = inflater.inflate(R.layout.fragment_my_main, container, false);
        }
        ButterKnife.bind(this, myMainView);
        InitData();
        InitView();
        return myMainView;
    }

    private void InitData() {

    }

    private void InitView() {
        String muserid = ConfigUserMessagePrefs.getValue(getActivity(), Contents.USER_ID, "");
        String url = URL.downloadimage + "userid=" + muserid;
        mImageLoaderByAsyncTask = new ImageLoaderByAsyncTask(imageviewPostUsericon);
        mImageLoaderByAsyncTask.loadImages(url);
        mImageLoaderByAsyncTask.showImageByAsyncTask(url);

        headerCenterText.setText("我");
        buttonAmended.setText("设置");
        linearBack.setVisibility(View.INVISIBLE);
        buttonAmended.setVisibility(View.VISIBLE);
        //imageviewPostUsericon.setImageResource(R.drawable.ic_launcher);
        textviewPostUserid.setText(ConfigUserMessagePrefs.getValue(getActivity(), Contents.USER_NAME, ""));
        //textviewPostUserschool.setText("您还没有登录，无法获取信息");
        textviewPostUserschool.setText(ConfigUserMessagePrefs.getValue(getActivity(), Contents.USER_SCHOOL, ""));
    }

    @OnClick({R.id.button_amended, R.id.imageview_post_usericon, R.id.rel_my_information,
              R.id.lin_myquestion_bank, R.id.lin_mysocial_Communication, R.id.lin_my_mall,
              R.id.lin_my_message, R.id.lin_transfer_record})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.button_amended:
                intent.setClass(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.imageview_post_usericon:
                break;
            case R.id.rel_my_information:
                intent.setClass(getActivity(), UserMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.lin_myquestion_bank:
                intent.setClass(getActivity(), MyexamCommunicationActivity.class);
                startActivity(intent);
                break;
            case R.id.lin_mysocial_Communication:
                intent.setClass(getActivity(), MycircleActivity.class);
                startActivity(intent);
                break;
            case R.id.lin_my_mall:
                intent.setClass(getActivity(), MymallActivity.class);
                startActivity(intent);
                break;
            case R.id.lin_my_message:
                intent.setClass(getActivity(), SystemMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.lin_transfer_record:
                ToTransferRecord();
                break;
        }
    }

    private void ToTransferRecord() {
        Intent intent = new Intent(getActivity(), TransferDataActivity.class);
        startActivity(intent);
    }
}
