package com.example.graduationdesign.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.graduationdesign.R;


public class SearchEditorView extends LinearLayout implements View.OnClickListener {
    private Context mc;
    private View mLayoutInflater = null;
    private ImageView imageDelete;
    private Button butSeaech;
    private EditText editSearch;
    private TextWatcher textWatcher;
    private String stringEditor = "";

    public SearchEditorView(Context context) {
        this(context, null);
    }

    public SearchEditorView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        mc = context;
        //在构造函数中将Xml中定义的布局解析出来。
        mLayoutInflater = LayoutInflater.from(context).inflate(R.layout.view_search_editor, this, true);
        InitBindView();
    }
//LoginRegisterView
    private void InitBindView() {
        editSearch = (EditText) mLayoutInflater.findViewById(R.id.edittext_search);
        imageDelete = (ImageView) mLayoutInflater.findViewById(R.id.imageview_search_right);
        butSeaech = (Button) mLayoutInflater.findViewById(R.id.button_search);
        imageDelete.setOnClickListener(this);
        InitTextWatcher();
        editSearch.addTextChangedListener(textWatcher);
    }

    private void InitTextWatcher() {
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!(s + "").equals("")) {
                    imageDelete.setVisibility(View.VISIBLE);
                } else {
                    imageDelete.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    public void setSearchOnClickListener(OnClickListener searchOnClickListener) {
        butSeaech.setOnClickListener(searchOnClickListener);
    }

    public String getEditorString() {
        return editSearch.getText().toString() + "";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageview_search_right:
                editSearch.setText("");
                Toast.makeText(mc, "22", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}