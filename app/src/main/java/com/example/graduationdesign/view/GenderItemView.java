package com.example.graduationdesign.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.graduationdesign.R;
import com.example.graduationdesign.activity.UserMessageActivity;
import com.example.graduationdesign.utils.MyColor;


public class GenderItemView extends LinearLayout implements View.OnClickListener {
    private SeleteStates mSeleteStates;
    private ImageView mImageView;
    private TextView mTextView = null;
    private View mLayoutInflater = null;
    private LinearLayout mLinearLayout = null;
    private boolean IsSelete = false;
    private String mItemId = "0";
private Context context;
    public GenderItemView(Context context) {
        this(context, null);
        this.context = context;
    }

    public GenderItemView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        InitView(context);
        Init(context, attrs);

    }

    private void InitView(Context context) {
        //在构造函数中将Xml中定义的布局解析出来。
        mLayoutInflater = LayoutInflater.from(context).inflate(R.layout.view_gender, this, true);
        mImageView = (ImageView) findViewById(R.id.gender_item_image);
        mTextView = (TextView) mLayoutInflater.findViewById(R.id.gender_item_text);
        mLinearLayout = (LinearLayout) mLayoutInflater.findViewById(R.id.lin_gender_item);
        mLinearLayout.setOnClickListener(this);
    }

    private void Init(final Context context, AttributeSet attrs) {

        //通过TypedArray类绑定在attr中的属性，在后面需要调用.recycle()；
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.attr_gender_item);
        //获取LeftText在xml中设定的值

        String itemid = typedArray.getString(R.styleable.attr_gender_item_itemId);
        String textString = typedArray.getString(R.styleable.attr_gender_item_itemText);
        int imageSrc = typedArray.getResourceId(R.styleable.attr_gender_item_imageSrc, R.drawable.cancel_iamge);
        int textColor = typedArray.getColor(R.styleable.attr_gender_item_mtextColor, ContextCompat.getColor(context, R.color.text_mes_unedit));
        int isSelete = typedArray.getInt(R.styleable.attr_gender_item_isSelete, 0);
        //防止内存泄漏

        mItemId = itemid;
        mImageView.setImageResource(imageSrc);
        mTextView.setText(textString);
        mTextView.setTextColor(textColor);
        if (isSelete == 1) {
            setIsSelete(true);
        } else if (isSelete == 0) {
            setIsSelete(false);
        }
        typedArray.recycle();
    }

    public boolean IsSelete() {
        return IsSelete;
    }

    public void setIsSelete(boolean selete) {
        if (selete) {
            mImageView.setImageResource(R.drawable.group_select);
            mTextView.setTextColor(ContextCompat.getColor(context, R.color.text_gender_select));
            IsSelete = selete;
        } else {
            mImageView.setImageResource(R.drawable.group_unselect);
            mTextView.setTextColor(ContextCompat.getColor(context, R.color.text_gender_unselect));
            IsSelete = selete;
        }
    }

    public void setItemId(String itemId) {
        mItemId = itemId;
    }

    public String getItemId() {
        return mItemId;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lin_gender_item:
                if (IsSelete()) {
                    setIsSelete(false);
                    mImageView.setImageResource(R.drawable.group_unselect);
                    mTextView.setTextColor(ContextCompat.getColor(context, R.color.text_gender_unselect));
                    mSeleteStates.SeleteStateChange(mItemId, false);
                } else {
                    setIsSelete(true);
                    mImageView.setImageResource(R.drawable.group_select);
                    mTextView.setTextColor(ContextCompat.getColor(context, R.color.text_gender_select));
                    mSeleteStates.SeleteStateChange(mItemId, true);
                }
                break;
        }
    }

    public String getItemText() {
        return mTextView.getText().toString() + "";
    }

    public void setInterfaceSeleteStates(SeleteStates iSeleteStates) {
        this.mSeleteStates = iSeleteStates;
    }

    public interface SeleteStates {
        void SeleteStateChange(String ItemId, boolean isSelete);
    }
}
