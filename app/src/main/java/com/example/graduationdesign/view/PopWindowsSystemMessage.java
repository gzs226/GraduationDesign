package com.example.graduationdesign.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduationdesign.R;
import com.example.graduationdesign.utils.DateUtil;
import com.example.graduationdesign.utils.OnPopWindowsResultListener;

/**
 * Created by zy on 2017/2/10.
 */

public class PopWindowsSystemMessage extends PopupWindow implements OnClickListener {
    private View conentView = null;
    private TextView textCenterTitle;
    private TextView textContent;
    private Button mButton;

    public PopWindowsSystemMessage(final Activity mactivity, String title, String content) {
        LayoutInflater inflater = (LayoutInflater) mactivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popwindow_system_message, null);
        InitPopWindows(mactivity);
        textCenterTitle = (TextView) conentView.findViewById(R.id.pop_system_title);
        textContent = (TextView) conentView.findViewById(R.id.pop_system_content);
        mButton = (Button) conentView.findViewById(R.id.pop_system_button);
        mButton.setOnClickListener(PopWindowsSystemMessage.this);

//        textCenterTitle.setText(title);
//        textContent.setText(content);
    }

    private void InitPopWindows(Activity context) {
        int[] ScreenSize = DateUtil.getScreenSize(context);
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ScreenSize[0]);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ScreenSize[1]-10);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(android.R.style.Animation_Dialog);

    }


    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAtLocation(parent, Gravity.CENTER, 0, 0);
        } else {
            this.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pop_system_button:
                this.dismiss();
                break;
        }
    }

}
