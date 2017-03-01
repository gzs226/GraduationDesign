package com.example.graduationdesign.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.graduationdesign.R;


public class LoginRegisterView extends FrameLayout implements View.OnClickListener {
    private View mLayoutInflater = null;
    private ImageView imageDelete;
    private ImageView imageLeft;
    private EditText editText;
    private TextWatcher textWatcher;
    private String stringEditor = "";

    public LoginRegisterView(Context context) {
        this(context, null);
    }

    public LoginRegisterView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        //在构造函数中将Xml中定义的布局解析出来。
        mLayoutInflater = LayoutInflater.from(context).inflate(R.layout.view_login_register, this, true);
        InitBindView(context, attrs);
    }

    //LoginRegisterView
    private void InitBindView(final Context context, AttributeSet attrs) {
        editText = (EditText) mLayoutInflater.findViewById(R.id.edit_login_register);
        imageDelete = (ImageView) mLayoutInflater.findViewById(R.id.image_editor_right);
        imageLeft = (ImageView) mLayoutInflater.findViewById(R.id.image_editor_left);

        imageDelete.setOnClickListener(this);
        InitTextWatcher();
        editText.addTextChangedListener(textWatcher);

        //通过TypedArray类绑定在attr中的属性，在后面需要调用.recycle()；
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.attr_login_register);
        //获取LeftText在xml中设定的值
        String hintText = typedArray.getString(R.styleable.attr_login_register_hintText);
        int imageLeftSrc = typedArray.getResourceId(R.styleable.attr_login_register_leftImageSrc, -1);
        int isPassword = typedArray.getInt(R.styleable.attr_login_register_isPassword, -1);

        if (hintText != null) {
            editText.setHint("" + hintText);
        }
        if (imageLeftSrc != -1) {
            imageLeft.setImageResource(imageLeftSrc);
        }
        switch (isPassword) {
            case -1:
                break;
            case 1:
                editText.setInputType(
                        InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
            case 0:
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;
        }
        typedArray.recycle();
    }

    private void InitTextWatcher() {
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!(s + "").equals("")) {
                    imageDelete.setVisibility(View.VISIBLE);
                } else {
                    imageDelete.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    public String getEditorString() {
        return editText.getText().toString() + "";
    }

    public void setEditorString(String mEditorString) {
        editText.setText(mEditorString + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_editor_right:
                editText.setText("");
                break;
        }
    }
}