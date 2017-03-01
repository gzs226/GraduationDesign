package com.example.graduationdesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.graduationdesign.R;
import com.example.graduationdesign.tool.StringsData2;
import com.example.graduationdesign.utils.ConfigUserMessagePrefs;
import com.example.graduationdesign.utils.Contents;
import com.example.graduationdesign.utils.DatabaseHelper;
import com.example.graduationdesign.utils.MD5Util;
import com.example.graduationdesign.utils.SelectQuestionDatas;
import com.example.graduationdesign.utils.TableOperate;
import com.example.graduationdesign.utils.UtilsToast;
import com.example.graduationdesign.utils.model.Question_bank;
import com.example.graduationdesign.view.LoginRegisterView;
import com.example.weblibrary.model.QuestionData;
import com.example.weblibrary.model.resultquestiondata;
import com.example.weblibrary.user.UserLogin;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.mview_login_username)
    LoginRegisterView mEdittextUserUsername;
    @BindView(R.id.mview_login_password)
    LoginRegisterView mEdittextUserPassword;

    @BindView(R.id.button_login_login)
    Button mButtonUserLogin;
    @BindView(R.id.button_login_register)
    Button mButtonUserRegister;
    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    @BindView(R.id.imageView_user_icon)
    ImageView imageViewUserIcon;

    private final String TAG = "LoginActivity";

    private String mUsername;
    private String mPassword;

    private DatabaseHelper helper;
    private TableOperate mTableOperate;
    private SelectQuestionDatas mSelectQuestionDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        IfHintActionBar(true);
        String path = this.getExternalFilesDir(null).toString();
        String s = Environment.getExternalStorageState();
        initView();
    }

    private void initView() {
        headerCenterText.setText("登录");
        linearBack.setVisibility(View.INVISIBLE);
        helper = new DatabaseHelper(this);

        mEdittextUserUsername.setEditorString(ConfigUserMessagePrefs.getValue(LoginActivity.this,Contents.USER_NAME,"")+"");
        mEdittextUserPassword.setEditorString(ConfigUserMessagePrefs.getValue(LoginActivity.this,Contents.USER_NAME,"")+"");

    }

    @OnClick({R.id.button_login_login, R.id.button_login_register, R.id.text_find_password})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login_login:
                              Login();
//                Intent intent = new Intent();
//                intent.setClass(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
                break;
            case R.id.button_login_register:
                toRegister();
                break;
            case R.id.text_find_password:
                testData();
                break;
        }
    }


    private void Login() {
        mUsername = mEdittextUserUsername.getEditorString();
        mPassword = mEdittextUserPassword.getEditorString();
        //进行DM5加密
        mPassword = MD5Util.md5Encode(mPassword);

        new UserLogin().Login(mUsername, mPassword, new UserLogin.ILogin() {
            @Override
            public void Success(String successMessage) {
                saveLoginMessage(successMessage);
                UtilsToast.Toast(LoginActivity.this, "登录成功！", 0);
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                Log.e(TAG, "Success: "+ConfigUserMessagePrefs.getValue(LoginActivity.this,Contents.USER_ID,"")+"");
                Log.e(TAG, "Success: "+ConfigUserMessagePrefs.getValue(LoginActivity.this,Contents.USER_NAME,"")+"");
                Log.e(TAG, "Success: "+ConfigUserMessagePrefs.getValue(LoginActivity.this,Contents.USER_PASSWORD,"")+"");
                Log.e(TAG, "Success: "+ConfigUserMessagePrefs.getValue(LoginActivity.this,Contents.USER_IS_LOGIN,false)+"");
            }

            @Override
            public void Faild(String failMessage) {
                UtilsToast.Toast(LoginActivity.this, failMessage, 0);
            }

            @Override
            public void Error(VolleyError volleyError) {
                UtilsToast.Toast(LoginActivity.this, volleyError.toString(), 0);
            }
        });
    }

    private void saveLoginMessage(String userid){
        ConfigUserMessagePrefs.putValue(LoginActivity.this, Contents.USER_ID,userid);
        ConfigUserMessagePrefs.putValue(LoginActivity.this, Contents.USER_NAME,mUsername);
        ConfigUserMessagePrefs.putValue(LoginActivity.this, Contents.USER_PASSWORD,mPassword);
        ConfigUserMessagePrefs.putValue(LoginActivity.this, Contents.USER_IS_LOGIN,true);
    }

    private void toRegister() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
//        mSelectQuestionDatas = new SelectQuestionDatas(helper.getReadableDatabase());
//        List<Question_bank> data = new ArrayList<>();
//        data = mSelectQuestionDatas.findAllDatas();
//
//        for (Question_bank d : data) {
//            Log.e("mQuestionData", "testData: " + d.getQuestion_id());
//        }
    }

    private void testData() {
        mTableOperate = new TableOperate(helper.getWritableDatabase());
        Gson gson = new Gson();
        QuestionData mQuestionData = gson.fromJson(StringsData2.data, QuestionData.class);
        List<resultquestiondata> result = new ArrayList<>();
        result.addAll(mQuestionData.getResultquestiondata());
        for (resultquestiondata d : result) {
            Question_bank insetQuestion_bank = new Question_bank();
            insetQuestion_bank.setQuestion_id(d.getQuestion_id());
            insetQuestion_bank.setSubject(d.getQuestion_subject());
            insetQuestion_bank.setChapter(d.getQuestion_chapter());
            insetQuestion_bank.setQuestion(d.getQuestion());
            insetQuestion_bank.setQuestion_number(d.getQuestion_number());
            insetQuestion_bank.setOption_a(d.getQuestion_option_a());
            insetQuestion_bank.setOption_b(d.getQuestion_option_b());
            insetQuestion_bank.setOption_c(d.getQuestion_option_c());
            insetQuestion_bank.setOption_d(d.getQuestion_option_d());
            insetQuestion_bank.setAnswer(d.getAnswer());
            insetQuestion_bank.setDifficulty_level(d.getDifficulty_level());
            insetQuestion_bank.setComment_number(d.getComment_number());
            insetQuestion_bank.setAnswer_analysis(d.getAnswer_analysis());
            insetQuestion_bank.setUser_do(0);
            insetQuestion_bank.setUser_do(-1);
            mTableOperate.Insert(insetQuestion_bank);
          Log.e("mQuestionData2--------", "testData: " + d.getQuestion_id());
        }
        mTableOperate.closeDB();
    }
}
