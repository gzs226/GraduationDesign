package com.example.graduationdesign.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.graduationdesign.R;


public class UserExamtimeView extends FrameLayout {

    TextView LeftTextView = null;
    View mLayoutInflater = null;
    int ItemID = 0;

    public UserExamtimeView(Context context) {
        this(context, null);
    }

    public UserExamtimeView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        //在构造函数中将Xml中定义的布局解析出来。
        mLayoutInflater = LayoutInflater.from(context).inflate(R.layout.view_exam_time, this, true);
        LeftTextView = (TextView) mLayoutInflater.findViewById(R.id.examtime_item_text);

        //通过TypedArray类绑定在attr中的属性，在后面需要调用.recycle()；
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.attr_examtime_time);
        //获取LeftText在xml中设定的值
        String leftString = typedArray.getString(R.styleable.attr_examtime_time_leftText);
        LeftTextView.setText(leftString);

        int itemId = typedArray.getInt(R.styleable.attr_examtime_time_examTimeId, 0);
        ItemID = itemId;
        //防止内存泄漏
        typedArray.recycle();
    }


    public void setItemID(int itemId) {
        this.ItemID = itemId;
    }

    public int getItemID() {
        return ItemID;
    }

    public void setLeftTextView(String text) {
        LeftTextView.setText(text + "");
    }

    public String getLeftTextView() {
        return LeftTextView.getText().toString() + "";
    }
}
