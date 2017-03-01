package com.example.graduationdesign.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.example.graduationdesign.R;
import com.example.graduationdesign.adapter.SearchQuestionAdapter;
import com.example.graduationdesign.view.SearchEditorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchQuestionActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.button_amended)
    TextView buttonAmended;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    @BindView(R.id.listview_search_question)
    ListView listviewSearchQuestion;
    @BindView(R.id.search_question_editor)
    SearchEditorView searchQuestionEditor;

    private SearchQuestionAdapter mSearchQuestionAdapter;
    private List<String> mData;
    private String searchString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_question);
        ButterKnife.bind(this);
        InitData();
        InitView();
    }

    private void InitData() {
        if (mData == null) {
            mData = new ArrayList<String>();
        }
        for (int i = 0; i < 10; i++) {
            mData.add("this is my data" + i);
        }
    }

    private void InitView() {

        headerCenterText.setText("搜索");
        buttonAmended.setVisibility(View.INVISIBLE);
        mSearchQuestionAdapter = new SearchQuestionAdapter(this, mData);
        listviewSearchQuestion.setAdapter(mSearchQuestionAdapter);
        listviewSearchQuestion.setOnItemClickListener(this);
        searchQuestionEditor.setSearchOnClickListener(new searchOnClickListener());
    }

    private class searchOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            searchString = searchQuestionEditor.getEditorString();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
