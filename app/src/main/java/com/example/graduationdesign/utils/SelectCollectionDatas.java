package com.example.graduationdesign.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.graduationdesign.utils.model.CollectionDatas;
import com.example.graduationdesign.utils.model.Question_bank_field;

import java.util.ArrayList;
import java.util.List;

public class SelectCollectionDatas {

    private SQLiteDatabase mDB;

    public SelectCollectionDatas(SQLiteDatabase db) {
        this.mDB = db;
    }

    public List<CollectionDatas> findAllDatas() {
        List<CollectionDatas> mDatas = new ArrayList<CollectionDatas>();
        String sql = "SELECT * FROM " +
                Question_bank_field.COLLECTION_TABLENAME;//+ " where " + Question_bank_field.question_id + " = 1";
        //cursor = coordinate.rawQuery("select * from coordinates1 where id > ? order by id asc limit 0, 1 ", new String[]{sid});
        Cursor result = this.mDB.rawQuery(sql, null);
        while (result.moveToNext()) {
            CollectionDatas mItemDatas = new CollectionDatas();

            mItemDatas.setQuestion_id(result.getInt(result.getColumnIndex(Question_bank_field.question_id)));
            mItemDatas.setQuestion(result.getString(result.getColumnIndex(Question_bank_field.question)));

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
        String sql = "select count(*) from  " + Question_bank_field.COLLECTION_TABLENAME + " where " +
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

    //Currsor = db.rawQuery("select * from tablea as a left outer join tableb as b on a.url = b.url where a.url= ?",  new String[]{url});

    public List<CollectionDatas> findAllTwoTableDatas() {
        List<CollectionDatas> mDatas = new ArrayList<CollectionDatas>();
        String sql = "SELECT * FROM " +
                Question_bank_field.COLLECTION_TABLENAME;//+ " where " + Question_bank_field.question_id + " = 1";
        //cursor = coordinate.rawQuery("select * from coordinates1 where id > ? order by id asc limit 0, 1 ", new String[]{sid});
        Cursor result = this.mDB.rawQuery(sql, null);
        while (result.moveToNext()) {
            CollectionDatas mItemDatas = new CollectionDatas();

            mItemDatas.setQuestion_id(result.getInt(result.getColumnIndex(Question_bank_field.question_id)));
            mItemDatas.setQuestion(result.getString(result.getColumnIndex(Question_bank_field.question)));

            mDatas.add(mItemDatas);
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
        String sql = "select count(*) from  " + Question_bank_field.COLLECTION_TABLENAME;
        Cursor cursor = mDB.rawQuery(sql, null);
        cursor.moveToFirst();
        long count = cursor.getLong(0);
        cursor.close();
        return count;
    }

}
