package com.example.graduationdesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.graduationdesign.R;
import com.example.graduationdesign.adapter.CollectionBankAdapter;
import com.example.graduationdesign.adapter.WrongBankAdapter;
import com.example.graduationdesign.utils.CollectionTableOperate;
import com.example.graduationdesign.utils.DatabaseHelper;
import com.example.graduationdesign.utils.SelectCollectionDatas;
import com.example.graduationdesign.utils.SelectQuestionDatas;
import com.example.graduationdesign.utils.model.CollectionDatas;
import com.example.graduationdesign.utils.model.QuestionTransmit;
import com.example.graduationdesign.utils.model.Question_bank;
import com.example.graduationdesign.view.CollectionBankListView;
import com.example.graduationdesign.view.PostDetailListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author SunnyCoffee
 * @version 1.0
 * @date 2014-1-28
 * @desc listview下拉刷新，上拉自动加载更多。 http：//blog.csdn.com/limb99
 */

public class WrongBankActivity extends BaseActivity implements CollectionBankListView.OnRefreshListener, CollectionBankListView.OnLoadListener, ListView.OnItemClickListener {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.button_amended)
    TextView buttonAmended;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    @BindView(R.id.list_collection_bank)
    CollectionBankListView postList;
    private WrongBankAdapter adapter;
    private List<Question_bank> ListResultdata = new ArrayList<>();
    private int LVmodel = -1;
    private int page = 1;
    private DatabaseHelper helper;
    private SelectQuestionDatas mSelectQuestionDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_bank);
        ButterKnife.bind(this);

        headerCenterText.setText("错题");
        helper = new DatabaseHelper(WrongBankActivity.this);
        adapter = new WrongBankAdapter(this, ListResultdata);
        postList.setAdapter(adapter);
        postList.setOnRefreshListener(this);
        postList.setOnLoadListener(this);
        postList.setOnItemClickListener(this);
        initData();
    }

    private void getData() {

        switch (LVmodel) {
            case PostDetailListView.REFRESH:
                page = 1;
                break;
            case PostDetailListView.LOAD:

                break;
        }
        List<Question_bank> data = new ArrayList<>();
        data.addAll(SelectWrongDatas());

        switch (LVmodel) {
            case PostDetailListView.REFRESH:
                postList.onRefreshComplete();
                ListResultdata.clear();
                ListResultdata.addAll(data);
                break;
        }
        adapter.notifyDataSetChanged();
        postList.setResultSize(0);
        page++;
    }

    private void initData() {

        LVmodel = PostDetailListView.REFRESH;
        getData();
    }

    @Override
    public void onRefresh() {
        //loadData(PostDetailListView.REFRESH);
        LVmodel = PostDetailListView.REFRESH;
        getData();
    }

    @Override
    public void onLoad() {
        //loadData(PostDetailListView.LOAD);
        LVmodel = PostDetailListView.LOAD;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //根据QuestionId获取Question数据
        ToQuestionDetail(position - 1, ListResultdata.get(position - 1).getQuestion_id());
    }

    @OnClick({R.id.linear_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                finish();
                break;
        }
    }

    private List<Question_bank> SelectWrongDatas() {
        List<Question_bank> list = new ArrayList<>();
        mSelectQuestionDatas = new SelectQuestionDatas(helper.getReadableDatabase());
        List<Question_bank> mQList = new ArrayList<>();
        mQList = mSelectQuestionDatas.findAllDatas();
        for (Question_bank i_Question_bank : mQList) {
            if (i_Question_bank.getUser_do() == 1) {
                if (i_Question_bank.getAnswer() != i_Question_bank.getUser_answer()) {
                    Log.e("vv", "SelectWrongDatas:----------- " + i_Question_bank.getAnswer());
                    Log.e("vv", "SelectWrongDatas: " + i_Question_bank.getUser_answer());
                    list.add(i_Question_bank);
                }
            }
        }
        Log.e("vv", "SelectWrongDatas: " + list.size());
        return list;
    }

    private void ToQuestionDetail(int position, int QuestionId) {
        mSelectQuestionDatas = new SelectQuestionDatas(helper.getReadableDatabase());
        Question_bank mQ = new Question_bank();
        mQ = mSelectQuestionDatas.findDataByQuestionId(QuestionId);

        QuestionTransmit mQuestionTransmit = new QuestionTransmit();
        mQuestionTransmit.setQuestion_id(mQ.getQuestion_id());
        mQuestionTransmit.setSubject(mQ.getSubject());
        mQuestionTransmit.setChapter(mQ.getChapter());
        mQuestionTransmit.setQuestion(mQ.getQuestion());
        mQuestionTransmit.setQuestion_number(position + 1);
        mQuestionTransmit.setOption_a(mQ.getOption_a());
        mQuestionTransmit.setOption_b(mQ.getOption_b());
        mQuestionTransmit.setOption_c(mQ.getOption_c());
        mQuestionTransmit.setOption_d(mQ.getOption_d());
        mQuestionTransmit.setAnswer(mQ.getAnswer());
        mQuestionTransmit.setDifficulty_level(mQ.getDifficulty_level());
        mQuestionTransmit.setComment_number(mQ.getComment_number());
        mQuestionTransmit.setAnswer_analysis(mQ.getAnswer_analysis());
        mQuestionTransmit.setUser_do(mQ.getUser_do());
        mQuestionTransmit.setUser_answer(mQ.getUser_answer());


        Intent intent = new Intent(WrongBankActivity.this, QuestionDetailedActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("questiondata", mQuestionTransmit);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
