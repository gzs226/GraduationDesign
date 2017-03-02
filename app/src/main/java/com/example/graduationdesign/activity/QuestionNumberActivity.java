package com.example.graduationdesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduationdesign.R;
import com.example.graduationdesign.adapter.QuestionNumberAdapter2;
import com.example.graduationdesign.tool.MyDividerGridItemDecoration;
import com.example.graduationdesign.utils.DatabaseHelper;
import com.example.graduationdesign.utils.OnRecyclerItemClickListener;
import com.example.graduationdesign.utils.SelectQuestionDatas;
import com.example.graduationdesign.utils.model.QuestionTransmit;
import com.example.graduationdesign.utils.model.Question_bank;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionNumberActivity extends BaseActivity {
    private final String TAG = "RecycleViewActivity";
    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private QuestionNumberAdapter2 mAdapter;
    private DatabaseHelper helper;
    private SelectQuestionDatas mSelectQuestionDatas;
    private int dataLeng;
    List<Question_bank> mQuestion_banks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        ButterKnife.bind(this);

        operateSql();

        initData();
        InitView();
    }

    private void operateSql() {
        helper = new DatabaseHelper(this);
        //        mSelectQuestionDatas = new SelectQuestionDatas(helper.getReadableDatabase());
        //        mTableOperate = new TableOperate(helper.getWritableDatabase());
    }


    private void select() {
        mSelectQuestionDatas = new SelectQuestionDatas(helper.getReadableDatabase());
        mQuestion_banks = mSelectQuestionDatas.findDatasBySubjectChater("'马原'", "'第一章'");
        dataLeng = mQuestion_banks.size();
        //        for (Question_bank mQuestion_bank : mQuestion_banks) {
        //            Log.e(TAG, "select: " + mQuestion_bank.getChapter());
        //            Log.e(TAG, "select: " + mQuestion_bank.getComment_number());
        //            Log.e(TAG, "select: " + mQuestion_bank.getAnswer_analysis());
        //        }
    }

    private void InitView() {
        headerCenterText.setText("马原");
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));


        mRecyclerView.addItemDecoration(new MyDividerGridItemDecoration(50, 50));
        mRecyclerView.setAdapter(mAdapter = new QuestionNumberAdapter2(mDatas));
        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(ViewHolder viewHolder) {
                Toast.makeText(QuestionNumberActivity.this,
                        "" + viewHolder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                QuestionTransmit mQuestionTransmit = new QuestionTransmit();
                mQuestionTransmit.setQuestion_id(mQuestion_banks.get(viewHolder.getAdapterPosition()).getQuestion_id());
                mQuestionTransmit.setSubject(mQuestion_banks.get(viewHolder.getAdapterPosition()).getSubject());
                mQuestionTransmit.setChapter(mQuestion_banks.get(viewHolder.getAdapterPosition()).getChapter());
                mQuestionTransmit.setQuestion(mQuestion_banks.get(viewHolder.getAdapterPosition()).getQuestion());
                mQuestionTransmit.setQuestion_number(viewHolder.getAdapterPosition() + 1);
                mQuestionTransmit.setOption_a(mQuestion_banks.get(viewHolder.getAdapterPosition()).getOption_a());
                mQuestionTransmit.setOption_b(mQuestion_banks.get(viewHolder.getAdapterPosition()).getOption_b());
                mQuestionTransmit.setOption_c(mQuestion_banks.get(viewHolder.getAdapterPosition()).getOption_c());
                mQuestionTransmit.setOption_d(mQuestion_banks.get(viewHolder.getAdapterPosition()).getOption_d());
                mQuestionTransmit.setAnswer(mQuestion_banks.get(viewHolder.getAdapterPosition()).getAnswer());
                mQuestionTransmit.setDifficulty_level(mQuestion_banks.get(viewHolder.getAdapterPosition()).getDifficulty_level());
                mQuestionTransmit.setComment_number(mQuestion_banks.get(viewHolder.getAdapterPosition()).getComment_number());
                mQuestionTransmit.setAnswer_analysis(mQuestion_banks.get(viewHolder.getAdapterPosition()).getAnswer_analysis());
                mQuestionTransmit.setUser_do(mQuestion_banks.get(viewHolder.getAdapterPosition()).getUser_do());
                mQuestionTransmit.setUser_answer(mQuestion_banks.get(viewHolder.getAdapterPosition()).getUser_answer());


                Intent intent = new Intent(QuestionNumberActivity.this, QuestionDetailedActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("questiondata", mQuestionTransmit);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onItemLOngClick(ViewHolder viewHolder) {
                Toast.makeText(QuestionNumberActivity.this,
                        "dddd" + viewHolder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void initData() {
        select();
        mDatas = new ArrayList<String>();
        for (int i = 0; i < dataLeng; i++) {
            mDatas.add(i + 1 + "");
        }
    }

    @OnClick(R.id.linear_back)
    public void onClick() {
        finish();
    }
}