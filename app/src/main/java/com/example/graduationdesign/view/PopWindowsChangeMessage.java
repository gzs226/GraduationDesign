package com.example.graduationdesign.view;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.example.graduationdesign.R;
import com.example.graduationdesign.utils.DateUtil;
import com.example.graduationdesign.utils.OnPopWindowsResultListener;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zy on 2017/2/10.
 */

public class PopWindowsChangeMessage extends PopupWindow implements View.OnClickListener {
    private Context popContext = null;
    private View conentView = null;
    private TextView textCenterTitle;
    private TextView textCancle;
    private TextView textConfirm;
    private EditText mEditText = null;
    private OnPopWindowsResultListener mReturnEditTextDatainterface = null;
    private String EditData = "";
    private int ID = -1;

    public PopWindowsChangeMessage(final Activity mactivity, int id) {
        this.popContext = mactivity;
        this.ID = id;
        LayoutInflater inflater = (LayoutInflater) mactivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popwindow_change_message, null);
        InitPopWindows(mactivity);
        InitView();
    }

    private void InitPopWindows(Activity context) {
        int[] ScreenSize = DateUtil.getScreenSize(context);
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ScreenSize[0]);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ScreenSize[1]);
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

    private void InitView() {
        textCenterTitle = (TextView) conentView.findViewById(R.id.text_pop_title);
        textConfirm = (TextView) conentView.findViewById(R.id.text_pop_select);
        textCancle = (TextView) conentView.findViewById(R.id.text_pop_cancle);
        mEditText = (EditText) conentView.findViewById(R.id.edit_change_message);

        textConfirm.setOnClickListener(PopWindowsChangeMessage.this);
        textCancle.setOnClickListener(PopWindowsChangeMessage.this);
    }

    public void setTitleAndHintText(String title, String hintText) {
        textCenterTitle.setText("" + title);
        mEditText.setHint("" + hintText);
    }

    public String getEditTextString() {
        EditData = mEditText.getText().toString() + "";
        return EditData;
    }

    public void setReturnEditTextDatainterface(OnPopWindowsResultListener mReturnEditTextDatainterface) {
        this.mReturnEditTextDatainterface = mReturnEditTextDatainterface;
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
            case R.id.text_pop_select:
                if (!EdittexIfNull()) {
                    //返回数据
                    mReturnEditTextDatainterface.TextData(EditData, ID);
                    this.dismiss();
                }
                break;
            case R.id.text_pop_cancle:
                this.dismiss();
                break;
        }
    }

    private boolean EdittexIfNull() {
        EditData = mEditText.getText().toString() + "";
        if (!EditData.equals("")) {
            return false;
        } else {
            Toast.makeText(popContext, "数据不正确", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

}
