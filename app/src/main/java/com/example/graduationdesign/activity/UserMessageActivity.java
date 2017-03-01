package com.example.graduationdesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.graduationdesign.R;
import com.example.graduationdesign.tool.ImageLoaderByAsyncTask;
import com.example.graduationdesign.utils.ConfigUserMessagePrefs;
import com.example.graduationdesign.utils.Contents;
import com.example.graduationdesign.utils.DateUtil;
import com.example.graduationdesign.utils.MyColor;
import com.example.graduationdesign.utils.OnPopWindowsResultListener;
import com.example.graduationdesign.utils.UtilsToast;
import com.example.graduationdesign.view.PopWindowsChangeMessage;
import com.example.graduationdesign.view.TimeSelectorView;
import com.example.graduationdesign.view.UserMessageView;
import com.example.weblibrary.URL;
import com.example.weblibrary.model.UserInformation;
import com.example.weblibrary.user.UserUpdateUserInformation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class UserMessageActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.button_amended)
    TextView buttonAmended;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    @BindView(R.id.userview_icon)
    UserMessageView userviewIcon;
    @BindView(R.id.userview_nickname)
    UserMessageView userviewNickname;
    @BindView(R.id.userview_gender)
    UserMessageView userviewGender;
    @BindView(R.id.userview_userschool)
    UserMessageView userviewUserschool;
    @BindView(R.id.userview_usermajor)
    UserMessageView userviewUsermajor;
    @BindView(R.id.userview_aimschool)
    UserMessageView userviewAimschool;
    @BindView(R.id.userview_aimmajor)
    UserMessageView userviewAimmajor;
    @BindView(R.id.userview_examtime)
    UserMessageView userviewExamtime;
    @BindView(R.id.userview_password)
    UserMessageView userviewPassword;
    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    private final String TAG = "UserMessageActivity";
    private List<UserMessageView> mUserMessageViewList = new ArrayList<UserMessageView>();
    private TimeSelectorView mTimeSelectorView;
    private OnPopWindowsResultListener PopWindowsResultListener;
    private UserInformation mUserInformation;
    private UserInformation updateUserInformation;
    private final int TO_GENDER_REQUEST_CODE = 101;
    private final int GENDER_BACK_TO_MESSAGE_CODE = 102;
    private final int ICON_BACK_TO_MESSAGE_CODE = 103;
    private ImageLoaderByAsyncTask mImageLoaderByAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);
        ButterKnife.bind(this);
        InitTimeSelectorView();
        InitView();
        InitPopWindowsResultListener();
    }

    /**
     * 初始化组件的显示数据
     */
    private void InitView() {
        //String url = ConfigUserMessagePrefs.getValue(this, Contents.USER_ICON_URL, "NULL").toString();
        String url = URL.downloadimage+"userid=22398";
        mImageLoaderByAsyncTask = new ImageLoaderByAsyncTask(userviewIcon.getIconImageView());
        mImageLoaderByAsyncTask.loadImages(url);
        mImageLoaderByAsyncTask.showImageByAsyncTask(url);

        headerCenterText.setText("个人资料");
        buttonAmended.setVisibility(View.VISIBLE);
        mUserMessageViewList.add(userviewIcon);
        //mUserMessageViewList.add(userviewNickname);
        mUserMessageViewList.add(userviewGender);
        mUserMessageViewList.add(userviewUserschool);
        mUserMessageViewList.add(userviewUsermajor);
        mUserMessageViewList.add(userviewAimschool);
        mUserMessageViewList.add(userviewAimmajor);
        mUserMessageViewList.add(userviewExamtime);
        mUserMessageViewList.add(userviewPassword);

        updateUserInformation = new UserInformation();
        mUserInformation = new UserInformation();

        updateUserInformation.setUserid(ConfigUserMessagePrefs.getValue(this, Contents.USER_ID, "NULL").toString());
        //updateUserInformation.setUserid("18819");

        mUserInformation.setUserid(ConfigUserMessagePrefs.getValue(this, Contents.USER_ID, "NULL").toString());
        mUserInformation.setAimmajor(ConfigUserMessagePrefs.getValue(this, Contents.USER_AIM_MAJOR, "NULL").toString());
        mUserInformation.setAimschool(ConfigUserMessagePrefs.getValue(this, Contents.USER_AIM_SCHOOL, "NULL").toString());
        mUserInformation.setExamtime(ConfigUserMessagePrefs.getValue(this, Contents.USER_EXAM_TIME, "NULL").toString());
        mUserInformation.setUsergender(ConfigUserMessagePrefs.getValue(this, Contents.USER_GENDER, "NULL").toString());
        mUserInformation.setUsericon(ConfigUserMessagePrefs.getValue(this, Contents.USER_ICON_URL, "NULL").toString());
        mUserInformation.setUsermajor(ConfigUserMessagePrefs.getValue(this, Contents.USER_MAJOR, "NULL").toString());
        mUserInformation.setUserschool(ConfigUserMessagePrefs.getValue(this, Contents.USER_SCHOOL, "NULL").toString());

        //userviewIcon.seticon
        userviewNickname.setRightText(ConfigUserMessagePrefs.getValue(this, Contents.USER_NAME, "NULL").toString());
        userviewGender.setRightText(mUserInformation.getUsergender());
        userviewUserschool.setRightText(mUserInformation.getUserschool());
        userviewUsermajor.setRightText(mUserInformation.getUsermajor());
        userviewAimschool.setRightText(mUserInformation.getAimschool());
        userviewAimmajor.setRightText(mUserInformation.getAimmajor());
        userviewExamtime.setRightText(mUserInformation.getExamtime());

    }

    private void InitTimeSelectorView() {
        mTimeSelectorView = new TimeSelectorView(this, new TimeSelectorView.ResultHandler() {
            @Override
            public void handle(String time) {
                String[] split = time.split(" ");
                if (split.length > 0) {
                    Toast.makeText(getApplicationContext(), split[0], Toast.LENGTH_LONG).show();
                    userviewExamtime.setRightText(split[0]);
                    updateUserInformation.setExamtime(split[0]);
                }

            }
        }, DateUtil.getStartDate() + " 00:00", DateUtil.getEndDate() + " 00:00");
        mTimeSelectorView.setMode(TimeSelectorView.MODE.YMD);
    }

    private void InitPopWindowsResultListener() {
        PopWindowsResultListener = new OnPopWindowsResultListener() {
            @Override
            public void TextData(String textData, int Id) {
                switch (Id) {
                    case R.id.userview_userschool:
                        updateUserInformation.setUserschool("" + textData);
                        userviewUserschool.setRightText("" + textData);
                        break;
                    case R.id.userview_usermajor:
                        updateUserInformation.setUsermajor("" + textData);
                        userviewUsermajor.setRightText("" + textData);
                        break;
                    case R.id.userview_aimschool:
                        updateUserInformation.setAimschool("" + textData);
                        userviewAimschool.setRightText("" + textData);
                        break;
                    case R.id.userview_aimmajor:
                        updateUserInformation.setAimmajor("" + textData);
                        userviewAimmajor.setRightText("" + textData);
                        break;
                }
            }
        };
    }


    private void AmendedMessage() {
        if (userviewIcon.getViewEnabled()) {//可点击
            for (UserMessageView mUserMessageView : mUserMessageViewList) {
                mUserMessageView.setTextColorBoth(MyColor.UserTextNarmal);
                mUserMessageView.setRightImageViewVisibility(View.GONE);
                mUserMessageView.setViewIsEditor(false);
                mUserMessageView.setViewEnabled(false);
            }
            UpdateUserInformation();
            buttonAmended.setText("修改");
        } else {//不可点击
            for (UserMessageView mUserMessageView : mUserMessageViewList) {
                mUserMessageView.setTextColorBoth(MyColor.UserTextEditor);
                mUserMessageView.setRightImageViewVisibility(View.VISIBLE);
                mUserMessageView.setViewEnabled(true);
            }


            buttonAmended.setText("保存");
        }
    }

    @OnClick({R.id.button_amended, R.id.linear_back, R.id.userview_icon, R.id.userview_nickname,
              R.id.userview_gender, R.id.userview_userschool, R.id.userview_usermajor,
              R.id.userview_aimschool, R.id.userview_aimmajor, R.id.userview_examtime,
              R.id.userview_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_amended:
                AmendedMessage();
                break;
            case R.id.linear_back:
                finish();
                break;
            case R.id.userview_icon:
                //打开头像显示界面，同时可以更改头像
                toUserFaceActivity();
                break;
            case R.id.userview_gender:
                toGenderActivity();
                break;
            //打开弹出框，
            case R.id.userview_examtime:
                mTimeSelectorView.show();
                break;
            //打开更改密码界面
            case R.id.userview_password:
                Intent intentPassword = new Intent(UserMessageActivity.this, ChangePasswordActivity.class);
                startActivity(intentPassword);
                break;
            case R.id.userview_nickname:
                break;
            case R.id.userview_userschool:
                PopWindowsChangeMessage mPopWindowsuserschool = new PopWindowsChangeMessage(UserMessageActivity.this, R.id.userview_userschool);
                mPopWindowsuserschool.setReturnEditTextDatainterface(PopWindowsResultListener);
                mPopWindowsuserschool.setTitleAndHintText("本科学校", "广西师范大学");
                mPopWindowsuserschool.showPopupWindow(userviewNickname);
                break;
            case R.id.userview_usermajor:
                PopWindowsChangeMessage mPopWindowsusermajor = new PopWindowsChangeMessage(UserMessageActivity.this, R.id.userview_usermajor);
                mPopWindowsusermajor.setReturnEditTextDatainterface(PopWindowsResultListener);
                mPopWindowsusermajor.setTitleAndHintText("本科专业", "通信工程");
                mPopWindowsusermajor.showPopupWindow(userviewNickname);
                break;
            case R.id.userview_aimschool:
                PopWindowsChangeMessage mPopWindowsaimschool = new PopWindowsChangeMessage(UserMessageActivity.this, R.id.userview_aimschool);
                mPopWindowsaimschool.setReturnEditTextDatainterface(PopWindowsResultListener);
                mPopWindowsaimschool.setTitleAndHintText("考研学校", "广西师范大学");
                mPopWindowsaimschool.showPopupWindow(userviewNickname);
                break;
            case R.id.userview_aimmajor:
                PopWindowsChangeMessage mPopWindowsaimjor = new PopWindowsChangeMessage(UserMessageActivity.this, R.id.userview_aimmajor);
                mPopWindowsaimjor.setReturnEditTextDatainterface(PopWindowsResultListener);
                mPopWindowsaimjor.setTitleAndHintText("考研专业", "电子信息工程");
                mPopWindowsaimjor.showPopupWindow(userviewNickname);
                break;
        }
    }

    private void saveIformationLocal() {

        if (updateUserInformation.getUsericon() != null) {
            ConfigUserMessagePrefs.putValue(UserMessageActivity.this, Contents.USER_ICON_URL, updateUserInformation.getUsericon());
        }
        if (updateUserInformation.getUsergender() != null) {
            ConfigUserMessagePrefs.putValue(UserMessageActivity.this, Contents.USER_GENDER, updateUserInformation.getUsergender());
        }
        if (updateUserInformation.getUserschool() != null) {
            ConfigUserMessagePrefs.putValue(UserMessageActivity.this, Contents.USER_SCHOOL, updateUserInformation.getUserschool());
        }
        if (updateUserInformation.getUsermajor() != null) {
            ConfigUserMessagePrefs.putValue(UserMessageActivity.this, Contents.USER_MAJOR, updateUserInformation.getUsermajor());
        }
        if (updateUserInformation.getAimschool() != null) {
            ConfigUserMessagePrefs.putValue(UserMessageActivity.this, Contents.USER_AIM_SCHOOL, updateUserInformation.getAimschool());
        }
        if (updateUserInformation.getAimmajor() != null) {
            ConfigUserMessagePrefs.putValue(UserMessageActivity.this, Contents.USER_AIM_MAJOR, updateUserInformation.getAimmajor());
        }
        if (updateUserInformation.getExamtime() != null) {
            ConfigUserMessagePrefs.putValue(UserMessageActivity.this, Contents.USER_EXAM_TIME, updateUserInformation.getExamtime());
        }
    }

    private void toUserFaceActivity() {
        Intent intent = new Intent(UserMessageActivity.this, ChangeUserFaceActivity.class);
        startActivityForResult(intent, TO_GENDER_REQUEST_CODE);
    }

    private void toGenderActivity() {
        Intent intent = new Intent(UserMessageActivity.this, GenderActivity.class);
        startActivityForResult(intent, TO_GENDER_REQUEST_CODE);
    }

    //第一个参数为请求码，即调用startActivityForResult()传递过去的值
    //第二个参数为结果码，结果码用于标识返回数据来自哪个新Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TO_GENDER_REQUEST_CODE && resultCode == GENDER_BACK_TO_MESSAGE_CODE) {
            String result = data.getExtras().getString("gender");//得到新Activity关闭后返回的数据
            userviewGender.setRightText(result);
            updateUserInformation.setUsergender(result);
            Log.e(TAG, "onActivityResult: resultCode=" + resultCode);
        } else if (requestCode == TO_GENDER_REQUEST_CODE &&
                   resultCode == ICON_BACK_TO_MESSAGE_CODE) {
            String result = data.getExtras().getString("iconurl");//得到新Activity关闭后返回的数据
            //userviewGender.setRightText(result);
            updateUserInformation.setUsericon(result);
            Log.e(TAG, "onActivityResult: resultCode=" + resultCode + "---" + result);
        }
    }

    private void UpdateUserInformation() {
        new UserUpdateUserInformation().UpdateUserInformation(updateUserInformation, new UserUpdateUserInformation.IUpdateUserInformation() {
            @Override
            public void Success(String successMessage) {
                saveIformationLocal();
                UtilsToast.Toast(UserMessageActivity.this, successMessage, 0);
            }

            @Override
            public void Faild(String failMessage) {
                UtilsToast.Toast(UserMessageActivity.this, failMessage, 0);
            }

            @Override
            public void Error(VolleyError volleyError) {
                UtilsToast.Toast(UserMessageActivity.this, volleyError.toString(), 0);
            }
        });
    }

}
