package com.example.graduationdesign.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduationdesign.R;
import com.example.graduationdesign.activity.CollectionBankActivity;
import com.example.graduationdesign.activity.NoteBankActivity;
import com.example.graduationdesign.activity.QuestionNumberActivity;
import com.example.graduationdesign.activity.WrongBankActivity;
import com.example.graduationdesign.adapter.SubjectAdapter;
import com.example.graduationdesign.tool.Model.SubjectSort;
import com.example.graduationdesign.tool.Model.Chapter;
import com.example.graduationdesign.view.SearchEditorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyQuestionBankFragment extends Fragment implements ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupClickListener {

    @BindView(R.id.expandablelist)
    ExpandableListView expandablelist;
    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    @BindView(R.id.lin_error_question)
    LinearLayout linErrorQuestion;
    @BindView(R.id.lin_collection_question)
    LinearLayout linCollectionQuestion;
    @BindView(R.id.lin_note_question)
    LinearLayout linNoteQuestion;
    @BindView(R.id.button_amended)
    TextView buttonAmended;
    @BindView(R.id.search_bank_editor)
    SearchEditorView searchBankEditor;


    private View questionView = null;
    private ArrayList<SubjectSort> subjectSortList;
    private ArrayList<List<Chapter>> childList;
    private SubjectAdapter adapter;
    private String searchString = "";

    public MyQuestionBankFragment() {

    }

    public MyQuestionBankFragment newFragment() {
        return new MyQuestionBankFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the body_fragment_mymain for this fragment
        if (questionView == null) {
            questionView = inflater.inflate(R.layout.fragment_question_bank, container, false);
        }
        ButterKnife.bind(this, questionView);
        Init();
        return questionView;
    }

    private void Init() {
        initData();
        InitView();
    }

    private void InitView() {
        headerCenterText.setText("题库");
        linearBack.setVisibility(View.INVISIBLE);
        buttonAmended.setVisibility(View.INVISIBLE);
        searchBankEditor.setSearchOnClickListener(new searchOnClickListener());
        adapter = new SubjectAdapter(getActivity(), subjectSortList, childList);
        expandablelist.setAdapter(adapter);

        // 展开所有group
        //        for (int i = 0, count = expandablelist.getCount(); i < count; i++) {
        //            expandablelist.expandGroup(i);
        //        }

        expandablelist.setOnChildClickListener(this);
        expandablelist.setOnGroupClickListener(this);
    }

    private class searchOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            searchString = searchBankEditor.getEditorString();

            Toast.makeText(getActivity(), "22", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        String name = childList.get(groupPosition).get(childPosition).getNumber() + "";
        Toast.makeText(getActivity(), "" + name, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), QuestionNumberActivity.class);
        startActivity(intent);
        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

        return false;
    }


    /***
     * InitData
     */
    void initData() {
        subjectSortList = new ArrayList<SubjectSort>();
        SubjectSort subjectSort = null;
        subjectSort = new SubjectSort();
        subjectSort.setTitle("马原");
        subjectSortList.add(subjectSort);
        subjectSort = new SubjectSort();
        subjectSort.setTitle("毛中特");
        subjectSortList.add(subjectSort);
        subjectSort = new SubjectSort();
        subjectSort.setTitle("史纲");
        subjectSortList.add(subjectSort);
        subjectSort = new SubjectSort();
        subjectSort.setTitle("思修与法基");
        subjectSortList.add(subjectSort);
        subjectSort = new SubjectSort();
        subjectSort.setTitle("时政");
        subjectSortList.add(subjectSort);

        childList = new ArrayList<List<Chapter>>();
        for (int i = 0; i < subjectSortList.size(); i++) {
            ArrayList<Chapter> childTemp;
            if (i == 0) {
                String[] mayuan = this.getResources().getStringArray(R.array.mayuan);
                childTemp = new ArrayList<Chapter>();
                for (int j = 0; j < mayuan.length; j++) {
                    Chapter chapter = new Chapter();
                    chapter.setSort(mayuan[j]);

                    childTemp.add(chapter);
                }
            } else if (i == 1) {
                String[] maozhongte = this.getResources().getStringArray(R.array.maozhongte);
                childTemp = new ArrayList<Chapter>();
                for (int j = 0; j < maozhongte.length; j++) {
                    Chapter chapter = new Chapter();
                    chapter.setSort(maozhongte[j]);
                    childTemp.add(chapter);
                }
            } else if (i == 2) {
                String[] shigang = this.getResources().getStringArray(R.array.shigang);
                childTemp = new ArrayList<Chapter>();
                for (int j = 0; j < shigang.length; j++) {
                    Chapter chapter = new Chapter();
                    chapter.setSort(shigang[j]);

                    childTemp.add(chapter);
                }
            } else if (i == 3) {
                String[] sixiu = this.getResources().getStringArray(R.array.sixiu_faji);
                childTemp = new ArrayList<Chapter>();
                for (int j = 0; j < sixiu.length; j++) {
                    Chapter chapter = new Chapter();
                    chapter.setSort(sixiu[j]);

                    childTemp.add(chapter);
                }
            } else {
                String[] shizheng = this.getResources().getStringArray(R.array.shizheng);
                childTemp = new ArrayList<Chapter>();
                for (int j = 0; j < shizheng.length; j++) {
                    Chapter chapter = new Chapter();
                    chapter.setSort(shizheng[j]);

                    childTemp.add(chapter);
                }
            }
            childList.add(childTemp);
        }

    }

    @OnClick({R.id.lin_error_question, R.id.lin_collection_question, R.id.lin_note_question})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_error_question:
                ToErrorActivity();
                break;
            case R.id.lin_collection_question:
                ToCollectionActivity();
                break;
            case R.id.lin_note_question:
                ToNoteActivity();
                break;
        }
    }

    private void ToErrorActivity() {
        Intent intent = new Intent(getActivity(), WrongBankActivity.class);
        startActivity(intent);
    }

    private void ToCollectionActivity() {
        Intent intent = new Intent(getActivity(), CollectionBankActivity.class);
        startActivity(intent);
    }

    private void ToNoteActivity() {
        Intent intent = new Intent(getActivity(), NoteBankActivity.class);
        startActivity(intent);
    }
}
