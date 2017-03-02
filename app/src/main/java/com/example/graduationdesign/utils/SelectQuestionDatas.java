package com.example.graduationdesign.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.graduationdesign.utils.model.Question_bank;
import com.example.graduationdesign.utils.model.Question_bank_field;

import java.util.ArrayList;
import java.util.List;

public class SelectQuestionDatas {

    private SQLiteDatabase mDB;

    public SelectQuestionDatas(SQLiteDatabase db) {
        this.mDB = db;
    }

    public List<Question_bank> findAllDatas() {
        List<Question_bank> mDatas = new ArrayList<Question_bank>();
        String sql = "SELECT * FROM " +
                     Question_bank_field.BANK_TABLENAME;//+ " where " + Question_bank_field.question_id + " = 1";
        //cursor = coordinate.rawQuery("select * from coordinates1 where id > ? order by id asc limit 0, 1 ", new String[]{sid});
        Cursor result = this.mDB.rawQuery(sql, null);
        while (result.moveToNext()) {
            Question_bank mItemDatas = new Question_bank();

            mItemDatas.setQuestion_id(result.getInt(result.getColumnIndex(Question_bank_field.question_id)));
            mItemDatas.setSubject(result.getString(result.getColumnIndex(Question_bank_field.subject)));
            mItemDatas.setChapter(result.getString(result.getColumnIndex(Question_bank_field.chapter)));
            mItemDatas.setQuestion(result.getString(result.getColumnIndex(Question_bank_field.question)));
            mItemDatas.setQuestion_number(result.getInt(result.getColumnIndex(Question_bank_field.question_number)));
            mItemDatas.setOption_a(result.getString(result.getColumnIndex(Question_bank_field.option_a)));
            mItemDatas.setOption_b(result.getString(result.getColumnIndex(Question_bank_field.option_b)));
            mItemDatas.setOption_c(result.getString(result.getColumnIndex(Question_bank_field.option_c)));
            mItemDatas.setOption_d(result.getString(result.getColumnIndex(Question_bank_field.option_d)));
            mItemDatas.setAnswer(result.getInt(result.getColumnIndex(Question_bank_field.answer)));
            mItemDatas.setDifficulty_level(result.getInt(result.getColumnIndex(Question_bank_field.difficulty_level)));
            mItemDatas.setComment_number(result.getInt(result.getColumnIndex(Question_bank_field.comment_number)));
            mItemDatas.setAnswer_analysis(result.getString(result.getColumnIndex(Question_bank_field.answer_analysis)));
            mItemDatas.setUser_do(result.getInt(result.getColumnIndex(Question_bank_field.user_do)));
            mItemDatas.setUser_answer(result.getInt(result.getColumnIndex(Question_bank_field.user_answer)));

            mDatas.add(mItemDatas);
        }
        this.mDB.close();
        return mDatas;
    }

    public List<Question_bank> findDatasBySubjectChater(String subject, String chapter) {
        List<Question_bank> mDatas = new ArrayList<Question_bank>();
        String sql = "SELECT * FROM " +
                     Question_bank_field.BANK_TABLENAME
                    + " where " + Question_bank_field.subject + " =  "+subject
                    +" and "+ Question_bank_field.chapter + " = "+ chapter;
        //cursor = coordinate.rawQuery("select * from coordinates1 where id > ? order by id asc limit 0, 1 ", new String[]{sid});
        Cursor result = this.mDB.rawQuery(sql, null);
        while (result.moveToNext()) {
            Question_bank mItemDatas = new Question_bank();

            mItemDatas.setQuestion_id(result.getInt(result.getColumnIndex(Question_bank_field.question_id)));
            mItemDatas.setSubject(result.getString(result.getColumnIndex(Question_bank_field.subject)));
            mItemDatas.setChapter(result.getString(result.getColumnIndex(Question_bank_field.chapter)));
            mItemDatas.setQuestion(result.getString(result.getColumnIndex(Question_bank_field.question)));
            mItemDatas.setQuestion_number(result.getInt(result.getColumnIndex(Question_bank_field.question_number)));
            mItemDatas.setOption_a(result.getString(result.getColumnIndex(Question_bank_field.option_a)));
            mItemDatas.setOption_b(result.getString(result.getColumnIndex(Question_bank_field.option_b)));
            mItemDatas.setOption_c(result.getString(result.getColumnIndex(Question_bank_field.option_c)));
            mItemDatas.setOption_d(result.getString(result.getColumnIndex(Question_bank_field.option_d)));
            mItemDatas.setAnswer(result.getInt(result.getColumnIndex(Question_bank_field.answer)));
            mItemDatas.setDifficulty_level(result.getInt(result.getColumnIndex(Question_bank_field.difficulty_level)));
            mItemDatas.setComment_number(result.getInt(result.getColumnIndex(Question_bank_field.comment_number)));
            mItemDatas.setAnswer_analysis(result.getString(result.getColumnIndex(Question_bank_field.answer_analysis)));
            mItemDatas.setUser_do(result.getInt(result.getColumnIndex(Question_bank_field.user_do)));
            mItemDatas.setUser_answer(result.getInt(result.getColumnIndex(Question_bank_field.user_answer)));

            mDatas.add(mItemDatas);
        }
        this.mDB.close();
        return mDatas;
    }

    public Question_bank findDataByQuestionId(int question_id) {
        Question_bank mDatas = new Question_bank();
        String sql = "SELECT * FROM " + Question_bank_field.BANK_TABLENAME + " where " +
                     Question_bank_field.question_id + " = " + question_id;
        //cursor = coordinate.rawQuery("select * from coordinates1 where id > ? order by id asc limit 0, 1 ", new String[]{sid});
        Cursor result = this.mDB.rawQuery(sql, null);
        while (result.moveToNext()) {
            Question_bank mItemDatas = new Question_bank();

            mItemDatas.setQuestion_id(result.getInt(result.getColumnIndex(Question_bank_field.question_id)));
            mItemDatas.setSubject(result.getString(result.getColumnIndex(Question_bank_field.subject)));
            mItemDatas.setChapter(result.getString(result.getColumnIndex(Question_bank_field.chapter)));
            mItemDatas.setQuestion(result.getString(result.getColumnIndex(Question_bank_field.question)));
            mItemDatas.setQuestion_number(result.getInt(result.getColumnIndex(Question_bank_field.question_number)));
            mItemDatas.setOption_a(result.getString(result.getColumnIndex(Question_bank_field.option_a)));
            mItemDatas.setOption_b(result.getString(result.getColumnIndex(Question_bank_field.option_b)));
            mItemDatas.setOption_c(result.getString(result.getColumnIndex(Question_bank_field.option_c)));
            mItemDatas.setOption_d(result.getString(result.getColumnIndex(Question_bank_field.option_d)));
            mItemDatas.setAnswer(result.getInt(result.getColumnIndex(Question_bank_field.answer)));
            mItemDatas.setDifficulty_level(result.getInt(result.getColumnIndex(Question_bank_field.difficulty_level)));
            mItemDatas.setComment_number(result.getInt(result.getColumnIndex(Question_bank_field.comment_number)));
            mItemDatas.setAnswer_analysis(result.getString(result.getColumnIndex(Question_bank_field.answer_analysis)));
            mItemDatas.setUser_do(result.getInt(result.getColumnIndex(Question_bank_field.user_do)));
            mItemDatas.setUser_answer(result.getInt(result.getColumnIndex(Question_bank_field.user_answer)));

            mDatas = (mItemDatas);
        }
        this.mDB.close();
        return mDatas;
    }

    /**
     * 查询数据库中的总条数.
     *
     * @return
     */
    public long allCaseNum() {
        String sql = "select count(*) from  " + Question_bank_field.BANK_TABLENAME;
        Cursor cursor = mDB.rawQuery(sql, null);
        cursor.moveToFirst();
        long count = cursor.getLong(0);
        cursor.close();
        return count;
    }

    /**
     * 查询数据库中某科目的总条数.
     *
     * @return
     */
    public long allCaseNumBySubject(String subject) {
        String sql = "select count(*) from  " + Question_bank_field.BANK_TABLENAME + " where " +
                     Question_bank_field.subject + " = '" + subject + "'";
        Cursor cursor = mDB.rawQuery(sql, null);
        cursor.moveToFirst();
        long count = cursor.getLong(0);
        cursor.close();
        return count;
    }

}
