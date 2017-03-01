package com.example.graduationdesign.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.graduationdesign.R;
import com.example.graduationdesign.tool.KeywordUtil;
import com.example.graduationdesign.tool.MyColor;
import com.example.graduationdesign.view.UserExamtimeView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserExamTimeActivity extends Activity {


    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.button_amended)
    TextView buttonAmended;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    @BindView(R.id.examtime_item_one)
    UserExamtimeView examtimeItemOne;
    @BindView(R.id.examtime_item_two)
    UserExamtimeView examtimeItemTwo;
    @BindView(R.id.examtime_item_three)
    UserExamtimeView examtimeItemThree;
    @BindView(R.id.examtime_item_four)
    UserExamtimeView examtimeItemFour;
    @BindView(R.id.examtime_item_five)
    UserExamtimeView examtimeItemFive;
    @BindView(R.id.test_keywords)
    TextView testKeywords;
    private int year = 0;
    private String data = "应该是好久没有写有关技术类的文 章了，前天还有人在群里问我，说群主很长时间没有分享干货了，" +
            "今天分享一篇Android中TextView在大段的文字内容中如何让关键字高亮变色的文章 ，希望对大家有所帮助，" +
            "我终于在歪路上回归正途了。这个篇文章在平时应该还算比较常用吧，如果你会了，就不用看了，如果还不会，可以看一眼，非常简单。";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_time);
        ButterKnife.bind(this);
        InitView();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    private void InitView() {
        buttonAmended.setVisibility(View.INVISIBLE);
        headerCenterText.setText("考研时间");
        year = getYear();
        examtimeItemOne.setLeftTextView(getYearMonth(year));
        examtimeItemTwo.setLeftTextView(getYearMonth(++year));
        examtimeItemThree.setLeftTextView(getYearMonth(++year));
        examtimeItemFour.setLeftTextView(getYearMonth(++year));
        examtimeItemFive.setLeftTextView(getYearMonth(++year));
        //int color, String text,  String keyword
        //KeywordUtil.matcherSearchTitle(MyColor.KeyWordsText, data, "文章");

        testKeywords.setText(KeywordUtil.matcherSearchTitle(MyColor.KeyWordsText, data, "文章"));
    }

    /**
     * @return 当前年份
     */
    private int getYear() {
        Calendar a = Calendar.getInstance();
        int year = a.get(Calendar.YEAR);
        return year;
    }

    private String getYearMonth(int iyear) {
        String yearMonth = iyear + "-12";
        return yearMonth;
    }

    @OnClick({R.id.examtime_item_one, R.id.examtime_item_two, R.id.examtime_item_three, R.id.examtime_item_four, R.id.examtime_item_five, R.id.linear_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.examtime_item_one:
                break;
            case R.id.examtime_item_two:
                break;
            case R.id.examtime_item_three:
                break;
            case R.id.examtime_item_four:
                break;
            case R.id.examtime_item_five:
                break;
            case R.id.linear_back:
                finish();
                break;
        }
    }


}
