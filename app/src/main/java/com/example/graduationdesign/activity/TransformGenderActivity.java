package com.example.graduationdesign.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.graduationdesign.R;
import com.example.graduationdesign.view.GenderItemView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransformGenderActivity extends BaseActivity {

    @BindView(R.id.header_center_text)
    TextView headerCenterText;
    @BindView(R.id.button_amended)
    TextView buttonAmended;
    @BindView(R.id.linear_back)
    LinearLayout linearBack;
    @BindView(R.id.gender_item_man)
    GenderItemView genderItemMan;
    @BindView(R.id.gender_item_woman)
    GenderItemView genderItemWoman;
    @BindView(R.id.gender_item_secrecy)
    GenderItemView genderItemSecrecy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform_gender);
        ButterKnife.bind(this);
        InitView();
    }

    private void InitView() {
        headerCenterText.setText("性别");
        buttonAmended.setVisibility(View.INVISIBLE);

        genderItemMan.setInterfaceSeleteStates(new mSeleteStates());
        genderItemWoman.setInterfaceSeleteStates(new mSeleteStates());
        genderItemSecrecy.setInterfaceSeleteStates(new mSeleteStates());
    }


    class mSeleteStates implements GenderItemView.SeleteStates {
        @Override
        public void SeleteStateChange(String ItemId, boolean isSelete) {
            setGenderItemState(ItemId);
            Toast.makeText(TransformGenderActivity.this, "" + isSelete + "---" + ItemId, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.linear_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                finish();
                break;
        }
    }

    private void setGenderItemState(String ItemId) {
        if (ItemId.equals("man")) {
            genderItemMan.setIsSelete(true);
            genderItemWoman.setIsSelete(false);
            genderItemSecrecy.setIsSelete(false);
        } else if (ItemId.equals("woman")) {
            genderItemMan.setIsSelete(false);
            genderItemWoman.setIsSelete(true);
            genderItemSecrecy.setIsSelete(false);
        } else if (ItemId.equals("secrecy")) {
            genderItemMan.setIsSelete(false);
            genderItemWoman.setIsSelete(false);
            genderItemSecrecy.setIsSelete(true);
        }
    }
}
