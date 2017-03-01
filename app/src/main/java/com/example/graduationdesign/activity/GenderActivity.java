package com.example.graduationdesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.graduationdesign.R;
import com.example.graduationdesign.utils.ConfigUserMessagePrefs;
import com.example.graduationdesign.utils.Contents;
import com.example.graduationdesign.view.GenderItemView;
import com.example.graduationdesign.view.UserMessageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class GenderActivity extends BaseActivity implements GenderItemView.SeleteStates {
    @BindView(R.id.button_amended)
    TextView buttonAmended;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    @BindView(R.id.gender_item_man)
    GenderItemView genderItemMan;
    @BindView(R.id.gender_item_woman)
    GenderItemView genderItemWoman;
    @BindView(R.id.gender_item_secrecy)
    GenderItemView genderItemSecrecy;
    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.but_gender_determine)
    Button butGenderDetermine;

    private final int GENDER_BACK_TO_MESSAGE_CODE = 102;
    private final int MAN = 0;
    private final int WOMAN = 1;
    private final int SECRECY = 2;

    private String userGender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_gender);
        ButterKnife.bind(this);
        InitView();
    }

    private void InitView() {
        headerCenterText.setText("性别");
        buttonAmended.setVisibility(View.INVISIBLE);
        genderItemMan.setInterfaceSeleteStates(this);
        genderItemWoman.setInterfaceSeleteStates(this);
        genderItemSecrecy.setInterfaceSeleteStates(this);

        userGender = ConfigUserMessagePrefs.getValue(GenderActivity.this, Contents.USER_GENDER, "保密");

        if (userGender.equals("保密")) {
            setItemView(SECRECY);
        } else if (userGender.equals("男")) {
            setItemView(MAN);
        } else if (userGender.equals("女")) {
            setItemView(WOMAN);
        }
    }


    @Override
    public void SeleteStateChange(String ItemId, boolean isSelete) {
        setGenderItemState(ItemId);
    }

    private void setGenderItemState(String ItemId) {
        if (ItemId.equals("man")) {
            setItemView(MAN);
            ConfigUserMessagePrefs.putValue(GenderActivity.this, Contents.USER_GENDER, "男");
        } else if (ItemId.equals("woman")) {
            setItemView(WOMAN);
            ConfigUserMessagePrefs.putValue(GenderActivity.this, Contents.USER_GENDER, "女");
        } else if (ItemId.equals("secrecy")) {
            setItemView(SECRECY);
            ConfigUserMessagePrefs.putValue(GenderActivity.this, Contents.USER_GENDER, "保密");
        }
    }

    private void setItemView(int model) {
        switch (model) {
            case MAN:
                userGender = "男";
                genderItemMan.setIsSelete(true);
                genderItemWoman.setIsSelete(false);
                genderItemSecrecy.setIsSelete(false);
                break;
            case WOMAN:
                userGender = "女";
                genderItemMan.setIsSelete(false);
                genderItemWoman.setIsSelete(true);
                genderItemSecrecy.setIsSelete(false);
                break;
            case SECRECY:
                userGender = "保密";
                genderItemMan.setIsSelete(false);
                genderItemWoman.setIsSelete(false);
                genderItemSecrecy.setIsSelete(true);
                break;
        }
    }

    private void ResultUserMessage() {
        Intent intent = new Intent(GenderActivity.this, UserMessageActivity.class);
        intent.putExtra("gender", userGender);
        GenderActivity.this.setResult(GENDER_BACK_TO_MESSAGE_CODE, intent);
        GenderActivity.this.finish();
    }

    @OnClick({R.id.linear_back, R.id.but_gender_determine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                ResultUserMessage();
                break;
            case R.id.but_gender_determine:
                ResultUserMessage();
                break;
        }
    }
}
