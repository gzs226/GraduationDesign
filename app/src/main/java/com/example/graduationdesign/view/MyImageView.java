package com.example.graduationdesign.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.graduationdesign.R;

public class MyImageView extends ImageView {


    private boolean IsSelect = false;

    public MyImageView(Context context) {
        super(context);
        setBack();
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBack();
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBack();
    }

    public boolean isSelect() {
        return IsSelect;
    }

    public void setSelect(boolean select) {
        IsSelect = select;
        setBack();
    }

    private void setBack() {
        if (IsSelect) {
            this.setBackgroundResource(R.drawable.askbar_dianzan);
            this.setEnabled(false);
        } else {
            this.setBackgroundResource(R.drawable.askbar_no_dianzan);
        }
    }
}
