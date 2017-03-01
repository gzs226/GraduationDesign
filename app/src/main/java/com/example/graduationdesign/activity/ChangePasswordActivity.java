package com.example.graduationdesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.graduationdesign.R;
import com.example.graduationdesign.utils.MD5Util;
import com.example.graduationdesign.utils.UtilsToast;
import com.example.weblibrary.user.UserUpdateUserPassword;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends BaseActivity {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    @BindView(R.id.edittext_old_password)
    EditText edittextOldPassword;
    @BindView(R.id.edittext_new_password)
    EditText edittextNewPassword;
    @BindView(R.id.edittext_check_newpassword)
    EditText edittextCheckNewpassword;
    @BindView(R.id.but_confirm_change_password)
    Button butConfirmChangePassword;

    private String userId = "";
    private String oldPassword = "";
    private String newPassword1 = "";
    private String newPassword2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
    }

    private void InitView() {
        headerCenterText.setText("修改密码");
    }

    @OnClick({R.id.linear_back, R.id.but_confirm_change_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.but_confirm_change_password:
                if (CheckPasswordNull()) {
                    UpdateUserPassword();
                }

                break;
        }
    }


    private boolean CheckPasswordNull() {
        oldPassword = edittextOldPassword.getText().toString().trim();
        newPassword1 = edittextNewPassword.getText().toString().trim();
        newPassword2 = edittextCheckNewpassword.getText().toString().trim();
        if (oldPassword == null || oldPassword.equals("")) {
            UtilsToast.Toast(ChangePasswordActivity.this, "旧密码不为空", 0);
            return false;
        } else if (newPassword1 == null || newPassword1.equals("")) {
            UtilsToast.Toast(ChangePasswordActivity.this, "密码不能为空", 0);
            return false;
        } else if (newPassword2 == null || newPassword2.equals("")) {
            UtilsToast.Toast(ChangePasswordActivity.this, "密码不能为空", 0);
            return false;
        } else if (!newPassword1.equals(newPassword2)) {
            UtilsToast.Toast(ChangePasswordActivity.this, "密码不一致", 0);
            return false;
        }

        return true;
    }

    private void UpdateUserPassword() {
        userId = "18819";
        oldPassword = "156";
        newPassword1 = "136";
        oldPassword = MD5Util.md5Encode(oldPassword);
        newPassword1 = MD5Util.md5Encode(newPassword1);
        new UserUpdateUserPassword().UpdateUserPassword(userId, oldPassword, newPassword1, new UserUpdateUserPassword.IUpdateUserPassword() {
            @Override
            public void Success(String successMessage) {
                UtilsToast.Toast(ChangePasswordActivity.this, successMessage, 0);
            }

            @Override
            public void Faild(String failMessage) {
                UtilsToast.Toast(ChangePasswordActivity.this, failMessage, 0);
            }

            @Override
            public void Error(VolleyError volleyError) {
                UtilsToast.Toast(ChangePasswordActivity.this, volleyError.toString(), 0);
            }
        });
    }
}
