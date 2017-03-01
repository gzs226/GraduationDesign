package com.example.graduationdesign.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.graduationdesign.R;
import com.example.graduationdesign.fragment.MyCommunityFragment;
import com.example.graduationdesign.fragment.MyMainFragment;
import com.example.graduationdesign.fragment.MyQuestionBankFragment;
import com.example.graduationdesign.tool.MyColor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.imageview_question_bank)
    ImageView imageviewQuestionBank;
    @BindView(R.id.textview_question_bank)
    TextView textviewQuestionBank;
    @BindView(R.id.lin_question_bank)
    LinearLayout linQuestionBank;
    @BindView(R.id.imageview_social_comment)
    ImageView imageviewSocialComment;
    @BindView(R.id.textview_social_comment)
    TextView textviewSocialComment;
    @BindView(R.id.lin_social_comment)
    LinearLayout linSocialComment;
    @BindView(R.id.imageview_user_information)
    ImageView imageviewUserInformation;
    @BindView(R.id.textview_user_information)
    TextView textviewUserInformation;
    @BindView(R.id.lin_user_information)
    LinearLayout linUserInformation;

    private int NOW_FRAGMENT = -1;
    private final int QUESTION_BANK = 1;
    private final int COMMUNITY = 2;
    private final int USER_INFORMATION = 3;

    //三个fragment
    private MyQuestionBankFragment mMyMyQuestionBank;
    private MyCommunityFragment mMyCommunityFragment;
    private MyMainFragment mMyMainFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //第一次初始化首页默认显示第一个fragment
        NOW_FRAGMENT = QUESTION_BANK;
        changeTextAndBgColor(R.id.lin_question_bank);
        initFragment1();

    }



    //显示第一个fragment
    private void initFragment1() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if (mMyMyQuestionBank == null) {
            mMyMyQuestionBank = new MyQuestionBankFragment().newFragment();
            transaction.add(R.id.main_frame_layout, mMyMyQuestionBank);
        }
        //隐藏所有fragment
        hideFragment(transaction);

        //显示需要显示的fragment
        transaction.show(mMyMyQuestionBank);

        //提交事务
        transaction.commit();
    }

    //显示第一个fragment
    private void initFragment3() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if (mMyMainFragment == null) {
            mMyMainFragment = new MyMainFragment().newFragment();
            transaction.add(R.id.main_frame_layout, mMyMainFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(mMyMainFragment);

        //提交事务
        transaction.commit();
    }

    //显示第一个fragment
    private void initFragment2() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if (mMyCommunityFragment == null) {
            mMyCommunityFragment = new MyCommunityFragment().newFragment();
            transaction.add(R.id.main_frame_layout, mMyCommunityFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(mMyCommunityFragment);
        //提交事务
        transaction.commit();
    }

    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (NOW_FRAGMENT != COMMUNITY && mMyCommunityFragment != null) {
            transaction.hide(mMyCommunityFragment);
        }
        if (NOW_FRAGMENT != USER_INFORMATION && mMyMainFragment != null) {
            transaction.hide(mMyMainFragment);
        }
        if (NOW_FRAGMENT != QUESTION_BANK && mMyMyQuestionBank != null) {
            transaction.hide(mMyMyQuestionBank);
        }
    }

    private void changeTextAndBgColor(int index) {
        switch (index) {
            case R.id.lin_question_bank:

                setQuestionBank();
                break;
            case R.id.lin_social_comment:
                setSocialComment();
                break;
            case R.id.lin_user_information:
                setUserInformation();
                break;
        }
    }

    private void setQuestionBank() {
        linQuestionBank.setBackgroundResource(R.color.main_lin_bg_pressed);
        imageviewQuestionBank.setImageResource(R.drawable.homepage_bottom_question_database_press);
        textviewQuestionBank.setTextColor(MyColor.MAIN_MENU_RED);

        linSocialComment.setBackgroundResource(R.color.main_lin_bg_normal);
        imageviewSocialComment.setImageResource(R.drawable.homepage_bottom_circle_default);
        textviewSocialComment.setTextColor(MyColor.MAIN_MENU_GRAY);

        linUserInformation.setBackgroundResource(R.color.main_lin_bg_normal);
        imageviewUserInformation.setImageResource(R.drawable.homepage_bottom_personal_center_default);
        textviewUserInformation.setTextColor(MyColor.MAIN_MENU_GRAY);
    }

    private void setSocialComment() {
        linQuestionBank.setBackgroundResource(R.color.main_lin_bg_normal);
        imageviewQuestionBank.setImageResource(R.drawable.homepage_bottom_question_database_default);
        textviewQuestionBank.setTextColor(MyColor.MAIN_MENU_GRAY);

        linSocialComment.setBackgroundResource(R.color.main_lin_bg_pressed);
        imageviewSocialComment.setImageResource(R.drawable.homepage_bottom_circle_press);
        textviewSocialComment.setTextColor(MyColor.MAIN_MENU_RED);

        linUserInformation.setBackgroundResource(R.color.main_lin_bg_normal);
        imageviewUserInformation.setImageResource(R.drawable.homepage_bottom_personal_center_default);
        textviewUserInformation.setTextColor(MyColor.MAIN_MENU_GRAY);
    }

    private void setUserInformation() {
        linQuestionBank.setBackgroundResource(R.color.main_lin_bg_normal);
        imageviewQuestionBank.setImageResource(R.drawable.homepage_bottom_question_database_default);
        textviewQuestionBank.setTextColor(MyColor.MAIN_MENU_GRAY);

        linSocialComment.setBackgroundResource(R.color.main_lin_bg_normal);
        imageviewSocialComment.setImageResource(R.drawable.homepage_bottom_circle_default);
        textviewSocialComment.setTextColor(MyColor.MAIN_MENU_GRAY);

        linUserInformation.setBackgroundResource(R.color.main_lin_bg_pressed);
        imageviewUserInformation.setImageResource(R.drawable.homepage_bottom_personal_center_press);
        textviewUserInformation.setTextColor(MyColor.MAIN_MENU_RED);
    }


    @OnClick({R.id.lin_question_bank, R.id.lin_social_comment, R.id.lin_user_information})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_question_bank:
                if (NOW_FRAGMENT != QUESTION_BANK) {
                    NOW_FRAGMENT = QUESTION_BANK;
                    changeTextAndBgColor(R.id.lin_question_bank);
                    initFragment1();
                }
                break;
            case R.id.lin_social_comment:
                if (NOW_FRAGMENT != COMMUNITY) {
                    NOW_FRAGMENT = COMMUNITY;
                    changeTextAndBgColor(R.id.lin_social_comment);
                    initFragment2();
                }

                break;
            case R.id.lin_user_information:
                if (NOW_FRAGMENT != USER_INFORMATION) {
                    NOW_FRAGMENT = USER_INFORMATION;
                    initFragment3();
                    changeTextAndBgColor(R.id.lin_user_information);
                }
                break;
        }
    }
}