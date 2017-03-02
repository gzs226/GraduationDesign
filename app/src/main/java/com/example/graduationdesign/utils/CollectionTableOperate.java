package com.example.graduationdesign.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.graduationdesign.utils.model.CollectionDatas;
import com.example.graduationdesign.utils.model.Question_bank;
import com.example.graduationdesign.utils.model.Question_bank_field;

public class CollectionTableOperate {
    private SQLiteDatabase mDB;

    public CollectionTableOperate(SQLiteDatabase db) {
        this.mDB = db;
    }

    public void Insert(CollectionDatas insetCollectionDatas) {
        ContentValues mContentValues = new ContentValues();
        mContentValues.put(Question_bank_field.question_id, insetCollectionDatas.getQuestion_id());
        mContentValues.put(Question_bank_field.question, insetCollectionDatas.getQuestion());
        this.mDB.insert(Question_bank_field.COLLECTION_TABLENAME, null, mContentValues);
        //this.mDB.close();
    }

    public void Delete(int question_id) {
        String WhereClause = Question_bank_field.question_id + " = ?";
        String WhereArgs[] = new String[]{String.valueOf(question_id)};

        this.mDB.delete(Question_bank_field.COLLECTION_TABLENAME, WhereClause, WhereArgs);
//        this.mDB.close();
    }

    public void DeleteAllData() {
        this.mDB.delete(Question_bank_field.COLLECTION_TABLENAME, null, null);
//        this.mDB.close();
    }

    /**
     * 查询数据库中某科目的总条数.
     *
     * @return
     */
    public long allCaseNumBySubject(int question_id) {
        String sql = "select count(*) from  " + Question_bank_field.COLLECTION_TABLENAME + " where " +
                Question_bank_field.question_id + " = '" + question_id + "'";
        Cursor cursor = mDB.rawQuery(sql, null);
        cursor.moveToFirst();
        long count = cursor.getLong(0);
        cursor.close();
        return count;
    }


    //使用完SQLiteDatabase之后，需要进行关闭，防止内存溢出
    public void closeDB() {
        this.mDB.close();
    }
}
