package com.example.graduationdesign.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.graduationdesign.R;
import com.example.graduationdesign.tool.MyColor;

import butterknife.BindView;
import butterknife.OnClick;


public class OptionDetailView extends LinearLayout implements View.OnClickListener {

    private View mLayoutInflater = null;
    private int ChoiceOption = -1;
    private final int OPTION_A = 0;
    private final int OPTION_B = 1;
    private final int OPTION_C = 2;
    private final int OPTION_D = 3;

    private Context context;
    private ImageView imageviewOptionA;
    private TextView textviewOptionA;
    private LinearLayout linOptionA;
    private ImageView imageviewOptionB;
    private TextView textviewOptionB;
    private LinearLayout linOptionB;
    private ImageView imageviewOptionC;
    private TextView textviewOptionC;
    private LinearLayout linOptionC;
    private ImageView imageviewOptionD;
    private TextView textviewOptionD;
    private LinearLayout linOptionD;

    public OptionDetailView(Context context) {
        this(context, null);
        this.context = context;
    }

    public OptionDetailView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        //在构造函数中将Xml中定义的布局解析出来。
        mLayoutInflater = LayoutInflater.from(context).inflate(R.layout.view_option_detail, this, true);
        InitBindView();
    }

    private void InitBindView() {

        imageviewOptionA = (ImageView) mLayoutInflater.findViewById(R.id.imageview_option_a);
        imageviewOptionB = (ImageView) mLayoutInflater.findViewById(R.id.imageview_option_b);
        imageviewOptionC = (ImageView) mLayoutInflater.findViewById(R.id.imageview_option_c);
        imageviewOptionD = (ImageView) mLayoutInflater.findViewById(R.id.imageview_option_d);

        textviewOptionA = (TextView) mLayoutInflater.findViewById(R.id.textview_option_a);
        textviewOptionB = (TextView) mLayoutInflater.findViewById(R.id.textview_option_b);
        textviewOptionC = (TextView) mLayoutInflater.findViewById(R.id.textview_option_c);
        textviewOptionD = (TextView) mLayoutInflater.findViewById(R.id.textview_option_d);

        linOptionA = (LinearLayout) mLayoutInflater.findViewById(R.id.lin_option_a);
        linOptionB = (LinearLayout) mLayoutInflater.findViewById(R.id.lin_option_b);
        linOptionC = (LinearLayout) mLayoutInflater.findViewById(R.id.lin_option_c);
        linOptionD = (LinearLayout) mLayoutInflater.findViewById(R.id.lin_option_d);

        linOptionA.setOnClickListener(this);
        linOptionB.setOnClickListener(this);
        linOptionC.setOnClickListener(this);
        linOptionD.setOnClickListener(this);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_option_a:
                ChoiceOneOption(R.id.lin_option_a);
                break;
            case R.id.lin_option_b:
                ChoiceOneOption(R.id.lin_option_b);
                break;
            case R.id.lin_option_c:
                ChoiceOneOption(R.id.lin_option_c);
                break;
            case R.id.lin_option_d:
                ChoiceOneOption(R.id.lin_option_d);
                break;
        }
    }


    private void recoveryView() {
        imageviewOptionA.setImageResource(R.drawable.icon_options_select_no);
        imageviewOptionB.setImageResource(R.drawable.icon_options_select_no);
        imageviewOptionC.setImageResource(R.drawable.icon_options_select_no);
        imageviewOptionD.setImageResource(R.drawable.icon_options_select_no);

        textviewOptionA.setTextColor(ContextCompat.getColor(context,R.color.text_item));
        textviewOptionB.setTextColor(ContextCompat.getColor(context,R.color.text_item));
        textviewOptionC.setTextColor(ContextCompat.getColor(context,R.color.text_item));
        textviewOptionD.setTextColor(ContextCompat.getColor(context,R.color.text_item));

    }

    private void ChoiceOneOption(int option) {
        recoveryView();
        switch (option) {
            case R.id.lin_option_a:
                ChoiceOption = OPTION_A;
                imageviewOptionA.setImageResource(R.drawable.icon_options_select_yes);
                break;
            case R.id.lin_option_b:
                ChoiceOption = OPTION_B;
                imageviewOptionB.setImageResource(R.drawable.icon_options_select_yes);
                break;
            case R.id.lin_option_c:
                ChoiceOption = OPTION_C;
                imageviewOptionC.setImageResource(R.drawable.icon_options_select_yes);
                break;
            case R.id.lin_option_d:
                ChoiceOption = OPTION_D;
                imageviewOptionD.setImageResource(R.drawable.icon_options_select_yes);
                break;
        }
    }

    public int getChoiceOption() {
        return this.ChoiceOption;
    }

    public void AnswerModel(int answer, int userAnswer) {
        recoveryView();
        switch (userAnswer) {
            case 0:
                textviewOptionA.setTextColor(ContextCompat.getColor(context,R.color.option_err));
                imageviewOptionA.setImageResource(R.drawable.icon_options_select_error);
                break;
            case 1:
                textviewOptionB.setTextColor(ContextCompat.getColor(context,R.color.option_err));
                imageviewOptionB.setImageResource(R.drawable.icon_options_select_error);
                break;
            case 2:
                textviewOptionC.setTextColor(ContextCompat.getColor(context,R.color.option_err));
                imageviewOptionC.setImageResource(R.drawable.icon_options_select_error);
                break;
            case 3:
                textviewOptionD.setTextColor(ContextCompat.getColor(context,R.color.option_err));
                imageviewOptionD.setImageResource(R.drawable.icon_options_select_error);
                break;
        }
        switch (answer) {
            case 0:
                textviewOptionA.setTextColor(ContextCompat.getColor(context,R.color.option_anwser));
                imageviewOptionA.setImageResource(R.drawable.icon_options_select_ok);
                break;
            case 1:
                textviewOptionB.setTextColor(ContextCompat.getColor(context,R.color.option_anwser));
                imageviewOptionB.setImageResource(R.drawable.icon_options_select_ok);
                break;
            case 2:
                textviewOptionC.setTextColor(ContextCompat.getColor(context,R.color.option_anwser));
                imageviewOptionC.setImageResource(R.drawable.icon_options_select_ok);
                break;
            case 3:
                textviewOptionD.setTextColor(ContextCompat.getColor(context,R.color.option_anwser));
                imageviewOptionD.setImageResource(R.drawable.icon_options_select_ok);
                break;
        }
    }

    public void setAllText(String textA, String textB, String textC, String textD) {
        textviewOptionA.setText(textA + "");
        textviewOptionB.setText(textB + "");
        textviewOptionC.setText(textC + "");
        textviewOptionD.setText(textD + "");

    }

    public void setAllLinEnabled(boolean enabled) {
        linOptionA.setEnabled(enabled);
        linOptionB.setEnabled(enabled);
        linOptionC.setEnabled(enabled);
        linOptionD.setEnabled(enabled);
    }

}