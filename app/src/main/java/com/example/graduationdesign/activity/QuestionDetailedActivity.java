package com.example.graduationdesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.VolleyError;
import com.example.graduationdesign.R;
import com.example.graduationdesign.utils.ConfigUserMessagePrefs;
import com.example.graduationdesign.utils.Contents;
import com.example.graduationdesign.utils.DatabaseHelper;
import com.example.graduationdesign.utils.OnPopWindowsResultListener;
import com.example.graduationdesign.utils.SelectQuestionDatas;
import com.example.graduationdesign.utils.TableOperate;
import com.example.graduationdesign.utils.model.QuestionTransmit;
import com.example.graduationdesign.utils.model.Question_bank;
import com.example.graduationdesign.view.DifficultyLevelView;
import com.example.graduationdesign.view.OptionDetailView;
import com.example.graduationdesign.view.PopWindowsChangeMessage;
import com.example.graduationdesign.view.QuestionCommentListView;
import com.example.weblibrary.model.PostCommentData;
import com.example.weblibrary.user.QuestionWriteNew;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionDetailedActivity extends BaseActivity {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.button_amended)
    TextView buttonAmended;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    @BindView(R.id.question_detail_chapter)
    TextView questionDetailChapter;
    @BindView(R.id.question_detail_question)
    TextView questionDetailQuestion;
    @BindView(R.id.question_detail_option)
    OptionDetailView questionDetailOption;
    @BindView(R.id.question_detail_difficulty_level)
    DifficultyLevelView questionDetailDifficultyLevel;
    @BindView(R.id.question_detail_comment_number)
    TextView questionDetailCommentNumber;
    @BindView(R.id.question_detail_handin_answer)
    TextView questionDetailHandinAnswer;
    @BindView(R.id.question_detail_answer)
    TextView questionDetailAnswer;
    @BindView(R.id.question_detail_answer_analysis)
    TextView questionDetailAnswerAnalysis;
    @BindView(R.id.question_detail_lin_answer)
    LinearLayout questionDetailLinAnswer;
    @BindView(R.id.question_detail_lin_analysis)
    LinearLayout questionDetailLinAnalysis;

    private int USER_ANSWER = -1;
    private int QuestionId = 1;
    private final int USER_DONE = 1;
    private final int USER_NO_CHOICE = -1;
    private final int QUESTION_NO_ID = -1;
    private final int USER_NODO = 0;

    private boolean IF_DO = false;
    private DatabaseHelper helper;
    private SelectQuestionDatas mSelectQuestionDatas;
    private Question_bank mQuestion_bank;
    private TableOperate mTableOperate;
    private QuestionTransmit mQuestionTransmit;
    private final int TO_QUESTION_COMMENT_CODE = 105;
    private final int QUESTION_COMMENT_BACK_QUESTION_CODE = 107;
    private String commentText;
    private int USER_ID;
    private String USER_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);
        ButterKnife.bind(this);

        getQuestionId();
        getQuestionData();
        InitView();
    }

    private void getQuestionId() {
        QuestionId = getIntent().getIntExtra("", QUESTION_NO_ID);
    }

    private void getQuestionData() {
        USER_ID = Integer.valueOf(ConfigUserMessagePrefs.getValue(QuestionDetailedActivity.this, Contents.USER_ID, ""));
        USER_NAME = ConfigUserMessagePrefs.getValue(QuestionDetailedActivity.this, Contents.USER_NAME, "");
        Intent intent = this.getIntent();
        mQuestionTransmit = (QuestionTransmit) intent.getSerializableExtra("questiondata");

        helper = new DatabaseHelper(this);
        mSelectQuestionDatas = new SelectQuestionDatas(helper.getReadableDatabase());
        mQuestion_bank = mSelectQuestionDatas.findDataByQuestionId(QuestionId);
    }

    private void InitView() {
        setViewModel(0);
        buttonAmended.setVisibility(View.INVISIBLE);
        headerCenterText.setText("" + mQuestionTransmit.getSubject());
        questionDetailChapter.setText("" + mQuestionTransmit.getChapter());
        questionDetailQuestion.setText("" + mQuestionTransmit.getQuestion_number() + "." +
                                       mQuestionTransmit.getQuestion());
        questionDetailOption.setAllText(mQuestionTransmit.getOption_a(), mQuestionTransmit.getOption_b(), mQuestionTransmit.getOption_c(), mQuestionTransmit.getOption_d());
        questionDetailDifficultyLevel.setDifficultyLevel(mQuestionTransmit.getDifficulty_level());
        questionDetailCommentNumber.setText("评论" + mQuestionTransmit.getComment_number() + "");
        questionDetailAnswer.setText(mQuestionTransmit.getAnswer() + "");
        questionDetailAnswerAnalysis.setText(mQuestionTransmit.getAnswer_analysis());
        //        setViewModel(0);
        //        buttonAmended.setVisibility(View.INVISIBLE);
        //        headerCenterText.setText("" + mQuestion_bank.getSubject());
        //        questionDetailChapter.setText("" + mQuestion_bank.getChapter());
        //        questionDetailQuestion.setText(
        //                "" + mQuestion_bank.getQuestion_number() + "." + mQuestion_bank.getQuestion());
        //        questionDetailOption.setAllText(mQuestion_bank.getOption_a(), mQuestion_bank.getOption_b(), mQuestion_bank.getOption_c(), mQuestion_bank.getOption_d());
        //        questionDetailDifficultyLevel.setDifficultyLevel(mQuestion_bank.getDifficulty_level());
        //        questionDetailCommentNumber.setText("评论" + mQuestion_bank.getComment_number() + "");
        //        questionDetailAnswer.setText(mQuestion_bank.getAnswer() + "");
        //        questionDetailAnswerAnalysis.setText(mQuestion_bank.getAnswer_analysis());
    }

    private void setViewModel(int useDo) {
        switch (useDo) {
            case USER_NODO://未做此题
                UserNoDo();
                break;
            case USER_DONE://已做此题
                UserDone();
                break;
        }
    }

    private void UserDone() {
        questionDetailHandinAnswer.setVisibility(View.GONE);
        int useranswer = questionDetailOption.getChoiceOption();
        questionDetailOption.AnswerModel(mQuestion_bank.getAnswer(), useranswer);
        questionDetailLinAnswer.setVisibility(View.VISIBLE);
        questionDetailLinAnalysis.setVisibility(View.VISIBLE);
        questionDetailOption.setAllLinEnabled(false);
    }

    private void UserNoDo() {
        questionDetailLinAnswer.setVisibility(View.GONE);
        questionDetailLinAnalysis.setVisibility(View.GONE);
        questionDetailHandinAnswer.setVisibility(View.VISIBLE);
        questionDetailOption.setAllLinEnabled(true);
    }

    private void ToCommentDetail() {
        Intent intent = new Intent(QuestionDetailedActivity.this, QuestionCommentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("questiondata", mQuestionTransmit);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void DoCommentQuestion() {
        Intent intent = new Intent(QuestionDetailedActivity.this, CommentQuestionActivity.class);
        startActivity(intent);
    }


    private void UserHandinAnswer() {
        USER_ANSWER = questionDetailOption.getChoiceOption();
        if (USER_ANSWER == USER_NO_CHOICE) {
            Toast.makeText(QuestionDetailedActivity.this, "未选择！", Toast.LENGTH_SHORT).show();
            return;
        }
        mTableOperate = new TableOperate(helper.getWritableDatabase());
        mTableOperate.UpdateUserDo(mQuestion_bank.getQuestion_id(), USER_DONE);
        mTableOperate.UpdateUserAnswer(mQuestion_bank.getQuestion_id(), USER_ANSWER);
        mTableOperate.closeDB();

        mSelectQuestionDatas = new SelectQuestionDatas(helper.getReadableDatabase());
        mQuestion_bank = mSelectQuestionDatas.findDataByQuestionId(QuestionId);
        setViewModel(USER_DONE);
    }


    private void toCommetnQuestionActivity() {
        Intent intent = new Intent(QuestionDetailedActivity.this, QuestionMainPostActivity.class);
        intent.putExtra("title", mQuestionTransmit.getChapter() + "");
        intent.putExtra("context", mQuestionTransmit.getQuestion() + "");
        startActivityForResult(intent, TO_QUESTION_COMMENT_CODE);
    }

    //第一个参数为请求码，即调用startActivityForResult()传递过去的值
    //第二个参数为结果码，结果码用于标识返回数据来自哪个新Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TO_QUESTION_COMMENT_CODE &&
            resultCode == QUESTION_COMMENT_BACK_QUESTION_CODE) {
            commentText = data.getExtras().getString("commenttext");//得到新Activity关闭后返回的数据
            Log.e("onActivityResult", "onActivityResult: resultCode=" + resultCode);
            WriteQuestionComment();
        }
    }


    @OnClick({R.id.linear_back, R.id.question_detail_comment_number,
              R.id.question_detail_handin_answer, R.id.text_question_detail_comment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.question_detail_comment_number:
                ToCommentDetail();
                break;
            case R.id.question_detail_handin_answer:
                UserHandinAnswer();
                break;
            case R.id.text_question_detail_comment:
                toCommetnQuestionActivity();
                break;
        }
    }

    private void WriteQuestionComment() {
        PostCommentData mPostCommentData = new PostCommentData();
        mPostCommentData.setTopic_id(mQuestionTransmit.getQuestion_id());
        mPostCommentData.setTopic_type(mQuestionTransmit.getSubject());
        mPostCommentData.setContent(commentText);
        mPostCommentData.setFrom_uid(USER_ID);
        mPostCommentData.setFrom_uname(USER_NAME);
        mPostCommentData.setTo_uid(mQuestionTransmit.getQuestion_id());
        mPostCommentData.setTo_uname("0");
        mPostCommentData.setComment_time("2017-02-12");
        mPostCommentData.setComment_number(0);
        mPostCommentData.setPraise_number(0);

        new QuestionWriteNew().WriteComment(mPostCommentData, new QuestionWriteNew.IWritePost() {
            @Override
            public void Success(String successMessage) {
                Toast.makeText(QuestionDetailedActivity.this,
                        "" + successMessage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void Faild(String failMessage) {

            }

            @Override
            public void Error(VolleyError volleyError) {

            }
        });
    }
}
