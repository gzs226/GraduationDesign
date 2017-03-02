package com.example.graduationdesign.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.graduationdesign.utils.model.Question_bank;
import com.example.graduationdesign.utils.model.Question_bank_field;

import java.util.ArrayList;
import java.util.List;

public class TableOperate {
    //private static final String TABLENAME = "mytab";
    private SQLiteDatabase mDB;

    public TableOperate(SQLiteDatabase db) {
        this.mDB = db;
    }

    public void Insert(Question_bank insetQuestion_bank) {
        ContentValues mContentValues = new ContentValues();
        mContentValues.put(Question_bank_field.question_id, insetQuestion_bank.getQuestion_id());
        mContentValues.put(Question_bank_field.subject, insetQuestion_bank.getSubject());
        mContentValues.put(Question_bank_field.chapter, insetQuestion_bank.getChapter());
        mContentValues.put(Question_bank_field.question, insetQuestion_bank.getQuestion());
        mContentValues.put(Question_bank_field.question_number, insetQuestion_bank.getQuestion_number());
        mContentValues.put(Question_bank_field.option_a, insetQuestion_bank.getOption_a());
        mContentValues.put(Question_bank_field.option_b, insetQuestion_bank.getOption_b());
        mContentValues.put(Question_bank_field.option_c, insetQuestion_bank.getOption_c());
        mContentValues.put(Question_bank_field.option_d, insetQuestion_bank.getOption_d());
        mContentValues.put(Question_bank_field.answer, insetQuestion_bank.getAnswer());
        mContentValues.put(Question_bank_field.difficulty_level, insetQuestion_bank.getDifficulty_level());
        mContentValues.put(Question_bank_field.comment_number, insetQuestion_bank.getComment_number());
        mContentValues.put(Question_bank_field.answer_analysis, insetQuestion_bank.getAnswer_analysis());
        mContentValues.put(Question_bank_field.user_do, insetQuestion_bank.getUser_do());
        mContentValues.put(Question_bank_field.user_answer, insetQuestion_bank.getUser_answer());
        this.mDB.insert(Question_bank_field.BANK_TABLENAME, null, mContentValues);
        //this.mDB.close();
    }

    public void UpdateCommentNumber(int question_id, int comment_number) {
        String WhereClause = Question_bank_field.question_id + " = ?";
        String WhereArgs[] = new String[]{String.valueOf(question_id)};

        ContentValues mContentValues = new ContentValues();
        mContentValues.put(Question_bank_field.comment_number, comment_number);

        this.mDB.update(Question_bank_field.BANK_TABLENAME, mContentValues, WhereClause, WhereArgs);

    }

    public void UpdateUserDo(int question_id, int user_do) {
        String WhereClause = Question_bank_field.question_id + " = ?";
        String WhereArgs[] = new String[]{String.valueOf(question_id)};

        ContentValues mContentValues = new ContentValues();
        mContentValues.put(Question_bank_field.user_do, user_do);

        this.mDB.update(Question_bank_field.BANK_TABLENAME, mContentValues, WhereClause, WhereArgs);

    }

    public void UpdateUserAnswer(int question_id, int user_answer) {
        String WhereClause = Question_bank_field.question_id + " = ?";
        String WhereArgs[] = new String[]{String.valueOf(question_id)};

        ContentValues mContentValues = new ContentValues();
        mContentValues.put(Question_bank_field.user_answer, user_answer);

        this.mDB.update(Question_bank_field.BANK_TABLENAME, mContentValues, WhereClause, WhereArgs);

    }

    public void Delete(int question_id) {
        String WhereClause = Question_bank_field.question_id + " = ?";
        String WhereArgs[] = new String[]{String.valueOf(question_id)};

        this.mDB.delete(Question_bank_field.BANK_TABLENAME, WhereClause, WhereArgs);
//        this.mDB.close();
    }

    public void DeleteAllData() {
        this.mDB.delete(Question_bank_field.BANK_TABLENAME, null, null);
//        this.mDB.close();
    }

    //使用完SQLiteDatabase之后，需要进行关闭，防止内存溢出
    public void closeDB() {
        this.mDB.close();
    }
}
