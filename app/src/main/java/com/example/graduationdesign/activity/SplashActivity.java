package com.example.graduationdesign.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import com.android.volley.VolleyError;
import com.example.graduationdesign.R;
import com.example.graduationdesign.utils.ConfigUserMessagePrefs;
import com.example.graduationdesign.utils.Contents;
import com.example.weblibrary.model.UserInformation;
import com.example.weblibrary.user.UserSelectMessage;

/**
 * 开屏页
 */
public class SplashActivity extends Activity {
    private RelativeLayout rootLayout;
    //private TextView versionText;
    private boolean IsLogin = true;
    private static final int sleepTime = 2000;

    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.em_activity_splash);
        super.onCreate(arg0);
        IsLogin = ConfigUserMessagePrefs.getValue(SplashActivity.this, Contents.USER_IS_LOGIN, false);
        rootLayout = (RelativeLayout) findViewById(R.id.splash_root);
        //versionText = (TextView) findViewById(R.id.tv_version);

        //versionText.setText(getVersion());
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1500);
        rootLayout.startAnimation(animation);
    }

    @Override
    protected void onStart() {
        super.onStart();

        new Thread(new Runnable() {
            public void run() {
                //  if (DemoHelper.getInstance().isLoggedIn()) {
                if (IsLogin) {
                    getUserMessage();
                    // auto login mode, make sure all group and conversation is
                    // loaed before enter the fragment_question_bank screen
                    long start = System.currentTimeMillis();
                    long costTime = System.currentTimeMillis() - start;
                    // wait
                    if (sleepTime - costTime > 0) {
                        try {
                            Thread.sleep(sleepTime - costTime);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // enter fragment_question_bank screen
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } else {
                    try {
                        Thread.sleep(sleepTime);
                    }
                    catch (InterruptedException e) {
                    }
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }).start();

    }

    private void getUserMessage() {
        String userid = ConfigUserMessagePrefs.getValue(SplashActivity.this, Contents.USER_ID, "");
        new UserSelectMessage().SelectMessage(userid, new UserSelectMessage.ISelectMessage() {
            @Override
            public void Success(UserInformation mUserInformation) {
                ConfigUserMessagePrefs.putValue(SplashActivity.this, Contents.USER_ID, mUserInformation.getUserid());
                ConfigUserMessagePrefs.putValue(SplashActivity.this, Contents.USER_AIM_MAJOR, mUserInformation.getAimmajor());
                ConfigUserMessagePrefs.putValue(SplashActivity.this, Contents.USER_AIM_SCHOOL, mUserInformation.getAimschool());
                ConfigUserMessagePrefs.putValue(SplashActivity.this, Contents.USER_EXAM_TIME, mUserInformation.getExamtime());
                ConfigUserMessagePrefs.putValue(SplashActivity.this, Contents.USER_GENDER, mUserInformation.getUsergender());
                ConfigUserMessagePrefs.putValue(SplashActivity.this, Contents.USER_ICON_URL, mUserInformation.getUsericon());
                ConfigUserMessagePrefs.putValue(SplashActivity.this, Contents.USER_MAJOR, mUserInformation.getUsermajor());
                ConfigUserMessagePrefs.putValue(SplashActivity.this, Contents.USER_SCHOOL, mUserInformation.getUserschool());
            }

            @Override
            public void Faild(String failMessage) {

            }

            @Override
            public void Error(VolleyError volleyError) {

            }
        });

    }

    /**
     * get sdk version
     */
    private String getVersion() {
        // return EMClient.getInstance().getChatConfig().getVersion();
        return "";
    }
}
