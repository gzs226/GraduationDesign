package com.example.graduationdesign.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.graduationdesign.R;
import com.example.graduationdesign.tool.StringsData2;
import com.example.graduationdesign.utils.DatabaseHelper;
import com.example.graduationdesign.utils.TableOperate;
import com.example.graduationdesign.utils.model.Question_bank;
import com.example.weblibrary.model.QuestionData;
import com.example.weblibrary.model.resultquestiondata;
import com.example.weblibrary.model.versiondata;
import com.example.weblibrary.user.QuestionSelectUpdateLocal;
import com.example.weblibrary.user.SelectVersionData;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateQuestionActivity extends BaseActivity {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.text_uestion_newversion)
    TextView textUestionNewversion;
    @BindView(R.id.text_uestion_oldversion)
    TextView textUestionOldversion;
    @BindView(R.id.text_update_quetion)
    TextView textUpdateQuetion;

    private boolean checkVersionFinish = false;
    private String OldVersion = "1.01";
    private String NewVersion = "";

    private int page = 1;
    private final int number = 40;

    private DatabaseHelper helper;
    private TableOperate mTableOperate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question);
        ButterKnife.bind(this);
        InitView();
    }

    private void InitView() {
        headerCenterText.setText("更新题库");
        textUestionOldversion.setText("1.01");
        textUestionNewversion.setText("*.*.*");
        textUpdateQuetion.setText("请稍后..");
        textUpdateQuetion.setEnabled(false);
        helper = new DatabaseHelper(this);
        CheckVersion();
    }

    private void CheckVersion() {
        new SelectVersionData().SelectVersion(new SelectVersionData.ISelectVersion() {
            @Override
            public void Success(List<versiondata> listresultVersionData) {
                versiondata Newversiondata = listresultVersionData.get(
                        listresultVersionData.size() - 1);
                textUestionNewversion.setText(Newversiondata.getQuestion_versions() + "");
                textUpdateQuetion.setText("更新数据");
                if (!(OldVersion.equals(NewVersion))) {
                    textUpdateQuetion.setEnabled(true);
                }
                checkVersionFinish = true;
            }

            @Override
            public void Faild(String failMessage) {

            }

            @Override
            public void Error(VolleyError volleyError) {

            }
        });
    }

    private void setUpdateQuetion() {
        if (checkVersionFinish) {
            if (!(OldVersion.equals(NewVersion))) {
                //start load question bank
                textUpdateQuetion.setText("正在更新");
                textUpdateQuetion.setEnabled(false);
                getQuestionData();
            }
        }
    }

    private void getQuestionData() {
        new QuestionSelectUpdateLocal().UpdateLocalQuestion(
                page + "", number + "", new QuestionSelectUpdateLocal.IUpdateLocalQuestion() {
                    @Override
                    public void Success(QuestionData mQuestionData) {
                        mTableOperate = new TableOperate(helper.getWritableDatabase());
                        for (resultquestiondata mresultquestiondata : mQuestionData.getResultquestiondata()) {
                            InsertData(mresultquestiondata);
                        }
                        mTableOperate.closeDB();
                        page++;
                        if (mQuestionData.getNumber() == number) {
                            getQuestionData();
                        }else{
                            textUpdateQuetion.setText("更新完成");
                            textUpdateQuetion.setEnabled(false);
                        }
                    }

                    @Override
                    public void Faild(String failMessage) {

                    }

                    @Override
                    public void Error(VolleyError volleyError) {

                    }
                });
    }

    private void InsertData(resultquestiondata mresultquestiondata) {

        Question_bank insetQuestion_bank = new Question_bank();
        insetQuestion_bank.setQuestion_id(mresultquestiondata.getQuestion_id());
        insetQuestion_bank.setSubject(mresultquestiondata.getQuestion_subject());
        insetQuestion_bank.setChapter(mresultquestiondata.getQuestion_chapter());
        insetQuestion_bank.setQuestion(mresultquestiondata.getQuestion());
        insetQuestion_bank.setQuestion_number(mresultquestiondata.getQuestion_number());
        insetQuestion_bank.setOption_a(mresultquestiondata.getQuestion_option_a());
        insetQuestion_bank.setOption_b(mresultquestiondata.getQuestion_option_b());
        insetQuestion_bank.setOption_c(mresultquestiondata.getQuestion_option_c());
        insetQuestion_bank.setOption_d(mresultquestiondata.getQuestion_option_d());
        insetQuestion_bank.setAnswer(mresultquestiondata.getAnswer());
        insetQuestion_bank.setDifficulty_level(mresultquestiondata.getDifficulty_level());
        insetQuestion_bank.setComment_number(mresultquestiondata.getComment_number());
        insetQuestion_bank.setAnswer_analysis(mresultquestiondata.getAnswer_analysis());
        insetQuestion_bank.setUser_do(0);
        insetQuestion_bank.setUser_do(-1);
        mTableOperate.Insert(insetQuestion_bank);
        Log.e("mQuestionData2--------", "testData: " + mresultquestiondata.getQuestion_id());
    }


    @OnClick({R.id.linear_back, R.id.text_update_quetion})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.text_update_quetion:
                Log.e("mQuestionData2--------", "testData:+++++++++++++ " );
                setUpdateQuetion();
                break;
        }
    }

}
