package com.example.graduationdesign.utils;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.graduationdesign.utils.model.NoteDatas;
import com.example.graduationdesign.utils.model.Question_bank;
import com.example.graduationdesign.utils.model.Question_bank_field;

public class NoteTableOperate {
    private SQLiteDatabase mDB;

    public NoteTableOperate(SQLiteDatabase db) {
        this.mDB = db;
    }

    public void Insert(NoteDatas insetNoteDatas) {
        ContentValues mContentValues = new ContentValues();
        mContentValues.put(Question_bank_field.question_id, insetNoteDatas.getQuestion_id());
        mContentValues.put(Question_bank_field.question, insetNoteDatas.getQuestion());
        mContentValues.put(Question_bank_field.note_text, insetNoteDatas.getNote_text());
        this.mDB.insert(Question_bank_field.NOTE_TABLENAME, null, mContentValues);
        //this.mDB.close();
    }

    public void UpdateNote(int question_id, String note) {
        String WhereClause = Question_bank_field.question_id + " = ?";
        String WhereArgs[] = new String[]{String.valueOf(question_id)};

        ContentValues mContentValues = new ContentValues();
        mContentValues.put(Question_bank_field.note_text, note);

        this.mDB.update(Question_bank_field.NOTE_TABLENAME, mContentValues, WhereClause, WhereArgs);

    }

    public void Delete(int question_id) {
        String WhereClause = Question_bank_field.question_id + " = ?";
        String WhereArgs[] = new String[]{String.valueOf(question_id)};

        this.mDB.delete(Question_bank_field.NOTE_TABLENAME, WhereClause, WhereArgs);
//        this.mDB.close();
    }

    public void DeleteAllData() {
        this.mDB.delete(Question_bank_field.NOTE_TABLENAME, null, null);
//        this.mDB.close();
    }

    //使用完SQLiteDatabase之后，需要进行关闭，防止内存溢出
    public void closeDB() {
        this.mDB.close();
    }
}
