package com.example.graduationdesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.graduationdesign.R;
import com.example.graduationdesign.utils.MD5Util;
import com.example.graduationdesign.utils.UtilsToast;
import com.example.graduationdesign.view.LoginRegisterView;
import com.example.weblibrary.user.UserRegister;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by gg on 2016/11/5.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.mview_register_username)
    LoginRegisterView mviewRegisterUsername;
    @BindView(R.id.mview_register_password1)
    LoginRegisterView mviewRegisterPassword1;
    @BindView(R.id.mview_register_password2)
    LoginRegisterView mviewRegisterPassword2;
    @BindView(R.id.button_register_register)
    Button mButtonRegisterRegister;
    @BindView(R.id.textview_login_backlogin)
    TextView mButtonLoginBacklogin;
    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;

    private String mUsername;
    private String mPassword1;
    private String mPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        IfHintActionBar(true);
        initView();
    }

    private void initView() {
        headerCenterText.setText("注册");
        linearBack.setVisibility(View.INVISIBLE);
    }

    @OnClick({R.id.button_register_register, R.id.textview_login_backlogin})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_register_register:
                if (CheckPasswordNull()) {
                    Register();
                }
                break;
            case R.id.textview_login_backlogin:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private boolean CheckPasswordNull() {
        mUsername = mviewRegisterUsername.getEditorString();
        mPassword1 = mviewRegisterPassword1.getEditorString();
        mPassword2 = mviewRegisterPassword2.getEditorString();
        if (mUsername == null || mUsername.equals("")) {
            UtilsToast.Toast(RegisterActivity.this, "用户名不能为空", 0);
            return false;
        } else if (mPassword1 == null || mPassword1.equals("")) {
            UtilsToast.Toast(RegisterActivity.this, "密码不能为空", 0);
            return false;
        } else if (mPassword2 == null || mPassword2.equals("")) {
            UtilsToast.Toast(RegisterActivity.this, "密码不能为空", 0);
            return false;
        } else if (!mPassword1.equals(mPassword2)) {
            UtilsToast.Toast(RegisterActivity.this, "密码不一致", 0);
            return false;
        }

        return true;
    }

    private void Register() {
        //进行MD5加密
        mPassword1 = MD5Util.md5Encode(mPassword1);

        new UserRegister().register(mUsername, mPassword1, new UserRegister.IRegister() {
            @Override
            public void Success(String successMessage) {
                mviewRegisterUsername.setEditorString("");
                mviewRegisterPassword1.setEditorString("");
                mviewRegisterPassword2.setEditorString("");
                UtilsToast.Toast(RegisterActivity.this, successMessage, 0);
            }

            @Override
            public void Faild(String failMessage) {
                UtilsToast.Toast(RegisterActivity.this, failMessage, 0);

            }

            @Override
            public void Error(VolleyError volleyError) {
                UtilsToast.Toast(RegisterActivity.this, volleyError.toString(), 0);
            }
        });
    }
}
