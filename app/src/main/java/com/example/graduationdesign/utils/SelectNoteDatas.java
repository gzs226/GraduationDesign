package com.example.graduationdesign.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.graduationdesign.utils.model.NoteDatas;
import com.example.graduationdesign.utils.model.Question_bank;
import com.example.graduationdesign.utils.model.Question_bank_field;

import java.util.ArrayList;
import java.util.List;

public class SelectNoteDatas {

    private SQLiteDatabase mDB;

    public SelectNoteDatas(SQLiteDatabase db) {
        this.mDB = db;
    }

    public List<NoteDatas> findAllDatas() {
        List<NoteDatas> mDatas = new ArrayList<NoteDatas>();
        String sql = "SELECT * FROM " +
                Question_bank_field.NOTE_TABLENAME;//+ " where " + Question_bank_field.question_id + " = 1";
        //cursor = coordinate.rawQuery("select * from coordinates1 where id > ? order by id asc limit 0, 1 ", new String[]{sid});
        Cursor result = this.mDB.rawQuery(sql, null);
        while (result.moveToNext()) {
            NoteDatas mItemDatas = new NoteDatas();

            mItemDatas.setQuestion_id(result.getInt(result.getColumnIndex(Question_bank_field.question_id)));
            mItemDatas.setQuestion(result.getString(result.getColumnIndex(Question_bank_field.question)));
            mItemDatas.setNote_text(result.getString(result.getColumnIndex(Question_bank_field.note_text)));

            mDatas.add(mItemDatas);
        }
        this.mDB.close();
        return mDatas;
    }

    /**
     * 查询数据库是否存在该questionId.
     *
     * @return
     */
    public boolean allCaseNumByQuestionId(int question_id) {
        String sql = "select count(*) from  " + Question_bank_field.NOTE_TABLENAME + " where " +
                Question_bank_field.question_id + " = '" + question_id + "'";
        Cursor cursor = mDB.rawQuery(sql, null);
        cursor.moveToFirst();
        long count = cursor.getLong(0);
        cursor.close();
        this.mDB.close();
        if (count > 0.5) {
            return true;
        } else {
            return false;
        }
    }

}
