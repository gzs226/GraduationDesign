package com.example.graduationdesign.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.graduationdesign.R;
import com.example.graduationdesign.utils.SystemBarTintManager;


public class BaseActivity extends AppCompatActivity {
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.body_fragment_mymain.activity_base);
        IfHintActionBar(true);
        setTopTitleColor(R.color.SearchLineColorPress);
    }

    public void IfHintActionBar(boolean hint) {
        if (hint) {
            actionBar = getActionBar(); //得到ActionBar
            if (actionBar != null) {
                actionBar.hide(); //隐藏ActionBar
            }
        } else {
            actionBar = getActionBar(); //得到ActionBar

            if (actionBar != null) {
                actionBar.show(); //显示ActionBar
            }
        }
    }

    public void setTopTitleColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(color);//通知栏所需颜色
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
