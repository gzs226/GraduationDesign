package com.example.graduationdesign.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduationdesign.R;


public class UserMessageView extends LinearLayout implements View.OnClickListener {

    LinearLayout showLinearLayout;
    LinearLayout editorLinearLayout;
    ImageView mImageViewSaveMessage;
    ImageView mImageViewCancelMessage;
    EditText mEditTextMessage;
    TextView LeftTextView = null;
    TextView RightTextView = null;
    View mLayoutInflater = null;
    ImageView RightImageView = null;
    ImageView IconImage = null;
    private boolean IsEnabled = false;
    private boolean IsEditor = false;

    public UserMessageView(Context context) {
        this(context, null);
    }

    public UserMessageView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        //在构造函数中将Xml中定义的布局解析出来。   
        mLayoutInflater = LayoutInflater.from(context).inflate(R.layout.view_message, this);
        setViewEnabled(false);

        showLinearLayout = (LinearLayout) findViewById(R.id.lin_show);
        editorLinearLayout = (LinearLayout) findViewById(R.id.lin_editor);

        mImageViewSaveMessage = (ImageView) findViewById(R.id.imageview_save_message);
//        mImageViewSaveMessage.setOnClickListener(this);
        mImageViewCancelMessage = (ImageView) findViewById(R.id.imageview_cancel_message);
        mImageViewCancelMessage.setOnClickListener(this);

        mEditTextMessage = (EditText) findViewById(R.id.edittext_message);
        mEditTextMessage.setOnFocusChangeListener(new mEditTextChangeState());
        setViewIsEditor(false);
        LeftTextView = (TextView) mLayoutInflater.findViewById(R.id.left_textview);
        RightTextView = (TextView) mLayoutInflater.findViewById(R.id.right_textview);
        RightImageView = (ImageView) mLayoutInflater.findViewById(R.id.right_imageview);
        IconImage = (ImageView) mLayoutInflater.findViewById(R.id.right_usericon);

        //通过TypedArray类绑定在attr中的属性，在后面需要调用.recycle()；
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.attr_message);
        //获取LeftText在xml中设定的值
        String leftString = typedArray.getString(R.styleable.attr_message_LeftText);
        LeftTextView.setText(leftString);

        String rightString = typedArray.getString(R.styleable.attr_message_RightText);
        RightTextView.setText(rightString);
        int rightTextHint = typedArray.getInt(R.styleable.attr_message_RightTextVisible, View.VISIBLE);
        setRightTextVisible(rightTextHint);

        int iconHint = typedArray.getInt(R.styleable.attr_message_IconVisible, View.GONE);
        setIconImageVisible(iconHint);
        //防止内存泄漏
        typedArray.recycle();
    }

    /**
     * 将保存的按钮暴露出来，在Activity中完成事件
     *
     * @param mOnClickListener
     */
    public void setImageViewSaveClickListener(View.OnClickListener mOnClickListener) {
        mImageViewSaveMessage.setOnClickListener(mOnClickListener);
    }

    /**
     * 设置编辑框的文字
     *
     * @param text
     */
    public void setEditTextString(String text) {
        mEditTextMessage.setText(text + "");
    }

    /**
     * 获取编辑框的文字
     *
     * @return
     */
    public String getEditTextString() {
        return mEditTextMessage.getText().toString() + "";
    }

    /**
     * 设置View的状态
     *
     * @param editor true-编辑 false-显示
     */
    public void setViewIsEditor(boolean editor) {
        if (editor) {//设置为编辑状态
            showLinearLayout.setVisibility(View.GONE);
            editorLinearLayout.setVisibility(View.VISIBLE);
            //获取焦点
            mEditTextMessage.setFocusable(true);
            mEditTextMessage.setFocusableInTouchMode(true);
            mEditTextMessage.requestFocus();
            mEditTextMessage.findFocus();
        } else {//设置为显示状态
            showLinearLayout.setVisibility(View.VISIBLE);
            editorLinearLayout.setVisibility(View.GONE);
        }
        IsEditor = editor;
    }

    /**
     * 获取View的状态
     *
     * @return true-编辑 false-显示
     */
    public boolean getViewEditorState() {
        return IsEditor;
    }

    /**
     * 设置用户头像IconImage可见性
     *
     * @param visible View.VISIBLE-0-可见 View.INVISIBLE-4-不可见 View.GONE-8-隐藏
     */
    public void setIconImageVisible(int visible) {
        switch (visible) {
            case View.GONE://8
                IconImage.setVisibility(View.GONE);
                break;
            case View.VISIBLE://0
                IconImage.setVisibility(View.VISIBLE);
                break;
            case View.INVISIBLE://4
                IconImage.setVisibility(View.INVISIBLE);
                break;
        }
    }

    /**
     * 设置右边textview-RightText的可见性
     *
     * @param visible View.VISIBLE-0-可见 View.INVISIBLE-4-不可见 View.GONE-8-隐藏
     */
    public void setRightTextVisible(int visible) {
        switch (visible) {
            case View.GONE://8
                RightTextView.setVisibility(View.GONE);
                break;
            case View.VISIBLE://0
                RightTextView.setVisibility(View.VISIBLE);
                break;
            case View.INVISIBLE://4
                RightTextView.setVisibility(View.INVISIBLE);
                break;
        }
    }

    /**
     * 设置组件是否可点击
     *
     * @param enabled true 可点击；false 不可点击
     */
    public void setViewEnabled(boolean enabled) {
        mLayoutInflater.setEnabled(enabled);
        IsEnabled = enabled;
    }

    public boolean getViewEnabled() {
        return IsEnabled;
    }

    /**
     * 设置组件的点击事件
     *
     * @param mOnClickListener 点击事件
     */
    public void setViewOnClickListener(OnClickListener mOnClickListener) {
        mLayoutInflater.setOnClickListener(mOnClickListener);
    }

    public ImageView getIconImageView(){
        return IconImage;
    }

    /**
     * 设置右箭头是否可见
     *
     * @param visible VISIBLE-0-可见 INVISIBLE-4-不可见 GONE-8-隐藏
     */
    public void setRightImageViewVisibility(int visible) {
        RightImageView.setVisibility(visible);
    }

    /**
     * 设置左textview的文字
     *
     * @param text 传入的文字
     */
    public void setLeftText(String text) {
        LeftTextView.setText(text + "");
    }

    /**
     * 获取右textview的文字
     *
     * @return 右textview的文字
     */
    public String getRightText() {
        return RightTextView.getText().toString() + "";
    }

    /**
     * 设置右textview的文字
     *
     * @param text 传入的文字
     */
    public void setRightText(String text) {
        RightTextView.setText(text + "");
    }

    /**
     * 设置左右的字体颜色
     *
     * @param color 设置的颜色
     */
    public void setTextColorBoth(int color) {
        LeftTextView.setTextColor(color);
        RightTextView.setTextColor(color);
    }

    class mEditTextChangeState implements OnFocusChangeListener {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {//此处为得到焦点时的处理内容

            } else {//此处为失去焦点时的处理内容
                setViewIsEditor(false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageview_save_message:
                if (getViewEditorState()) {//处于编辑状态



                    setViewIsEditor(false);
                } else {//处于显示状态
                    setViewIsEditor(true);
                }
                break;
            case R.id.imageview_cancel_message:
                if (getViewEditorState()) {//处于编辑状态
                    setViewIsEditor(false);
                } else {//处于显示状态
                    setViewIsEditor(true);
                }
                break;

        }
    }
}
