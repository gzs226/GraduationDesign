package com.example.graduationdesign.tool;

import android.content.Context;

import com.example.graduationdesign.utils.DatabaseHelper;
import com.example.graduationdesign.utils.SelectCollectionDatas;
import com.example.graduationdesign.utils.SelectNoteDatas;
import com.example.graduationdesign.utils.SelectQuestionDatas;
import com.example.graduationdesign.utils.TableOperate;
import com.example.graduationdesign.utils.model.CollectionDatas;
import com.example.graduationdesign.utils.model.NoteDatas;
import com.example.graduationdesign.utils.model.QuestionTransmit;
import com.example.graduationdesign.utils.model.Question_bank;
import com.example.graduationdesign.utils.model.Question_bank_field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PackQuestionData {
    private DatabaseHelper helper;
    private SelectQuestionDatas mSelectQuestionDatas;
    private SelectNoteDatas mSelectNoteDatas;
    private SelectCollectionDatas mSelectCollectionDatas;

    public PackQuestionData(Context context) {
        helper = new DatabaseHelper(context);
    }

    public String CollectionDataToString() {
        mSelectCollectionDatas = new SelectCollectionDatas(helper.getReadableDatabase());
        List<CollectionDatas> collectionDatasList = new ArrayList<>();
        collectionDatasList = mSelectCollectionDatas.findAllDatas();

        List<Map> mList = new ArrayList<>();

        for (int i = 0; i < collectionDatasList.size(); i++) {
            Map mMap = new HashMap();
            mMap.put(Question_bank_field.question_id, collectionDatasList.get(i).getQuestion_id());
           // mMap.put(Question_bank_field.question, collectionDatasList.get(i).getQuestion());
            mList.add(mMap);
        }

        return mList.toString();
    }

    public String NoteDataToString() {
        mSelectNoteDatas = new SelectNoteDatas(helper.getReadableDatabase());
        List<NoteDatas> mNoteDatas = new ArrayList<>();
        mNoteDatas = mSelectNoteDatas.findAllDatas();

        List<Map> mList = new ArrayList<>();

        for (int i = 0; i < mNoteDatas.size(); i++) {
            Map mMap = new HashMap();
            mMap.put(Question_bank_field.question_id, mNoteDatas.get(i).getQuestion_id());
            mMap.put(Question_bank_field.note_text, mNoteDatas.get(i).getNote_text());
            mList.add(mMap);
        }

        return mList.toString();
    }

    public String QuestionDataToString() {
        mSelectQuestionDatas = new SelectQuestionDatas(helper.getReadableDatabase());
        List<Question_bank> mQuestion_bank = new ArrayList<>();
        mQuestion_bank = mSelectQuestionDatas.findAllDatas();

        List<Map> mList = new ArrayList<>();

        for (int i = 0; i < mQuestion_bank.size(); i++) {
            Map mMap = new HashMap();

            if (mQuestion_bank.get(i).getUser_do() != -1) {
                mMap.put(Question_bank_field.question_id, mQuestion_bank.get(i).getQuestion_id());
                mMap.put(Question_bank_field.user_answer, mQuestion_bank.get(i).getUser_answer());
                mList.add(mMap);
            }
        }
        return mList.toString();
    }
}
